package drivers.stubs;

import utils.Ball_color;
import utils.Pair;

import java.util.Arrays;

public class FeedbackStub {

    public String Feedback() {
        return "An empty instance of Feedback has been created successfully.";
    }

    public String setSecretCode(Ball_color[] sc) {
        return "secret_code variable set to " + Arrays.toString(sc) + ".";
    }

    public Ball_color[] getSecretCode() {
        return new Ball_color[]{Ball_color.Red,Ball_color.Red,Ball_color.Red,Ball_color.Red};
    }

    public Pair<Integer,Integer> compareSecretCode(Ball_color[] code) {
        return new Pair<>(4, 2);
    }

    public Pair<Integer,Ball_color> askHint() {
        return new Pair<>(0, Ball_color.Blue);
    }

}
