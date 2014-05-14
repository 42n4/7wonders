package sevenWonders.backend;

import java.util.List;

public interface ClientConnection {
    /**
     * Sends a GameState and a Hand to the Client
     * @param gameState
     * @param hand
     * @return
     */
    public boolean SendGameState(GameState gameState, Hand hand);
    
    /**
     * Sends a GameState and the list of victory points
     * to the Client. The value of Era is 4, meaning the 
     * game has ended
     * @return
     */
    public boolean SendEndState(GameState gameState, List<List<Integer>> victoryPoints);
    
    public Action GetAction();
}
