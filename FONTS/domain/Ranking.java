package domain;


import domain.exceptions.*;

import utils.Pair;

import java.util.ArrayList;

public class Ranking {

    private ArrayList<Pair<String,Integer>> rankingCB; //The ArrayList representing the CodeBreaker ranking
    private ArrayList<Pair<String,Integer>> rankingCM; //The ArrayList representing the CodeMaker ranking

    // Constructors

    /**
     * Constructs a new empty ranking.
     */
    public Ranking() {
        this.rankingCB = new ArrayList<>();
        this.rankingCM = new ArrayList<>();
    }

    /**
     * Constructs a new ranking with the specified rankings for the CodeMaker and CodeBreaker.
     *
     * @param rankingCB a ArrayList representing the ranking for the CodeBreaker
     * @param rankingCM a ArrayList representing the ranking for the CodeMaker
     */
    public Ranking(ArrayList<Pair<String,Integer>> rankingCB, ArrayList<Pair<String,Integer>>  rankingCM) {
        this.rankingCB = rankingCB;
        this.rankingCM = rankingCM;
    }

    // Methods

    /**
     * Updates the CodeBreaker ranking with a new entry for the specified player and score.
     * If the ranking is not full (less than 20 entries), or if the new score is higher than the lowest score in the ranking, the new entry is added to the ranking. If the ranking has more than 20 entries, the lowest entry is removed.
     *
     * @param playerName a String representing the name of the player
     * @param score an int representing the score of the player
     */
    public void updateRankingCB(String playerName, int score) throws InvalidPlayerNameException {
        if (playerName == null || playerName.isEmpty()) {
            throw new InvalidPlayerNameException();
        }
        updateRanking(playerName, score, rankingCB); //The score is always positive (checked in the GameStats class)
    }

    /**
     * Updates the CodeMaker ranking with a new entry for the specified player and score.
     * If the ranking is not full (less than 20 entries), or if the new score is higher than the lowest score in the ranking, the new entry is added to the ranking. If the ranking has more than 20 entries, the lowest entry is removed.
     *
     * @param playerName a String representing the name of the player
     * @param score an int representing the score of the player
     */
    public void updateRankingCM(String playerName, int score) throws InvalidPlayerNameException {
        if (playerName == null || playerName.isEmpty()) {
            throw new InvalidPlayerNameException();
        }
        updateRanking(playerName, score, rankingCM); //The score is always positive (checked in the GameStats class)
    }

    /**
     * Returns the CodeBreaker ranking.
     *
     * @return a ArrayList representing the CodeBreaker ranking
     */
    public ArrayList<Pair<String,Integer>> getRankingCB() {
        return rankingCB;
    }

    /**
     * Sets the CodeBreaker ranking.
     *
     * @param rankingCB a ArrayList representing the new CodeBreaker ranking
     */
    public void setRankingCB(ArrayList<Pair<String,Integer>> rankingCB) {
        this.rankingCB = rankingCB;
    }

    /**
     * Returns the CodeMaker ranking.
     *
     * @return a ArrayList representing the CodeMaker ranking
     */
    public ArrayList<Pair<String,Integer>> getRankingCM() {
        return rankingCM;
    }

    /**
     * Sets the CodeMaker ranking.
     *
     * @param rankingCM a ArrayList representing the new CodeMaker ranking
     */
    public void setRankingCM(ArrayList<Pair<String,Integer>> rankingCM) {
        this.rankingCM = rankingCM;
    }

    /**
     * Checks if the specified player is in the CodeBreaker ranking.
     *
     * @param playerName the name of the player to check for in CodeBreaker ranking
     * @return true if the player is in the ranking, false otherwise
     */
    public boolean isInRankingCB(String playerName) {
        return isInRanking(playerName, rankingCB);
    }

    /**
     * Checks if the specified player is in the CodeMaker ranking.
     *
     * @param playerName the name of the player to check for in CodeMaker ranking
     * @return true if the player is in the ranking, false otherwise
     */
    public boolean isInRankingCM(String playerName) {
        return isInRanking(playerName, rankingCM);
    }

    /**
     * Returns the score of a player in the CodeBreaker ranking. If the player is not in the ranking, returns 0.
     *
     * @param playerName the name of the player
     * @return the score of the player in the CodeBreaker ranking, or 0 if the player is not in the ranking
     */
    public int getScoreCB(String playerName) {
        return getScore(playerName, rankingCB);
    }

    /**
     * Returns the score of a player in the CodeMaker ranking. If the player is not in the ranking, returns 0.
     *
     * @param playerName the name of the player
     * @return the score of the player in the CodeMaker ranking, or 0 if the player is not in the ranking
     */
    public int getScoreCM(String playerName) {
        return getScore(playerName, rankingCM);
    }

    /**
     * Private method that updates the specified ranking with a new entry for the specified player and score.
     * If the ranking is not full (less than 20 entries), or if the new score is higher than the lowest score in the ranking, the new entry is added to the ranking. If the ranking has more than 20 entries, the lowest entry is removed.
     * The ranking is sorted by score in descending order.

     * @param playerName a String representing the name of the player
     * @param score an int representing the score of the player
     * @param ranking an ArrayList representing the ranking to update
     */
    private void updateRanking(String playerName, int score, ArrayList<Pair<String,Integer>> ranking) {
        final int MAX_NUM_RANKING_POSITIONS = 20;
        Pair<String,Integer> newEntry = new Pair<String,Integer>(playerName, score);

        // If the ranking is empty, add the new entry
        if (ranking.isEmpty()) {
            ranking.add(newEntry);
            return;
        }

        // Check if the new entry should be added to the ranking
        boolean shouldAddNewEntry = false;
        if (ranking.size() < MAX_NUM_RANKING_POSITIONS) {
            shouldAddNewEntry = true; // If the ranking is not full, add the new entry
        } else if (score > ranking.get(ranking.size() - 1).second()) {
            shouldAddNewEntry = true; // If the new score is higher than the lowest score in the ranking, add the new entry
        }

        if (shouldAddNewEntry) {
            ranking.add(newEntry);

            // Sort the ranking by score in descending order
            ranking.sort((p1, p2) -> p2.second().compareTo(p1.second()));

            // If the ranking has more than 20 entries, remove the lowest entry
            if (ranking.size() > MAX_NUM_RANKING_POSITIONS) {
                ranking.remove(ranking.size()-1);
            }
        }
    }

    /**
     * Private method to checks if the specified player is in the ranking.
     *
     * @param playerName the name of the player to check for in ranking
     * @return true if the player is in the ranking, false otherwise
     */
    private boolean isInRanking(String playerName, ArrayList<Pair<String,Integer>> ranking) {
        for (Pair<String,Integer> entry : ranking) {
            if (entry.first().equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Private method that returns the score of a player in ranking. If the player is not in the ranking, returns 0.
     *
     * @param playerName the name of the player
     * @return the score of the player in ranking, or 0 if the player is not in the ranking
     */
    private int getScore(String playerName, ArrayList<Pair<String,Integer>> ranking) {
        for (Pair<String,Integer> entry : ranking) {
            if (entry.first().equals(playerName)) {
                return entry.second();
            }
        }
        return 0;
    }
}



