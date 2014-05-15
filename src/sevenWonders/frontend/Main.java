package sevenWonders.frontend;

import sevenWonders.backend.ClientConnection;
import sevenWonders.backend.GameEngine;
import sevenWonders.frontend.ServerConnection.GamePackage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Main starts a new local game of Seven Wonders!
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Main extends Application {

    public static void main(String[] args) {
	// System.out.println(Main.class.getResource("Main.fxml"));return;
	Application.launch(Main.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {

	final ServerConnection conn = new LocalClient(0);

	final MainBoard page = new MainBoard(conn);

	Scene scene = new Scene(page);
	primaryStage.setScene(scene);
	primaryStage.setTitle("BasicGame");
	primaryStage.show();

	final GameEngine engine = GameEngine.createAIGame("Andreas",
		(ClientConnection) conn, 3);

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
			// TODO currently the fxml loader doesn't work.
			Platform.runLater(new Runnable() {
			    public void run() {
				page.parsePoints(gp.points,
					gp.gameState.players, 0);
			    }
			});
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
    }
}
