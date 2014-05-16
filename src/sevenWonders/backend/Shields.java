package sevenWonders.backend;

/**
 * Any wonder of card that has military strength will implement this interface.
 * It has a mthod for fetching the military sterngth of the ibject.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public interface Shields {

    /**
     * Retrieve objects military strength.
     * 
     * @return int military strength
     */
    public int getShields();
}
