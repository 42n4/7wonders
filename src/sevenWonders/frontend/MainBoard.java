package sevenWonders.frontend;

import javafx.event.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import sevenWonders.Program;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Hand;
import sevenWonders.backend.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * Mainboard is the Anchorpane in the gui that contains the other gui elements.
 * It contains a imageview for displaying mouse-over targets from the other
 * views in a larger size. It contains parser methods for the gui elements it
 * incorpates, PlayerBoard, WonderHandController and VictoryScreenController.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class MainBoard extends AnchorPane implements Initializable {

    @FXML
    private Pane PlayerBoard, OpponentBoard1, OpponentBoard2, OpponentBoard3,
	    OpponentBoard4, OpponentBoard5, OpponentBoard6, MenuContainer;

    @FXML
    private ImageView HoverTarget;

    @FXML
    private Label EraLabel, RoundLabel;

    private Pane[] playerBoards;
    private final ServerConnection conn;

    public MainBoard(ServerConnection conn) {
	this.conn = conn;

	FXMLLoader loader = new FXMLLoader(Program.getURL("MainBoard.fxml"));
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
	playerBoards = new Pane[] { PlayerBoard, OpponentBoard1,
		OpponentBoard2, OpponentBoard3, OpponentBoard4, OpponentBoard5,
		OpponentBoard6 };
	HoverTarget.addEventHandler(MouseEvent.MOUSE_ENTERED,
		new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent e) {
			ImageView target = (ImageView) e.getSource();
			target.setVisible(true);
		    }
		});
	HoverTarget.addEventHandler(MouseEvent.MOUSE_EXITED,
		new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent e) {
			ImageView target = (ImageView) e.getSource();
			target.setVisible(false);
		    }
		});
    }

    public void parseGameState(GameState gs) {
	for (int i = 0; i < gs.players.size(); i++) {
	    PlayerBoard pb = new PlayerBoard(gs.players.get(i), HoverTarget);
	    playerBoards[i].getChildren().clear();
	    playerBoards[i].getChildren().add(pb);
	}
	if (gs.currentEra == 4) {
	    EraLabel.setText("Game is finished!");
	    RoundLabel.setText("");
	} else {
	    EraLabel.setText("Era " + gs.currentEra);
	    RoundLabel.setText("Round " + gs.eraRound);
	}
    }

    public void parseHand(Hand h) {
	MenuContainer.getChildren().clear();
	MenuContainer.getChildren().add(
		new WondersHandController(conn, h, HoverTarget));
    }

    public void parsePoints(List<List<Integer>> points, List<Player> players,
	    int playerIndex) {
	MenuContainer.getChildren().clear();
	MenuContainer.getChildren().add(
		new VictoryScreenController(points, players, playerIndex));
    }
}
