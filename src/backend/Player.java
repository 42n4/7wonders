package backend;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public final Wonder wonder;
    private List<Card> buildings = new ArrayList<>();
    private int money; // TODO
    private int militaryTokens = 0; // TODO: left/right?
    public final String name;
    
    /**
     * Creates a new player with passed attributes
     * @param name The player's name
     * @param wonder The player's wonder
     */
    public Player(String name, Wonder wonder) {
	this.wonder = wonder;
	this.name = name;
    }
    
    public int getMoney() {
	return money;
    }
    
    /**
     * Does not have a access modifier because it should only 
     * be accessible from this package.
     */
    void addMoney(int m) {
	money += m;
    }
    
    /**
     * Does not have a access modifier because it should only 
     * be accessible from this package.
     */
    void removeMoney(int m) {
	if (m > money)
	    throw new IllegalArgumentException("Not enough funds");
	money -= m;
    }
    
    public int getMilitaryTokens() {
	return militaryTokens;
    }
    
    /**
     * Adds the passed card to the player's buildings. Doesn't check resources.
     * @param building
     * Does not have a access modifier because it should only 
     * be accessible from this package.
     */
    void buildCard(Card building) {
	buildings.add(building);
    }
}
