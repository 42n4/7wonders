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
 * Creates hands specific to a player. It currently only gives you the cheapest
 * paymentOptions.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class HandGenerator {

    /**
     * Creates a Hand object for a specific player containing the cards sent.
     * The paymentOptions available will be the cheapest ones.
     * 
     * @param cards
     *            the cards that should be in the player's hand
     * @param players
     *            all of the players in the game
     * @param playerIndex
     *            at which index the player is at
     * @return
     */
    static Hand getHand(List<Card> cards, List<Player> players, int playerIndex) {
	final Player player = players.get(playerIndex);
	final Player left = players
		.get(modulo(playerIndex - 1, players.size()));
	final Player right = players
		.get(modulo(playerIndex + 1, players.size()));
	HashMap<Card, List<PaymentOption>> cardHand = new HashMap<>();
	List<PaymentOption> wonderOptions = new ArrayList<>();
	for (Card card : cards) {
	    // Checks if a card with same name is already built
	    boolean isDuplicateCard = false;
	    for (Card building : player.getBuildings()) {
		if (building.name.equals(card.name)) {
		    isDuplicateCard = true;
		}
	    }
	    if (isDuplicateCard) {
		cardHand.put(card, new ArrayList<PaymentOption>());
	    } else {
		cardHand.put(
			card,
			getOptions(card.cost, card.moneyCost, player, left,
				right));
	    }
	}
	wonderOptions = getWonderOptions(player, left, right);
	for (Card card : cardHand.keySet()) {
	    cardHand.put(card, rinse(cardHand.get(card)));
	}
	wonderOptions = rinse(wonderOptions);
	return new Hand(cardHand, wonderOptions);
    }

    /**
     * Denerates a hand out of the given cards. Cards with the same name as
     * already built cards for the player will not be buildable. All other cards
     * will have the paymentOption to build for free.
     * 
     * @param cards
     * @param player
     * @return
     */
    static Hand getFreeHand(List<Card> cards, Player player) {
	HashMap<Card, List<PaymentOption>> cardHand = new HashMap<>();
	List<PaymentOption> wonderOptions = new ArrayList<>();
	for (Card card : cards) {
	    // Checks if a card with same name is already built
	    boolean isDuplicateCard = false;
	    for (Card building : player.getBuildings()) {
		if (building.name.equals(card.name)) {
		    isDuplicateCard = true;
		}
	    }
	    if (!isDuplicateCard) {
		List<PaymentOption> option = new ArrayList<>();
		option.add(Hand.PaymentOption.newBuilder().freeBuild(true)
			.build());
		cardHand.put(card, option);
	    }
	}
	return new Hand(cardHand, wonderOptions);
    }

    /**
     * Given a buildingcost, a players produce, and the produce of the players
     * neighbours, creates a list of available payment options for a specific
     * card.
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
    private static List<PaymentOption> getOptions(Map<Resource, Integer> cost,
	    int moneyCost, Player player, Player left, Player right) {
	final List<Map<Resource, Integer>> playerProduce = getPlayerProduce(
		player, false);
	final List<Map<Resource, Integer>> shoppingList = reduceMap(cost,
		playerProduce);
	List<PaymentOption> options = new ArrayList<>();

	// cant afford the selfcost
	if (moneyCost > 0 && player.getMoney() < moneyCost) {
	    return options;
	}
	// can build at no cost other than selfcost
	if (cost.isEmpty() || shoppingList.isEmpty()) {
	    options.add(Hand.PaymentOption.newBuilder().selfCost(moneyCost)
		    .build());
	    return options;
	}
	// Resources needs to be bought.
	final List<Map<Resource, Integer>> leftProduce = getPlayerProduce(left,
		true);
	final List<Map<Resource, Integer>> rightProduce = getPlayerProduce(
		right, true);
	return getNeighborPaymentPlan(player, moneyCost, shoppingList,
		rightProduce, leftProduce);
    }

    /**
     * Is only called if resources needed to build a card must be bought from
     * neighbours. Generates a list of payment options for the resources left to
     * buy.
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
	List<PaymentOption> options = new ArrayList<>();
	// for each mapoption in shoppingList
	// for each combination of listentries in right and left Produce
	// get options
	for (int s = 0; s < shoppingList.size(); s++) {
	    for (int l = 0; l < leftProduce.size(); l++) {
		for (int r = 0; r < rightProduce.size(); r++) {
		    getSpecificNeighborPayment(options, shoppingList.get(s),
			    player, moneyCost, leftProduce.get(l),
			    rightProduce.get(r), leftPrices, rightPrices);
		}
	    }
	}
	return options;
    }

    /**
     * This is where the final calculation magic happens. Currently it only
     * calculates the cheapest options available.
     * 
     * @param options
     * @param map
     * @param map2
     * @param map3
     * @param leftPrices
     * @param rightPrices
     */
    private static void getSpecificNeighborPayment(List<PaymentOption> options,
	    Map<Resource, Integer> shoppingList, Player player, int moneyCost,
	    Map<Resource, Integer> leftP, Map<Resource, Integer> rightP,
	    Map<Resource, Integer> leftPrices,
	    Map<Resource, Integer> rightPrices) {
	// TODO change to generate multiple paymentoptions instead of only the
	// cheapest one.
	Map<Resource, Integer> leftProduce = new HashMap<Resource, Integer>(
		leftP);
	Map<Resource, Integer> rightProduce = new HashMap<Resource, Integer>(
		rightP);
	boolean enoughResources = true;
	int leftCost = 0;
	int rightCost = 0;
	Map<Resource, Integer> leftBuys = new EnumMap<>(Resource.class);
	Map<Resource, Integer> rightBuys = new EnumMap<>(Resource.class);
	for (Resource r : shoppingList.keySet()) {
	    int numberToBuy = shoppingList.get(r);
	    for (; numberToBuy > 0;)
		if (rightPrices.get(r) < leftPrices.get(r)
			&& rightProduce.containsKey(r)) {
		    int count = 0;
		    if (rightBuys.containsKey(r)) {
			count = rightBuys.get(r);
		    }
		    rightBuys.put(r, count + 1);
		    rightProduce.put(r, rightProduce.get(r) - 1);
		    rightCost += rightPrices.get(r);
		    numberToBuy--;
		} else if (leftProduce.containsKey(r)) {
		    int count = 0;
		    if (leftBuys.containsKey(r)) {
			count = leftBuys.get(r);
		    }
		    leftBuys.put(r, count + 1);
		    leftProduce.put(r, leftProduce.get(r) - 1);
		    leftCost += leftPrices.get(r);
		    numberToBuy--;
		} else if (rightProduce.containsKey(r)) {
		    int count = 0;
		    if (rightBuys.containsKey(r)) {
			count = rightBuys.get(r);
		    }
		    rightBuys.put(r, count + 1);
		    rightProduce.put(r, rightProduce.get(r) - 1);
		    rightCost += rightPrices.get(r);
		    numberToBuy--;
		} else {
		    // Not enough resources available from neighbours
		    numberToBuy = 0;
		    enoughResources = false;
		}
	}
	// If the purchase is possible, add to options
	if (enoughResources
		&& leftCost + rightCost + moneyCost <= player.getMoney()) {
	    Hand.PaymentOption.Builder builder = Hand.PaymentOption
		    .newBuilder();
	    builder.selfCost(moneyCost).leftCost(leftCost).rightCost(rightCost);
	    for (Resource r : leftBuys.keySet()) {
		builder.leftResource(r, leftBuys.get(r));
	    }
	    for (Resource r : rightBuys.keySet()) {
		builder.rightResource(r, rightBuys.get(r));
	    }
	    options.add(builder.build());
	}

	// List<Map<Resource, int[]>> mew;
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
	    for (Resource r : cost.keySet()) {
		if (playerProduce.get(i).containsKey(r)) {
		    int leftToBuy = cost.get(r) - playerProduce.get(i).get(r);
		    if (leftToBuy > 0) {
			leftOvers.put(r, leftToBuy);
		    }
		} else {
		    leftOvers.put(r, cost.get(r));
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
    private static List<PaymentOption> getWonderOptions(Player player,
	    Player left, Player right) {
	final Map<Resource, Integer> cost = player.wonder.cost[player.wonder
		.getCurrentLevel()];
	return getOptions(cost, 0, player, left, right);
    }

    private static int modulo(int x, int m) {
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
	produce.get(0).put(player.wonder.produce, 1);
	final List<ResourceCard> playerBuildings = getPlayerResorceBuildings(
		player, onlySellable);

	// for each resourcecard the player has built
	for (ResourceCard building : playerBuildings) {
	    Map<Resource, Integer> buildingProduce = building.getResources();

	    // an array with the resources the building can produce, if it's
	    // longer than one then it can produce one of the types of resources
	    // in the array.
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
		    int current = 0;
		    if (produce.get(i).containsKey(res[0])) {
			current = produce.get(i).get(res[0]);
		    }
		    produce.get(i).put(res[0],
			    current + buildingProduce.get(res[0]));
		}

		// If there's multiple options for production for a building
	    } else if (res.length > 1) {
		final int produceSize = produce.size();
		for (int i = 0; i < produceSize * (res.length - 1); i++) {
		    // Create enough duplicates of the current maps to contain
		    // all options
		    produce.add(new EnumMap<Resource, Integer>(produce.get(i
			    % produceSize)));
		}
		for (int i = 0; i < produce.size(); i++) {
		    int current = 0;
		    if (produce.get(i).containsKey(res[i % res.length])) {
			current = produce.get(i).get(res[i % res.length]);
		    }
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
	    if (building instanceof ResourceCard) {
		if (!onlySellable) {
		    playerResourceBuildings.add((ResourceCard) building);
		} else if (((ResourceCard) building).isSellable()) {
		    playerResourceBuildings.add((ResourceCard) building);
		}
	    }
	}
	return playerResourceBuildings;
    }

    /**
     * @param wonderOptions
     * @return
     */
    private static List<PaymentOption> rinse(List<PaymentOption> options) {
	options.removeAll(Collections.singleton(null));
	List<PaymentOption> cleanOptions = new ArrayList<>();
	for (PaymentOption o : options) {
	    boolean isDuplicate = false;
	    for (PaymentOption newO : cleanOptions) {
		if (o.selfCost == newO.selfCost && o.leftCost == newO.leftCost
			&& o.rightCost == newO.rightCost) {
		    isDuplicate = true;
		}
	    }
	    if (!isDuplicate) {
		cleanOptions.add(o);
	    }
	}
	return cleanOptions;
    }
}
