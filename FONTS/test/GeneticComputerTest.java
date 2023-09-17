package test;

import domain.GeneticComputer;

import domain.exceptions.*;
import utils.Pair;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class GeneticComputerTest {

    private GeneticComputer alg;
    @Before
    public void setUp() {
        int maxSteps = 10;
        int lengthCode = 4;
        boolean emptyColors = false;
        boolean repeatedColors = false;
        alg = new GeneticComputer(maxSteps, lengthCode, emptyColors, repeatedColors);
    }

    @Test
    public void testSolveNotRepeated() throws Exception {
        // Define the secret code
        List<Integer> solution = Arrays.asList(1, 2, 3, 4);
        List<List<Integer>> gameHistory = alg.solve(solution);

        List<Integer> lastGuess = gameHistory.get(gameHistory.size() - 1);
        assertEquals(solution, lastGuess);
    }

    @Test
    public void testSolveRepeated() throws Exception {
        alg = new GeneticComputer(10, 4, false, true);
        List<Integer> solution = Arrays.asList(1, 2, 3, 3);
        List<List<Integer>> gameHistory = alg.solve(solution);

        List<Integer> lastGuess = gameHistory.get(gameHistory.size() - 1);
        assertEquals(solution, lastGuess);
    }

    @Test(expected = NotRepeatedColorsException.class)
    public void testSolveWithInvalidSolution() throws Exception {
        List<Integer> invalidSolution = Arrays.asList(1, 1, 2, 3);
        alg.solve(invalidSolution);
    }

    @Test
    public void testCompareCodes() {
        List<Integer> solution = Arrays.asList(1, 2, 3, 4);
        List<Integer> guess = Arrays.asList(1, 3, 2, 5);

        Pair<Integer, Integer> feedback = alg.compareCodes(solution, guess);
        assertEquals(1, feedback.first().intValue());
        assertEquals(2, feedback.second().intValue());
    }

    @Test
    public void testCompareCodesWithEmptyCodes() {
        List<Integer> emptySolution = List.of();
        List<Integer> emptyGuess = List.of();

        Pair<Integer, Integer> feedback = alg.compareCodes(emptySolution, emptyGuess);
        assertEquals(0, feedback.first().intValue());
        assertEquals(0, feedback.second().intValue());
    }

    @Test
    public void testCalculateSingleFitness() {
        List<List<Integer>> gameHistory = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 1, 2, 3)
        );
        List<Pair<Integer, Integer>> prevFeedback = Arrays.asList(
                new Pair<>(2, 1),
                new Pair<>(0, 4),
                new Pair<>(1, 2)
        );

        alg.setGameHistory(gameHistory);
        alg.setPrevFeedback(prevFeedback);

        List<Integer> code = Arrays.asList(2, 4, 6, 8);
        double fitness = alg.calculateSingleFitness(code);

        assertEquals(-13.0, fitness, 0.01);
    }

    @Test
    public void testSortPopulationByFitness() {
        List<List<Integer>> population = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 1, 2, 3)
        );
        List<Double> fitness = Arrays.asList(10.0, 5.0, 7.0);

        alg.setPopulation(population);
        alg.setFitness(fitness);

        List<List<Integer>> sortedPopulation = alg.sortPopulationByFitness(population);

        List<List<Integer>> expectedPopulation = Arrays.asList(
                Arrays.asList(1, 2, 3, 4), // 1st
                Arrays.asList(9, 1, 2, 3), // 2nd
                Arrays.asList(5, 6, 7, 8)  // 3rd
        );
        assertEquals(expectedPopulation, sortedPopulation);
    }


}
