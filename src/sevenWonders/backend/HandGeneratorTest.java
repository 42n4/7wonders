package sevenWonders.backend;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class HandGeneratorTest {

    List<Player> players = new ArrayList<>();

    @Test
    public void test() {
	players.add(Player.randPlayer());
	players.add(Player.randPlayer());
	players.add(Player.randPlayer());
	List<Card> handCards = Deck.GetDeck(2, 3);
	for (; handCards.size() > 7;) {
	    handCards.remove(0);
	}
	Hand hand = HandGenerator.getHand(handCards, players, 1);
	System.out.println(hand.wonderPaymentOptions.size());
	Hand hand2 = HandGenerator.getHand(handCards, players, 0);
	System.out.println(hand2.wonderPaymentOptions.size());
    }
    
    // test for a private method
    // @Test
    // public void getPlayerProduceTest() {
    // List<Map<Resource, Integer>> produce =
    // HandGenerator.getPlayerProduce(players[0], false);
    // }
}
