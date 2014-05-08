package sevenWonders.backend;

import java.util.EnumMap;

public class WonderRewardCard extends YellowCard implements Money,
	VictoryPoints, NeighborDependant {
    private int rewardCount = 0;

    public WonderRewardCard(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
    }

    WonderRewardCard(WonderRewardCardBuilder builder) {
	super(builder);
    }

    @Override
    public int getVictoryPoints() {
	return rewardCount;
    }

    @Override
    public int getMoney() {
	return rewardCount * 3;
    }

    @Override
    public void update(int count) {
	rewardCount = count;
    }

    static class WonderRewardCardBuilder extends
	    Builder<WonderRewardCard, WonderRewardCardBuilder> {

	WonderRewardCard build() {
	    return new WonderRewardCard(this);
	}
    }
}
