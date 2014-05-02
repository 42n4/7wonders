package sevenWonders.backend;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Hand {
    public final HashMap<Card, List<PaymentOption>> cards;
    public final List<PaymentOption> wonderPaymentOptions;

    public Hand(HashMap<Card, List<PaymentOption>> cards, List<PaymentOption> wonderPaymentOptions) {
	this.cards = cards;
	this.wonderPaymentOptions = wonderPaymentOptions;
    }

    public static class PaymentOption {
	public final int leftCost;
	public final int selfCost;
	public final int rightCost;
	Map<Resource, Integer> leftResources;
	Map<Resource, Integer> rightResource;
	public final boolean free;
	
	public PaymentOption(int leftCost, int selfCost, int rightCost, Map<Resource, Integer> leftResources, Map<Resource, Integer> rightResource, boolean free) {
	    this.leftCost = leftCost;
	    this.selfCost = selfCost;
	    this.rightCost = rightCost;
	    this.leftResources = leftResources;
	    this.rightResource = rightResource;
	    this.free = free;
	}
	public PaymentOption(int leftCost, int selfCost, int rightCost, Map<Resource, Integer> leftResources, Map<Resource, Integer> rightResource) {
	    this(leftCost, selfCost, rightCost, leftResources, rightResource, false);
	}
    }
}
