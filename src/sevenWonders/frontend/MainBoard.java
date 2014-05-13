package sevenWonders.frontend;

import javafx.event.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import sevenWonders.Program;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Hand;
import sevenWonders.backend.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class MainBoard extends AnchorPane implements Initializable {
    
    @FXML
    private Pane PlayerBoard, OpponentBoard1, OpponentBoard2, 
	OpponentBoard3, OpponentBoard4, OpponentBoard5, OpponentBoard6,
	MenuContainer;
    
    @FXML
    private ImageView HoverTarget;
    
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
	playerBoards = new Pane[] {
	    PlayerBoard, OpponentBoard1, OpponentBoard2, OpponentBoard3, OpponentBoard4, OpponentBoard5, OpponentBoard6
	};
	HoverTarget.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
        	ImageView target = (ImageView)e.getSource();
        	target.setVisible(true);
            }
	});
        HoverTarget.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
        	ImageView target = (ImageView)e.getSource();
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
    }
    
    public void parseHand(Hand h) {
	MenuContainer.getChildren().clear();
	MenuContainer.getChildren().add(new WondersHandController(conn, h, HoverTarget));
    }
}
