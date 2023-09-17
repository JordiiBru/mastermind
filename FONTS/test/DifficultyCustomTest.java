package test;

import domain.DifficultyCustom;
import domain.exceptions.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author javier
 * Testing of the class DifficultyCustom
 */
public class DifficultyCustomTest {
    private DifficultyCustom custom;

    /**
     * SetUps the DifficultyCustom test
     */
    @Before
    public void setUp() {
        custom = new DifficultyCustom();
    }

    /**
     * Checks that the value of algorithm set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomAlgorithm() {
        custom.setAlgCus(false);
        assertFalse(custom.getAlgorithm());
    }

    /**
     * Checks that the value of repeated_colors set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomRepeatedColors() {
        custom.setRepColCus(true);
        assertTrue(custom.getRepeatedColors());
    }

    /**
     * Checks that the value of number_positions set in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     */
    @Test
    public void testSetValuesCustomNumberPositions() throws DifficultyNumberPositionsMismatchException {
        try {
            custom.setNumPosCus(7);
        }
        catch (DifficultyNumberPositionsMismatchException e) {
            throw new DifficultyNumberPositionsMismatchException();
        }
    }

    /**
     * Checks that the value of number_positions set in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     */
    @Test (expected = DifficultyNumberPositionsMismatchException.class)
    public void testSetValuesCustomNumberPositionsNegativeGrater() throws DifficultyNumberPositionsMismatchException {
        try {
            custom.setNumPosCus(9);
        }
        catch (DifficultyNumberPositionsMismatchException e) {
            throw new DifficultyNumberPositionsMismatchException();
        }
    }

    /**
     * Checks that the value of number_positions set in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     */
    @Test (expected = DifficultyNumberPositionsMismatchException.class)
    public void testSetValuesCustomNumberPositionsNegativeLower() throws DifficultyNumberPositionsMismatchException {
        try {
            custom.setNumPosCus(0);
        }
        catch (DifficultyNumberPositionsMismatchException e) {
            throw new DifficultyNumberPositionsMismatchException();
        }
    }

    /**
     * Checks that the value of empty_colors set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomEmptyColors() {
        custom.setEmptyColCus(true);
        assertTrue(custom.getEmptyColors());
    }

    /**
     * Checks that the value of max_turns set in the class DifficultyCustom is correct
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test
    public void testSetValuesCustomMaxTurns() throws DifficultyMaxTurnsMismatchException {
        try {
            custom.setMaxTurCus(6);
        }
        catch (DifficultyMaxTurnsMismatchException e) {
            throw new DifficultyMaxTurnsMismatchException();
        }
    }

    /**
     * Checks that the value of max_turns set in the class DifficultyCustom is correct
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test (expected = DifficultyMaxTurnsMismatchException.class)
    public void testSetValuesCustomMaxTurnsNegativeLower() throws DifficultyMaxTurnsMismatchException {
        try {
            custom.setMaxTurCus(-1);
        }
        catch (DifficultyMaxTurnsMismatchException e) {
            throw new DifficultyMaxTurnsMismatchException();
        }
    }

    /**
     * Checks that the value of max_turns set in the class DifficultyCustom is correct
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test (expected = DifficultyMaxTurnsMismatchException.class)
    public void testSetValuesCustomMaxTurnsNegativeGreater() throws DifficultyMaxTurnsMismatchException {
        try {
            custom.setMaxTurCus(11);
        }
        catch (DifficultyMaxTurnsMismatchException e) {
            throw new DifficultyMaxTurnsMismatchException();
        }
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomMultiplier1() {
        assertEquals(1.0, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomMultiplier3() {
        custom.setEmptyColCus(true);
        custom.setMulCus();
        assertEquals(3.0, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     */
    @Test
    public void testSetValuesCustomMultiplier5() {
        custom.setRepColCus(true);
        custom.setMulCus();
        assertEquals(5.0, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test
    public void testSetValuesCustomMultiplier13() throws DifficultyMaxTurnsMismatchException {
        custom.setMaxTurCus(1);
        custom.setMulCus();
        assertEquals(13.42, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     */
    @Test
    public void testSetValuesCustomMultiplier8() throws DifficultyNumberPositionsMismatchException {
        custom.setNumPosCus(7);
        custom.setMulCus();
        assertEquals(8.0, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier set in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test
    public void testSetValuesCustomMultiplierLowest() throws DifficultyNumberPositionsMismatchException, DifficultyMaxTurnsMismatchException {
        custom.setNumPosCus(1);
        custom.setMaxTurCus(10);
        custom.setMulCus();
        assertEquals(0.591, custom.getMultiplier(), 0.001);
    }

    /**
     * Checks that the value of multiplier calculated in the class DifficultyCustom is correct
     * @throws DifficultyNumberPositionsMismatchException
     * @throws DifficultyMaxTurnsMismatchException
     */
    @Test
    public void testSetValuesCustomMultiplierHighest() throws DifficultyNumberPositionsMismatchException, DifficultyMaxTurnsMismatchException {
        custom.setRepColCus(true);
        custom.setNumPosCus(7);
        custom.setEmptyColCus(true);
        custom.setMaxTurCus(1);
        custom.setMulCus();
        assertEquals(26.42, custom.getMultiplier(), 0.001);
    }
}
