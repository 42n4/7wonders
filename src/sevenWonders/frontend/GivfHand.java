package sevenWonders.frontend;

import sevenWonders.backend.Resource;
import sevenWonders.backend.Hand;
import sevenWonders.backend.Card;
import sevenWonders.backend.Deck;
import sevenWonders.backend.Hand.PaymentOption;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Testclass to try out WondersHandController and WondersHand.fxml.
 * Creates a dummy Hand.
 */
public class GivfHand {

    static Hand Givf() {
	Hand hand;
	HashMap<Card, List<PaymentOption>> cards = new HashMap<>();
	List<PaymentOption> wonderPaymentOptions;
	Deck deck = new Deck();
	List<Card> rawCards = Deck.GetDeck(1, 3);

	List<PaymentOption> list1 = new ArrayList<>();
	List<PaymentOption> list2 = new ArrayList<>();
	List<PaymentOption> list3 = new ArrayList<>();

	Map<Resource, Integer> r1 = new EnumMap<Resource, Integer>(
		Resource.class);
	Map<Resource, Integer> r2 = new EnumMap<Resource, Integer>(
		Resource.class);
	Map<Resource, Integer> r3 = new EnumMap<Resource, Integer>(
		Resource.class);

	r1.put(Resource.GLASS, 1);
	r2.put(Resource.STONE, 2);
	r2.put(Resource.WOOD, 1);

	PaymentOption p1 = new PaymentOption(2, 0, 0, r1, r3);
	PaymentOption p2 = new PaymentOption(0, 1, 0, r3, r3);
	PaymentOption p3 = new PaymentOption(1, 0, 6, r1, r2);
	PaymentOption p4 = new PaymentOption(0, 0, 0, r3, r3);

	list1.add(p2);
	list1.add(p1);
	list1.add(p3);
	list2.add(p2);
	list3.add(p4);

	cards.put(rawCards.get(0), list1);
	cards.put(rawCards.get(1), list2);
	cards.put(rawCards.get(2), list3);
	cards.put(rawCards.get(3), list1);
	cards.put(rawCards.get(4), list2);
	cards.put(rawCards.get(5), list3);
	cards.put(rawCards.get(6), list3);
	
	hand = new Hand(cards, list1);

	return hand;
    }
}
