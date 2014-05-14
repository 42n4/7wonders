package sevenWonders.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Acts as dealer. Creates hands of cards for each player per era and
 * playernumber. Also keeps track of the discardpile.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class DeckDealer {
    private List<Card> discardPile = new ArrayList<>();

    /**
     * Creates the players hands and returns them.
     * 
     * @param era
     * @param playerCount
     * @return
     */
    List<Card>[] getHands(int era, int playerCount) {
	List<Card>[] hands;
	{
	    @SuppressWarnings("unchecked")
	    // for this declaration only
	    List<Card>[] temphands = new List[playerCount];
	    hands = temphands;
	}
	for (int i = 0; i < playerCount; i++) {
	    hands[i] = new LinkedList<>();
	}
	List<Card> deck = Deck.GetDeck(era, playerCount);

	// Shuffles the deck and deals the cards in a traditional fashion
	//Collections.shuffle(deck);
	for (int i = 0; i < deck.size(); i++) {
	    hands[i % playerCount].add(deck.get(i));
	}
	return hands;
    }

    /**
     * Returns a copy of the discard pile.
     * 
     * @return
     */
    public List<Card> getDiscardPile() {
	return new ArrayList<>(discardPile);
    }

    /**
     * Adds the card to the discard pile. Does not reward any money.
     * 
     * @param c
     */
    void discardCard(Card c) {
	discardPile.add(c);
    }

    /**
     * Removes the passed card from the discard pile.
     * 
     * @param c
     */
    void resurrectCard(Card c) {
	if (!discardPile.remove(c))
	    throw new IllegalArgumentException(
		    "Could not resurrect card from discard pile");
    }
}
