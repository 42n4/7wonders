package sevenWonders.backend;

import java.util.EnumMap;

public abstract class Card {
    public final int id;
    public final String name, description;
    public final int[] preCards, postCards;
    public final EnumMap<Resource, Integer> cost;
    public final int moneyCost;
    
    /**
     * Creates a card. Must be called from inherited classes.
     * @param id
     * @param name
     * @param description
     * @param preCards
     * @param postCards
     * @param cost
     * @param moneyCost
     */
    protected Card(int id, String name, String description, int[] preCards, int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost) {
	this.id = id;
	this.name = name;
	this.description = description;
	this.preCards = preCards;
	this.postCards = postCards;
	this.cost = cost;
	this.moneyCost = moneyCost;
    }
}
