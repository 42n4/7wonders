package sevenWonders.backend;

import java.util.EnumMap;

public class ScienceGuild extends PurpleCard implements ScienceChoice {

    public ScienceGuild(int id, String name, String description,
	    int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost,
	    int moneyCost) {
	super(id, name, description, preCards, postCards, cost, moneyCost);
    }

    ScienceGuild(ScienceGuildBuilder builder) {
	super(builder);
    }

    static class ScienceGuildBuilder extends
	    Builder<ScienceGuild, ScienceGuildBuilder> {

	ScienceGuild build() {
	    return new ScienceGuild(this);
	}
    }
}
