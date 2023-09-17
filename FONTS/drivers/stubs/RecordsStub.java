package drivers.stubs;

public class RecordsStub {
    public String Records(){
        return "An empty instance of Records has been created successfully. Records are empty by default.";
    }

    public String updateRecords(String playerName, int playerScore, int seconds, int numTurn){
        return "The records will be updated with the following values of the game (if they break any record): \n" +
                "                    Player name -> " + playerName + "\n" +
                "                    Player score -> " + playerScore + "\n" +
                "                    Game time [seconds] -> " + seconds + "\n" +
                "                    Game end turn -> " + numTurn;
    }
}
