package sevenWonders.frontend;

import java.util.List;
import sevenWonders.backend.Action;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Hand;

/**
 * This interface is used by the local client to represent a connection to the
 * server. It contains a player identifier, can send an Action and receive
 * GamePackages.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public interface ServerConnection {
    public boolean SendAction(Action action);

    public GamePackage GetGameState();

    public int getPlayerIndex();

    public static class GamePackage {
	GameState gameState;
	Hand hand;
	List<List<Integer>> points;
    }
}
