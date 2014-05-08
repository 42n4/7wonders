package sevenWonders.backend;

import java.util.EnumMap;

/**
 * Red cards. These are military cards with a shieldsvalue to describe military strength.
 * 
 * @author Jenny Norelius
 */
public class RedCard extends Card implements Shields {
    private int shields;
    
    public RedCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, int shields) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.shields = shields;
    }
    
    RedCard(RedCardBuilder builder){
	super(builder);
	this.shields = builder.militaryShields;
    }

    @Override
    public int getShields() {
        return shields;
    }
    
    static class RedCardBuilder extends Builder<RedCard, RedCardBuilder> {
	private int militaryShields;
	
	RedCardBuilder shields(int shields) {
	    militaryShields = shields;
	    return this;
	}
	
	RedCard build() {
	    return new RedCard(this);
	}
    }
}
