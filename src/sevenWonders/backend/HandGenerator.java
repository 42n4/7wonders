package sevenWonders.backend;

import sevenWonders.backend.Hand.PaymentOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Creates hands specific to a player.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class HandGenerator {

    static Hand getHand(List<Card> cards, Player[] players, int playerIndex) {
	final Player player = players[playerIndex];
	final Player left = players[modulo(playerIndex - 1, players.length)];
	final Player right = players[modulo(playerIndex + 1, players.length)];
	HashMap<Card, List<PaymentOption>> cardHand = new HashMap<>();
	List<PaymentOption> wonderOptions = new ArrayList<>();
	for (Card card : cards) {
	    cardHand.put(card,
		    getOptions(card.cost, card.moneyCost, player, left, right));
	}
	wonderOptions = getWonderOptions(player, left, right);

	return Hand(cardHand, wonderOptions);
	return null;
    }

    /**
     * Given a buildingcost, a players produce, and the produce of the players
     * neighbors, creates a list of available payment options.
     * 
     * @param cost
     *            to build the card/wonder
     * @param moneyCost
     *            of the building, wonders are always 0.
     * @param player
     * @param left
     *            player
     * @param right
     *            player
     * @return
     */
    static List<PaymentOption> getOptions(Map<Resource, Integer> cost,
	    int moneyCost, Player player, Player left, Player right) {
	final List<Map<Resource, Integer>> playerProduce = getPlayerProduce(
		player, false);
	final List<Map<Resource, Integer>> leftProduce = getPlayerProduce(left,
		true);
	final List<Map<Resource, Integer>> rightProduce = getPlayerProduce(
		right, true);
	final List<Map<Resource, Integer>> shoppingList = reduceMap(cost,
		playerProduce);
	List<PaymentOption> options = new ArrayList<>();

	if (moneyCost > 0 && player.getMoney() < moneyCost) {
	    return options;
	}
	if (cost.isEmpty() || shoppingList.isEmpty()) { // noCost
	    options.add(Hand.PaymentOption.newBuilder().selfCost(moneyCost)
		    .build());
	    return options;
	}
	return getNeighborPaymentPlan(player, moneyCost, shoppingList,
		rightProduce, leftProduce);
    }

    /**
     * Is only called if resources must be bought from neighbours. Genrates a
     * list of payment options for the resources left to buy.
     * 
     * @param shoppingList
     *            resources that needs to be bought
     * @param rightProduce
     * @param leftProduce
     * @return
     */
    private static List<PaymentOption> getNeighborPaymentPlan(Player player,
	    int moneyCost, List<Map<Resource, Integer>> shoppingList,
	    List<Map<Resource, Integer>> rightProduce,
	    List<Map<Resource, Integer>> leftProduce) {
	final Map<Resource, Integer> leftPrices = getNeighbourPrices(player,
		true);
	final Map<Resource, Integer> rightPrices = getNeighbourPrices(player,
		false);
	for (int i = 0; i < shoppingList.size(); i++)
	    Resource[] buyOptions = new Resource[shoppingList.size()]; //TODO
	    for (Resource r : shoppingList.get(i).keySet()) {
		List<Map<Resource, int[]>> mew;
	    }
	Map<Resource, Integer> leftBuys = new EnumMap<>(Resource.class);
	Map<Resource, Integer> rightBuys = new EnumMap<>(Resource.class);

	// TODO do the calculation magic and generate a list of PaymentOptions

	return null;
    }

    /**
     * Creates a Map detailing the price for a player to buy each resource from
     * a neighbour.
     * 
     * @param player
     * @param left
     *            if true calculates prices for left neighbour, if false for
     *            right neighbour
     * @return
     */
    private static Map<Resource, Integer> getNeighbourPrices(Player player,
	    boolean left) {
	Map<Resource, Integer> prices = new EnumMap<Resource, Integer>(
		Resource.class);
	for (Resource r : Resource.values()) {
	    prices.put(r, modifiedPrice(player, r, left));
	}
	return prices;
    }

    /**
     * Checks the price for a player to buy a resource from a neighbour.
     * 
     * @param player
     * @param resource
     * @param left
     *            if true calculates price for left neighbour, if false for
     *            right neighbour
     * @return
     */
    private static int modifiedPrice(Player player, Resource resource,
	    boolean left) {
	final List<Card> playerBuildings = player.getBuildings();
	for (Card building : playerBuildings) {
	    if (building instanceof CostModifierCard) {
		Resource[] res = ((CostModifierCard) building).getResources();
		for (Resource r : res) {
		    if (r == resource)
			if (left
				&& ((CostModifierCard) building)
					.getDirections()[0]) {
			    return 1;
			} else if (!left
				&& ((CostModifierCard) building)
					.getDirections()[1]) {
			    return 1;
			}
		}
	    }
	}
	return 2;
    }

    /**
     * Checks if the shoppinglist is empty.
     * 
     * @param shoppingList
     *            the resources needed
     * @return
     */
    @Deprecated
    private static boolean noCost(List<Map<Resource, Integer>> shoppingList) {
	for (Map<Resource, Integer> map : shoppingList) {
	    for (Resource r : map.keySet()) {
		if (map.get(r) > 0) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Creates a list for each option of resources left to buy based on how the
     * player's own produced resources can be used to pay for building the card.
     * 
     * @param cost
     *            of building card
     * @param playerProduce
     *            is player currently produced resources
     * @return
     */
    private static List<Map<Resource, Integer>> reduceMap(
	    Map<Resource, Integer> cost,
	    List<Map<Resource, Integer>> playerProduce) {
	List<Map<Resource, Integer>> shoppingList = new ArrayList<>();
	for (int i = 0; i < playerProduce.size(); i++) {
	    Map<Resource, Integer> leftOvers = new EnumMap<Resource, Integer>(
		    Resource.class);
	    for (Resource r : Resource.values()) {
		if (cost.containsKey(r)) {
		    int leftToBuy = cost.get(r) - playerProduce.get(i).get(r);
		    if (leftToBuy > 0) {
			leftOvers.put(r, leftToBuy);
		    }
		}
	    }
	    if (!leftOvers.isEmpty()) {
		shoppingList.add(leftOvers);
	    }
	}
	return shoppingList;
    }

    /**
     * Creates a list of PaymentOptions for building the next step on the
     * players wonder.
     * 
     * @param player
     * @param left
     *            player
     * @param right
     *            player
     * @return
     */
    static List<PaymentOption> getWonderOptions(Player player, Player left,
	    Player right) {
	final EnumMap<Resource, Integer> cost = player.wonder.cost[player.wonder
		.getCurrentLevel() - 1];
	return getOptions(cost, 0, player, left, right);
    }

    static private int modulo(int x, int m) {
	final int result = x % m;
	return (result >= 0) ? result : result + m;
    }

    /**
     * Makes a list of all possible combinations of players production. If
     * player has no ResourceCards that has multiple produce options the list is
     * of size 1. Can count either all resources or only sellable resources.
     * 
     * @param player
     * @param onlySellable
     *            specifies if only sellable resources should be counted.
     * @return
     */
    private static List<Map<Resource, Integer>> getPlayerProduce(Player player,
	    boolean onlySellable) {
	List<Map<Resource, Integer>> produce = new ArrayList<>();
	produce.add(new EnumMap<Resource, Integer>(Resource.class));
	produce.get(1).put(player.wonder.produce, 1);
	final List<ResourceCard> playerBuildings = getPlayerResorceBuildings(
		player, onlySellable);
	for (ResourceCard building : playerBuildings) {
	    Map<Resource, Integer> buildingProduce = building.getResources();
	    final Resource[] res = building
		    .getResources()
		    .keySet()
		    .toArray(
			    new Resource[building.getResources().keySet()
				    .size()]);

	    // If only one option for production for building
	    if (res.length == 1) {
		// adds the new produce to every current option
		for (int i = 0; i < produce.size(); i++) {
		    final int current = produce.get(i).get(res[0]);
		    produce.get(i).put(res[0],
			    current + buildingProduce.get(res[0]));
		}

		// If there's multiple options for production for building
	    } else if (res.length > 1) {
		final int produceSize = produce.size();
		for (int i = 0; i < produceSize * (res.length - 1); i++) {
		    produce.add(produce.get(i % produceSize));
		}
		for (int i = 0; i < produce.size(); i++) {
		    final int current = produce.get(i).get(res[i % res.length]);
		    produce.get(i).put(res[i % res.length],
			    current + buildingProduce.get(res[i % res.length]));
		}
	    }
	}
	return produce;
    }

    /**
     * Makes a list of a players ResourceBuildings. Can count either all
     * resources or only sellable resources.
     * 
     * @param player
     * @param onlySellable
     *            specifies if only sellable resources should be counted.
     * @return
     */
    private static List<ResourceCard> getPlayerResorceBuildings(Player player,
	    boolean onlySellable) {
	final List<Card> playerBuildings = player.getBuildings();
	List<ResourceCard> playerResourceBuildings = new ArrayList<>();
	for (Card building : playerBuildings) {
	    if (building instanceof ResourceCard && onlySellable ? ((ResourceCard) building)
		    .isSellable() : true) {
		playerResourceBuildings.add((ResourceCard) building);
	    }
	}
	return playerResourceBuildings;
    }
}
