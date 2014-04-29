package sevenWonders.backend;

import java.util.EnumMap;

public class NeighborCardGuild extends PurpleCard implements VictoryPoints,
	NeighborDependant {
    public final int victoryPointsReward;
    public final Class<Card> cardType;
    private int updatedReward = 0;

    public NeighborCardGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost, int victoryPointsReward, Class<Card> cardType) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.victoryPointsReward = victoryPointsReward;
	this.cardType = cardType;
    }

    @Override
    public int getVictoryPoints() {
	return updatedReward;
    }

    @Override
    public void update(int count) {
	updatedReward = count * victoryPointsReward;
    }
}
