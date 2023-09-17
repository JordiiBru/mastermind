package test;

import domain.DifficultyHard;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author javier
 * Testing of the class DifficultyHard
 */
public class DifficultyHardTest {
    private DifficultyHard hard;

    /**
     * SetUps the DifficultyHard test
     */
    @Before
    public void setUp() {
        hard = new DifficultyHard();
    }

    /**
     * Checks that the value of algorithm set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardAlgorithm() {
        assertTrue(hard.getAlgorithm());
    }

    /**
     * Checks that the value of repeated_colors set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardRepeatedColors() {
        assertFalse(hard.getRepeatedColors());
    }

    /**
     * Checks that the value of number_positions set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardNumberPositions() {
        assertEquals(7, hard.getNumberPositions());
    }

    /**
     * Checks that the value of empty_colors set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardEmptyColors() {
        assertFalse(hard.getEmptyColors());
    }

    /**
     * Checks that the value of max_turns set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardMaxTurns() {
        assertEquals(8, hard.getMaxTurns());
    }

    /**
     * Checks that the value of multiplier set by default creating the class DifficultyHard is correct
     */
    @Test
    public void testSetValuesHardMultiplier() {
        assertEquals(8.0, hard.getMultiplier(), 0.001);
    }

}
