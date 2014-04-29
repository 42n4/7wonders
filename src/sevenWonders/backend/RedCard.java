package sevenWonders.backend;

import java.util.EnumMap;

public class RedCard extends Card implements Shields {
    private int shields;
    
    public RedCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, int shields) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.shields = shields;
    }

    @Override
    public int getShields() {
        return shields;
    }
}
