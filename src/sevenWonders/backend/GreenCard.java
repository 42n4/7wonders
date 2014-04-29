package sevenWonders.backend;

import java.util.EnumMap;

public class GreenCard extends Card implements ScienceCard {
    private Science science;
    
    public GreenCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, Science science) {
	super(id, name, description, preCards, postCards, cost);
	this.science = science;
    }

    @Override
    public Science getScience() {
	return science;
    }
}
