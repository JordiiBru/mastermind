package drivers.stubs;

import utils.Ball_color;

import java.util.ArrayList;
import java.util.List;

public class ParserColorIntStub {

    public static List<Integer> ballColorArrayToIntList(Ball_color[] ballColorArray) {
        List<Integer> code = new ArrayList<>();
        code.add(2);
        code.add(2);
        code.add(2);
        code.add(2);
        return code;
    }

    public static Ball_color[][] intListListToBallColorMatrix(List<List<Integer>> intListList) {
        return new Ball_color[][]{new Ball_color[]{Ball_color.Red, Ball_color.Red, Ball_color.Red, Ball_color.Red},
                                  new Ball_color[]{Ball_color.Green, Ball_color.Green, Ball_color.Green, Ball_color.Green},
                                  new Ball_color[]{Ball_color.Red, Ball_color.Red, Ball_color.Red, Ball_color.Red},
                                  new Ball_color[]{Ball_color.Green, Ball_color.Green, Ball_color.Green, Ball_color.Green}};
    }

}
