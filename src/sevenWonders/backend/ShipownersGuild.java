package sevenWonders.backend;

import java.util.EnumMap;

public class ShipownersGuild extends PurpleCard implements VictoryPoints,
	NeighborDependant {
    private int rewardCount = 0;

    public ShipownersGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
    }

    ShipownersGuild(ShipownersGuildBuilder builder) {
	super(builder);
    }

    @Override
    public int getVictoryPoints() {
	return rewardCount;
    }

    @Override
    public void update(int count) {
	rewardCount = count;
    }

    static class ShipownersGuildBuilder extends
	    Builder<ShipownersGuild, ShipownersGuildBuilder> {
	
	ShipownersGuild build() {
	    return new ShipownersGuild(this);
	}
    }
}
