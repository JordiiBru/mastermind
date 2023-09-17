package test;

import domain.FiveGuessComputer;

import domain.exceptions.*;
import utils.Pair;
import org.junit.Test;
import static org.junit.Assert.*;;

import java.util.Arrays;
import java.util.List;

public class FiveGuessComputerTest {

    private FiveGuessComputer alg;

    // SOLVE tests

    /**
     * Test if usual game solves in 5 turns
     */
    @Test
    public void solveFiveMax() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(5,4,false,false);
        List<Integer> solution = Arrays.asList(1,2,5,0);

        List<List<Integer>> result = alg.solve(solution);
        assertTrue((result.size() - 1) <= 5);
    }

    /**
     * Test if repeated and empty game solves in 5 or less games
     */
    @Test
    public void solveFiveMaxRepeatedEmpty() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(5,4,true,true);
        List<Integer> solution = Arrays.asList(8,2,2,5);

        List<List<Integer>> result = alg.solve(solution);
        assertTrue((result.size() - 1) <= 5);
    }

    /**
     * Test if game with repeated colors is resolve in 5 or less steps
     */
    @Test
    public void solveFiveMaxRepeated() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(5,4,false,true);
        List<Integer> solution = Arrays.asList(5,1,2,5);

        List<List<Integer>> result = alg.solve(solution);
        assertTrue((result.size() - 1) <= 5);
    }

    /**
     * Test of a common game
     */
    @Test
    public void solveNormalSize() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(8,4,false,false);
        List<Integer> solution = Arrays.asList(6,2,7,3);

        List<List<Integer>> result = alg.solve(solution);
        assertEquals(solution,result.get(result.size()-1));
    }

    /**
     * Test trivial game
     */
    @Test
    public void solveTrivial() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(10, 4, true, true);
        List<Integer> solution = Arrays.asList(0, 0, 1, 1);

        List<List<Integer>> result = alg.solve(solution);
        assertEquals(solution, result.get(result.size() - 1));
    }

    /**
     * Test if the algorithm returns when not enough turns
     */
    @Test
    public void solveFewSteps() throws  DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(2,2,false,false);
        List<Integer> solution = Arrays.asList(1,5);

        List<List<Integer>> result = alg.solve(solution);
        assertEquals(2,result.size());
    }

    /**
     * Testea una partida con un alto nombre de combinaciones (TEST LARGO)
     */
    @Test
    public void solveMax() throws DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(10,5,false,true);
        List<Integer> solution = Arrays.asList(4,7,5,6,7);

        List<List<Integer>> result = alg.solve(solution);
        assertEquals(solution,result.get(result.size()-1));
    }




    // Solve exceptions

    /**
     * Repeated colors are not permitted and solution contains repeated colors
     */
    @Test(expected = NotRepeatedColorsException.class)
    public void solveNotRepeated() throws  DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(8,4,false,false);
        List<Integer> solution = Arrays.asList(1,2,2,5);

        List<List<Integer>> result = alg.solve(solution);
    }

    /**
     * Leaving empty spaces is not permitted and solution contains empty spaces
     */
    @Test(expected = NotEmptyColorsException.class)
    public void solveNotEmpty() throws  DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(2,4,false,false);
        List<Integer> solution = Arrays.asList(1,2,4,8);

        List<List<Integer>> result = alg.solve(solution);
    }

    /**
     * The length code option is different to the solution size
     */
    @Test(expected = DifferentLengthCodeException.class)
    public void solveDifferentLength() throws  DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(2,4,false,false);
        List<Integer> solution = Arrays.asList(1,2,4,5,6);

        List<List<Integer>> result = alg.solve(solution);
    }

    /**
     * Un-known color
     */
    @Test(expected = IncorrectColorException.class)
    public void solveIncorrectColor() throws  DifferentLengthCodeException, IncorrectColorException, NotRepeatedColorsException, NotEmptyColorsException {
        alg = new FiveGuessComputer(2,4,false,false);
        List<Integer> solution = Arrays.asList(1,2,4,10);

        List<List<Integer>> result = alg.solve(solution);
    }


    // "PRIVATE" functions tests

    @Test
    public void getFirstGuessUsual()  {
        alg = new FiveGuessComputer(8, 5, false, true);
        List<Integer> solution = Arrays.asList(0, 0, 1, 1, 2);

        assertEquals(solution, alg.getFirstGuess());

    }

    @Test
    public void compareCodesTest() {
        alg = new FiveGuessComputer(8,5,true,true);
        List<Integer> code1 = Arrays.asList(2,1,3,2,8);
        List<Integer> code2 = Arrays.asList(1,4,2,8,8);

        Pair<Integer,Integer> expected = new Pair<>(1,2);
        Pair<Integer,Integer> solution = alg.compareCodes(code1,code2);

        assertEquals(expected.first(), solution.first());
        //assertEquals(expected.second(), solution.second());
    }

    @Test
    public void compareCodesTest2() {
        alg = new FiveGuessComputer(8,8,true,true);
        List<Integer> code1 = Arrays.asList(2,1,3,2,8,1,4,7);
        List<Integer> code2 = Arrays.asList(1,4,2,8,8,6,4,3);

        Pair<Integer,Integer> expected = new Pair<>(2,5);
        Pair<Integer,Integer> solution = alg.compareCodes(code1,code2);

        assertEquals(expected.first(), solution.first());
        //assertEquals(expected.second(), solution.second());
    }


    @Test
    public void eliminateCodesUsual() {
        alg = new FiveGuessComputer(8,2,false,true);
        List<Integer> guess = Arrays.asList(1,2);
        Pair<Integer,Integer> fd = new Pair<>(1,0);
        List<List<Integer>> possibleCodes = Arrays.asList(Arrays.asList(1,1), Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(1,4), Arrays.asList(2,1), Arrays.asList(2,2), Arrays.asList(2,3), Arrays.asList(2,4), Arrays.asList(3,1), Arrays.asList(3,2), Arrays.asList(3,3), Arrays.asList(3,4), Arrays.asList(4,1), Arrays.asList(4,2), Arrays.asList(4,3), Arrays.asList(4,4));

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1,1), Arrays.asList(1,3), Arrays.asList(1,4), Arrays.asList(2,2), Arrays.asList(3,2), Arrays.asList(4,2));
        List<List<Integer>> result = alg.eliminateCodes(guess,fd,possibleCodes);

        assertEquals(expected.size(), result.size());
    }

    @Test
    public void eliminateCodesUsual2() {
        alg = new FiveGuessComputer(8,2,false,true);
        List<Integer> guess = Arrays.asList(4,1);
        Pair<Integer,Integer> fd = new Pair<>(0,1);
        List<List<Integer>> possibleCodes = Arrays.asList(Arrays.asList(1,1), Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(1,4), Arrays.asList(2,1), Arrays.asList(2,2), Arrays.asList(2,3), Arrays.asList(2,4), Arrays.asList(3,1), Arrays.asList(3,2), Arrays.asList(3,3), Arrays.asList(3,4), Arrays.asList(4,1), Arrays.asList(4,2), Arrays.asList(4,3), Arrays.asList(4,4));

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1,2), Arrays.asList(1,3), Arrays.asList(2,4), Arrays.asList(3,4));
        List<List<Integer>> result = alg.eliminateCodes(guess,fd,possibleCodes);

        assertEquals(expected.size(), result.size());
    }
}
