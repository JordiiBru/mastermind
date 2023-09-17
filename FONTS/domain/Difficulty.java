package domain;

/**
 * @author javier
 * Difficulty class of the game. This class provides the information of the difficulty settings.
 */
public class Difficulty {

    //Data
    protected boolean algorithm;
    protected boolean repeated_colors;
    protected int number_positions;
    protected boolean empty_colors;
    protected int max_turns;
    protected double multiplier;

    //Constructor
    /**
     * Creates an empty Difficulty class
     */
    public Difficulty() {}

    //Getter
    /**
     * Getter of the value of "algorithm"
     * @return Returns a boolean. True -> 5Guess, False -> Genetic
     */
    public boolean getAlgorithm() {
        return algorithm;
    }

    /**
     * Getter of the value of "repeated_colors"
     * @return Returns a boolean. True -> repeated colors are allowed, False -> repeated colors are NOT allowed
     */
    public boolean getRepeatedColors() {
        return repeated_colors;
    }

    /**
     * Getter of the value of "number_positions"
     * @return Returns an integer with the number of positions in the board that the player will be playing with. The value of this integer is also the length of the code that will be introduced by the player.
     */
    public int getNumberPositions() {
        return number_positions;
    }

    /**
     * Getter of the value of "void_colors"
     * @return Returns a boolean. True -> empty colors are allowed, False -> empty colors are NOT allowed
     */
    public boolean getEmptyColors() {
        return empty_colors;
    }

    /**
     * Getter of the value of "max_turns"
     * @return Returns an integer with the number of maximum turns in the current game. The value of this integer is also the maximum height of the board.
     */
    public int getMaxTurns() {
        return max_turns;
    }

    /**
     * Getter of the value of "multiplier"
     * @return Returns a double with the value of the multiplier of the current game
     */
    public double getMultiplier() {
        return multiplier;
    }
}
