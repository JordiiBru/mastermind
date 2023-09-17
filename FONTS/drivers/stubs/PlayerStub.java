package drivers.stubs;

import utils.Ball_color;
import utils.Mode;

public class PlayerStub {

    public String Player(String name) {
        return "An instance of Player with name " + name + " has been created successfully.";
    }

    public String cleanCode() {
        return "Code cleared successfully.";
    }

    public String addColor(int pos, Ball_color col) {
        return "Added the color " + col + " at position " + pos + ".";
    }

    public Ball_color[] confirmedCode(boolean repeatedColors, boolean emptyColors) {
        return new Ball_color[]{Ball_color.Red, Ball_color.Green, Ball_color.Blue, Ball_color.Yellow};
    }

    public String initCode(int numElem) {
        return "Code initialized to Ball_color.Empty with " + numElem + " positions successfully.";
    }

    public String getName() {
        return "javier was here";
    }

    public Mode getRole() {
        return Mode.CodeBreaker;
    }

    public Ball_color[] getCurrentCode() {
        return new Ball_color[]{Ball_color.Red, Ball_color.Green, Ball_color.Blue, Ball_color.Yellow};
    }

    public String setRole(Mode role) {
        return "Role set successfully.";
    }

}
