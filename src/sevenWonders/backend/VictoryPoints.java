package sevenWonders.backend;

/**
 * Any wonder or card that grants victory points at the end of the game will
 * implement this interface. It returns the ammount of vistory points granted.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public interface VictoryPoints {
    
    /**
     * Returns the objects victory points.
     * @return int victory points
     */
    public int getVictoryPoints();
}
