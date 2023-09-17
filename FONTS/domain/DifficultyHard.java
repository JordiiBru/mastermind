package domain;

/**
 * @author javier
 * DifficultyHard class, extends Difficulty. This class sets a group of values considered "hard" into Difficulty class
 */
public class DifficultyHard extends Difficulty {

    //Constructor
    /**
     * Creates a new DifficultyHard subclass of Difficulty and sets the values to "Hard" default
     */
    public DifficultyHard() {
        setValuesHard();
    }

    //Setter
    /**
     * Sets all the values of Difficulty with the Hard Difficulty values pack
     */
    public void setValuesHard() {
        algorithm = false;
        repeated_colors = false;
        number_positions = 7;
        empty_colors = false;
        max_turns = 8;
        multiplier = 8.0;
    }
}