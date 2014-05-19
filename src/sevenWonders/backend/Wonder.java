package sevenWonders.backend;

import java.util.Map;

/**
 * This class represents a Wonder in the game. It has a name, cost, production
 * resource, stages and currently built level. Stages are represented buy an
 * enum, there's five diffrent StageTypes that describes if any action happens
 * when you build the wonder.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Wonder {
    public final String name;
    public final Map<Resource, Integer>[] cost;
    public final Resource produce;
    public final Stage[] stages;
    public final boolean isBside;
    private int currentLevel = -1;

    /**
     * Creates a new wonder with the passed attributes.
     * 
     * @param name
     * @param cost
     * @param produce
     */
    public Wonder(String name, Map<Resource, Integer>[] cost, Resource produce,
	    Stage[] stages, boolean isBside) {
	this.name = name;
	this.cost = cost;
	this.produce = produce;
	this.stages = stages;
	this.isBside = isBside;
    }

    public int getCurrentLevel() {
	return currentLevel + 1;
    }

    /**
     * Builds a level on the wonder and returns an event. TODO Does not have a
     * access modifier because it should only be accessible from this package.
     */
    void build(int currentEra) {
	if (getCurrentLevel() >= cost.length)
	    throw new IllegalArgumentException(
		    "Trying to build a finished wonder");
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

    public static enum StageType {
	NORMAL, RESURRECT, LASTCARD, FREEBUILD, COPYGUILD
    }
}
