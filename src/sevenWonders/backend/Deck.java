package sevenWonders.backend;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains all the cards in the game.
 * 
 * @author Jenny Norelius & Andreas Jönsson 
 */
public class Deck {

    /**
     * @param era
     * @param playerCount
     * @return
     */
    public static List<Card> GetDeck(int era, int playerCount) {
	return getEra2Players3Deck();
    }

    private List<Card> getEra1Players3Deck() {
	List<Card> deck = new LinkedList<>();
	deck.add(new BrownCard(1, null, null, null, null, null, 0, null, 0));
	return null;
    }

    private static List<Card> getEra2Players3Deck() { // TODO add proper pre- and
					       // postcards
	List<Card> deck = new LinkedList<>();
	EnumMap<Resource, Integer> cost = new EnumMap<Resource, Integer>(
		Resource.class);
	ResourceBuilder builder = ResourceBuilder.newBuilder();

	builder.set(Resource.WOOD, 1).set(Resource.CLAY, 1)
		.set(Resource.GLASS, 1);
	deck.add(new BlueCard(50, "Temple", "era2 - 1.png", new int[] { 5 },
		new int[] { 106 }, builder.build(), 0, 3));
	builder.reset();

	builder.set(Resource.WOOD, 2);
	deck.add(new YellowResourceCard(51, "Caravansery", "era2 - 2.png",
		new int[] { 5 }, new int[] { 106 }, builder.build(), 0,
		new Resource[] { Resource.WOOD, Resource.STONE, Resource.ORE,
			Resource.CLAY}));
	builder.reset();

	builder.set(Resource.STONE, 3);
	deck.add(new BlueCard(52, "Aqueduct", "era2 - 3.png", new int[] { 5 },
		null, builder.build(), 0, 5));
	builder.reset();

	builder.set(Resource.CLAY, 2);
	deck.add(new YellowResourceCard(53, "Forum", "era2 - 4.png",
		new int[] { 8 }, new int[] { 107 }, builder.build(), 0,
		new Resource[] { Resource.GLASS, Resource.LOOM,
			Resource.PAPYRUS }));
	builder.reset();
	
	builder.set(Resource.STONE, 2).set(Resource.LOOM, 1);
	deck.add(new GreenCard(54, "Library", "era2 - 5.png",
		new int[] { 8 }, new int[] { 107 }, builder.build(), 0, Science.WRITING));
	builder.reset();
	
	deck.add(new GrayCard(55, "Glassworks", "era2 - 6.png", null,
		null, builder.build(), 0, new Resource[] {Resource.GLASS}, 1));
	builder.reset();
	
	deck.add(new GrayCard(56, "Foundry", "era2 - 7.png", null,
		null, builder.build(), 0, new Resource[] {Resource.ORE}, 2));
	builder.reset();

	return deck;
    }

    static class ResourceBuilder {
	EnumMap<Resource, Integer> map = new EnumMap<Resource, Integer>(
		Resource.class);

	EnumMap<Resource, Integer> build() {
	    EnumMap<Resource, Integer> result = new EnumMap<Resource, Integer>(
		    Resource.class);
	    result.putAll(map);
	    return result;
	}

	ResourceBuilder reset() {
	    map.clear();
	    return this;
	}

	ResourceBuilder set(Resource r, int nr) {
	    map.put(r, nr);
	    return this;
	}

	static ResourceBuilder newBuilder() {
	    return new ResourceBuilder();
	}
    }
}
