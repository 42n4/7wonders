package sevenWonders.frontend;

import javafx.fxml.FXMLLoader;
import sevenWonders.backend.Player;
import sevenWonders.Program;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * Displays the final results of the game. it takes a list of points generated
 * by the PointCalculator, a list of players and an index for the local player.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class VictoryScreenController extends AnchorPane implements
	Initializable {
    private final String WIN = "Victory!";
    private final String LOSE = "You Lose!";
    private final String DRAW = "It's a draw!";
    private final String YOU = "You!";
    private List<List<Integer>> points;
    private List<Player> players;
    private int playerIndex;
    private final List<String> identifier;

    @FXML
    private Label conflictLabel;
    @FXML
    private ListView<String> conflictList;
    @FXML
    private ImageView playerImage1;
    @FXML
    private ImageView playerImage2;
    @FXML
    private ImageView playerImage3;
    @FXML
    private ImageView playerImage4;
    @FXML
    private ImageView playerImage5;
    @FXML
    private ImageView playerImage6;
    @FXML
    private ImageView playerImage7;
    @FXML
    private ListView<?> playerList1;
    @FXML
    private ListView<?> playerList2;
    @FXML
    private ListView<?> playerList3;
    @FXML
    private ListView<?> playerList4;
    @FXML
    private ListView<?> playerList5;
    @FXML
    private ListView<?> playerList6;
    @FXML
    private ListView<?> playerList7;
    @FXML
    private Label playerName1;
    @FXML
    private Label playerName2;
    @FXML
    private Label playerName3;
    @FXML
    private Label playerName4;
    @FXML
    private Label playerName5;
    @FXML
    private Label playerName6;
    @FXML
    private Label playerName7;
    @FXML
    private Label status;

    public VictoryScreenController(List<List<Integer>> points,
	    List<Player> players, int playerIndex) {
	this.points = points;
	this.players = players;
	this.playerIndex = playerIndex;
	identifier = Arrays.asList("Military", "Treasury", "Wonders",
		"Civilian", "Scientific", "Commerce", "Guilds", "Total");

	FXMLLoader loader = new FXMLLoader(
		sevenWonders.Program.getURL("VictoryScreen.fxml"));
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
	assertContainers();
	List<ImageView> playerImages = Arrays.asList(new ImageView[] {
		playerImage1, playerImage2, playerImage3, playerImage4,
		playerImage5, playerImage6, playerImage7 });
	List<Label> playerNames = Arrays.asList(new Label[] { playerName1,
		playerName2, playerName3, playerName4, playerName5,
		playerName6, playerName7 });
	List<ListView> playerLists = Arrays.asList(new ListView[] {
		playerList1, playerList2, playerList3, playerList4,
		playerList5, playerList6, playerList7 });

	// Calculate winners and set status
	List<Integer> winners = getWinners();
	if (!winners.contains(playerIndex)) {
	    status.setText(LOSE);
	} else if (winners.size() == 1) {
	    status.setText(WIN);
	} else {
	    status.setText(DRAW);
	}

	// Add crowns to the victors
	Image img = Program.getImageFromFilename("victor.png");
	for (Integer playerIndex : winners) {
	    playerImages.get(playerIndex).setImage(img);
	}

	// Set all playernames, sets the local player to YOU.
	for (int i = 0; i < players.size(); i++) {
	    playerNames.get(i).setText(players.get(i).name);
	}
	playerNames.get(playerIndex).setText(YOU);

	// Sets the leftmost listView with a points type name
	conflictList.getItems().setAll(identifier);

	// Sets the player points lists
	for (int i = 0; i < players.size(); i++) {
	    playerLists.get(i).getItems().setAll(points.get(i));
	}
    }

    /**
     * @return
     */
    private List<Integer> getWinners() {
	List<Integer> winners = new ArrayList<>();
	winners.add(0);
	for (int i = 1; i < points.size(); i++) {
	    if (points.get(i).get(7) > points.get(winners.get(0)).get(7)) {
		winners.clear();
		winners.add(i);
	    } else if (points.get(i).get(7) > points.get(winners.get(0)).get(7)) {
		winners.add(i);
	    }
	}
	return winners;
    }

    /**
     * 
     */
    private void assertContainers() {
	assert conflictLabel != null : "fx:id=\"conflictLabel\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert conflictList != null : "fx:id=\"conflictList\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage1 != null : "fx:id=\"playerImage1\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage2 != null : "fx:id=\"playerImage2\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage3 != null : "fx:id=\"playerImage3\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage4 != null : "fx:id=\"playerImage4\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage5 != null : "fx:id=\"playerImage5\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage6 != null : "fx:id=\"playerImage6\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerImage7 != null : "fx:id=\"playerImage7\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList1 != null : "fx:id=\"playerList1\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList2 != null : "fx:id=\"playerList2\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList3 != null : "fx:id=\"playerList3\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList4 != null : "fx:id=\"playerList4\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList5 != null : "fx:id=\"playerList5\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList6 != null : "fx:id=\"playerList6\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerList7 != null : "fx:id=\"playerList7\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName1 != null : "fx:id=\"playerName1\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName2 != null : "fx:id=\"playerName2\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName3 != null : "fx:id=\"playerName3\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName4 != null : "fx:id=\"playerName4\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName5 != null : "fx:id=\"playerName5\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName6 != null : "fx:id=\"playerName6\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert playerName7 != null : "fx:id=\"playerName7\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
	assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'VictoryScreen.fxml'.";
    }
}
