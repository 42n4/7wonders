package sevenWonders.backend;

import java.util.EnumMap;

public class CostModifierCard extends YellowCard implements CostModifier {
    private Resource[] resources;
    private boolean[] directions;

    public CostModifierCard(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost,
	    Resource[] resources, boolean[] directions) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.resources = resources;
	this.directions = directions;
    }

    @Override
    public Resource[] getResources() {
	return resources;
    }

    @Override
    public boolean[] getDirections() {
	return directions;
    }

}
