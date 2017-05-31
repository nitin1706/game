package org.nitin.practice.console;

import java.io.Serializable;

/**
 * Created by nitin1706 on 5/28/17.
 *
 *  Model class to hold the details of a player. All the player will be objects of this class.
 */
public class PlayerDetails implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -612336826193856493L;
	
	private String name = "";
    private int score = 100;
    private int totalGames = 0;
    private int gamesWon = 0;
    private int maxScore = 0;
    private int opponentScore = 0;
    private StringBuilder lastGameHistory = new StringBuilder();

    public PlayerDetails() {
        this.score = 100;
        this.opponentScore = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public StringBuilder getLastGameHistory() {
        return lastGameHistory;
    }

    public void setLastGameHistory(StringBuilder lastGameHistory) {
        this.lastGameHistory = lastGameHistory;
    }

    @Override
    public String toString() {
        return "" +
                "name=" + name +
                ",score=" + score +
                ",opponentScore=" + opponentScore +
                ",totalGames=" + totalGames +
                ",gamesWon=" + gamesWon +
                ",maxScore=" + maxScore +
                ",lastGameHistory=" + lastGameHistory +
                "";
    }

    public String saveData() {
        return "" + name +
                "," + score +
                "," + opponentScore +
                "," + totalGames +
                "," + gamesWon +
                "," + maxScore +
                ",\n" + lastGameHistory +
                "";
    }
    public String saveGameActivityLog() {
    	return "" + this.getLastGameHistory();
    }
}
