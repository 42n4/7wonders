package sevenWonders.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.management.RuntimeErrorException;

import sevenWonders.Program;
import sevenWonders.backend.Card;
import sevenWonders.backend.Player;
import sevenWonders.backend.Wonder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PlayerBoard extends AnchorPane implements Initializable {
    private static final int NUMCARDS = 19; 

    @FXML
    private ImageView WonderImage;
    @FXML
    private ImageView BaseCard;
    @FXML
    private ImageView BaseWonderStage;
    @FXML
    private Label CoinLabel;
    @FXML
    private ImageView BaseLeftMilitary, BaseRightMilitary;
    
    
    private ImageView[] cardsObjects = new ImageView[NUMCARDS];
    private ImageView[] stageObjects;
    private ImageView[] leftMilitary = new ImageView[3];
    private ImageView[] rightMilitary = new ImageView[3];
    private Player player;
    
    public PlayerBoard(Player player) {
	this.player = player;
	stageObjects = new ImageView[player.wonder.stages.length];
	
	FXMLLoader loader = new FXMLLoader(Program.getURL("PlayerBoard.fxml"));
	loader.setRoot(this);
	loader.setController(this);
	
	try {
	    loader.load();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
    
    /**
     * Creates all the nodes such as placeholders
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	// Initialize card placeholders
    	int offsetX = 15, offsetY = 30, stackSize = 6,
    	    stackOffsetX = 110, stackOffsetY = -28;
    	for (int i = 0; i < NUMCARDS; i++) {
    	    
    	    ImageView v = new ImageView();
    	    v.setLayoutX(BaseCard.getLayoutX() + offsetX*(i%stackSize) + stackOffsetX*(i/stackSize));
    	    v.setLayoutY(BaseCard.getLayoutY() + offsetY*(i%stackSize) + stackOffsetY*(i/stackSize));
    	    
    	    v.setPreserveRatio(true);
    	    v.setFitHeight(BaseCard.getFitHeight());
    	    v.setFitWidth(BaseCard.getFitWidth());
    	    
    	    cardsObjects[i] = v;
    	    this.getChildren().add(v);
    	}
    	
    	ImageView last = cardsObjects[NUMCARDS - 1];
    	ImageView secondLast = cardsObjects[NUMCARDS - 2];
    	last.setLayoutX(secondLast.getLayoutX() + offsetX);
    	last.setLayoutY(secondLast.getLayoutY() + offsetY);
    	
    	this.getChildren().remove(BaseCard);
	
	// Initialize stages of the Wonder
    	Wonder wonder = player.wonder;
    	int offsetStep = 65 - (int)BaseWonderStage.getFitWidth();
    	if (wonder.stages.length == 4) {
    	    offsetStep = 0;
    	    BaseWonderStage.setLayoutY(BaseWonderStage.getLayoutY() - 20);
    	}
    	
    	for (int i = 0, idx = wonder.stages.length-1; i < wonder.stages.length; i++, idx--) {
    	    ImageView v = new ImageView();
    	    v.setLayoutY(BaseWonderStage.getLayoutY() + i*offsetStep + i*BaseWonderStage.getFitWidth());
    	    
    	    v.setRotate(BaseWonderStage.getRotate());
    	    v.setLayoutX(BaseWonderStage.getLayoutX());
    	    v.setPreserveRatio(true);
    	    v.setFitHeight(BaseWonderStage.getFitHeight());
    	    v.setFitWidth(BaseWonderStage.getFitWidth());
    	    
    	    stageObjects[idx] = v;
    	    this.getChildren().add(v);
    	}
    	this.getChildren().remove(BaseWonderStage);
    	this.getChildren().remove(WonderImage);
    	this.getChildren().add(WonderImage);
    	
    	// Initialize Military token placeholders
    	int offsetMilitary = 9;
    	for (int i = 0; i < 3; i++) {
    	    ImageView l = new ImageView();
    	    ImageView r = new ImageView();

    	    l.setLayoutX(BaseLeftMilitary.getLayoutX() + (offsetMilitary + (int)BaseLeftMilitary.getFitWidth())*i);
    	    r.setLayoutX(BaseRightMilitary.getLayoutX() - (offsetMilitary + (int)BaseRightMilitary.getFitWidth())*i);

    	    l.setFitHeight(BaseLeftMilitary.getFitHeight());
    	    r.setFitHeight(BaseRightMilitary.getFitHeight());
    	    l.setFitWidth(BaseLeftMilitary.getFitWidth());
    	    r.setFitWidth(BaseRightMilitary.getFitWidth());
    	    l.setLayoutY(BaseLeftMilitary.getLayoutY());
    	    r.setLayoutY(BaseRightMilitary.getLayoutY());

    	    leftMilitary[i] = l;
    	    rightMilitary[i] = r;
    	    this.getChildren().add(r);
    	    this.getChildren().add(l);
    	}
    	this.getChildren().remove(BaseLeftMilitary);
    	this.getChildren().remove(BaseRightMilitary);
    	
    	// Displays the proper data
    	displayData();
    }
    
    private void displayData() {
	// Addresses money
	CoinLabel.setText("" + player.getMoney());
	
	// Addresses military
	for (int i = 0; i < 3; i++) {
	    int[][] wins = player.getMilitaryWins();
	    if (wins[0][i] != 0) {
		leftMilitary[i].setImage(Program.getImageFromFilename("Military" + wins[0][i] + ".png"));
	    }
	    if (wins[1][i] != 0) {
		rightMilitary[i].setImage(Program.getImageFromFilename("Military" + wins[1][i] + ".png"));
	    }
	}
	
	// Addresses the player's buildings/cards
	List<Card> buildings = player.getBuildings();
	for (int i = 0; i < buildings.size(); i++) {
	    cardsObjects[i].setImage(Program.getImageFromFilename(buildings.get(i).description));
	}
	
	
    }
}
