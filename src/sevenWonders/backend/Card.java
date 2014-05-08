package sevenWonders.backend;

import sevenWonders.backend.ScienceGuild.ScienceGuildBuilder;

import sevenWonders.backend.StrategistGuild.StrategistGuildBuilder;

import sevenWonders.backend.BuildersGuild.BuildersGuildBuilder;

import sevenWonders.backend.ShipownersGuild.ShipownersGuildBuilder;
import sevenWonders.backend.NeighborCardGuild.NeighborCardGuildBuilder;
import sevenWonders.backend.BuildingRewardCard.BuildingRewardCardBuilder;
import sevenWonders.backend.YellowResourceCard.YellowResourceCardBuilder;
import sevenWonders.backend.WonderRewardCard.WonderRewardCardBuilder;
import sevenWonders.backend.RedCard.RedCardBuilder;
import sevenWonders.backend.GrayCard.GrayCardBuilder;
import sevenWonders.backend.CostModifierCard.CostModifierCardBuilder;
import sevenWonders.backend.GreenCard.GreenCardBuilder;
import sevenWonders.backend.BlueCard.BlueCardBuilder;
import sevenWonders.backend.BrownCard.BrownCardBuilder;
import java.util.EnumMap;

public abstract class Card {
    public final int id;
    public final String name, description;
    public final int[] preCards, postCards;
    public final EnumMap<Resource, Integer> cost;
    public final int moneyCost;

    /**
     * Creates a card. Must be called from inherited classes.
     * 
     * @param id
     * @param name
     * @param description
     * @param preCards
     * @param postCards
     * @param cost
     * @param moneyCost
     */
    protected Card(int id, String name, String description, int[] preCards,
	    int[] postCards, EnumMap<Resource, Integer> cost, int moneyCost) {
	this.id = id;
	this.name = name;
	this.description = description;
	this.preCards = preCards;
	this.postCards = postCards;
	this.cost = cost;
	this.moneyCost = moneyCost;
    }
    
    protected Card(Builder<?, ?> builder) {
	this.id = builder.id;
	this.name = builder.name;
	this.description = builder.description;
	this.preCards = builder.preCards;
	this.postCards = builder.postCards;
	this.cost = builder.cost;
	this.moneyCost = builder.moneyCost;
    }

    @Override
    public int hashCode() {
	return id;
    }

    @Override
    public String toString() {
	return name;
    }

    abstract static class Builder<B, S extends Builder<B, S>> {
	int id;
	String name;
	String description;
	int[] preCards;
	int[] postCards;
	EnumMap<Resource, Integer> cost = new EnumMap<Resource, Integer>(
		Resource.class);
	int moneyCost;
	
	S cost(Resource r, int nr) {
	    cost.put(r, nr);
	    return self();
	}
	
	S moneyCost(int money) {
	    moneyCost = money;
	    return self();
	}
	
	S id(int nr) {
	    id = nr;
	    return self();
	}

	S name(String s) {
	    name = s;
	    return self();
	}

	S description(String s) {
	    description = s;
	    return self();
	}
	
	S preCards(int... cards) {
	    preCards = cards;
	    return self();
	}
	
	S postCards(int... cards) {
	    postCards = cards;
	    return self();
	}
	
	protected S self() {
	    return (S) this; // TODO changed cast from Builder to S...
	}

	abstract B build();
    }
    
    public static BrownCardBuilder newBrownCard() {
	return new BrownCardBuilder();
    }
    
    public static BlueCardBuilder newBlueCard() {
	return new BlueCardBuilder();
    }
    
    public static GreenCardBuilder newGreenCard() {
	return new GreenCardBuilder();
    }
    
    public static CostModifierCardBuilder newCostModifierCard() {
	return new CostModifierCardBuilder();
    }
    
    public static GrayCardBuilder newGrayCard() {
	return new GrayCardBuilder();
    }
    
    public static RedCardBuilder newRedCard() {
	return new RedCardBuilder();
    }
    
    public static WonderRewardCardBuilder newWonderRewardCard() {
	return new WonderRewardCardBuilder();
    }
    
    public static YellowResourceCardBuilder newYellowResourceCard() {
	return new YellowResourceCardBuilder();
    }
    
    public static BuildingRewardCardBuilder newBuildingRewardCard() {
	return new BuildingRewardCardBuilder();
    }
    
    public static NeighborCardGuildBuilder newNeighborCardGuild() {
	return new NeighborCardGuildBuilder();
    }
    
    public static ShipownersGuildBuilder newShipownersGuild() {
	return new ShipownersGuildBuilder();
    }
    
    public static BuildersGuildBuilder newBuildersGuild() {
	return new BuildersGuildBuilder();
    }
    
    public static StrategistGuildBuilder newStrategistGuild() {
	return new StrategistGuildBuilder();
    }
    
    public static ScienceGuildBuilder newScienceGuild() {
	return new ScienceGuildBuilder();
    }
}
