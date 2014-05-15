package sevenWonders.frontend;

import java.util.List;
import sevenWonders.backend.Action;
import sevenWonders.backend.ClientConnection;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Hand;

/**
 * The LocalClient is a singleplayer implementation of ServerConnection. It
 * handles all the local game's communication with the server.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class LocalClient implements ServerConnection, ClientConnection {
    private Action action;
    private int playerIndex;
    GamePackage gp = new GamePackage();

    public LocalClient(int playerIndex) {
	this.playerIndex = playerIndex;
    }

    @Override
    public int getPlayerIndex() {
	return playerIndex;
    }

    private final Lock readyGameState = new Lock();

    @Override
    public boolean SendGameState(GameState gameState, Hand hand) {
	synchronized (readyGameState) {
	    if (readyGameState.b == true)
		return false;

	    gp = new GamePackage();
	    gp.gameState = gameState;
	    gp.hand = hand;
	    readyGameState.b = true;
	}
	return true;
    }

    @Override
    public boolean SendEndState(GameState gameState,
	    List<List<Integer>> victoryPoints) {
	synchronized (readyGameState) {
	    if (readyGameState.b == true)
		return false;

	    gp = new GamePackage();
	    gp.gameState = gameState;
	    gp.points = victoryPoints;
	    readyGameState.b = true;
	}
	return true;
    }

    @Override
    public GamePackage GetGameState() {
	while (true) {
	    synchronized (readyGameState) {
		if (readyGameState.b)
		    break;
	    }
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
	    }
	}
	synchronized (readyGameState) {
	    readyGameState.b = false;
	}
	return gp;
    }

    private final Lock readyAction = new Lock();

    @Override
    public boolean SendAction(Action action) {
	synchronized (readyAction) {
	    if (readyAction.b == true)
		return false;

	    this.action = action;
	    readyAction.b = true;
	}
	return true;
    }

    @Override
    public Action GetAction() {
	while (true) {
	    synchronized (readyAction) {
		if (readyAction.b)
		    break;
	    }
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
	    }
	}
	synchronized (readyAction) {
	    readyAction.b = false;
	}
	return action;
    }

    private class Lock {
	boolean b = false;
    }
}
