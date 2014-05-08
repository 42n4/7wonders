package sevenWonders.backend;

import java.util.EnumMap;
import java.util.Map;

public class GrayCard extends Card implements ResourceCard {
    private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
	    Resource.class);

    public GrayCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost,
	    Resource[] resources, int amount) {
	super(id, name, description, preCards, postCards, cost, moneyCost);

	for (Resource r : resources) {
	    this.resources.put(r, amount);
	}
    }
    
    GrayCard(GrayCardBuilder builder){
	super(builder);
	this.resources = builder.resources;
    }

    @Override
    public Map<Resource, Integer> getResources() {
	return resources;
    }

    @Override
    public boolean isSellable() {
	return true;
    }
    
    static class GrayCardBuilder extends Builder<GrayCard, GrayCardBuilder> {
	private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
		Resource.class);
	
	GrayCardBuilder produce(Resource r, int nr) {
	    resources.put(r, nr);
	    return this;
	}
	
	GrayCard build() {
	    return new GrayCard(this);
	}
    }
}
