package drivers.stubs;

import utils.Ball_color;
import utils.Mode;
import utils.Pair;

public class GameControllerStub {
    public String startGame(String playerName, Mode playerMode){
        return "GameController has been notified to create a new instance of Player with name " + playerName + " and play" + playerMode + "role.";
    }

    public String setDifficultyToNormal(){
        return "GameController has been notified to create a new instance of DifficultyNormal with default values. " +
                "Normal default values: \n" +
                "   > algorithm variable set to true\n" +
                "   > repeated_colors variable set to false\n" +
                "   > number_positions variable set to 4\n" +
                "   > empty_color variable set to false\n" +
                "   > max_turns variable set to 8\n" +
                "   > multiplier variable set to 1.0";
    }

    public String setDifficultyToHard(){
        return "GameController has been notified to create a new instance of DifficultyHard with default values. " +
                "Hard default values: \n" +
                "   > algorithm variable set to true\n" +
                "   > repeated_colors variable set to false\n" +
                "   > number_positions variable set to 7\n" +
                "   > empty_color variable set to false\n" +
                "   > max_turns variable set to 8\n" +
                "   > multiplier variable set to 8.0";
    }

    public String setDifficultyToCustom(boolean algorithm, boolean repeated_colors, int number_positions, boolean empty_colors, int max_turns){
        return "GameController has been notified to create a new instance of DifficultyCustom with the following values: \n" +
                "   > algorithm variable set to " + algorithm  + "\n" +
                "   > repeated_colors variable set to " + repeated_colors + "\n" +
                "   > number_positions variable set to " + number_positions + "\n" +
                "   > empty_color variable set to " + empty_colors + "\n" +
                "   > max_turns variable set to " + max_turns + "\n" +
                "   > multiplier variable update correctly";

    }

    public String newColor(int ball_position, Ball_color ball_color) {
        return "GameController has been notified to add a ball of color " + ball_color + " at position " + ball_position + " in the currentCode. \n";
    }

    public String confirmCode(){
        return "GameController has been notified that Presentation want to confirm the current code: \n";
    }

    public Ball_color[] cleanCode(){
        return new Ball_color[]{Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
    }

    public Pair<Integer, Ball_color> desiredHint() {
        return new Pair<>(2, Ball_color.Orange);
    }

    public String surrender() {
        return "GameController has been notified that Presentation want to Surrender and immediately lose: \n" +
                "                                                                                          \n" +
                "                           Surrender confirmed!\n";
    }

    public Ball_color[] newSecretCodeComputer() {
        return new Ball_color[]{Ball_color.Purple, Ball_color.Green, Ball_color.Orange, Ball_color.Blue};
    }
}
