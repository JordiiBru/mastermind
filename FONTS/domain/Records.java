package domain;
import utils.Pair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jordi
 * Records class which will store the chosen records of the history of the game.
 * Records:
 *  - Fastest and Slowest game
 *  - High score and Low score game
 *  - More turns and Fewer turns in a game
 */
public class Records {
    private Map<String, Pair<String, Integer> > recordsMap;

    public Records() {
        recordsMap = new HashMap<>();
        initRecords();
    }

    /**
     * Initializes all records empty. No need to be victory game stats.
     */
    public void initRecords() {
        Pair<String, Integer> empty_pair = new Pair<>();
        this.recordsMap.put("Fastest",empty_pair);
        this.recordsMap.put("Slowest",empty_pair);
        this.recordsMap.put("High-score",empty_pair);
        this.recordsMap.put("Low-score",empty_pair);
        this.recordsMap.put("More-turns",empty_pair);
        this.recordsMap.put("Fewer-turns",empty_pair);
    }

    /**
     * It compares the info of a finished game stat with every record.
     * If some stat is better, it will replace the value with the player name.
     * @param playerName player name received from the controller
     * @param score of the player
     * @param seconds of the game duration
     * @param turns of the finished game
     */
    public void updateRecords(String playerName, int score, int seconds, int turns) {
        if(checkFastest(seconds)) {
            Pair<String, Integer> new_record = new Pair<>(playerName, seconds);
            this.recordsMap.put("Fastest",new_record);
        }

        if(checkSlowest(seconds)) {
            Pair<String, Integer> new_record = new Pair<>(playerName, seconds);
            this.recordsMap.put("Slowest",new_record);
        }

        if(checkHighScore(score)) {
            Pair<String, Integer> new_record = new Pair<>(playerName, score);
            this.recordsMap.put("High-score",new_record);
        }

        if(checkLowScore(score)) {
            Pair<String, Integer> new_record = new Pair<>(playerName,score);
            this.recordsMap.put("Low-score",new_record);
        }

        if(checkMoreTurns(turns)) {
            Pair<String, Integer> new_record = new Pair<>(playerName, turns);
            this.recordsMap.put("More-turns",new_record);
        }

        if(checkFewerTurns(turns)) {
            Pair<String, Integer> new_record = new Pair<>(playerName, turns);
            this.recordsMap.put("Fewer-turns",new_record);
        }

    }

    // checks for a better record

    /**
     * @param seconds dedicated stat of the record "Fastest"
     * @return true if the param passed is better
     */
    private boolean checkFastest(int seconds) {
        Pair<String, Integer> record = this.recordsMap.get("Fastest");
        return record.first() == null || record.first().equals("null")|| record.second() > seconds;
    }

    /**
     * @param seconds dedicated stat of the record "Slowest"
     * @return true if the param passed is better
     */
    private boolean checkSlowest(int seconds) {
        Pair<String, Integer> record = this.recordsMap.get("Slowest");
        return record.first() == null || record.first().equals("null")|| record.second() < seconds;
    }

    /**
     * @param score dedicated stat of the record "high-score"
     * @return true if the param passed is better
     */
    private boolean checkHighScore(int score) {
        Pair<String, Integer> record = this.recordsMap.get("High-score");
        return record.first() == null || record.first().equals("null")|| record.second() < score;
    }

    /**
     * @param score dedicated stat of the record "low-score"
     * @return true if the param passed is better
     */
    private boolean checkLowScore(int score) {
        Pair<String, Integer> record = this.recordsMap.get("Low-score");
        return record.first() == null || record.first().equals("null")|| record.second() > score;
    }

    /**
     * @param turns dedicated stat of the record "more-turns"
     * @return true if the param passed is better
     */
    private boolean checkMoreTurns(int turns) {
        Pair<String, Integer> record = this.recordsMap.get("More-turns");
        return record.first() == null || record.first().equals("null")|| record.second() < turns;
    }

    /**
     * @param turns dedicated stat of the record "fewer-turns"
     * @return true if the param passed is better
     */
    private boolean checkFewerTurns(int turns) {
        Pair<String, Integer> record = this.recordsMap.get("Fewer-turns");
        return record.first() == null || record.first().equals("null")|| record.second() > turns;
    }

    public void setRecords(Map<String, Pair<String, Integer> > data) {
        this.recordsMap = data;
    }

    // getters

    public Map<String, Pair<String, Integer>> getRecordsMap() {
        return this.recordsMap;
    }

    public Pair<String, Integer> getFastestRecord() {
        return this.recordsMap.get("Fastest");
    }

    public Pair<String, Integer> getSlowestRecord() {
        return this.recordsMap.get("Slowest");
    }

    public Pair<String, Integer> getHighScoreRecord() {
        return this.recordsMap.get("High-score");
    }

    public Pair<String, Integer> getLowScoreRecord() {
        return this.recordsMap.get("Low-score");
    }

    public Pair<String, Integer> getMoreTurnsRecord() {
        return recordsMap.get("More-turns");
    }

    public Pair<String, Integer> getFewerTurnsRecord() {
        return this.recordsMap.get("Fewer-turns");
    }

}
