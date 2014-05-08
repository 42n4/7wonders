package sevenWonders.backend;

import java.util.EnumMap;
import java.util.Map;

public class YellowResourceCard extends YellowCard implements ResourceCard {

    private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
	    Resource.class);

    public YellowResourceCard(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, Resource[] resources) {
	super(id, name, description, preCards, postCards, cost, moneyCost);

	for (Resource r : resources) {
	    this.resources.put(r, 1);
	}
    }
    
    YellowResourceCard(YellowResourceCardBuilder builder){
	super(builder);
	this.resources = builder.resources;
    }

    @Override
    public Map<Resource, Integer> getResources() {
	return resources;
    }

    @Override
    public boolean isSellable() {
	return false;
    }
    
    static class YellowResourceCardBuilder extends Builder<YellowResourceCard, YellowResourceCardBuilder> {
	private Map<Resource, Integer> resources = new EnumMap<Resource, Integer>(
		    Resource.class);
	
	YellowResourceCardBuilder produce(Resource r, int nr) {
	    resources.put(r, nr);
	    return this;
	}
	
	YellowResourceCard build() {
	    return new YellowResourceCard(this);
	}
    }
}
