package sevenWonders.backend;

import java.util.EnumMap;

public class GreenCard extends Card implements ScienceCard {
    private Science science;
    
    public GreenCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, Science science) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.science = science;
    }

    @Override
    public Science getScience() {
	return science;
    }
}
