package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PersistenceController {
    private final GameManager gameManager;
    private final RankingsManager rankingManager;
    private final RecordsManager recordsManager;

    public PersistenceController() {
        gameManager = new GameManager();
        rankingManager = new RankingsManager();
        recordsManager = new RecordsManager();
    }

    public void saveGame(List<String> game) throws IOException {
        gameManager.saveGame(game);
    }

    public List<String> loadGame() throws FileNotFoundException {
        return gameManager.loadGame();
    }

    public boolean existsGame() {
        return gameManager.existFile();
    }

    public void removeGame() {
        gameManager.deleteFile();
    }

    public void saveRankings(List<String> rankings) throws IOException {
        rankingManager.saveRankings(rankings);
    }

    public List<String> loadRankings() throws FileNotFoundException {
        return rankingManager.loadRankings();
    }

    public void saveRecords(List<String> records) throws IOException {
        recordsManager.saveRecords(records);
    }

    public List<String> loadRecords() throws FileNotFoundException {
        return recordsManager.loadRecords();
    }

}
