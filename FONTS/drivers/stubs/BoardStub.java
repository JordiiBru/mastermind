package drivers.stubs;

import utils.Ball_color;
import utils.Pair;

import java.util.Arrays;

public class BoardStub {

    public String Board(int numBalls, int numTurnsMax) {
        return "An instance of Board of size " + numBalls + "x" + numTurnsMax + " has been created successfully.";
    }

    public String updateRowBoard(Ball_color[] codePlayer, int numTurn) {
        return "The row of the board in position " + numTurn + " has been updated with " + Arrays.toString(codePlayer) + ".";
    }

    public String updateFeedback(int numTurn, Pair<Integer,Integer> fd) {
        return "The feedback in position " + numTurn + " has been updated with " + fd.first() + " " + fd.second() + ".";
    }

    public Ball_color[][] getBoard() {
        return new Ball_color[][]{new Ball_color[]{Ball_color.Red, Ball_color.Red, Ball_color.Red, Ball_color.Red},
                                  new Ball_color[]{Ball_color.Green, Ball_color.Green, Ball_color.Green, Ball_color.Green},
                                  new Ball_color[]{Ball_color.Red, Ball_color.Red, Ball_color.Red, Ball_color.Red},
                                  new Ball_color[]{Ball_color.Green, Ball_color.Green, Ball_color.Green, Ball_color.Green}};
    }

    public Pair<Integer,Integer>[] getFeedback() {
        Pair<Integer,Integer> p1 = new Pair<>(1,1);
        Pair<Integer,Integer>[] ret = new Pair[4];
        ret[0] = p1;
        ret[1] = p1;
        ret[2] = p1;
        ret[3] = p1;
        return ret;
    }

    public Pair<Integer, Integer> getCurrentFeedback(int numTurn) {
        return new Pair<>(1,1);
    }

}
