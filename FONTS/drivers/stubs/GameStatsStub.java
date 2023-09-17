package drivers.stubs;

import utils.Mode;

public class GameStatsStub {

    public String GameStats() {
        return "An empty instance of GameStats has been created successfully.";
    }

    public String startGameStats() {
        return "GameStats started successfully.";
    }

    public String finishGameStats(Mode playerRole, int maxTurns, double multiplier) {
        return "GameStats finished successfully.";
    }

    public String addTurn(int maxTurns) {
        return "The turn incremented successfully.";
    }

    public String addHintUsed() {
        return "The hints used incremented successfully.";
    }

    public int getScore() {
        return 22522;
    }

    public int getNumTurn() {
        return 3;
    }

    public int getElapsedTime() {
        return 42;
    }

}
