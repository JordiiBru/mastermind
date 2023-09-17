package drivers.stubs;

public class RankingStub {

    public String Ranking(){
        return "An empty instance of Ranking has been created successfully. Rankings are empty by default.";
    }

    public String updateRankingCM(String playerName, int playerScore) {
        return "GameController has been notified to update CodeMaker ranking. It will be updated with the following entry: \n" +
                "                    player name -> " + playerName + " with score " + playerScore;
    }

    public String updateRankingCB(String playerName, int playerScore) {
        return "GameController has been notified to update CodeBreaker ranking. It will be updated with the following entry: \n" +
                "                    player name -> " + playerName + " with score " + playerScore;
    }
}
