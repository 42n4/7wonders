package sevenWonders.frontend;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Testclass to try out WondersHandController and WondersHand.fxml.
 * 
 * @author Jenny Norelius
 */

public class SevenWondersHandTest extends Application {
    public static void main(String[] args) {
	Application.launch(SevenWondersHandTest.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
	try {
	    AnchorPane page = (AnchorPane) FXMLLoader.load(SevenWondersHandTest.class
		    .getResource("WondersHand.fxml"));
	    Scene scene = new Scene(page);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Seven Wonders");
	    primaryStage.show();
	} catch (Exception ex) {
	    Logger.getLogger(SevenWondersHandTest.class.getName()).log(Level.SEVERE,
		    null, ex);
	}
    }
    
    
}
