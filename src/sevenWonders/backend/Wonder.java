package sevenWonders.backend;

import java.util.Map;

public class Wonder {
    public final String name;
    public final Map<Resource, Integer>[] cost;
    public final Resource produce;
    public final Stage[] stages;
    private int currentLevel = -1; 
    
    /**
     * Creates a new wonder with the passed attributes.
     * @param name
     * @param cost
     * @param produce
     */
    public Wonder(String name, Map<Resource, Integer>[] cost, Resource produce, Stage[] stages) {
	this.name = name;
	this.cost = cost;
	this.produce = produce;
	this.stages = stages;
        build(1);
        build(2);
        build(3);
    }

    public int getCurrentLevel() {
	return currentLevel + 1;
    }
    
    /**
     * Builds a level on the wonder and returns an event. TODO
     * Does not have a access modifier because it should only 
     * be accessible from this package.
     */
    void build(int currentEra) {
	if (getCurrentLevel() >= cost.length) 
	    throw new IllegalArgumentException("Trying to build a finished wonder");
	currentLevel++;
	stages[currentLevel].eraBuilt = currentEra;
	// TODO: return some action thingy
    }
    
    public static abstract class Stage {
	public final StageType type;
	private int eraBuilt = 0;

	public Stage(StageType type) {
	    this.type = type;
	}
	
	public int built() {
	    return eraBuilt;
	}
    }
    
    public static enum StageType{
	NORMAL, RESURRECT, LASTCARD, FREEBUILD, COPYGUILD
    }
}
