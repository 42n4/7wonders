package sevenWonders.frontend;

import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import sevenWonders.backend.Action.ActionType;
import sevenWonders.backend.Action;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import sevenWonders.backend.Card;
import sevenWonders.backend.Hand.PaymentOption;
import sevenWonders.backend.Hand;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ChoiceBox;
import sevenWonders.Program;

/**
 * Together with WondersHanf.fxml describes and processes the logic for the
 * displaypane for the hand you receive each turn in Seven Wonders. The
 * doneButton will onAction return the action choice of the player.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class WondersHandController extends AnchorPane implements Initializable {
    private final String BUILD = "Build";
    private final String WONDER = "Wonder";
    private final String SELL = "Sell";
    private Action action; // the currently selected action
    private final ServerConnection conn;

    @FXML
    private ImageView cardImage1;
    @FXML
    private ImageView cardImage2;
    @FXML
    private ImageView cardImage3;
    @FXML
    private ImageView cardImage4;
    @FXML
    private ImageView cardImage5;
    @FXML
    private ImageView cardImage6;
    @FXML
    private ImageView cardImage7;
    @FXML
    private ChoiceBox<Card> cardBox;
    @FXML
    private ChoiceBox<String> actionBox; // build, wonder or sell
    @FXML
    private ChoiceBox<PaymentOption> buildBox;
    @FXML
    private ListView paymentList; // listview of available paymentoptions
    @FXML
    private Button doneButton; // returns the selected action
    
    private Hand hand;
    private final ImageView hoverTarget;
    
    public WondersHandController(ServerConnection conn, Hand hand, ImageView hoverTarget) {
	this.conn = conn;
	this.hand = hand;
	this.hoverTarget = hoverTarget;
	
        FXMLLoader loader = new FXMLLoader(sevenWonders.Program.getURL("WondersHand.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	assertContainers();

	List<ImageView> views = Arrays.asList(new ImageView[] { cardImage1,
		cardImage2, cardImage3, cardImage4, cardImage5, cardImage6,
		cardImage7 });

	// Fetch and store some data from hand
	Set<Card> handCardSet = hand.cards.keySet();
	Card[] cardArray = handCardSet.toArray(new Card[handCardSet.size()]);
	String[] handCardNames = new String[cardArray.length];

	//Mouseover event handlers - same as in PlayerBoard
	EventHandler<MouseEvent> mouseIn = new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
        	ImageView target = (ImageView)e.getSource();
        	hoverTarget.setImage(target.getImage());
        	hoverTarget.setVisible(true);
            }
        };
	EventHandler<MouseEvent> mouseOut = new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            	hoverTarget.setVisible(false);
            }
        };
	
	// Fills the image views and the card choice box.
	for (int i = 0; i < handCardNames.length; i++) {
	    Image img = Program.getImageFromFilename(cardArray[i].description);
	    ImageView cardView = views.get(i);
	    cardView.setImage(img);
	    cardBox.getItems().add(cardArray[i]);
	    cardView.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseIn);
            cardView.addEventHandler(MouseEvent.MOUSE_EXITED, mouseOut);
	}

	// Sets custom cell for paymentList.
	setPaymentCellFactory();
	// Sets a String converter for the payment option buildBox.
	setPaymentBoxConverter();

	// Sets tooltips.
	cardBox.setTooltip(new Tooltip("Select card to use."));
	actionBox.setTooltip(new Tooltip(
		"Select action to perform with your card."));
	buildBox.setTooltip(new Tooltip("Select payment option."));
	paymentList.setTooltip(new Tooltip(
		"Available payment options for your card."));
	doneButton
		.setTooltip(new Tooltip("Press when you've made you choice."));

	// Set all listeners and handlers.
	actionBox.getItems().addAll(BUILD, WONDER, SELL);
	setCardBoxListener(hand.cards, hand.wonderPaymentOptions, cardArray);
	setActionBoxListener(hand.cards, hand.wonderPaymentOptions);
	doneButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		if (actionBox.getValue().equals(SELL)
			|| buildBox.getValue() != null) {
		    conn.SendAction(action);
		}
	    }
	});
    }

    /**
     * Sets a custom StringConverter of PaymentOption for the choicebox buildBox.
     */
    private void setPaymentBoxConverter() {
	buildBox.setConverter(new StringConverter<PaymentOption>() {

	    @Override
	    public PaymentOption fromString(String o) {
		return null;
	    }

	    @Override
	    public String toString(PaymentOption o) {
		if (o.free) {
		    return "Free build";
		}
		int total = o.leftCost + o.selfCost + o.rightCost;
		if (total == 0) {
		    return "No cost";
		}
		return total + "g";
	    }
	});
    }

    /**
     * Sets a custom cell with a custom StringConverter of PaymentOptions for the 
     * listView paymentList.
     */
    private void setPaymentCellFactory() {
	paymentList
		.setCellFactory(new Callback<ListView<PaymentOption>, javafx.scene.control.cell.TextFieldListCell<PaymentOption>>() {

		    @Override
		    public TextFieldListCell<PaymentOption> call(
			    ListView<PaymentOption> arg0) {
			TextFieldListCell<PaymentOption> cell = new TextFieldListCell<PaymentOption>();
			cell.setConverter(new StringConverter<PaymentOption>() {

			    @Override
			    public PaymentOption fromString(String arg0) {
				return null;
			    }

			    @Override
			    public String toString(PaymentOption o) {
				if (o.free) {
				    return "Free build!";
				}
				int total = o.leftCost + o.selfCost
					+ o.rightCost;
				if (total == 0) {
				    return "No cost for building.";
				}
				String s = total + "g:";
				if (o.selfCost > 0) {
				    s = s + " " + o.selfCost + "g self cost.";
				}
				if (o.leftCost > 0) {
				    s = s + " " + o.leftCost
					    + "g to left neighbour for "
					    + o.leftResources.keySet() + ".";
				}
				if (o.rightCost > 0) {
				    s = s + " " + o.rightCost
					    + "g to right neighbour for "
					    + o.rightResources.keySet() + ".";
				}
				return s;
			    }
			});
			return cell;
		    }
		});
    }

    /**
     * Adds a listener to the cardBox that changes the items in buildBox and
     * paymentList.
     * 
     * @param buildingOptionsMap
     * @param wonderPaymentOptions
     * @param arrayOfCards
     */
    private void setCardBoxListener(
	    HashMap<Card, List<PaymentOption>> buildingOptionsMap,
	    List<PaymentOption> wonderPaymentOptions, Card[] arrayOfCards) {
	final HashMap<Card, List<PaymentOption>> buildOptions = buildingOptionsMap;
	final List<PaymentOption> wonderOptions = wonderPaymentOptions;
	final Card[] cardArray = arrayOfCards;

	cardBox.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener() {
		    @Override
		    public void changed(ObservableValue ov, Object oldSelected,
			    Object newSelected) {
			int index = Integer.parseInt(newSelected.toString());
			if (actionBox.getValue().equals(BUILD)) {
			    buildBox.setValue(null);
			    List<PaymentOption> list = buildOptions
				    .get(cardArray[index]);
			    buildBox.getItems().setAll(list);
			    paymentList.getItems().setAll(list);
			} else if (actionBox.getValue().equals(WONDER)) {
			    buildBox.setValue(null);
			    buildBox.getItems().setAll(wonderOptions);
			    paymentList.getItems().setAll(wonderOptions);
			} else if (actionBox.getValue().equals(SELL)) {
			    paymentList.getItems().setAll("Sell for 3g.");
			}
		    }
		});
    }

    /**
     * Adds a listener to the actionBox that changes the items in buildBox and
     * paymentList.
     * 
     * @param buildingOptionsMap
     * @param wonderPaymentOptions
     */
    private void setActionBoxListener(
	    HashMap<Card, List<PaymentOption>> buildingOptionsMap,
	    List<PaymentOption> wonderPaymentOptions) {
	final HashMap<Card, List<PaymentOption>> buildOptions = buildingOptionsMap;
	final List<PaymentOption> wonderOptions = wonderPaymentOptions;
	actionBox.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener() {
		    @Override
		    public void changed(ObservableValue ov, Object oldSelected,
			    Object newSelected) {
			int index = Integer.parseInt(newSelected.toString());
			buildBox.setValue(null);
			if (cardBox.getValue() != null) {
			    if (index == 0) { // BUILD
				List<PaymentOption> list = buildOptions
					.get(cardBox.getValue());
				buildBox.getItems().setAll(list);
				paymentList.getItems().setAll(list);
			    } else if (index == 1) { // WONDER
				buildBox.getItems().setAll(wonderOptions);
				paymentList.getItems().setAll(wonderOptions);
			    } else if (index == 2) { // SELL
				buildBox.getItems().clear();
				paymentList.getItems().clear();
				paymentList.getItems().setAll("Sell for 3g.");
				setAction(-1);
			    }
			}
		    }
		});
    }

    /**
     * When a payment option is selected the action choice saved in action is
     * updated.
     */
    private void setPaymnetBoxListener() {
	buildBox.getSelectionModel().selectedIndexProperty()
		.addListener(new ChangeListener() {
		    @Override
		    public void changed(ObservableValue ov, Object oldSelected,
			    Object newSelected) {
			int index = Integer.parseInt(newSelected.toString());
			if (!actionBox.getValue().isEmpty()
				&& buildBox.getValue() != null) {
			    setAction(index);
			}
		    }
		});
    }

    /**
     * Sets action to be the one currently chosen.
     * 
     * @param paymentOptionIndex
     */
    private void setAction(int paymentOptionIndex) {
	ActionType actionType = null;
	int index = paymentOptionIndex;
	if (actionBox.getValue().equals(BUILD)) {
	    actionType = ActionType.BUILD_BUILDING;
	} else if (actionBox.getValue().equals(WONDER)) {
	    actionType = ActionType.BUILD_WONDER;
	} else if (actionBox.getValue().equals(SELL)) {
	    actionType = ActionType.DISCARD_CARD;
	}
	Action action = new Action(cardBox.getValue(), actionType,
		paymentOptionIndex);
	this.action = action;
    }

    /**
     * Checks that injection worked.
     */
    private void assertContainers() {
	assert actionBox != null : "fx:id=\"actionBox\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert buildBox != null : "fx:id=\"buildBox\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardBox != null : "fx:id=\"cardBox\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage1 != null : "fx:id=\"cardImage1\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage2 != null : "fx:id=\"cardImage2\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage3 != null : "fx:id=\"cardImage3\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage4 != null : "fx:id=\"cardImage4\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage5 != null : "fx:id=\"cardImage5\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage6 != null : "fx:id=\"cardImage6\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert cardImage7 != null : "fx:id=\"cardImage7\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert doneButton != null : "fx:id=\"doneButton\" was not injected: check your FXML file 'WondersHand.fxml'.";
	assert paymentList != null : "fx:id=\"paymentList\" was not injected: check your FXML file 'WondersHand.fxml'.";
    }
}