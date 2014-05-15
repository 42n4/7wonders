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
	    for (int j = 3; j <= 7; j++) {
		entireDeck.addAll(Deck.GetDeck(i, j));
	    }
	}
	for (Card card : entireDeck) {
	    if (card.preCards == null && card.postCards == null) {
		continue;
	    }
	    int preCardCountDown = card.preCards != null ? card.preCards.length : 0;
	    int postCardCountDown = card.postCards != null ? card.postCards.length : 0;
	    for (Card otherCard : entireDeck) {
		if (card.preCards != null && checkPreCards(card, otherCard)) {
		    preCardCountDown--;
		}
		if (card.postCards != null && checkPostCards(card, otherCard)) {
		    postCardCountDown--;
		}
	    }
	    if (preCardCountDown != 0 || postCardCountDown != 0) {
		Assert.fail("Pre/Post card mismatch! id:"+card.id+" pre:"+preCardCountDown+ " post:"+postCardCountDown);
	    }
	}

    }

    private boolean checkPostCards(Card card, Card otherCard) {
	for (int i : card.postCards) {
	    if (otherCard.id == i) {
		if (otherCard.preCards != null) {
		    for (int j : otherCard.preCards) {
			if (j == card.id) {
			    return true;
			}
		    }
		}
		Assert.fail("Found that card " + otherCard.id
			+ " is a post-card of " + card.id
			+ " but has no pre-cards defined");
	    }
	}
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
		Assert.fail("Found that card " + otherCard.id
			+ " is a pre-card of " + card.id
			+ " but has no post-cards defined");
	    }
	}
	return false;
    }
}
