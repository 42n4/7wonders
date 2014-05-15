package sevenWonders.backend;

import static org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for Deck.
 */
public class DeckTest {

    @Test
    public void preAndPostCardsTest() {
	List<Card> entireDeck = new ArrayList<>();
	for (int i = 1; i <= 3; i++) {
	    entireDeck.addAll(Deck.GetDeck(i, 7));
	}

	for (Card card : entireDeck) {
	    if (card.preCards == null && card.postCards == null) {
		continue;
	    }
	    int preCardCountDown = card.preCards != null ? card.preCards.length
		    : 0;
	    int postCardCountDown = card.postCards != null ? card.postCards.length
		    : 0;
	    for (Card otherCard : entireDeck) {
		if (card.preCards != null && checkPreCards(card, otherCard)) {
		    preCardCountDown--;
		}
		if (card.postCards != null && checkPostCards(card, otherCard)) {
		    postCardCountDown--;
		}
	    }
	    if (preCardCountDown != 0 || postCardCountDown != 0) {
		Assert.fail("Pre/Post card mismatch! id:" + card.id + " pre:"
			+ preCardCountDown + " post:" + postCardCountDown);
	    }
	}

    }

    private boolean checkPostCards(Card card, Card otherCard) {
	// for every postCard card has
	for (int i : card.postCards) {
	    // otherCard is a postcard to card
	    if (otherCard.id == i) {
		// otherCard has preCards
		if (otherCard.preCards != null) {
		    // for every preCard otherCard has
		    for (int j : otherCard.preCards) {
			// if otherCard's preCard matches card
			if (j == card.id) {
			    return true;
			}
		    }
		}
		// otherCard is preCard to card, but card is not a postCard to
		// otherCard
		Assert.fail("Found that card " + otherCard.id + " " + otherCard
			+ " is a post-card of " + card.id + " " + card
			+ " but has no pre-cards defined");
	    }
	}
	// otherCard was not a postCard of card
	return false;
    }

    private boolean checkPreCards(Card card, Card otherCard) {
	for (int i : card.preCards) {
	    if (otherCard.id == i) {
		if (otherCard.postCards != null) {
		    for (int j : otherCard.postCards) {
			if (j == card.id) {
			    return true;
			}
		    }
		}
		Assert.fail("Found that card " + otherCard.id + " " + otherCard
			+ " is a pre-card of " + card.id + " " + card
			+ " but has no post-cards defined");
	    }
	}
	return false;
    }

    @Test
    public void handSizeTest() {
	for (int i = 1; i <= 3; i++) {
	    for (int j = 3; j <= 7; j++) {
		System.out.println(j);
		List<Card> deck = Deck.GetDeck(i, j);
		assertEquals(7*j, deck.size());
	    }
	}
    }
}
