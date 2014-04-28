package backend;

import java.util.EnumMap;

public class Wonder {
    public final String name;
    public final EnumMap<Resource, Integer>[] cost;
    public final Resource produce;
    private int currentLevel = -1;
    
    /**
     * Creates a new wonder with the passed attributes.
     * @param name
     * @param cost
     * @param produce
     */
    public Wonder(String name, EnumMap<Resource, Integer>[] cost, Resource produce) {
	this.name = name;
	this.cost = cost;
	this.produce = produce;
    }

    public int getCurrentLevel() {
	return currentLevel + 1;
    }
    
    /**
     * Builds a level on the wonder and returns an event. TODO
     * Does not have a access modifier because it should only 
     * be accessible from this package.
     */
    void build() {
	if (getCurrentLevel() >= cost.length) 
	    throw new IllegalArgumentException("Trying to build a finished wonder");
	currentLevel++;
	// TODO: return some action thingy
    }
}
