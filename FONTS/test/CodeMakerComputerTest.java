package test;

import domain.CodeMakerComputer;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class CodeMakerComputerTest {

    private CodeMakerComputer c;

    /**
     * Test purpose method
     */
    private boolean hasRepeatedColors(List<Integer> code) {
        HashSet<Integer> colors = new HashSet<>(code);
        return code.size() > colors.size();
    }

    /**
     * Test purpose method
     */
    private boolean hasEmptyColors(List<Integer> code) {
        for (Integer s : code) {
            if (Objects.equals(s, 8)) return true;
        }
        return false;
    }

    @Test
    public void solveNotEmpty() {
        c = new CodeMakerComputer(5,false,true);
        assertFalse(hasEmptyColors(c.solve(null).get(0)));
    }

    @Test
    public void solveNotRepeated() {
        c = new CodeMakerComputer(4,true,false);
        assertFalse(hasRepeatedColors(c.solve(null).get(0)));
    }

    @Test
    public void solveLength() {
        c = new CodeMakerComputer(3,true,true);
        assertEquals(3, c.solve(null).get(0).size());
    }

}
