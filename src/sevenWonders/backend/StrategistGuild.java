package sevenWonders.backend;

import java.util.EnumMap;

public class StrategistGuild extends PurpleCard implements VictoryPoints,
	NeighborDependant {
    private int rewardCount = 0;

    public StrategistGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
    }

    StrategistGuild(StrategistGuildBuilder builder) {
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

    static class StrategistGuildBuilder extends
	    Builder<StrategistGuild, StrategistGuildBuilder> {
	
	StrategistGuild build() {
	    return new StrategistGuild(this);
	}
    }
}
