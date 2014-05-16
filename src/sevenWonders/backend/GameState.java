package sevenWonders.backend;

import java.util.List;

/**
 * Represents the current state of the game.
 * 
 * @author Jenny Norelius
 */
public class GameState {
    public final List<Player> players;
    public final int currentEra;
    public final int eraRound;

    public GameState(List<Player> players, int currentEra, int eraRound) {
	this.players = players;
	this.currentEra = currentEra;
	this.eraRound = eraRound;
    }
}
