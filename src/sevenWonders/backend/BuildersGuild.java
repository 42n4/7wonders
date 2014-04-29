package sevenWonders.backend;

import java.util.EnumMap;

public class BuildersGuild extends PurpleCard implements VictoryPoints, NeighborDependant {
    private int rewardCount = 0;
    
    public BuildersGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
    }

    @Override
    public int getVictoryPoints() {
	return rewardCount;
    }
 
    @Override
    public void update(int count) {
	rewardCount = count;
    }
}
