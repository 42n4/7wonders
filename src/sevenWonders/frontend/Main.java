package sevenWonders.frontend;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import sevenWonders.Program;
import sevenWonders.backend.ClientConnection;
import sevenWonders.backend.GameEngine;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Player;
import sevenWonders.backend.Resource;
import sevenWonders.backend.Wonder;
import sevenWonders.backend.Wonder.StageType;
import sevenWonders.backend.Deck;
import sevenWonders.frontend.ServerConnection.GamePackage;
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

	ServerConnection conn = new LocalClient(0);
	
	MainBoard page = new MainBoard(conn);
	
	Scene scene = new Scene(page);
	primaryStage.setScene(scene);
	primaryStage.setTitle("BasicGame");
	primaryStage.show();
	
	final GameEngine engine = GameEngine.createAIGame("Andreas", (ClientConnection)conn, 3);
	
	new Thread() {
	    public void run() {
		engine.run();
	    }
	}.start();
	
	while(true) {
	    GamePackage gp = conn.GetGameState();
	    
	    page.parseGameState(gp.gameState);
	    
	    if (gp.gameState.currentEra == 4) break;
	    page.parseHand(gp.hand);
	}
	// TODO: show points
    }
}
