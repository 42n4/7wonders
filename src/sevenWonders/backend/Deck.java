package sevenWonders.backend;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains all the cards in the game.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Deck {

    /**
     * Returns a shuffled deck containing all the cards for a set era and number of players.
     * Era must be between 1-3 inclusive, playerCount must be between 3-7 inclusive.
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
	    switch(playerCount) {
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

    private static void getEra1Players3Deck(List<Card> deck) {
	deck.add(Card.newBrownCard().name("Stone Pit").id(1).description("era1 - 1.png").produce(Resource.STONE ,1).build());
	deck.add(Card.newBlueCard().name("Theater").id(2).description("era1 - 2.png").postCards(69).points(2).build());
	deck.add(Card.newGreenCard().name("Scriptorium").id(3).description("era1 - 3.png").postCards(68, 54).cost(Resource.PAPYRUS, 1).science(Science.WRITING).build());
	deck.add(Card.newBrownCard().name("Clay Pit").id(4).description("era1 - 4.png").moneyCost(1).produce(Resource.CLAY, 1).produce(Resource.ORE, 1).build());
	deck.add(Card.newCostModifierCard().name("East Trading Post").id(5).description("era1 - 5.png").postCards(53).modifiedResources(Resource.CLAY, Resource.STONE, Resource.WOOD, Resource.ORE).modifyDirections(false, true).build());
	deck.add(Card.newBrownCard().name("Clay Pool").id(6).description("era1 - 6.png").produce(Resource.CLAY, 1).build());
	deck.add(Card.newGrayCard().name("Press").id(7).description("era1 - 7.png").produce(Resource.PAPYRUS, 1).build());
	deck.add(Card.newRedCard().name("Guard Tower").id(8).description("era1 - 8.png").cost(Resource.CLAY, 1).shields(1).build());
	deck.add(Card.newRedCard().name("Stockade").id(9).description("era1 - 9.png").cost(Resource.WOOD, 1).shields(1).build());
	deck.add(Card.newGrayCard().name("Loom").id(10).description("era1 - 10.png").produce(Resource.LOOM, 1).build());
	deck.add(Card.newGrayCard().name("Glassworks").id(11).description("era1 - 11.png").produce(Resource.GLASS, 1).build());
	deck.add(Card.newCostModifierCard().name("Marketplace").id(12).description("era1 - 12.png").postCards(51).modifiedResources(Resource.GLASS, Resource.LOOM, Resource.PAPYRUS).modifyDirections(true, true).build());
	deck.add(Card.newBrownCard().name("Ore Vein").id(13).description("era1 - 13.png").produce(Resource.ORE ,1).build());
	deck.add(Card.newBlueCard().name("Altar").id(14).description("era1 - 14.png").postCards(50).points(2).build());
	deck.add(Card.newGreenCard().name("Apothecary").id(15).description("era1 - 15.png").postCards(62, 70, 76).cost(Resource.LOOM, 1).science(Science.ASTRONOMY).build());
	deck.add(Card.newGreenCard().name("Workshop").id(16).description("era1 - 16.png").postCards(65, 60).cost(Resource.GLASS, 1).science(Science.MECHANICS).build());
	deck.add(Card.newBrownCard().name("Lumber Yard").id(17).description("era1 - 17.png").produce(Resource.WOOD,1).build());
	deck.add(Card.newRedCard().name("Barracks").id(18).description("era1 - 18.png").cost(Resource.ORE, 1).shields(1).build());
	deck.add(Card.newBlueCard().name("Baths").id(19).description("era1 - 19.png").cost(Resource.STONE, 1).postCards(52).points(3).build());
	deck.add(Card.newBrownCard().name("Timber Yard").id(20).description("era1 - 20.png").moneyCost(1).produce(Resource.STONE, 1).produce(Resource.WOOD,1).build());
	deck.add(Card.newCostModifierCard().name("West Trading Post").id(21).description("era1 - 21.png").postCards(53).modifiedResources(Resource.CLAY, Resource.STONE, Resource.WOOD, Resource.ORE).modifyDirections(true, false).build());
    }
    
    private static void getEra1Players4Deck(List<Card> deck) {
	deck.add(Card.newBrownCard().name("Lumber Yard").id(22).description("era1 - 17.png").produce(Resource.WOOD,1).build());
	deck.add(Card.newBrownCard().name("Excavation").id(23).description("era1 - 23.png").moneyCost(1).produce(Resource.STONE, 1).produce(Resource.CLAY, 1).build());
	deck.add(Card.newBrownCard().name("Ore Vein").id(24).description("era1 - 13.png").produce(Resource.ORE ,1).build());
	deck.add(Card.newBlueCard().name("Pawnshop").id(25).description("era1 - 25.png").points(3).build());
	deck.add(Card.newBuildingRewardCard().name("Tavern").id(26).description("era2 - 26.png").moneyReward(5).build());
	deck.add(Card.newGreenCard().name("Scriptorium").id(27).description("era1 - 3.png").postCards(68, 54).cost(Resource.PAPYRUS, 1).science(Science.WRITING).build());
	deck.add(Card.newRedCard().name("Guard Tower").id(28).description("era1 - 8.png").cost(Resource.CLAY, 1).shields(1).build());
    }
    
    private static void getEra1Players5Deck(List<Card> deck) {
	    //TODO add cards
    }
    
    private static void getEra1Players6Deck(List<Card> deck) {
	    //TODO add cards
    }
    
    private static void getEra1Players7Deck(List<Card> deck) {
	    //TODO add cards
    }

    private static void getEra2Players3Deck(List<Card> deck) {
	deck.add(Card.newBlueCard().name("Temple").id(50).description("era2 - 1.png").preCards(14).postCards(108).cost(Resource.WOOD, 1).cost(Resource.CLAY, 1).cost(Resource.GLASS, 1).points(3).build());
	deck.add(Card.newYellowResourceCard().name("Caravansery").id(51).description("era2 - 2.png").preCards(12).postCards(100).cost(Resource.WOOD, 2).produce(Resource.WOOD, 1).produce(Resource.STONE, 1).produce(Resource.ORE, 1).produce(Resource.CLAY, 1).build());
	deck.add(Card.newBlueCard().name("Aqueduct").id(52).description("era2 - 3.png").preCards(19).cost(Resource.STONE, 3).points(5).build());
	deck.add(Card.newYellowResourceCard().name("Forum").id(53).description("era2 - 4.png").preCards(5, 21).postCards(99, 119).cost(Resource.CLAY, 2).produce(Resource.GLASS, 1).produce(Resource.LOOM, 1).produce(Resource.PAPYRUS, 1).build());
	deck.add(Card.newGreenCard().name("Library").id(54).description("era2 - 5.png").preCards(3, 27).postCards(113, 107, 115).cost(Resource.STONE, 2).cost(Resource.LOOM, 1).science(Science.WRITING).build());
	deck.add(Card.newGrayCard().name("Glassworks").id(55).description("era2 - 6.png").produce(Resource.GLASS, 1).build());
	deck.add(Card.newBrownCard().name("Foundry").id(56).description("era2 - 7.png").moneyCost(1).produce(Resource.ORE, 2).build());
	deck.add(Card.newBuildingRewardCard().name("Vineyard").id(57).description("era2 - 8.png").rewardCard(BrownCard.class).neighbors(true).moneyReward(1).build());
	deck.add(Card.newGreenCard().name("School").id(58).description("era2 - 9.png").postCards(102, 112).cost(Resource.WOOD, 1).cost(Resource.PAPYRUS, 1).science(Science.WRITING).build());
	deck.add(Card.newGrayCard().name("Loom").id(59).description("era2 - 10.png").produce(Resource.LOOM, 1).build());
	deck.add(Card.newRedCard().name("Archery Range").id(60).description("era2 - 11.png").preCards(16).cost(Resource.WOOD, 2).cost(Resource.ORE, 1).shields(2).build());
	deck.add(Card.newBrownCard().name("Brickyard").id(61).description("era2 - 12.png").moneyCost(1).produce(Resource.CLAY, 2).build());
	deck.add(Card.newRedCard().name("Stables").id(62).description("era2 - 13.png").preCards(15).cost(Resource.CLAY, 1).cost(Resource.WOOD, 1).cost(Resource.ORE, 1).shields(2).build());
	deck.add(Card.newGrayCard().name("Press").id(63).description("era2 - 14.png").produce(Resource.PAPYRUS, 1).build());
	deck.add(Card.newBrownCard().name("Sawmill").id(64).description("era2 - 15.png").moneyCost(1).produce(Resource.WOOD, 2).build());
	deck.add(Card.newGreenCard().name("Laboratory").id(65).description("era2 - 16.png").preCards(16).postCards(114, 106).cost(Resource.CLAY, 2).cost(Resource.PAPYRUS, 1).science(Science.MECHANICS).build());
	deck.add(Card.newRedCard().name("Walls").id(66).description("era2 - 17.png").postCards(105).cost(Resource.STONE, 3).shields(2).build());
	deck.add(Card.newBrownCard().name("Quarry").id(67).description("era2 - 18.png").moneyCost(1).produce(Resource.STONE, 2).build());
	deck.add(Card.newBlueCard().name("Courthouse").id(68).description("era2 - 19.png").preCards(3, 27).cost(Resource.CLAY, 3).cost(Resource.LOOM, 1).points(4).build());
	deck.add(Card.newBlueCard().name("Statue").id(69).description("era2 - 20.png").preCards(2).postCards(103, 118).cost(Resource.ORE, 2).cost(Resource.WOOD, 1).points(4).build());
	deck.add(Card.newGreenCard().name("Dispensary").id(70).description("era2 - 21.png").preCards(15).postCards(110, 101).cost(Resource.ORE, 2).cost(Resource.GLASS, 1).science(Science.ASTRONOMY).build());
    }
    
    private static void getEra2Players4Deck(List<Card> deck) {
	deck.add(Card.newRedCard().name("Training Ground").id(71).description("era2 - 22.png").postCards(117).cost(Resource.ORE, 2).cost(Resource.WOOD, 1).shields(2).build());
	deck.add(Card.newBuildingRewardCard().name("Bazar").id(72).description("era2 - 23.png").rewardCard(GrayCard.class).neighbors(true).moneyReward(2).build());
	deck.add(Card.newBrownCard().name("Sawmill").id(73).description("era2 - 15.png").moneyCost(1).produce(Resource.WOOD, 2).build());
	deck.add(Card.newBrownCard().name("Foundry").id(74).description("era2 - 7.png").moneyCost(1).produce(Resource.ORE, 2).build());
	deck.add(Card.newBrownCard().name("Brickyard").id(75).description("era2 - 12.png").moneyCost(1).produce(Resource.CLAY, 2).build());
	deck.add(Card.newGreenCard().name("Dispensary").id(76).description("era2 - 21.png").preCards(15).postCards(110, 101).cost(Resource.ORE, 2).cost(Resource.GLASS, 1).science(Science.ASTRONOMY).build());
	deck.add(Card.newBrownCard().name("Quarry").id(67).description("era2 - 18.png").moneyCost(1).produce(Resource.STONE, 2).build());
    }
    
    private static void getEra2Players5Deck(List<Card> deck) {
	    //TODO add cards
    }
    
    private static void getEra2Players6Deck(List<Card> deck) {
	    //TODO add cards
    }
    
    private static void getEra2Players7Deck(List<Card> deck) {
	    //TODO add cards
    }
    
    /**
     * @param playerCount
     * @return
     */
    private static void getGuildsDeck(int playerCount, List<Card> returnDeck) {
    List<Card> deck = new LinkedList<>();
	deck.add(Card.newNeighborCardGuild().name("Philosophers Guild").id(139).description("guild - 1.png").cost(Resource.CLAY, 3).cost(Resource.PAPYRUS, 1).cost(Resource.LOOM, 1).rewardCard(GreenCard.class).pointsReward(1).build());
	deck.add(Card.newNeighborCardGuild().name("Traders Guild").id(140).description("guild - 2.png").cost(Resource.GLASS, 1).cost(Resource.LOOM, 1).cost(Resource.PAPYRUS, 1).rewardCard(YellowCard.class).pointsReward(1).build());
	deck.add(Card.newShipownersGuild().name("Shipowners Guild").id(141).description("guild - 3.png").cost(Resource.WOOD, 3).cost(Resource.GLASS, 1).cost(Resource.PAPYRUS, 1).build());
	deck.add(Card.newNeighborCardGuild().name("Spies Guild").id(142).description("guild - 4.png").cost(Resource.CLAY, 3).cost(Resource.GLASS, 1).rewardCard(RedCard.class).pointsReward(1).build());
	deck.add(Card.newBuildersGuild().name("Builders Guild").id(143).description("guild - 5.png").cost(Resource.STONE, 2).cost(Resource.CLAY, 2).cost(Resource.GLASS, 1).build());
	deck.add(Card.newNeighborCardGuild().name("Craftsmens Guild").id(144).description("guild - 6.png").cost(Resource.ORE, 2).cost(Resource.STONE, 2).rewardCard(GrayCard.class).pointsReward(2).build());
	deck.add(Card.newStrategistGuild().name("Strategist Guild").id(145).description("guild - 7.png").cost(Resource.ORE, 2).cost(Resource.STONE, 1).cost(Resource.LOOM, 1).build());
	deck.add(Card.newNeighborCardGuild().name("Workers Guild").id(146).description("guild - 8.png").cost(Resource.ORE, 2).cost(Resource.CLAY, 1).cost(Resource.STONE, 1).cost(Resource.WOOD, 1).rewardCard(BrownCard.class).pointsReward(1).build());
	deck.add(Card.newScienceGuild().name("Scientists Guild").id(147).description("guild - 9.png").cost(Resource.WOOD, 2).cost(Resource.ORE, 2).cost(Resource.PAPYRUS, 1).build());
	deck.add(Card.newNeighborCardGuild().name("Magistrates Guild").id(148).description("guild - 10.png").cost(Resource.WOOD, 3).cost(Resource.STONE, 1).cost(Resource.LOOM, 1).rewardCard(BlueCard.class).pointsReward(1).build());
	Collections.shuffle(deck);
	// Returns 5 cards for 3 players, 6 cards for 4 players,... 9 cards for 7 players.
	for (int i = 9; i > playerCount+1; i--) {
	    deck.remove(i);
	}
	returnDeck.addAll(deck);
    }
    
    private static void getEra3Players3Deck(List<Card> deck) {
    deck.add(Card.newBuildingRewardCard().name("Haven").id(99).description("era3 - 1.png").preCards(53).cost(Resource.WOOD, 1).cost(Resource.ORE, 1).cost(Resource.LOOM, 1).rewardCard(BrownCard.class).neighbors(false).moneyReward(1).pointsReward(1).build());
    deck.add(Card.newBuildingRewardCard().name("Lighthouse").id(100).description("era3 - 2.png").preCards(51).cost(Resource.STONE, 1).cost(Resource.GLASS, 1).rewardCard(YellowCard.class).neighbors(false).moneyReward(1).pointsReward(1).build());
    deck.add(Card.newWonderRewardCard().name("Arena").id(101).description("era3 - 3.png").preCards(70, 76).cost(Resource.STONE, 2).cost(Resource.ORE, 1).build());
    deck.add(Card.newGreenCard().name("Academy").id(102).description("era3 - 4.png").preCards(58).cost(Resource.STONE, 3).cost(Resource.GLASS, 1).science(Science.ASTRONOMY).build());
    deck.add(Card.newBlueCard().name("Gardens").id(103).description("era3 - 5.png").preCards(69).cost(Resource.CLAY, 2).cost(Resource.WOOD, 1).points(5).build());
    deck.add(Card.newRedCard().name("Arsenal").id(104).description("era3 - 6.png").cost(Resource.WOOD, 2).cost(Resource.ORE, 1).cost(Resource.LOOM, 1).shields(3).build());
    deck.add(Card.newRedCard().name("Fortifications").id(105).description("era3 - 7.png").preCards(66).cost(Resource.ORE, 3).cost(Resource.STONE, 1).shields(3).build());
    deck.add(Card.newRedCard().name("Siege Workshop").id(106).description("era3 - 8.png").preCards(65).cost(Resource.CLAY, 3).cost(Resource.WOOD, 1).shields(3).build());
    deck.add(Card.newGreenCard().name("University").id(107).description("era3 - 9.png").preCards(54).cost(Resource.WOOD, 2).cost(Resource.PAPYRUS, 1).cost(Resource.GLASS, 1).science(Science.WRITING).build());
    deck.add(Card.newBlueCard().name("Pantheon").id(108).description("era3 - 10.png").preCards(50).cost(Resource.CLAY, 2).cost(Resource.ORE, 1).cost(Resource.GLASS, 1).cost(Resource.PAPYRUS, 1).cost(Resource.LOOM, 1).points(7).build());
    deck.add(Card.newBlueCard().name("Townhall").id(109).description("era3 - 11.png").cost(Resource.STONE, 2).cost(Resource.ORE, 1).cost(Resource.GLASS, 1).points(6).build());
    deck.add(Card.newGreenCard().name("Lodge").id(110).description("era3 - 12.png").preCards(70, 76).cost(Resource.CLAY, 2).cost(Resource.PAPYRUS, 1).cost(Resource.LOOM, 1).science(Science.ASTRONOMY).build());
    deck.add(Card.newBlueCard().name("Palace").id(111).description("era3 - 13.png").cost(Resource.STONE, 1).cost(Resource.ORE, 1).cost(Resource.WOOD, 1).cost(Resource.CLAY, 1).cost(Resource.GLASS, 1).cost(Resource.PAPYRUS, 1).cost(Resource.LOOM, 1).points(8).build());
    deck.add(Card.newGreenCard().name("Study").id(112).description("era3 - 14.png").preCards(58).cost(Resource.WOOD, 1).cost(Resource.PAPYRUS, 1).cost(Resource.LOOM, 1).science(Science.MECHANICS).build());
    deck.add(Card.newBlueCard().name("Senate").id(113).description("era3 - 15.png").preCards(54).cost(Resource.WOOD, 2).cost(Resource.STONE, 1).cost(Resource.ORE, 1).points(6).build());
    deck.add(Card.newGreenCard().name("Observatory").id(114).description("era3 - 16.png").preCards(65).cost(Resource.ORE, 2).cost(Resource.GLASS, 1).cost(Resource.LOOM, 1).science(Science.MECHANICS).build());
    }
    
    private static void getEra3Players4Deck(List<Card> deck) {
	deck.add(Card.newGreenCard().name("University").id(115).description("era3 - 9.png").preCards(54).cost(Resource.WOOD, 2).cost(Resource.PAPYRUS, 1).cost(Resource.GLASS, 1).science(Science.WRITING).build());
	deck.add(Card.newRedCard().name("Arsenal").id(116).description("era3 - 6.png").cost(Resource.WOOD, 2).cost(Resource.ORE, 1).cost(Resource.LOOM, 1).shields(3).build());
	deck.add(Card.newRedCard().name("Circus").id(117).description("era3 - 19.png").preCards(71).cost(Resource.STONE, 3).cost(Resource.ORE, 1).shields(3).build());
	deck.add(Card.newBlueCard().name("Gardens").id(118).description("era3 - 5.png").preCards(69).cost(Resource.CLAY, 2).cost(Resource.WOOD, 1).points(5).build());
	deck.add(Card.newBuildingRewardCard().name("Haven").id(119).description("era3 - 1.png").preCards(53).cost(Resource.WOOD, 1).cost(Resource.ORE, 1).cost(Resource.LOOM, 1).rewardCard(BrownCard.class).neighbors(false).moneyReward(1).pointsReward(1).build());
	deck.add(Card.newBuildingRewardCard().name("Chamber of Commerce").id(120).description("era3 - 22.png").rewardCard(GrayCard.class).neighbors(false).moneyReward(2).pointsReward(2).build());
    }
    
    private static void getEra3Players5Deck(List<Card> deck) {
    //TODO add cards
    }
    
    private static void getEra3Players6Deck(List<Card> deck) {
    //TODO add cards
    }
    
    private static void getEra3Players7Deck(List<Card> deck) {
    //TODO add cards
    }
}
