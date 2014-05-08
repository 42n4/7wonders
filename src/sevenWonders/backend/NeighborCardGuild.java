package sevenWonders.backend;

import java.util.EnumMap;

public class NeighborCardGuild extends PurpleCard implements VictoryPoints,
	NeighborDependant {
    public final int victoryPointsReward;
    public final Class<? extends Card> cardType;
    private int updatedReward = 0;

    public NeighborCardGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost, int victoryPointsReward, Class<Card> cardType) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
	this.victoryPointsReward = victoryPointsReward;
	this.cardType = cardType;
    }

    NeighborCardGuild(NeighborCardGuildBuilder builder) {
	super(builder);
	this.cardType = builder.cardType;
	this.victoryPointsReward = builder.victoryPointsReward;
    }

    @Override
    public int getVictoryPoints() {
	return updatedReward;
    }

    @Override
    public void update(int count) {
	updatedReward = count * victoryPointsReward;
    }

    static class NeighborCardGuildBuilder extends
	    Builder<NeighborCardGuild, NeighborCardGuildBuilder> {
	private Class<? extends Card> cardType;
	private int victoryPointsReward;

	NeighborCardGuildBuilder rewardCard(Class<? extends Card> cardType) {
	    this.cardType = cardType;
	    return this;
	}

	NeighborCardGuildBuilder pointsReward(int points) {
	    this.victoryPointsReward = points;
	    return this;
	}

	NeighborCardGuild build() {
	    return new NeighborCardGuild(this);
	}
    }
}
