package sevenWonders.frontend;

import sevenWonders.backend.Action;
import sevenWonders.backend.GameState;
import sevenWonders.backend.Hand;

public interface ServerConnection {
    public boolean SendAction(Action action);
    
    public GamePackage GetGameState();

    public int getPlayerIndex();
    
    public static class GamePackage {
	GameState gameState;
	Hand hand;
    }
}
