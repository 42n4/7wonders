package sevenWonders.backend;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Hand represents a hand of cards in 7wonders. Each round a hand is sent to a
 * player, they use one card from the hand and then the remaining cards in the
 * hand is used to make a new hand for the next player. A hand can have 1-7
 * cards in it. Each card has a hashmap representing options on how to pay for
 * the card if you decide to build it and a list representing options how to pay
 * if you decide to build a wonder with the card.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Hand {
    public final HashMap<Card, List<PaymentOption>> cards;
    public final List<PaymentOption> wonderPaymentOptions;

    public Hand(HashMap<Card, List<PaymentOption>> cards,
	    List<PaymentOption> wonderPaymentOptions) {
	this.cards = cards;
	this.wonderPaymentOptions = wonderPaymentOptions;
    }

    /**
     * Representing one option for how you can pay for building a card.
     */
    public static class PaymentOption {
	public final int leftCost;
	public final int selfCost;
	public final int rightCost;
	public Map<Resource, Integer> leftResources;
	public Map<Resource, Integer> rightResources;
	public final boolean free;

	public PaymentOption(int leftCost, int selfCost, int rightCost,
		Map<Resource, Integer> leftResources,
		Map<Resource, Integer> rightResources, boolean free) {
	    this.leftCost = leftCost;
	    this.selfCost = selfCost;
	    this.rightCost = rightCost;
	    this.leftResources = leftResources;
	    this.rightResources = rightResources;
	    this.free = free;
	}

	public PaymentOption(int leftCost, int selfCost, int rightCost,
		Map<Resource, Integer> leftResources,
		Map<Resource, Integer> rightResources) {
	    this(leftCost, selfCost, rightCost, leftResources, rightResources,
		    false);
	}
    }
}
