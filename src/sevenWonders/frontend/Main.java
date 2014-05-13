package sevenWonders.frontend;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sevenWonders.Program;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Player;
import sevenWonders.backend.Resource;
import sevenWonders.backend.Wonder;
import sevenWonders.backend.Wonder.StageType;
import sevenWonders.backend.Deck;
import sun.net.www.content.text.plain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*; 
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
	//System.out.println(Main.class.getResource("Main.fxml"));return;
	Application.launch(Main.class, (java.lang.String[]) null);
    } 

    @Override
    public void start(Stage primaryStage) {
	ArrayList<Player> players = new ArrayList<Player>(3);
	players.add(Player.randPlayer());
	players.add(Player.randPlayer());
	players.add(Player.randPlayer());

	ServerConnection conn = new LocalClient(0);
	
	GameState gs = new GameState(players, 1, 1);
	MainBoard page = new MainBoard(conn);
	
	Scene scene = new Scene(page);
	primaryStage.setScene(scene);
	primaryStage.setTitle("BasicGame");
	primaryStage.show();
	
	page.parseGameState(gs);
	page.parseHand(GivfHand.Givf());
    }
}
