package sevenWonders.backend;

import java.util.EnumMap;

public class GreenCard extends Card implements ScienceCard {
    private Science science;
    
    public GreenCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, Science science) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.science = science;
    }
    
    GreenCard(GreenCardBuilder builder){
	super(builder);
	science = builder.scienceField;
    }

    @Override
    public Science getScience() {
	return science;
    }
    
    static class GreenCardBuilder extends Builder<GreenCard, GreenCardBuilder> {
	private Science scienceField;
	
	GreenCardBuilder science(Science s) {
	    scienceField = s;
	    return this;
	}
	
	GreenCard build() {
	    return new GreenCard(this);
	}
    }
}
