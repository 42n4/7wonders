package sevenWonders.frontend;

import java.net.URL;
import java.util.ResourceBundle;

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

    Image image = new Image("file:era1 - 2.png");

    @FXML
    ImageView imageView;

    @FXML
    ChoiceBox cardBox;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	cardBox.getItems().addAll("first", "second", "third");
	cardBox.setTooltip(new Tooltip("Select card to play."));
    }

    /**
     * @FXML private void submitButtonAction(ActionEvent event){ }
     * @FXML ChoiceBox actionBox = new ChoiceBox();
     *       actionBox.getItems().addAll("Build", "Wonder", "Sell");
     *       actionBox.setTooltip(new
     *       Tooltip("Select action to perform with your card."));
     * @FXML ChoiceBox buildBox = new ChoiceBox();
     * 
     *       buildBox.getItems().addAll("Option 1", "Option 2", "Option 3");
     *       buildBox.setTooltip(new Tooltip("Select payment option."));
     * 
     *       cb.getSelectionModel().selectIndexProperty().addListener(new
     *       ChangeListener<Number>() { public void changed(ObservableValue ov,
     *       Number value, Number new_value) {
     *       label.setText(texts[new_Value.intValue()]); } }); }
     */
}