package sevenWonders.backend;

import sevenWonders.backend.Hand.PaymentOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles point calculation and deciding which player won.
 * 
 * @author Jenny Norelius & Andreas Jönsson
 */
public class PointCalculator {

    /**
     * Returns a HashMap of all players with a list of their final scores
     * attached to them.
     * 
     * @param players
     * @return
     */
    static Map<Player, List<Integer>> getPoints(List<Player> players) {
	Map<Player, List<Integer>> points = new HashMap<>();
	for (Player player : players) {
	    points.put(player, getPlayerPoints(i, players));
	}
	return points;
    }

    /**
     * @param player
     * @return
     */
    private static List<Integer> getPlayerPoints(Player player) {
	List<Integer> points = new ArrayList<>();

	// Military Conflicts
	points.add(0, militaryPoints(player));
	// Treasury Contents
	points.add(1, moneyPoints(player));
	// Wonders
	points.add(2, wonderPoints(player));
	// Civilian Structures
	points.add(3, civilianPoints(player));
	// Scientific Structures
	points.add(4, sciencePoints(player));
	// Commercial Structures
	points.add(5, commercePoints(player));
	// Guilds
	points.add(6, guildPoints(player));

	return points;
    }

    private static int militaryPoints(Player player) {
	int military = 0;
	int[][] wins = player.getMilitaryWins();
	for (int i = 0; i < wins.length; i++) {
	    for (int j = 0; j < wins[i].length; j++) {
		military += wins[i][j];
	    }
	}
	return military;
    }

    private static int moneyPoints(Player player) {
	return player.getMoney() / 3;
    }

    private static int wonderPoints(Player player) {
	// How many stages are currently built on the players wonder
	int wonderStages = player.wonder.getCurrentLevel() + 1;
	int wonder = 0;
	for (int i = 0; i < wonderStages; i++) {
	    if (player.wonder.stages[i] instanceof VictoryPoints) {
		wonder += ((VictoryPoints) player.wonder.stages[i])
			.getVictoryPoints();
	    }
	}
	return wonder;
    }

    private static int civilianPoints(Player player) {
	int civilian = 0;
	for (Card card : player.getBuildings()) {
	    if (card instanceof VictoryPoints) {
		civilian += ((VictoryPoints) card).getVictoryPoints();
	    }
	}
	return civilian;
    }

    /**
     * @param player
     * @return
     */
    private static int sciencePoints(Player player) {
	// How many stages are currently built on the players wonder
	int wonderStages = player.wonder.getCurrentLevel() + 1;
	int[] scienceBuildings = new int[3];
	int scienceChoices = 0;
	for (Card c : player.getBuildings()) {
	    if (c instanceof ScienceCard) {
		scienceBuildings[((ScienceCard) c).getScience().ordinal()]++;
	    }
	    if (c instanceof ScienceChoice) {
		scienceChoices++;
	    }
	}
	for (int i = 0; i < wonderStages; i++) {
	    if (player.wonder.stages[i] instanceof ScienceChoice) {
		scienceChoices++;
	    }
	}

	// You can at most have 2 scienceChoices.
	// No choices
	if (scienceChoices == 0) {
	    return calculateSciencePoints(scienceBuildings);
	}
	// 1-2 choices
	int maxPoints = 0;
	for (int i = 0; i < Science.values().length; i++) {
	    int[] temp = scienceBuildings;
	    temp[i]++;
	    if (scienceChoices > 1) { // 2 choices
		for (int j = 0; j < Science.values().length; j++) {
		    int[] temp2 = temp;
		    temp[j]++;
		    maxPoints = Math.max(maxPoints,
			    calculateSciencePoints(temp2));
		}
	    }
	    maxPoints = Math.max(maxPoints, calculateSciencePoints(temp));
	}
	return maxPoints;
    }

    private static int calculateSciencePoints(int[] buildings) {
	final int COMBOPOINTS = 7;
	int science = 0;
	int combo = buildings[0];
	for (int i = 0; i < buildings.length; i++) {
	    science += Math.pow(buildings[i], 2);
	    combo = Math.min(combo, buildings[i]);
	}
	science += combo * COMBOPOINTS;
	return science;
    }

    private static int commercePoints(Player player) {
	int commerce = 0;
	for (Card card : player.getBuildings()) {
	    if (card instanceof BuildingRewardCard) {
		commerce += ((BuildingRewardCard) card).getVictoryPoints();
	    }
	}
	return commerce;
    }

    private static int guildPoints(Player player) {
	int guilds = 0;
	for (Card card : player.getBuildings()) {
	    if (card instanceof VictoryPoints) {
		guilds += ((VictoryPoints)card).getVictoryPoints();
	    }
	}
	return guilds;
    }
}
