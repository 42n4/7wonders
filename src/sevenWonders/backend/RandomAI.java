package sevenWonders.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import sevenWonders.backend.Action.ActionType;
import sevenWonders.backend.Hand.PaymentOption;

public class RandomAI implements ClientConnection {
    private static Random r = new Random();
    
    private int playerIndex;
    private int chanceToPlayCard, chanceToPlayWonder;
    private Action nextAction;
    
    
    /**
     * 
     * @param chanceToPlayCard
     * @param chanceToPlayWonder
     */
    public RandomAI(int playerIndex, double chanceToPlayCard, double chanceToPlayWonder) {
	this.playerIndex = playerIndex;
	this.chanceToPlayCard = (int)(10*chanceToPlayCard);
	this.chanceToPlayWonder = (int)(10*chanceToPlayWonder);
    }

    @Override
    public boolean SendGameState(GameState gameState, Hand hand) {
	Player thisPlayer = gameState.players.get(playerIndex);
	int playWonder = chanceToPlayWonder;
	int playCard = chanceToPlayCard;
	
	ArrayList<Card> playable = new ArrayList<>(hand.cards.size());
	for (Iterator<Card> it = hand.cards.keySet().iterator(); it.hasNext();) {
	    Card tempC = it.next();
	    if (hand.cards.get(tempC).size() != 0) {
		playable.add(tempC);
	    }
	}
	
	
	int chance = 1000;
        if (thisPlayer.wonder.getCurrentLevel() == thisPlayer.wonder.stages.length
        	|| hand.wonderPaymentOptions.size() == 0) {
            // Can't build on wonder. Is already built or no way to pay for it.
            // Removes chance of playing it.
            playWonder = 1000;
        }
        if (playable.size() == 0) {
            playCard = 1000;
        }

        int roll = r.nextInt(chance);
        if (chanceToPlayCard <= roll && roll < chanceToPlayCard+playWonder) {
            // Builds on a wonder
            int cardToPlay = r.nextInt(hand.cards.size());
            Card c = new ArrayList<Card>(hand.cards.keySet()).get(cardToPlay);
            int paymentOptionIndex = r.nextInt(hand.wonderPaymentOptions.size());
            nextAction = new Action(c, ActionType.BUILD_WONDER, paymentOptionIndex);
        }
        else if (roll < playCard) {
	    // Builds a card
	    Card cardToPlay = playable.get(r.nextInt(playable.size()));
	    int paymentOptionIndex = r.nextInt(hand.cards.get(cardToPlay).size());
	    nextAction = new Action(cardToPlay, ActionType.BUILD_BUILDING, paymentOptionIndex);
	}
        else {
            // Discards a card
            int cardToPlay = r.nextInt(hand.cards.size());
            Card c = new ArrayList<Card>(hand.cards.keySet()).get(cardToPlay);
            nextAction = new Action(c, ActionType.DISCARD_CARD, -1);
        }
	return true;
    }
    
    @Override
    public Action GetAction() {
        return nextAction;
    }
}
