package sevenWonders.frontend;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import sevenWonders.Program;
import sevenWonders.backend.ClientConnection;
import sevenWonders.backend.GameEngine;
import sevenWonders.frontend.ServerConnection.GamePackage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * StartScreen is the gui element that represents the startscreen gui element.
 * You can choose number of players, input name, choose A or B sides of Wonders,
 * read the manual (launches as a seperate file), read instructions and quit the
 * game.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class StartScreen extends AnchorPane implements Initializable {

    @FXML
    private TextField NameInput;

    @FXML
    private CheckBox SidesChooser;

    @FXML
    private ChoiceBox<Integer> NumPlayerChoice;

    private Stage parent;

    public StartScreen(Stage parent) {
	this.parent = parent;

	FXMLLoader loader = new FXMLLoader(
		sevenWonders.Program.getURL("StartScreen.fxml"));
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
    }

    @FXML
    void exitClick(ActionEvent event) {
	parent.close();
    }

    @FXML
    void instructionsClick(ActionEvent event) {
	String instructions = "Play the goddamn game!";

	Label label = new Label(instructions);
	label.setPadding(new Insets(30));
	Pane p = new Pane();
	p.getChildren().add(label);

	Scene scene = new Scene(p);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("Instructions");
	stage.show();
    }

    @FXML
    void rulesClick(ActionEvent event) {
	new Thread() {
	    public void run() {
		try {
		    Desktop.getDesktop().open(
			    new File(Program.getURL("rulebook.pdf").toURI()));
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	    }
	}.start();
    }

    @FXML
    void startClick(ActionEvent event) {
	String playerName = NameInput.getText();
	int playerCount = NumPlayerChoice.getValue();
	boolean useBSides = SidesChooser.isSelected();

	if (NameInput.getText().equals("")) {
	    NameInput.setText("Please enter name");
	    NameInput.requestFocus();
	    return;
	}

	((Button) event.getSource()).setDisable(true);
	Program.loadCardImages(playerCount);

	// All is good. Start the game, close this window.

	final ServerConnection conn = new LocalClient(0);

	final MainBoard page = new MainBoard(conn);

	Scene scene = new Scene(page);
	Stage stage = new Stage();
	stage.setScene(scene);
	stage.setTitle("7 wonders");
	stage.show();

	final GameEngine engine = GameEngine.createAIGame(playerName,
		(ClientConnection) conn, playerCount, useBSides);

	new Thread() {
	    public void run() {
		engine.run();
	    }
	}.start();

	new Thread() {
	    public void run() {
		boolean gameNotFinished = true;
		while (gameNotFinished) {
		    final GamePackage gp = conn.GetGameState();

		    Platform.runLater(new Runnable() {
			public void run() {
			    page.parseGameState(gp.gameState);
			}
		    });

		    // Shows the victory screen if game is over
		    if (gp.gameState.currentEra == 4) {
			Platform.runLater(new Runnable() {
			    public void run() {
				page.parsePoints(gp.points,
					gp.gameState.players, 0);
			    }
			});
			gameNotFinished = false;
		    } else {
			Platform.runLater(new Runnable() {
			    public void run() {
				page.parseHand(gp.hand);
			    }
			});
		    }
		}
	    }
	}.start();

	parent.close();
    }
}
