package sevenWonders.backend;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import sevenWonders.backend.Wonder.Stage;
import sevenWonders.backend.Wonder.StageType;

/**
 * WonderFactory contains all the available Wonders in the game. There is a max
 * of 7 Wonders, all with an A-version and B-version. This class generates and
 * provides them to the rest of the game.
 * 
 * @author Jenny Norelius
 */
public class WonderFactory {
    private static class ResourceMap extends EnumMap<Resource, Integer> {
	public ResourceMap() {
	    super(Resource.class);
	}
    }

    /**
     * Will not return the same wonder twice unless you resetRandom()
     * 
     * @return A random Wonder
     */
    public static Wonder getRandomWonder(boolean useBSide) {
	// TODO: differentiate B-sides
	if (returned.size() == allWonders.length) {
	    throw new IllegalArgumentException("All wonders hve been used");
	}
	int idx;
	do {
	    idx = rand.nextInt(allWonders.length);
	} while (returned.contains(allWonders[idx]));

	returned.add(allWonders[idx]);
	return allWonders[idx];
    }

    private static ArrayList<Wonder> returned = new ArrayList<>();
    private static Random rand = new Random();

    public static void resetRandom() {
	returned = new ArrayList<>();
    }

    private static class WonderBuilder {
	String name;
	List<ResourceMap> cost = new ArrayList<>();
	ResourceMap thisStage = new ResourceMap();
	Resource production;
	List<Stage> stages = new ArrayList<>();

	WonderBuilder(String name, Resource production) {
	    this.name = name;
	    this.production = production;
	}

	WonderBuilder addCost(Resource r, int amount) {
	    thisStage.put(r, amount);
	    return this;
	}

	WonderBuilder finishStage(Stage s) {
	    cost.add(thisStage);
	    thisStage = new ResourceMap();
	    stages.add(s);
	    return this;
	}

	Wonder done() {
	    ResourceMap[] costArr = cost.toArray(new ResourceMap[cost.size()]);
	    Stage[] stagesArr = stages.toArray(new Stage[stages.size()]);
	    return new Wonder(name, costArr, production, stagesArr);
	}
    }

    private static class VictoryWonder extends Stage implements VictoryPoints {
	private int points;

	public VictoryWonder(int points) {
	    super(StageType.NORMAL);
	    this.points = points;
	}

	@Override
	public int getVictoryPoints() {
	    return points;
	}
    }

    private static class ResourceWonder extends Stage implements ResourceCard {
	EnumMap<Resource, Integer> resource = new EnumMap<>(Resource.class);

	public ResourceWonder(boolean luxury) {
	    super(StageType.NORMAL);
	    if (luxury) {
		resource.put(Resource.LOOM, 1);
		resource.put(Resource.PAPYRUS, 1);
		resource.put(Resource.GLASS, 1);
	    } else {
		resource.put(Resource.CLAY, 1);
		resource.put(Resource.ORE, 1);
		resource.put(Resource.WOOD, 1);
		resource.put(Resource.STONE, 1);
	    }
	}

	@Override
	public boolean isSellable() {
	    return false;
	}

	@Override
	public Map<Resource, Integer> getResources() {
	    return resource;
	}
    }

    private static class GoldWonder extends Stage implements Money {
	int gold;

	public GoldWonder(int gold) {
	    super(StageType.NORMAL);
	    this.gold = gold;
	}

	@Override
	public int getMoney() {
	    return gold;
	}
    }

    private static class GoldAndVictory extends GoldWonder implements
	    VictoryPoints {
	int points;

	public GoldAndVictory(int gold, int points) {
	    super(gold);
	    this.points = points;
	}

	@Override
	public int getVictoryPoints() {
	    return points;
	}
    }

    private static class ArmyGoldANdVictory extends GoldAndVictory implements
	    Shields {
	public ArmyGoldANdVictory(int gold, int points) {
	    super(gold, points);
	}

	@Override
	public int getShields() {
	    return 1;
	}
    }

    private static class ArmyWonder extends Stage implements Shields {
	public ArmyWonder() {
	    super(StageType.NORMAL);
	}

	@Override
	public int getShields() {
	    return 2;
	}
    }

    private static Wonder[] allWonders = new Wonder[] {
	    // Rhódos A
	    new WonderBuilder("wonder - 1.png", Resource.ORE)
		    .addCost(Resource.WOOD, 2)
		    .finishStage(new VictoryWonder(3))
		    .addCost(Resource.CLAY, 3).finishStage(new ArmyWonder())
		    .addCost(Resource.ORE, 4).finishStage(new VictoryWonder(7))
		    .done(),
	    // Éphesos A
	    new WonderBuilder("wonder - 2.png", Resource.PAPYRUS)
		    .addCost(Resource.STONE, 2)
		    .finishStage(new VictoryWonder(3))
		    .addCost(Resource.WOOD, 2).finishStage(new GoldWonder(9))
		    .addCost(Resource.PAPYRUS, 2)
		    .finishStage(new VictoryWonder(7)).done(),
	    // Gizah A
	    new WonderBuilder("wonder - 3.png", Resource.STONE)
		    .addCost(Resource.STONE, 2)
		    .finishStage(new VictoryWonder(3))
		    .addCost(Resource.WOOD, 3)
		    .finishStage(new VictoryWonder(5))
		    .addCost(Resource.STONE, 4)
		    .finishStage(new VictoryWonder(7)).done(),
	    // Alexandria A
	    new WonderBuilder("wonder - 4.png", Resource.GLASS)
		    .addCost(Resource.STONE, 2)
		    .finishStage(new VictoryWonder(3)).addCost(Resource.ORE, 2)
		    .finishStage(new ResourceWonder(false))
		    .addCost(Resource.GLASS, 2)
		    .finishStage(new VictoryWonder(7)).done(),
	    // Rhódos B
	    new WonderBuilder("wonder - 5.png", Resource.ORE)
		    .addCost(Resource.STONE, 3)
		    .finishStage(new ArmyGoldANdVictory(3, 3))
		    .addCost(Resource.ORE, 4)
		    .finishStage(new ArmyGoldANdVictory(4, 4)).done(),
	    // Gizah B
	    new WonderBuilder("wonder - 6.png", Resource.STONE)
		    .addCost(Resource.WOOD, 2)
		    .finishStage(new VictoryWonder(3))
		    .addCost(Resource.STONE, 3)
		    .finishStage(new VictoryWonder(5))
		    .addCost(Resource.CLAY, 3)
		    .finishStage(new VictoryWonder(5))
		    .addCost(Resource.PAPYRUS, 1).addCost(Resource.STONE, 4)
		    .finishStage(new VictoryWonder(7)).done(), };
}
