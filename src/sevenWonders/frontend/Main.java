package sevenWonders.frontend;

import java.net.URL;
import java.util.ResourceBundle;

import sevenWonders.Program;
import sevenWonders.backend.Player;
import sevenWonders.backend.Resource;
import sevenWonders.backend.Wonder;
import sevenWonders.backend.Wonder.StageType;
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

public class Main extends Application {
    public static void main(String[] args) {
	//System.out.println(Main.class.getResource("Main.fxml"));return;
	Application.launch(Main.class, (java.lang.String[]) null);
    } 

    @Override
    public void start(Stage primaryStage) {
	Wonder.Stage s = new Wonder.Stage(StageType.COPYGUILD) {
	    
	};
	Player p = new Player("Andreas", new Wonder("Pyramids", null, Resource.CLAY, new Wonder.Stage[] {
		s,s,s
	}));
	int[][] wins = p.getMilitaryWins();
	wins[0][0] = -1;
	wins[0][1] = 0;
	wins[0][2] = 5;
	wins[1][0] = 1;
	wins[1][1] = 3;
	wins[1][2] = 5;
	
	try {
	    //Pane page = (Pane) FXMLLoader.load(Program.getURL("Main.fxml"));
	    Scene scene = new Scene(new PlayerBoard(p));
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("BasicGame");
	    primaryStage.show();
	    
	} catch (Exception e) {
	    System.err.println(e);
	    for (StackTraceElement t : e.getStackTrace()) {
		System.err.println(t);
	    }
	}
    }
}
