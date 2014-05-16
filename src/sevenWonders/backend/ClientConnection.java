package sevenWonders.backend;

import java.util.List;

/**
 * The server will contain a client connection to access the clients. It can
 * send a GameState and a Hand, send an endstate wich contains a GameState and a
 * points list, and retrieves an action fro m the client.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public interface ClientConnection {
    /**
     * Sends a GameState and a Hand to the Client
     * 
     * @param gameState
     * @param hand
     * @return
     */
    public boolean SendGameState(GameState gameState, Hand hand);

    /**
     * Sends a GameState and the list of victory points to the Client. The value
     * of Era is 4, meaning the game has ended
     * 
     * @return
     */
    public boolean SendEndState(GameState gameState,
	    List<List<Integer>> victoryPoints);

    public Action GetAction();
}
