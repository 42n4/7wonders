package sevenWonders.backend;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class GameRules {
    private static int[] pointsForWin = new int[] {
	1, 3, 5
    };
    static void wageWar(List<Player> players, int era) {
	int numPlayers = players.size();
	// has to start at 0
	era--;
	
	// Calculates the military power of every player
	int[] shields = new int[numPlayers];
	for (int i = 0; i < numPlayers; i++) {
	    Player p = players.get(i);
	    for (Card c : p.getBuildings()) {
		if (c instanceof Shields) {
		    shields[i] += ((Shields)c).getShields();
		}
	    }
	    for (int stage = 0; stage < p.wonder.getCurrentLevel(); stage++) {
		if (p.wonder.stages[stage] instanceof Shields) {
		    shields[i] += ((Shields)p.wonder.stages[stage]).getShields();
		}
	    }
	}
	// Wages war between them
	for (int a = 0; a < numPlayers; a++) {
	    int b = (a + 1 ) % numPlayers;
	    // No one wins
	    if (shields[a] == shields[b]) continue;
	    if (shields[a] > shields[b]) { // a wins
		players.get(b).getMilitaryWins()[0][era] = -1;
		players.get(a).getMilitaryWins()[1][era] = pointsForWin[era];
	    } else { // b wins
		players.get(a).getMilitaryWins()[1][era] = -1;
		players.get(b).getMilitaryWins()[0][era] = pointsForWin[era];
	    }
	}
    }
    
    static void updateNeighborDependants(List<Player> players) {
	// TODO: DONT RELY ON LIST! USE ONLY ONE CARD
	int numPlayers = players.size();
	List<List<NeighborDependant>> dependants = new ArrayList<>();

	for (Player p : players) {
	    List<NeighborDependant> cards = new LinkedList<NeighborDependant>();
	    dependants.add(cards);
	    
	    for (Card c : p.getBuildings()) {
		if (c instanceof NeighborDependant) {
		    cards.add((NeighborDependant)c);
		}
	    }
	    for (Wonder.Stage s : p.wonder.stages) {
		if (s instanceof NeighborDependant) {
		    cards.add((NeighborDependant)s);
		}
	    }
	}
	for (int i = 0; i < numPlayers; i++) {
	    for (NeighborDependant nd : dependants.get(i)) {
		int count = 0;
        	if (nd == null) continue;
        	    
        	if (nd instanceof BuildingRewardCard) {
        	    BuildingRewardCard c = (BuildingRewardCard)nd;
        	    List<Player> toCheck = new LinkedList<Player>();
        	    toCheck.add(players.get(i));
        	    if (c.neighbors) {
        		toCheck.add(players.get(left(i, numPlayers)));
        		toCheck.add(players.get(right(i, numPlayers)));
        	    }
        	    for (Player p : toCheck) for (Card compare : p.getBuildings()) {
        		if (c.cardType == compare.getClass())
        		    count++;
        	    }
        	} else if (nd instanceof WonderRewardCard) {
        	    count = players.get(i).wonder.getCurrentLevel();
        	} else if (nd instanceof NeighborCardGuild) {
        	    NeighborCardGuild c = (NeighborCardGuild)nd;
        	    for (Player p : new Player[] {
            		players.get(left(i, numPlayers)),
            		players.get(right(i, numPlayers))
            	    }) for (Card compare : p.getBuildings()) {
        		if (c.cardType == compare.getClass())
        		    count++;
        	    }
        	} else if (nd instanceof BuildersGuild) {
        	    count += players.get(i).wonder.getCurrentLevel();
        	    count += players.get(left(i, numPlayers)).wonder.getCurrentLevel();
        	    count += players.get(right(i, numPlayers)).wonder.getCurrentLevel();
        	} else if (nd instanceof StrategistGuild) {
        	    for (Player p : new Player[] {
            		players.get(left(i, numPlayers)),
            		players.get(right(i, numPlayers))
            	    }) for (int[] militaryWins: p.getMilitaryWins())
            	       for (int win : militaryWins){
            		if (win == -1) {
            		    count++;
            		}
            	    }
        	} else if (nd instanceof ShipownersGuild) {
        	    for (Card c : players.get(i).getBuildings()) {
        		if (c instanceof BrownCard 
        		 || c instanceof GrayCard
        		 || c instanceof PurpleCard) {
        		    count++;
        		}
        	    }
        	}
        	nd.update(count);
	    }
	}
    }
    

    
    private static int modulo(int a, int b) {
	int res = a % b;
	if (res < 0) res += b;
	return res;
    }
    private static int left(int idx, int numPlayers) {
	return modulo(idx - 1, numPlayers);
    }
    private static int right(int idx, int numPlayers) {
	return modulo(idx + 1, numPlayers);
    }
}
