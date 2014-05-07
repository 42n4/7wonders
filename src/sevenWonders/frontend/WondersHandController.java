package sevenWonders.frontend;

import javafx.scene.control.Button;

import javafx.beans.binding.IntegerExpression;

import javafx.collections.ObservableList;

import javafx.scene.control.Label;

import javafx.collections.FXCollections;

import javafx.scene.input.PickResult;

import javafx.scene.input.ContextMenuEvent;

import javafx.event.EventHandler;

import sevenWonders.backend.Deck;

import sevenWonders.backend.Card;
import sevenWonders.backend.Hand.PaymentOption;

import sevenWonders.backend.Hand;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ChoiceBox;

public class WondersHandController implements Initializable {
    // testhand
    private final String BUILD = "Build";
    private final String WONDER = "Wonder";
    private final String SELL = "Sell";

    Card selectedCard;

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
    private ChoiceBox cardBox;
    @FXML
    private ChoiceBox<String> actionBox;
    @FXML
    private ChoiceBox buildBox;
    @FXML
    private Label pickedCard;
    @FXML
    private Button doneButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	assertContainers();
	Hand hand = GivfHand.Givf(); // TODO replace

	List<ImageView> views = Arrays.asList(new ImageView[] { cardImage1,
		cardImage2, cardImage3, cardImage4, cardImage5, cardImage6,
		cardImage7 });

	Set<Card> handCardSet = hand.cards.keySet();

	Card[] cardArray = handCardSet.toArray(new Card[handCardSet.size()]);
	String[] handCardNames = new String[cardArray.length];
	for (int j = 0; j < handCardNames.length; j++) {
	    Image img = new Image("file:"
		    + getClass().getResource("/" + cardArray[j].description)
			    .getPath());
	    ImageView cardView = views.get(j);
	    cardView.setImage(img);
	    cardBox.getItems().add(cardArray[j]);
	}

	cardBox.setTooltip(new Tooltip("Select card to use."));
	actionBox.getItems().addAll(BUILD, WONDER, SELL);
	actionBox.setTooltip(new Tooltip(
		"Select action to perform with your card."));
	buildBox.setTooltip(new Tooltip("Select payment option."));
	doneButton
		.setTooltip(new Tooltip("Press when you've made you choice."));

	setCardBoxListener(hand.cards, hand.wonderPaymentOptions, cardArray);
	setActionBoxListener(hand.cards, hand.wonderPaymentOptions);

	// int i = 0;
	// for (Card card : handCardSet) {
	// Image img = new Image("file:"
	// + getClass().getResource("/" + card.description).getPath());
	// ImageView cardView = views.get(i++);
	// cardView.setImage(img);
	// cardBox.getItems().add(card);
	// }



	// cardBox.getSelectionModel().selectedIndexProperty()
	// .addListener(new ChangeListener<Number>() {
	// public void changed(ObservableValue ov, Number value,
	// Number new_value) {
	// List<PaymentOption> options = hand.cards
	// .get(handCardArray[new_value.intValue()]);
	// for (int j = 0; j < options.size(); j++) {
	// buildBox.getItems().add(
	// options.get(j).leftCost
	// + options.get(j).rightCost
	// + options.get(j).selfCost);
	// }
	// buildBox = new
	// ChoiceBox(FXCollections.observableArrayList();
	// label.setText(texts[new_Value.intValue()]);
	// }
	// });

    }

    /**
     * @param items
     * @param cards
     * @param wonderPaymentOptions
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
			} else if (actionBox.getValue().equals(WONDER)) {
			    buildBox.setValue(null);
			    buildBox.getItems().setAll(wonderOptions);
			}
		    }
		});
    }

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
			if (index == 0) { // BUILD
			    List<PaymentOption> list = buildOptions.get(cardBox
				    .getValue());
			    buildBox.getItems().setAll(list);
			} else if (index == 1) { // WONDER
			    buildBox.getItems().setAll(wonderOptions);
			} else if (index == 2) { // SELL
			    buildBox.getItems().clear();
			}
		    }
		});
    }

    private ChoiceBox paymentOptionMenu(List<PaymentOption> list) {
	ChoiceBox cb = new ChoiceBox();
	cardBox.getItems().addAll(list);
	return cb;
    }

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
    }
}