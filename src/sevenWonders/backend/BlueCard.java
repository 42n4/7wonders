package sevenWonders.backend;

import java.util.EnumMap;

public class BlueCard extends Card implements VictoryPoints {
    private int victoryPoints;
    
    public BlueCard(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, int victoryPoints) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.victoryPoints = victoryPoints;
    }
    
    BlueCard(BlueCardBuilder builder){
	super(builder);
	this.victoryPoints = builder.points;
    }
    
    @Override
    public int getVictoryPoints() {
        return victoryPoints;
    }
    
    static class BlueCardBuilder extends Builder<BlueCard, BlueCardBuilder> {
	private int points;
	
	BlueCardBuilder points(int victoryPoints) {
	    points = victoryPoints;
	    return this;
	}
	
	BlueCard build() {
	    return new BlueCard(this);
	}
    }
}
