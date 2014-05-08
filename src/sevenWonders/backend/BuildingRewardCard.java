package sevenWonders.backend;

import java.util.EnumMap;

public class BuildingRewardCard extends YellowCard implements Money,
	VictoryPoints, NeighborDependant {
    public final Class<? extends Card> cardType;
    public final boolean neighbors;
    public final int moneyReward, victoryPointsReward;
    private int updatedMoney, updatedVictoryPoints;

    public BuildingRewardCard(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost, Class<Card> cardType, boolean neighbors,
	    int moneyReward, int victoryPointsReward) {
	super(id, name, description, preCards, postCards, cost, moneyCost);

	this.cardType = cardType;
	this.neighbors = neighbors;
	this.moneyReward = moneyReward;
	this.victoryPointsReward = victoryPointsReward;
    }

    BuildingRewardCard(BuildingRewardCardBuilder builder) {
	super(builder);
	this.cardType = builder.cardType;
	this.neighbors = builder.neighbors;
	this.moneyReward = builder.moneyReward;
	this.victoryPointsReward = builder.victoryPointsReward;
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

    static class BuildingRewardCardBuilder extends
	    Builder<BuildingRewardCard, BuildingRewardCardBuilder> {
	private Class<? extends Card> cardType;
	private boolean neighbors;
	private int moneyReward, victoryPointsReward;

	BuildingRewardCardBuilder rewardCard(Class<? extends Card> cardType) {
	    this.cardType = cardType;
	    return this;
	}

	BuildingRewardCardBuilder neighbors(boolean b) {
	    this.neighbors = b;
	    return this;
	}

	BuildingRewardCardBuilder moneyReward(int money) {
	    this.moneyReward = money;
	    return this;
	}

	BuildingRewardCardBuilder pointsReward(int points) {
	    this.victoryPointsReward = points;
	    return this;
	}

	BuildingRewardCard build() {
	    return new BuildingRewardCard(this);
	}
    }
}
