package utils;

import java.util.ArrayList;
import java.util.List;

public class ParserColorInt {

    /**
     * Convert from List<Integer> to Ball_color[]
     * @param intList List of integers that represents a list of colors
     * @return An array of ball_color
     */
    public static Ball_color[] intListToBallColorArray(List<Integer> intList) {
        Ball_color[] ballColorArray = new Ball_color[intList.size()];
        for (int i = 0; i < intList.size(); i++) {
            ballColorArray[i] = Ball_color.values()[intList.get(i)];
        }
        return ballColorArray;
    }


    /**
     * Convert from Ball_color[] to List<Integer>
     * @param ballColorArray Ball_color array
     * @return A list of integers that represents a list of colors
     */
    public static List<Integer> ballColorArrayToIntList(Ball_color[] ballColorArray) {
        List<Integer> intList = new ArrayList<>();
        for (Ball_color ballColor : ballColorArray) {
            intList.add(ballColor.ordinal());
        }
        return intList;
    }


    /**
     * Convert from List<List<Integer>> to Ball_color[][]
     * @param intListList Matrix made of list that reresents a board of integers
     * @return Matrix of ball_color
     */
    public static Ball_color[][] intListListToBallColorMatrix(List<List<Integer>> intListList) {
        Ball_color[][] ballColorMatrix = new Ball_color[intListList.size()][];
        for (int i = 0; i < intListList.size(); i++) {
            List<Integer> intList = intListList.get(i);
            ballColorMatrix[i] = intListToBallColorArray(intList);
        }
        return ballColorMatrix;
    }

    /**
     * Convert from Bal_color[][] to List<List<Integer>>
     * @param ballColorArray matrix of ball color
     * @return list of lists with integers that represents colors
     */
    public static List<List<Integer>> ballColorArrayToListOfIntLists(Ball_color[][] ballColorArray) {
        List<List<Integer>> listOfIntLists = new ArrayList<>();
        for (Ball_color[] ballColorRow : ballColorArray) {
            List<Integer> intList = new ArrayList<>();
            for (Ball_color ballColor : ballColorRow) {
                intList.add(ballColor.ordinal()+1);
            }
            listOfIntLists.add(intList);
        }
        return listOfIntLists;
    }

}

