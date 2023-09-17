package test;

import domain.DifficultyNormal;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author javier
 * Testing of the class DifficultyNormal
 */
public class DifficultyNormalTest {
    private DifficultyNormal normal;

    /**
     * SetUps the DifficultyNormal test
     */
    @Before
    public void setUp() {
        normal = new DifficultyNormal();
    }

    /**
     * Checks that the value of algorithm set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalAlgorithm() {
        assertTrue(normal.getAlgorithm());
    }

    /**
     * Checks that the value of repeated_colors set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalRepeatedColors() {
        assertFalse(normal.getRepeatedColors());
    }

    /**
     * Checks that the value of number_positions set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalNumberPositions() {
        assertEquals(4, normal.getNumberPositions());
    }

    /**
     * Checks that the value of empty_colors set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalEmptyColors() {
        assertFalse(normal.getEmptyColors());
    }

    /**
     * Checks that the value of max_turns set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalMaxTurns() {
        assertEquals(8, normal.getMaxTurns());
    }

    /**
     * Checks that the value of multiplier set by default creating the class DifficultyNormal is correct
     */
    @Test
    public void testSetValuesNormalMultiplier() {
        assertEquals(1.0, normal.getMultiplier(), 0.001);
    }

}
