package domain;

/**
 * @author javier
 * DifficultyNormal class, extends Difficulty. This class sets a group of values considered "normal" into Difficulty class
 */
public class DifficultyNormal extends Difficulty {

    //Constructor
    /**
     * Creates a new DifficultyNormal subclass of Difficulty and sets the values to "Normal" default
     */
    public DifficultyNormal() {
        setValuesNormal();
    }

    //Setter
    /**
     * Sets all the values of Difficulty with the Normal Difficulty values pack
     */
    public void setValuesNormal() {
        algorithm = true;
        repeated_colors = false;
        number_positions = 4;
        empty_colors = false;
        max_turns = 8;
        multiplier = 1.0;
    }
}
