package sevenWonders.backend;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains all the cards in the game. Deck has the static method GetDeck that
 * returns the deck for an era dependent on which era it is and the number of
 * players in the game.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Deck {

    /**
     * Returns a shuffled deck containing all the cards for a set era and number
     * of players. Era must be between 1-3 inclusive, playerCount must be
     * between 3-7 inclusive.
     * 
     * @param era
     * @param playerCount
     * @return
     */
    public static List<Card> GetDeck(int era, int playerCount) {
	if (era < 1 || era > 3 || playerCount < 3 || playerCount > 7) {
	    throw new IllegalArgumentException("Invalid era or playercount.");
	}
	List<Card> deck = new LinkedList<>();
	if (era == 1) {
	    switch (playerCount) {
	    case 7:
		getEra1Players7Deck(deck);
		playerCount--;
	    case 6:
		getEra1Players6Deck(deck);
		playerCount--;
	    case 5:
		getEra1Players5Deck(deck);
		playerCount--;
	    case 4:
		getEra1Players4Deck(deck);
		playerCount--;
	    case 3:
		getEra1Players3Deck(deck);
	    }
	} else if (era == 2) {
	    switch (playerCount) {
	    case 7:
		getEra2Players7Deck(deck);
		playerCount--;
	    case 6:
		getEra2Players6Deck(deck);
		playerCount--;
	    case 5:
		getEra2Players5Deck(deck);
		playerCount--;
	    case 4:
		getEra2Players4Deck(deck);
		playerCount--;
	    case 3:
		getEra2Players3Deck(deck);
	    }
	} else if (era == 3) {
	    getGuildsDeck(playerCount, deck);
	    switch (playerCount) {
	    case 7:
		getEra3Players7Deck(deck);
		playerCount--;
	    case 6:
		getEra3Players6Deck(deck);
		playerCount--;
	    case 5:
		getEra3Players5Deck(deck);
		playerCount--;
	    case 4:
		getEra3Players4Deck(deck);
		playerCount--;
	    case 3:
		getEra3Players3Deck(deck);
	    }
	}
	Collections.shuffle(deck);
	return deck;
    }

    /**
     * Adds the 21 cards required for playing the first era for 3 players.
     * Contains cards 1-21.
     * 
     * @param deck
     *            adds cards 1-21 to the deck.
     */
    private static void getEra1Players3Deck(List<Card> deck) {
	deck.add(Card.newBrownCard()
		.name("Stone Pit")
		.id(1)
		.description("era1 - 1.png")
		.produce(Resource.STONE, 1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Theater")
		.id(2)
		.description("era1 - 2.png")
		.postCards(69, 94)
		.points(2)
		.build());
	deck.add(Card.newGreenCard()
		.name("Scriptorium")
		.id(3)
		.description("era1 - 3.png")
		.postCards(68, 54, 84, 86)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newBrownCard()
		.name("Clay Pit")
		.id(4)
		.description("era1 - 4.png")
		.moneyCost(1)
		.produce(Resource.CLAY, 1)
		.produce(Resource.ORE, 1)
		.build());
	deck.add(Card
		.newCostModifierCard()
		.name("East Trading Post")
		.id(5)
		.description("era1 - 5.png")
		.postCards(53, 88, 92)
		.modifiedResources(Resource.CLAY, Resource.STONE,
			Resource.WOOD, Resource.ORE)
		.modifyDirections(false, true)
		.build());
	deck.add(Card.newBrownCard()
		.name("Clay Pool")
		.id(6)
		.description("era1 - 6.png")
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Press")
		.id(7)
		.description("era1 - 7.png")
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Guard Tower")
		.id(8)
		.description("era1 - 8.png")
		.cost(Resource.CLAY, 1)
		.shields(1)
		.build());
	deck.add(Card.newRedCard()
		.name("Stockade")
		.id(9)
		.description("era1 - 9.png")
		.cost(Resource.WOOD, 1)
		.shields(1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Loom")
		.id(10)
		.description("era1 - 10.png")
		.produce(Resource.LOOM, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Glassworks")
		.id(11)
		.description("era1 - 11.png")
		.produce(Resource.GLASS, 1)
		.build());
	deck.add(Card
		.newCostModifierCard()
		.name("Marketplace")
		.id(12)
		.description("era1 - 12.png")
		.postCards(51, 83, 85)
		.modifiedResources(Resource.GLASS, Resource.LOOM,
			Resource.PAPYRUS)
		.modifyDirections(true, true)
		.build());
	deck.add(Card.newBrownCard()
		.name("Ore Vein")
		.id(13)
		.description("era1 - 13.png")
		.produce(Resource.ORE, 1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Altar")
		.id(14)
		.description("era1 - 14.png")
		.postCards(50, 87)
		.points(2)
		.build());
	deck.add(Card.newGreenCard()
		.name("Apothecary")
		.id(15)
		.description("era1 - 15.png")
		.postCards(62, 70, 76, 78)
		.cost(Resource.LOOM, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newGreenCard()
		.name("Workshop")
		.id(16)
		.description("era1 - 16.png")
		.postCards(65, 60, 80, 89)
		.cost(Resource.GLASS, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newBrownCard()
		.name("Lumber Yard")
		.id(17)
		.description("era1 - 17.png")
		.produce(Resource.WOOD, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Barracks")
		.id(18)
		.description("era1 - 18.png")
		.cost(Resource.ORE, 1)
		.shields(1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Baths")
		.id(19)
		.description("era1 - 19.png")
		.cost(Resource.STONE, 1)
		.postCards(52, 97)
		.points(3)
		.build());
	deck.add(Card.newBrownCard()
		.name("Timber Yard")
		.id(20)
		.description("era1 - 20.png")
		.moneyCost(1)
		.produce(Resource.STONE, 1)
		.produce(Resource.WOOD, 1)
		.build());
	deck.add(Card
		.newCostModifierCard()
		.name("West Trading Post")
		.id(21)
		.description("era1 - 21.png")
		.postCards(53, 88, 92)
		.modifiedResources(Resource.CLAY, Resource.STONE,
			Resource.WOOD, Resource.ORE)
		.modifyDirections(true, false)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the first era for 4 players.
     * Contains cards 22-28.
     * 
     * @param deck
     *            adds cards 22-28 to the deck.
     */
    private static void getEra1Players4Deck(List<Card> deck) {
	deck.add(Card.newBrownCard()
		.name("Lumber Yard")
		.id(22)
		.description("era1 - 17.png")
		.produce(Resource.WOOD, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Excavation")
		.id(23)
		.description("era1 - 23.png")
		.moneyCost(1)
		.produce(Resource.STONE, 1)
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Ore Vein")
		.id(24)
		.description("era1 - 13.png")
		.produce(Resource.ORE, 1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Pawnshop")
		.id(25)
		.description("era1 - 25.png")
		.points(3)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Tavern")
		.id(26)
		.description("era1 - 26.png")
		.moneyReward(5)
		.build());
	deck.add(Card.newGreenCard()
		.name("Scriptorium")
		.id(27)
		.description("era1 - 3.png")
		.postCards(68, 54, 84, 86)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newRedCard()
		.name("Guard Tower")
		.id(28)
		.description("era1 - 8.png")
		.cost(Resource.CLAY, 1)
		.shields(1)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the first era for 5 players.
     * Contains cards 29-35.
     * 
     * @param deck
     *            adds cards 29-35 to the deck.
     */
    private static void getEra1Players5Deck(List<Card> deck) {
	deck.add(Card.newBuildingRewardCard()
		.name("Tavern")
		.id(29)
		.description("era1 - 29.png")
		.moneyReward(5)
		.build());
	deck.add(Card.newBlueCard()
		.name("Altar")
		.id(30)
		.description("era1 - 14.png")
		.postCards(50, 87)
		.points(2)
		.build());
	deck.add(Card.newGreenCard()
		.name("Apothecary")
		.id(31)
		.description("era1 - 15.png")
		.postCards(62, 70, 76, 78)
		.cost(Resource.LOOM, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newBrownCard()
		.name("Forest Cave")
		.id(32)
		.description("era1 - 32.png")
		.moneyCost(1)
		.produce(Resource.WOOD, 1)
		.produce(Resource.ORE, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Stone Pit")
		.id(33)
		.description("era1 - 33.png")
		.produce(Resource.STONE, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Clay Pool")
		.id(34)
		.description("era1 - 6.png")
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Barracks")
		.id(35)
		.description("era1 - 18.png")
		.cost(Resource.ORE, 1)
		.shields(1)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the first era for 6 players.
     * Contains cards 36-42.
     * 
     * @param deck
     *            adds cards 36-42 to the deck.
     */
    private static void getEra1Players6Deck(List<Card> deck) {
	deck.add(Card.newCostModifierCard()
		.name("Marketplace")
		.id(36)
		.description("era1 - 12.png")
		.postCards(51, 83, 85)
		.modifiedResources(Resource.GLASS, Resource.LOOM,
			Resource.PAPYRUS)
		.modifyDirections(true, true)
		.build());
	deck.add(Card.newBlueCard()
		.name("Theater")
		.id(37)
		.description("era1 - 2.png")
		.postCards(69, 94)
		.points(2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Mine")
		.id(38)
		.description("era1 - 38.png")
		.moneyCost(1)
		.produce(Resource.STONE, 1)
		.produce(Resource.ORE, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Glassworks")
		.id(39)
		.description("era1 - 11.png")
		.produce(Resource.GLASS, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Mine")
		.id(40)
		.description("era1 - 40.png")
		.moneyCost(1)
		.produce(Resource.WOOD, 1)
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Press")
		.id(41)
		.description("era1 - 7.png")
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Loom")
		.id(42)
		.description("era1 - 10.png")
		.produce(Resource.LOOM, 1)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the first era for 7 players.
     * Contains cards 43-49.
     * 
     * @param deck
     *            adds cards 43-49 to the deck.
     */
    private static void getEra1Players7Deck(List<Card> deck) {
	deck.add(Card.newBuildingRewardCard()
		.name("Tavern")
		.id(43)
		.description("era1 - 26.png")
		.moneyReward(5)
		.build());
	deck.add(Card.newRedCard()
		.name("Stockade")
		.id(44)
		.description("era1 - 9.png")
		.cost(Resource.WOOD, 1)
		.shields(1)
		.build());
	deck.add(Card
		.newCostModifierCard()
		.name("West Trading Post")
		.id(45)
		.description("era1 - 21.png")
		.postCards(53, 88, 92)
		.modifiedResources(Resource.CLAY, Resource.STONE,
			Resource.WOOD, Resource.ORE)
		.modifyDirections(true, false)
		.build());
	deck.add(Card.newBlueCard()
		.name("Baths")
		.id(46)
		.description("era1 - 19.png")
		.cost(Resource.STONE, 1)
		.postCards(52, 97)
		.points(3)
		.build());
	deck.add(Card.newGreenCard()
		.name("Workshop")
		.id(47)
		.description("era1 - 16.png")
		.postCards(65, 60, 80, 89)
		.cost(Resource.GLASS, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newCostModifierCard()
		.name("East Trading Post")
		.id(48)
		.description("era1 - 5.png")
		.postCards(53, 88, 92)
		.modifiedResources(Resource.CLAY, Resource.STONE,
			Resource.WOOD, Resource.ORE)
		.modifyDirections(false, true)
		.build());
	deck.add(Card.newBlueCard()
		.name("Pawnshop")
		.id(49)
		.description("era1 - 25.png")
		.points(3)
		.build());
    }

    /**
     * Adds the 21 cards required for playing the second era for 3 players.
     * Contains cards 50-70.
     * 
     * @param deck
     *            adds cards 50-70 to the deck.
     */
    private static void getEra2Players3Deck(List<Card> deck) {
	deck.add(Card.newBlueCard()
		.name("Temple")
		.id(50)
		.description("era2 - 1.png")
		.preCards(14, 30)
		.postCards(108, 131)
		.cost(Resource.WOOD, 1)
		.cost(Resource.CLAY, 1)
		.cost(Resource.GLASS, 1)
		.points(3)
		.build());
	deck.add(Card.newYellowResourceCard()
		.name("Caravansery")
		.id(51)
		.description("era2 - 2.png")
		.preCards(12, 36)
		.postCards(100, 132)
		.cost(Resource.WOOD, 2)
		.produce(Resource.WOOD, 1)
		.produce(Resource.STONE, 1)
		.produce(Resource.ORE, 1)
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Aqueduct")
		.id(52)
		.description("era2 - 3.png")
		.preCards(19, 46)
		.cost(Resource.STONE, 3)
		.points(5)
		.build());
	deck.add(Card.newYellowResourceCard()
		.name("Forum")
		.id(53)
		.description("era2 - 4.png")
		.preCards(5, 21, 45, 48)
		.postCards(99, 119)
		.cost(Resource.CLAY, 2)
		.produce(Resource.GLASS, 1)
		.produce(Resource.LOOM, 1)
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newGreenCard()
		.name("Library")
		.id(54)
		.description("era2 - 5.png")
		.preCards(3, 27)
		.postCards(113, 107, 115, 126)
		.cost(Resource.STONE, 2)
		.cost(Resource.LOOM, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newGrayCard()
		.name("Glassworks")
		.id(55)
		.description("era2 - 6.png")
		.produce(Resource.GLASS, 1).
		build());
	deck.add(Card.newBrownCard()
		.name("Foundry")
		.id(56)
		.description("era2 - 7.png")
		.moneyCost(1)
		.produce(Resource.ORE, 2)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Vineyard")
		.id(57)
		.description("era2 - 8.png")
		.rewardCard(BrownCard.class)
		.neighbors(true)
		.moneyReward(1)
		.build());
	deck.add(Card.newGreenCard()
		.name("School")
		.id(58)
		.description("era2 - 9.png")
		.postCards(102, 112, 124, 138)
		.cost(Resource.WOOD, 1)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newGrayCard()
		.name("Loom")
		.id(59)
		.description("era2 - 10.png")
		.produce(Resource.LOOM, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Archery Range")
		.id(60)
		.description("era2 - 11.png")
		.preCards(16, 47)
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 1)
		.shields(2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Brickyard")
		.id(61)
		.description("era2 - 12.png")
		.moneyCost(1)
		.produce(Resource.CLAY, 2)
		.build());
	deck.add(Card.newRedCard()
		.name("Stables")
		.id(62)
		.description("era2 - 13.png")
		.preCards(15, 31)
		.cost(Resource.CLAY, 1)
		.cost(Resource.WOOD, 1)
		.cost(Resource.ORE, 1)
		.shields(2)
		.build());
	deck.add(Card.newGrayCard()
		.name("Press")
		.id(63)
		.description("era2 - 14.png")
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newBrownCard()
		.name("Sawmill")
		.id(64)
		.description("era2 - 15.png")
		.moneyCost(1)
		.produce(Resource.WOOD, 2)
		.build());
	deck.add(Card.newGreenCard()
		.name("Laboratory")
		.id(65)
		.description("era2 - 16.png")
		.preCards(16, 47)
		.postCards(114, 106, 122, 137)
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newRedCard()
		.name("Walls")
		.id(66)
		.description("era2 - 17.png")
		.postCards(105, 135)
		.cost(Resource.STONE, 3)
		.shields(2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Quarry")
		.id(67)
		.description("era2 - 18.png")
		.moneyCost(1)
		.produce(Resource.STONE, 2)
		.build());
	deck.add(Card.newBlueCard()
		.name("Courthouse")
		.id(68)
		.description("era2 - 19.png")
		.preCards(3, 27)
		.cost(Resource.CLAY, 3)
		.cost(Resource.LOOM, 1)
		.points(4)
		.build());
	deck.add(Card.newBlueCard()
		.name("Statue")
		.id(69)
		.description("era2 - 20.png")
		.preCards(2, 37)
		.postCards(103, 118)
		.cost(Resource.ORE, 2)
		.cost(Resource.WOOD, 1)
		.points(4)
		.build());
	deck.add(Card.newGreenCard()
		.name("Dispensary")
		.id(70)
		.description("era2 - 21.png")
		.preCards(15, 31)
		.postCards(110, 101, 123, 128, 134)
		.cost(Resource.ORE, 2)
		.cost(Resource.GLASS, 1)
		.science(Science.ASTRONOMY)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the second era for 4 players.
     * Contains cards 71-77.
     * 
     * @param deck
     *            adds cards 71-77 to the deck.
     */
    private static void getEra2Players4Deck(List<Card> deck) {
	deck.add(Card.newRedCard()
		.name("Training Ground")
		.id(71)
		.description("era2 - 22.png")
		.postCards(117, 125, 127)
		.cost(Resource.ORE, 2)
		.cost(Resource.WOOD, 1)
		.shields(2)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Bazar")
		.id(72)
		.description("era2 - 23.png")
		.rewardCard(GrayCard.class)
		.neighbors(true)
		.moneyReward(2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Sawmill")
		.id(73)
		.description("era2 - 15.png")
		.moneyCost(1)
		.produce(Resource.WOOD, 2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Foundry")
		.id(74)
		.description("era2 - 7.png")
		.moneyCost(1)
		.produce(Resource.ORE, 2)
		.build());
	deck.add(Card.newBrownCard()
		.name("Brickyard")
		.id(75)
		.description("era2 - 12.png")
		.moneyCost(1)
		.produce(Resource.CLAY, 2)
		.build());
	deck.add(Card.newGreenCard()
		.name("Dispensary")
		.id(76)
		.description("era2 - 21.png")
		.preCards(15, 31)
		.postCards(110, 101, 123, 128, 134)
		.cost(Resource.ORE, 2)
		.cost(Resource.GLASS, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newBrownCard()
		.name("Quarry")
		.id(77)
		.description("era2 - 18.png")
		.moneyCost(1)
		.produce(Resource.STONE, 2)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the second era for 5 players.
     * Contains cards 78-84.
     * 
     * @param deck
     *            adds cards 78-84 to the deck.
     */
    private static void getEra2Players5Deck(List<Card> deck) {
	deck.add(Card.newRedCard().
		name("Stables")
		.id(78)
		.description("era2 - 13.png")
		.preCards(15, 31)
		.cost(Resource.CLAY, 1)
		.cost(Resource.WOOD, 1)
		.cost(Resource.ORE, 1)
		.shields(2)
		.build());
	deck.add(Card.newGrayCard()
		.name("Press")
		.id(79)
		.description("era2 - 14.png")
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newGreenCard()
		.name("Laboratory")
		.id(80)
		.description("era2 - 16.png")
		.preCards(16, 47)
		.postCards(114, 106, 122, 137)
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newGrayCard()
		.name("Glassworks")
		.id(81)
		.description("era2 - 6.png")
		.produce(Resource.GLASS, 1)
		.build());
	deck.add(Card.newGrayCard()
		.name("Loom")
		.id(82)
		.description("era2 - 10.png")
		.produce(Resource.LOOM, 1)
		.build());
	deck.add(Card.newYellowResourceCard()
		.name("Caravansery")
		.id(83)
		.description("era2 - 2.png")
		.preCards(12, 36)
		.postCards(100, 132)
		.cost(Resource.WOOD, 2)
		.produce(Resource.WOOD, 1)
		.produce(Resource.STONE, 1)
		.produce(Resource.ORE, 1)
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newBlueCard()
		.name("Courthouse")
		.id(84)
		.description("era2 - 19.png")
		.preCards(3, 27)
		.cost(Resource.CLAY, 3)
		.cost(Resource.LOOM, 1)
		.points(4)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the second era for 6 players.
     * Contains cards 85-91.
     * 
     * @param deck
     *            adds cards 85-91 to the deck.
     */
    private static void getEra2Players6Deck(List<Card> deck) {
	deck.add(Card.newYellowResourceCard()
		.name("Caravansery")
		.id(85)
		.description("era2 - 2.png")
		.preCards(12, 36)
		.postCards(100, 132)
		.cost(Resource.WOOD, 2)
		.produce(Resource.WOOD, 1)
		.produce(Resource.STONE, 1)
		.produce(Resource.ORE, 1)
		.produce(Resource.CLAY, 1)
		.build());
	deck.add(Card.newGreenCard()
		.name("Library")
		.id(86)
		.description("era2 - 5.png")
		.preCards(3, 27)
		.postCards(113, 107, 115, 126)
		.cost(Resource.STONE, 2)
		.cost(Resource.LOOM, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newBlueCard()
		.name("Temple")
		.id(87)
		.description("era2 - 1.png")
		.preCards(14, 30)
		.postCards(108, 131)
		.cost(Resource.WOOD, 1)
		.cost(Resource.CLAY, 1)
		.cost(Resource.GLASS, 1)
		.points(3)
		.build());
	deck.add(Card.newYellowResourceCard()
		.name("Forum")
		.id(88)
		.description("era2 - 4.png")
		.preCards(5, 21, 45, 48)
		.postCards(99, 119)
		.cost(Resource.CLAY, 2)
		.produce(Resource.GLASS, 1)
		.produce(Resource.LOOM, 1)
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Archery Range")
		.id(89)
		.description("era2 - 11.png")
		.preCards(16, 47)
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 1)
		.shields(2)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Vineyard")
		.id(90)
		.description("era2 - 8.png")
		.rewardCard(BrownCard.class)
		.neighbors(true)
		.moneyReward(1)
		.build());
	deck.add(Card.newRedCard()
		.name("Training Ground")
		.id(91)
		.description("era2 - 22.png")
		.postCards(117, 125, 127)
		.cost(Resource.ORE, 2)
		.cost(Resource.WOOD, 1)
		.shields(2)
		.build());
    }

    /**
     * Adds the extra 7 cards required for playing the second era for 7 players.
     * Contains cards 92-98.
     * 
     * @param deck
     *            adds cards 92-98 to the deck.
     */
    private static void getEra2Players7Deck(List<Card> deck) {
	deck.add(Card.newYellowResourceCard()
		.name("Forum")
		.id(92)
		.description("era2 - 4.png")
		.preCards(5, 21, 45, 48)
		.postCards(99, 119)
		.cost(Resource.CLAY, 2)
		.produce(Resource.GLASS, 1)
		.produce(Resource.LOOM, 1)
		.produce(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Walls")
		.id(93)
		.description("era2 - 17.png")
		.postCards(105, 135)
		.cost(Resource.STONE, 3)
		.shields(2)
		.build());
	deck.add(Card.newBlueCard()
		.name("Statue")
		.id(94)
		.description("era2 - 20.png")
		.preCards(2, 37)
		.postCards(103, 118)
		.cost(Resource.ORE, 2)
		.cost(Resource.WOOD, 1)
		.points(4)
		.build());
	deck.add(Card.newGreenCard()
		.name("School")
		.id(95)
		.description("era2 - 9.png")
		.postCards(102, 112, 124, 138)
		.cost(Resource.WOOD, 1)
		.cost(Resource.PAPYRUS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newRedCard()
		.name("Training Ground")
		.id(96)
		.description("era2 - 22.png")
		.postCards(117, 125, 127)
		.cost(Resource.ORE, 2)
		.cost(Resource.WOOD, 1)
		.shields(2)
		.build());
	deck.add(Card.newBlueCard()
		.name("Aqueduct")
		.id(97)
		.description("era2 - 3.png")
		.preCards(19, 46)
		.cost(Resource.STONE, 3)
		.points(5)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Bazar")
		.id(98)
		.description("era2 - 23.png")
		.rewardCard(GrayCard.class)
		.neighbors(true)
		.moneyReward(2)
		.build());
    }

    /**
     * There is 10 guilds in 7 Wonders. 5 guilds are used for 3 players, 6
     * guilds for 4 players,... 9 guilds for 7 players. The guilds are randomly
     * selected and added to the main deck. The remaining guild cards will be
     * discarded and not included in the game.
     * 
     * @param playerCount
     *            numbers of players in the game
     * @param returnDeck
     *            adds guild cards to the deck dependent on number of players
     */
    private static void getGuildsDeck(int playerCount, List<Card> returnDeck) {
	List<Card> deck = new LinkedList<>();
	deck.add(Card.newNeighborCardGuild()
		.name("Philosophers Guild")
		.id(139)
		.description("guild - 1.png")
		.cost(Resource.CLAY, 3)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.rewardCard(GreenCard.class)
		.pointsReward(1)
		.build());
	deck.add(Card.newNeighborCardGuild()
		.name("Traders Guild")
		.id(140)
		.description("guild - 2.png")
		.cost(Resource.GLASS, 1)
		.cost(Resource.LOOM, 1)
		.cost(Resource.PAPYRUS, 1)
		.rewardCard(YellowCard.class)
		.pointsReward(1)
		.build());
	deck.add(Card.newShipownersGuild()
		.name("Shipowners Guild")
		.id(141)
		.description("guild - 3.png")
		.cost(Resource.WOOD, 3)
		.cost(Resource.GLASS, 1)
		.cost(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newNeighborCardGuild()
		.name("Spies Guild")
		.id(142)
		.description("guild - 4.png")
		.cost(Resource.CLAY, 3)
		.cost(Resource.GLASS, 1)
		.rewardCard(RedCard.class)
		.pointsReward(1)
		.build());
	deck.add(Card.newBuildersGuild()
		.name("Builders Guild")
		.id(143)
		.description("guild - 5.png")
		.cost(Resource.STONE, 2)
		.cost(Resource.CLAY, 2)
		.cost(Resource.GLASS, 1)
		.build());
	deck.add(Card.newNeighborCardGuild()
		.name("Craftsmens Guild")
		.id(144)
		.description("guild - 6.png")
		.cost(Resource.ORE, 2)
		.cost(Resource.STONE, 2)
		.rewardCard(GrayCard.class)
		.pointsReward(2)
		.build());
	deck.add(Card.newStrategistGuild()
		.name("Strategist Guild")
		.id(145)
		.description("guild - 7.png")
		.cost(Resource.ORE, 2)
		.cost(Resource.STONE, 1)
		.cost(Resource.LOOM, 1)
		.build());
	deck.add(Card.newNeighborCardGuild()
		.name("Workers Guild")
		.id(146)
		.description("guild - 8.png")
		.cost(Resource.ORE, 2)
		.cost(Resource.CLAY, 1)
		.cost(Resource.STONE, 1)
		.cost(Resource.WOOD, 1)
		.rewardCard(BrownCard.class)
		.pointsReward(1)
		.build());
	deck.add(Card.newScienceGuild()
		.name("Scientists Guild")
		.id(147)
		.description("guild - 9.png")
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 2)
		.cost(Resource.PAPYRUS, 1)
		.build());
	deck.add(Card.newNeighborCardGuild()
		.name("Magistrates Guild")
		.id(148)
		.description("guild - 10.png")
		.cost(Resource.WOOD, 3)
		.cost(Resource.STONE, 1)
		.cost(Resource.LOOM, 1)
		.rewardCard(BlueCard.class)
		.pointsReward(1)
		.build());
	
	Collections.shuffle(deck);
	// Adds 5 cards for 3 players, 6 cards for 4 players,... 9 cards for
	// 7 players.
	for (int i = 0; i < playerCount + 2; i++) {
	    returnDeck.add(deck.get(i));
	}
    }

    /**
     * Adds the 16 cards required for playing the third era for 3 players.
     * Contains cards 99-114.
     * 
     * @param deck
     *            adds cards 99-114 to the deck.
     */
    private static void getEra3Players3Deck(List<Card> deck) {
	deck.add(Card.newBuildingRewardCard()
		.name("Haven")
		.id(99)
		.description("era3 - 1.png")
		.preCards(53, 88, 92)
		.cost(Resource.WOOD, 1)
		.cost(Resource.ORE, 1)
		.cost(Resource.LOOM, 1)
		.rewardCard(BrownCard.class)
		.neighbors(false)
		.moneyReward(1)
		.pointsReward(1)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Lighthouse")
		.id(100)
		.description("era3 - 2.png")
		.preCards(51, 83, 85)
		.cost(Resource.STONE, 1)
		.cost(Resource.GLASS, 1)
		.rewardCard(YellowCard.class)
		.neighbors(false)
		.moneyReward(1)
		.pointsReward(1)
		.build());
	deck.add(Card.newWonderRewardCard()
		.name("Arena")
		.id(101)
		.description("era3 - 3.png")
		.preCards(70, 76)
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.build());
	deck.add(Card.newGreenCard()
		.name("Academy")
		.id(102)
		.description("era3 - 4.png")
		.preCards(58, 95)
		.cost(Resource.STONE, 3)
		.cost(Resource.GLASS, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newBlueCard()
		.name("Gardens")
		.id(103)
		.description("era3 - 5.png")
		.preCards(69, 94)
		.cost(Resource.CLAY, 2)
		.cost(Resource.WOOD, 1)
		.points(5)
		.build());
	deck.add(Card.newRedCard()
		.name("Arsenal")
		.id(104)
		.description("era3 - 6.png")
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.LOOM, 1)
		.shields(3)
		.build());
	deck.add(Card.newRedCard()
		.name("Fortifications")
		.id(105)
		.description("era3 - 7.png")
		.preCards(66, 93)
		.cost(Resource.ORE, 3)
		.cost(Resource.STONE, 1)
		.shields(3)
		.build());
	deck.add(Card.newRedCard()
		.name("Siege Workshop")
		.id(106)
		.description("era3 - 8.png")
		.preCards(65, 80)
		.cost(Resource.CLAY, 3)
		.cost(Resource.WOOD, 1)
		.shields(3)
		.build());
	deck.add(Card.newGreenCard()
		.name("University")
		.id(107)
		.description("era3 - 9.png")
		.preCards(54, 86)
		.cost(Resource.WOOD, 2)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.GLASS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newBlueCard()
		.name("Pantheon")
		.id(108)
		.description("era3 - 10.png")
		.preCards(50, 87)
		.cost(Resource.CLAY, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.GLASS, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.points(7)
		.build());
	deck.add(Card.newBlueCard()
		.name("Townhall")
		.id(109)
		.description("era3 - 11.png")
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.GLASS, 1)
		.points(6)
		.build());
	deck.add(Card.newGreenCard()
		.name("Lodge")
		.id(110)
		.description("era3 - 12.png")
		.preCards(70, 76)
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newBlueCard()
		.name("Palace")
		.id(111)
		.description("era3 - 13.png")
		.cost(Resource.STONE, 1)
		.cost(Resource.ORE, 1)
		.cost(Resource.WOOD, 1)
		.cost(Resource.CLAY, 1)
		.cost(Resource.GLASS, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.points(8)
		.build());
	deck.add(Card.newGreenCard()
		.name("Study")
		.id(112)
		.description("era3 - 14.png")
		.preCards(58, 95)
		.cost(Resource.WOOD, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newBlueCard()
		.name("Senate")
		.id(113)
		.description("era3 - 15.png")
		.preCards(54, 86)
		.cost(Resource.WOOD, 2)
		.cost(Resource.STONE, 1)
		.cost(Resource.ORE, 1)
		.points(6)
		.build());
	deck.add(Card.newGreenCard()
		.name("Observatory")
		.id(114)
		.description("era3 - 16.png")
		.preCards(65, 80)
		.cost(Resource.ORE, 2)
		.cost(Resource.GLASS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.MECHANICS)
		.build());
    }

    /**
     * Adds the extra 6 cards required for playing the third era for 4 players.
     * Contains cards 115-120.
     * 
     * @param deck
     *            adds cards 115-120 to the deck.
     */
    private static void getEra3Players4Deck(List<Card> deck) {
	deck.add(Card.newGreenCard()
		.name("University")
		.id(115)
		.description("era3 - 9.png")
		.preCards(54, 86)
		.cost(Resource.WOOD, 2)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.GLASS, 1)
		.science(Science.WRITING)
		.build());
	deck.add(Card.newRedCard()
		.name("Arsenal")
		.id(116)
		.description("era3 - 6.png")
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.LOOM, 1)
		.shields(3)
		.build());
	deck.add(Card.newRedCard()
		.name("Circus")
		.id(117)
		.description("era3 - 19.png")
		.preCards(71, 91, 96)
		.cost(Resource.STONE, 3)
		.cost(Resource.ORE, 1)
		.shields(3)
		.build());
	deck.add(Card.newBlueCard()
		.name("Gardens")
		.id(118)
		.description("era3 - 5.png")
		.preCards(69, 94)
		.cost(Resource.CLAY, 2)
		.cost(Resource.WOOD, 1)
		.points(5)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Haven")
		.id(119)
		.description("era3 - 1.png")
		.preCards(53, 88, 92)
		.cost(Resource.WOOD, 1)
		.cost(Resource.ORE, 1)
		.cost(Resource.LOOM, 1)
		.rewardCard(BrownCard.class)
		.neighbors(false)
		.moneyReward(1)
		.pointsReward(1)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Chamber of Commerce")
		.id(120)
		.description("era3 - 22.png")
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.rewardCard(GrayCard.class)
		.neighbors(false)
		.moneyReward(2)
		.pointsReward(2)
		.build());
    }

    /**
     * Adds the extra 6 cards required for playing the third era for 5 players.
     * Contains cards 121-126.
     * 
     * @param deck
     *            adds cards 121-126 to the deck.
     */
    private static void getEra3Players5Deck(List<Card> deck) {
	deck.add(Card.newBlueCard()
		.name("Townhall")
		.id(121)
		.description("era3 - 11.png")
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.GLASS, 1)
		.points(6)
		.build());
	deck.add(Card.newRedCard()
		.name("Siege Workshop")
		.id(122)
		.description("era3 - 8.png")
		.preCards(65, 80)
		.cost(Resource.CLAY, 3)
		.cost(Resource.WOOD, 1)
		.shields(3)
		.build());
	deck.add(Card.newWonderRewardCard()
		.name("Arena")
		.id(123)
		.description("era3 - 3.png")
		.preCards(70, 76)
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.build());
	deck.add(Card.newGreenCard()
		.name("Study")
		.id(124)
		.description("era3 - 14.png")
		.preCards(58, 95)
		.cost(Resource.WOOD, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newRedCard()
		.name("Circus")
		.id(125)
		.description("era3 - 19.png")
		.preCards(71, 91, 96)
		.cost(Resource.STONE, 3)
		.cost(Resource.ORE, 1)
		.shields(3)
		.build());
	deck.add(Card.newBlueCard()
		.name("Senate")
		.id(126)
		.description("era3 - 15.png")
		.preCards(54, 86)
		.cost(Resource.WOOD, 2)
		.cost(Resource.STONE, 1)
		.cost(Resource.ORE, 1)
		.points(6)
		.build());
    }

    /**
     * Adds the extra 6 cards required for playing the third era for 6 players.
     * Contains cards 127-132.
     * 
     * @param deck
     *            adds cards 127-132 to the deck.
     */
    private static void getEra3Players6Deck(List<Card> deck) {
	deck.add(Card.newRedCard()
		.name("Circus")
		.id(127)
		.description("era3 - 19.png")
		.preCards(71, 91, 96)
		.cost(Resource.STONE, 3)
		.cost(Resource.ORE, 1)
		.shields(3)
		.build());
	deck.add(Card.newGreenCard()
		.name("Lodge")
		.id(128)
		.description("era3 - 12.png")
		.preCards(70, 76)
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.ASTRONOMY)
		.build());
	deck.add(Card.newBlueCard()
		.name("Townhall")
		.id(129)
		.description("era3 - 11.png")
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.GLASS, 1)
		.points(6)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Chamber of Commerce")
		.id(130)
		.description("era3 - 22.png")
		.cost(Resource.CLAY, 2)
		.cost(Resource.PAPYRUS, 1)
		.rewardCard(GrayCard.class)
		.neighbors(false)
		.moneyReward(2)
		.pointsReward(2)
		.build());
	deck.add(Card.newBlueCard()
		.name("Pantheon")
		.id(131)
		.description("era3 - 10.png")
		.preCards(50, 87)
		.cost(Resource.CLAY, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.GLASS, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.points(7)
		.build());
	deck.add(Card.newBuildingRewardCard()
		.name("Lighthouse")
		.id(132)
		.description("era3 - 2.png")
		.preCards(51, 83, 85)
		.cost(Resource.STONE, 1)
		.cost(Resource.GLASS, 1)
		.rewardCard(YellowCard.class)
		.neighbors(false)
		.moneyReward(1)
		.pointsReward(1)
		.build());
    }

    /**
     * Adds the extra 6 cards required for playing the third era for 4 players.
     * Contains cards 133-138.
     * 
     * @param deck
     *            adds cards 133-138 to the deck.
     */
    private static void getEra3Players7Deck(List<Card> deck) {
	deck.add(Card.newRedCard()
		.name("Arsenal")
		.id(133)
		.description("era3 - 6.png")
		.cost(Resource.WOOD, 2)
		.cost(Resource.ORE, 1)
		.cost(Resource.LOOM, 1)
		.shields(3)
		.build());
	deck.add(Card.newWonderRewardCard()
		.name("Arena")
		.id(134)
		.description("era3 - 3.png")
		.preCards(70, 76)
		.cost(Resource.STONE, 2)
		.cost(Resource.ORE, 1)
		.build());
	deck.add(Card.newRedCard()
		.name("Fortifications")
		.id(135)
		.description("era3 - 7.png")
		.preCards(66, 93)
		.cost(Resource.ORE, 3)
		.cost(Resource.STONE, 1)
		.shields(3)
		.build());
	deck.add(Card.newBlueCard()
		.name("Palace")
		.id(136)
		.description("era3 - 13.png")
		.cost(Resource.STONE, 1)
		.cost(Resource.ORE, 1)
		.cost(Resource.WOOD, 1)
		.cost(Resource.CLAY, 1)
		.cost(Resource.GLASS, 1)
		.cost(Resource.PAPYRUS, 1)
		.cost(Resource.LOOM, 1)
		.points(8)
		.build());
	deck.add(Card.newGreenCard()
		.name("Observatory")
		.id(137)
		.description("era3 - 16.png")
		.preCards(65, 80)
		.cost(Resource.ORE, 2)
		.cost(Resource.GLASS, 1)
		.cost(Resource.LOOM, 1)
		.science(Science.MECHANICS)
		.build());
	deck.add(Card.newGreenCard()
		.name("Academy")
		.id(138)
		.description("era3 - 4.png")
		.preCards(58, 95)
		.cost(Resource.STONE, 3)
		.cost(Resource.GLASS, 1)
		.science(Science.ASTRONOMY)
		.build());
    }
}
