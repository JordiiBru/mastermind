package drivers.stubs;

import java.util.ArrayList;
import java.util.List;

public class CodeMakerComputerStub {

    public String CodeMakerComputer(int lengthCode, boolean emptyColors, boolean repeatedColors) {
        return "An instance of CodeMakerComputer with lengthCode " + lengthCode + ", emptyColors " + emptyColors + ", repeatedColors " + repeatedColors + ", has been created successfully.";
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
