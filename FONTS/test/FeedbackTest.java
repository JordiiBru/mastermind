package test;

import domain.Feedback;
import domain.exceptions.*;
import utils.Ball_color;
import utils.Pair;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author javier
 * Testing of the class Feedback
 */
public class FeedbackTest {
    private Feedback feedback;

    private final Ball_color[] sc1 = {Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
    private final Ball_color[] sc2 = {Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
    private final Ball_color[] sc3 = {Ball_color.Green, Ball_color.Yellow, Ball_color.Blue, Ball_color.Red};
    private final Ball_color[] sc4 = {Ball_color.Green, Ball_color.Blue, Ball_color.Red, Ball_color.Yellow};
    private final Ball_color[] sc5 = {Ball_color.Green, Ball_color.Blue, Ball_color.Yellow, Ball_color.Red};
    private final Ball_color[] sc6 = {Ball_color.Red, Ball_color.Red, Ball_color.Red, Ball_color.Red};
    private final Ball_color[] sc7 = {Ball_color.Red, Ball_color.Green, Ball_color.Blue, Ball_color.Yellow};
    private final Ball_color[] sc8 = {Ball_color.Blue, Ball_color.Yellow, Ball_color.Blue, Ball_color.Yellow, Ball_color.Blue, Ball_color.Yellow, Ball_color.Blue, Ball_color.Yellow};
    private final Ball_color[] sc9 = {Ball_color.Red, Ball_color.Green, Ball_color.Blue, Ball_color.Yellow, Ball_color.Purple, Ball_color.Orange, Ball_color.Green, Ball_color.Pink};
    private final Ball_color[] sc10 = {Ball_color.Blue, Ball_color.Blue, Ball_color.Green, Ball_color.Green, Ball_color.Blue};
    private final Ball_color[] sc11 = {Ball_color.Yellow, Ball_color.Purple, Ball_color.Red, Ball_color.Orange, Ball_color.Blue};
    private final Ball_color[] sc12 = {Ball_color.Yellow, Ball_color.Purple, Ball_color.Green, Ball_color.Red, Ball_color.Blue};

    /**
     * SetUps the Feedback test
     */
    @Before
    public void setUp() {
        feedback = new Feedback();
    }

    /**
     * Checks that the code set with setSecretCode is correct
     */
    @Test
    public void testSetSecretCode() {
        feedback.setSecretCode(sc1);
        assertEquals(sc1,feedback.getSecretCode());
    }

    /**
     * Checks that the code set with setSecretCode is not correct
     */
    @Test
    public void testSetSecretCodeNegative() {
        feedback.setSecretCode(sc1);
        assertNotEquals(sc2,feedback.getSecretCode());
    }

    /**
     * Checks that the secret code has the same length as the code sent to compare
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testLengthMismatch() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc1);
        try {
            feedback.compareSecretCode(sc6);
        }
        catch (FeedbackSecretCodeLengthMismatchException e) {
            throw new FeedbackSecretCodeLengthMismatchException();
        }
    }

    /**
     * Checks that the secret code has not the same length as the code sent to compare
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test(expected = FeedbackSecretCodeLengthMismatchException.class)
    public void testLengthMismatchNegative() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc1);
        try {
            feedback.compareSecretCode(sc2);
        }
        catch (FeedbackSecretCodeLengthMismatchException e) {
            throw new FeedbackSecretCodeLengthMismatchException();
        }
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultEmpty() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc1);
        Pair p = feedback.compareSecretCode(sc1);
        assertEquals(4, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4Equals() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc6);
        Pair p = feedback.compareSecretCode(sc6);
        assertEquals(4, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4Different() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc6);
        Pair p = feedback.compareSecretCode(sc1);
        assertEquals(0, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc6);
        Pair p = feedback.compareSecretCode(sc7);
        assertEquals(1, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4_2() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc5);
        Pair p = feedback.compareSecretCode(sc7);
        assertEquals(0, p.first());
        assertEquals(4, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4_3() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc5);
        Pair p = feedback.compareSecretCode(sc4);
        assertEquals(2, p.first());
        assertEquals(2, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize4_4() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc3);
        Pair p = feedback.compareSecretCode(sc4);
        assertEquals(1, p.first());
        assertEquals(3, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize8() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc2);
        Pair p = feedback.compareSecretCode(sc8);
        assertEquals(0, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize8_2() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc8);
        Pair p = feedback.compareSecretCode(sc8);
        assertEquals(8, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize8_3() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc8);
        Pair p = feedback.compareSecretCode(sc9);
        assertEquals(2, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize5() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc10);
        Pair p = feedback.compareSecretCode(sc11);
        assertEquals(1, p.first());
        assertEquals(0, p.second());
    }

    /**
     * Checks that the result given by compareSecretCode in Feedback returns the correct value
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    @Test
    public void testFeedbackResultSize5_2() throws FeedbackSecretCodeLengthMismatchException {
        feedback.setSecretCode(sc12);
        Pair p = feedback.compareSecretCode(sc11);
        assertEquals(3, p.first());
        assertEquals(1, p.second());
    }

    /**
     * Checks that the position given by the askHint function is not out of bounds of the secret code itself
     * @throws FeedbackHintIndexOutOfBoundsException The position returned on the Pair is lower than 0 or greater than the length of the secret code
     */
    @Test
    public void testFeedbackAskHintBounds() throws FeedbackHintIndexOutOfBoundsException {
        feedback.setSecretCode(sc7);
        try {
            feedback.askHint();
        }
        catch (FeedbackHintIndexOutOfBoundsException e) {
            throw new FeedbackHintIndexOutOfBoundsException();
        }
    }

    /**
     * Checks that the position of the secret_code contains the Color given
     * @throws FeedbackHintIndexOutOfBoundsException The position returned on the Pair is lower than 0 or greater than the length of the secret code
     */
    @Test
    public void testFeedbackAskHintValue() throws FeedbackHintIndexOutOfBoundsException {
        feedback.setSecretCode(sc7);
        Pair p = feedback.askHint();
        int first = (int) p.first();
        Ball_color second = (Ball_color) p.second();
        assertEquals(sc7[first], second);
    }
}
