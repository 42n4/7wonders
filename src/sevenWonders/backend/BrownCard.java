package sevenWonders.backend;

import java.util.EnumMap;
import java.util.Map;

public class BrownCard extends Card implements ResourceCard {
    private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
	    Resource.class);
    private boolean sellable;

    public BrownCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost,
	    Resource[] resources, int amount, boolean sellable) {
	super(id, name, description, preCards, postCards, cost);

	for (Resource r : resources) {
	    this.resources.put(r, amount);
	}
	this.sellable = sellable;
    }

    @Override
    public Map<Resource, Integer> getResources() {
	return resources;
    }

    @Override
    public boolean isSellable() {
	return sellable;
    }
}
