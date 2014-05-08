package sevenWonders.backend;


import java.util.EnumMap;
import java.util.Map;

public class BrownCard extends Card implements ResourceCard {
    private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
	    Resource.class);

    public BrownCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost,
	    Resource[] resources, int amount) {
	super(id, name, description, preCards, postCards, cost, moneyCost);

	for (Resource r : resources) {
	    this.resources.put(r, amount);
	}
    }

    BrownCard(BrownCardBuilder builder) {
	super(builder);
	resources = builder.resources;
    }

    @Override
    public Map<Resource, Integer> getResources() {
	return resources;
    }

    @Override
    public boolean isSellable() {
	return true;
    }

    static class BrownCardBuilder extends Builder<BrownCard, BrownCardBuilder> {
	private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
		Resource.class);
	
	BrownCardBuilder produce(Resource r, int nr) {
	    resources.put(r, nr);
	    return this;
	}
	
	BrownCard build() {
	    return new BrownCard(this);
	}
    }
}
