package sevenWonders.backend;

import java.util.EnumMap;

public class BuildingRewards extends YellowCard implements Money, VictoryPoints, NeighborDependant {
    public final Class<Card> cardType;
    public final boolean neighbors;
    public final int moneyReward, victoryPointsReward;
    private int updatedMoney, updatedVictoryPoints;
    
    public BuildingRewards(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost, Class<Card> cardType, boolean neighbors, int moneyReward, int victoryPointsReward) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	
	this.cardType = cardType;
	this.neighbors = neighbors;
	this.moneyReward = moneyReward;
	this.victoryPointsReward = victoryPointsReward;
    }

    @Override
    public int getVictoryPoints() {
	return updatedVictoryPoints;
    }

    @Override
    public int getMoney() {
	return updatedMoney;
    }

    @Override
    public void update(int count) {
        updatedMoney = moneyReward * count;
        updatedVictoryPoints = victoryPointsReward * count;
    }

}
