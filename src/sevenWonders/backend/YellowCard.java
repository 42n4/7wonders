package sevenWonders.backend;

import java.util.EnumMap;

public abstract class YellowCard extends Card{

    protected YellowCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	
    }

    public YellowCard(Builder<?, ?> builder) {
	super(builder);
    }
    
}
