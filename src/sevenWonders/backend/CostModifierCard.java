package sevenWonders.backend;

import java.util.EnumMap;

public class CostModifierCard extends YellowCard implements CostModifier {
    private Resource[] resources;
    // 0 = left, 1 = right
    private boolean[] directions;

    public CostModifierCard(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost, Resource[] resources, boolean[] directions) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.resources = resources;
	this.directions = directions;
    }
    
    CostModifierCard(CostModifierCardBuilder builder){
	super(builder);
	resources = builder.modifiedResources;
	directions = builder.modifiedDirections;
    }

    @Override
    public Resource[] getResources() {
	return resources;
    }

    /**
     * directions[0] = left, directions[1] = right
     */
    @Override
    public boolean[] getDirections() {
	return directions;
    }

    static class CostModifierCardBuilder extends
	    Builder<CostModifierCard, CostModifierCardBuilder> {
	private Resource[] modifiedResources;
	private boolean[] modifiedDirections;

	CostModifierCardBuilder modifiedResources(Resource... r) {
	    modifiedResources = r;
	    return this;
	}
	
	/**
	 * left, right
	 * @param left modifies if true
	 * @param right modifies if true
	 * @return
	 */
	CostModifierCardBuilder modifyDirections(boolean left, boolean right) {
	    modifiedDirections = new boolean[]{left, right};
	    return this;
	}

	CostModifierCard build() {
	    return new CostModifierCard(this);
	}
    }
}
