package sevenWonders.backend;

public interface ClientConnection {
    /**
     * Sends a GameState and a Hand to the Client
     * @param gameState
     * @param hand
     * @return
     */
    public boolean SendGameState(GameState gameState, Hand hand);
    
    public Action GetAction();
}
