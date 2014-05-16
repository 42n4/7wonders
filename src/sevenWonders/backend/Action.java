package sevenWonders.backend;

/**
 * And Action represent a players action for a turn. It has a caed, and
 * actiontype and a paymentoptionindex. It can only choose from already exsiting
 * paymentoptions so it can't cheat.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class Action {
    public final Card card;
    public final ActionType action;
    public final int paymentOptionIndex;

    public static enum ActionType {
	BUILD_WONDER, DISCARD_CARD, BUILD_BUILDING
    }

    /**
     * An action is to be sent between the server and client. It holds a card
     * and the action the card will be used for. If the action involves building
     * something a paymentOptionIndex is required, referring to the index in the
     * list held in the Hand-object.
     * 
     * @param card
     * @param action
     * @param paymentOptionIndex
     */
    public Action(Card card, ActionType action, int paymentOptionIndex) {
	this.card = card;
	this.action = action;
	this.paymentOptionIndex = paymentOptionIndex;
    }
}
