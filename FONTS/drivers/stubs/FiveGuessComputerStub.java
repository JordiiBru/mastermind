package drivers.stubs;

import java.util.*;

public class FiveGuessComputerStub {

    public String FiveGuessComputer(int maxSteps, int lengthCode, boolean emptyColors, boolean repeatedColors) {
        return "An instance of FiveGuessComputer with maxSteps " + maxSteps + ", lengthCode " + lengthCode + ", emptyColors " + emptyColors + ", repeatedColors " + repeatedColors + " has been created successfully.";
    }

    public List<List<Integer>> solve(List<Integer> solution) {
        List<List<Integer>> returnValue = new ArrayList<>();
        List<Integer> randomCode = new ArrayList<>();
        randomCode.add(0);
        randomCode.add(0);
        randomCode.add(0);
        randomCode.add(0);
        returnValue.add(randomCode);
        return returnValue;
    }

}
