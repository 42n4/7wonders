package sevenWonders.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import sevenWonders.backend.Hand.PaymentOption;
import sevenWonders.backend.Wonder.StageType;

public class GameEngine {
    private DeckDealer dealer = new DeckDealer();
    private ClientConnection[] playerClients;
    private List<Player> players = new ArrayList<>();
    private int numPlayers;
    private Money[] giveGold;
    
    
    GameEngine(ClientConnection[] playerClients, String[] playerNames) {
	this.playerClients = playerClients;
	numPlayers = playerClients.length;
	
	// TODO: Handle this differently?
	for (int i = 0; i < numPlayers; i++) {
	    Wonder w = WonderFactory.getRandomWonder();
	    Player p = new Player(playerNames[i], w);
	    players.add(p);
	    p.addMoney(3);
	}
    }

    public void run() {
	// The game has 3 eras
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
		    // retries until succesful
		    while(!playerClients[i].SendGameState(gs, hand));
		}

		giveGold = new Money[numPlayers]; 
		
		// Gets responses from every client and acts thereafter
		for (int i = 0; i < numPlayers; i++) {
		    Action a = playerClients[i].GetAction();
		    Player p = players.get(i);
		    
		    PaymentOption po = null;
		    
		    switch (a.action) {
		    case BUILD_BUILDING:
			p.buildCard(a.card);
			execute(a.card, i);
			po = handsWithPaymentOptions[i].cards.get(a.card).get(a.paymentOptionIndex);
			break;
		    case BUILD_WONDER:
			p.wonder.build(era);
			execute(p.wonder.stages[p.wonder.getCurrentLevel()-1], i);
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
		
		GameRules.updateNeighborDependants(players);
		
		for (int i = 0; i < numPlayers; i++) {
		    if (giveGold[i] != null) {
			players.get(i).addMoney(giveGold[i].getMoney());
		    }
		}
		// TODO: Special Wonder actions
		
		if (era == 2) { //rotates counter-clockwise
		    List<Card> temp = hands[hands.length - 1];
		    for (int i = hands.length - 1; i > 0; i--) {
			hands[i] = hands[modulo(i - 1, numPlayers)];
		    }
		    hands[0] = temp;
		} else { //rotates clockwise
		    List<Card> temp = hands[0];
		    for (int i = 0; i < hands.length - 1; i++) {
			hands[i] = hands[modulo(i + 1, numPlayers)];
		    }
		    hands[modulo(hands.length - 1, numPlayers)] = temp;
		}
	    }
	    // WAR!!!!
	    GameRules.wageWar(players, era);
	}
	// The game is over, indicated by the era value of 4
	GameState gs = new GameState(players, 4, -1);
	List<List<Integer>> points = null;//PointCalculator.getPoints(players);
	for (int i = 0; i < numPlayers; i++) {
	    // retries until succesful
	    while(!playerClients[i].SendEndState(gs, points));
	}
    }
    
    private void execute(Object obj, int playerIndex) {
	if (obj instanceof Money) {
	    giveGold[playerIndex] = (Money)obj;
	}
	// TODO: identify special wonder actions
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
    
    /**
     * Creates a single player game vs AI
     * @param playerName
     * @param players
     * @return
     */
    public static GameEngine createAIGame(String playerName, ClientConnection connection ,int players) {
	ClientConnection[] connections = new ClientConnection[players];
	String[] names = new String[players];

	connections[0] = connection;
	names[0] = playerName;
	
	for (int i = 1; i < players; i++) {
	    connections[i] = new RandomAI(i, 60.0, 20.0);
	    names[i] = "CPU " + i;
	}
	return new GameEngine(connections, names);
    }
}
