package sevenWonders.backend;

import java.util.HashMap;
import java.util.List;

import sevenWonders.backend.Hand.PaymentOption;
import sevenWonders.backend.Wonder.StageType;

public class GameEngine {
    private DeckDealer dealer = new DeckDealer();
    private ClientConnection[] playerClients;
    private List<Player> players;
    private int numPlayers;
    
    
    private class HashMapp extends HashMap<Resource, Integer>{};
    GameEngine(ClientConnection[] playerClients) {
	this.playerClients = playerClients;
	numPlayers = playerClients.length;
	
	// TODO: Create real wonders etc.
	for (int i = 0; i < numPlayers; i++) {
	    Wonder w = new Wonder("wonder - 1.png", new HashMapp[4], Resource.CLAY, new Wonder.Stage[] {
		new Wonder.Stage(StageType.COPYGUILD) {},
		new Wonder.Stage(StageType.COPYGUILD) {},
		new Wonder.Stage(StageType.COPYGUILD) {},
		new Wonder.Stage(StageType.COPYGUILD) {}
	    });
	    Player p = new Player("Andreas", w);
	    players.add(p);
	    p.addMoney(4);
	}
    }

    public void run() {
	// The game ahs 3 eras
	for (int era = 1; era <= 3; era++) {
	    // Every era, new cards are needed
	    List<Card>[] hands = dealer.getHands(era, numPlayers);
	    // Every era has 6 rounds
	    for (int round = 1; round <= 6; round++){
		
		Hand[] handsWithPaymentOptions = new Hand[numPlayers];
		
		GameState gs = new GameState(players, era, round);
		// calculates paymentoptions for every player and sends data to client
		for (int i = 0; i < numPlayers; i++) {
		    Hand hand = handsWithPaymentOptions[i] = HandGenerator.getHand(hands[i], players, i);
		    playerClients[i].SendGameState(gs, hand);
		}

		// Gets responses from every client and acts thereafter
		for (int i = 0; i < numPlayers; i++) {
		    Action a = playerClients[i].GetAction();
		    Player p = players.get(i);
		    
		    PaymentOption po = null;
		    
		    switch (a.action) {
		    case BUILD_BUILDING:
			p.buildCard(a.card);
			// TODO: execute actions
			po = handsWithPaymentOptions[i].cards.get(a.card).get(a.paymentOptionIndex);
			break;
		    case BUILD_WONDER:
			p.wonder.build(era);
			// TODO: execute actions
			po = handsWithPaymentOptions[i].wonderPaymentOptions.get(a.paymentOptionIndex);
			break;
		    case DISCARD_CARD:
			dealer.getDiscardPile().add(a.card);
			p.addMoney(3);
			break;
		    }
		    hands[i].remove(a.card);
		    
		    if (po == null || po.free) continue;
		    
		    players.get(left(i)).addMoney(po.leftCost);
		    players.get(right(i)).addMoney(po.rightCost);
		    p.removeMoney(po.leftCost + po.selfCost + po.rightCost);
		}
		
		// rotation 1 means counter-clockwise
		int rotation = 1;
		// rotation -1 means clockwise
		if (era == 2) rotation = -1;
		
		List<Card> temp = hands[0];
		for (int i = 0; i < hands.length-1; i++) {
		    hands[i] = hands[modulo(i+rotation, numPlayers)];
		}
		hands[modulo(hands.length-rotation, numPlayers)] = temp;
	    }
	}
    }
    
    private int modulo(int a, int b) {
	int res = a % b;
	if (res < 0) res += b;
	return res;
    }
    private int left(int idx) {
	return modulo(idx - 1, numPlayers);
    }
    private int right(int idx) {
	return modulo(idx + 1, numPlayers);
    }
}
