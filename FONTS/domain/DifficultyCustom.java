package domain;

import domain.exceptions.*;

/**
 * @author javier
 * DifficultyCustom class, extends Difficulty. This class sets a group of values considered "normal" into Difficulty class by default.
 * It allows to modify each single value of the class Difficulty to obtain a fully custom game.
 * This class algo recalculates the multiplier to fit the values selected.
 */
public class DifficultyCustom extends Difficulty {

    //Constructor
    /**
     * Creates a new DifficultyCustom subclass of Difficulty and sets the values to "Normal" default
     */
    public DifficultyCustom() {
        setValuesCustom();
    }

    //Setter
    /**
     * Sets the values of Difficulty to default (Normal Difficulty)
     */
    public void setValuesCustom() {
        algorithm = true;
        repeated_colors = false;
        number_positions = 4;
        empty_colors = false;
        max_turns = 8;
        multiplier = 1.0;
    }

    /**
     * Updates the parameter "algorithm" in Difficulty with the sent "alg"
     * @param alg boolean
     */
    public void setAlgCus(boolean alg) {
        algorithm = alg;
    }

    /**
     * Updates the parameter "repeated_colors" in Difficulty with the sent "rep_col"
     * @param rep_col boolean
     */
    public void setRepColCus(boolean rep_col) {
        repeated_colors = rep_col;
    }

    /**
     * Updates the parameter "number_positions" in Difficulty with the sent "num_pos"
     * @param num_pos integer
     */
    public void setNumPosCus(int num_pos) throws DifficultyNumberPositionsMismatchException {
        if (num_pos <= 0 || num_pos > 7) throw new DifficultyNumberPositionsMismatchException();
        number_positions = num_pos;
    }

    /**
     * Updates the parameter "empty_colors" in Difficulty with the sent "emp_col"
     * @param emp_col boolean
     */
    public void setEmptyColCus(boolean emp_col) {
        empty_colors = emp_col;
    }

    /**
     * Updates the parameter "max_turns" in Difficulty with the sent "max_tur"
     * @param max_tur integer
     */
    public void setMaxTurCus(int max_tur) throws DifficultyMaxTurnsMismatchException {
        if (max_tur <= 0 || max_tur > 10) throw new DifficultyMaxTurnsMismatchException();
        max_turns = max_tur;
    }

    /**
     * Recalculates the parameter "multiplier" in Difficulty using the values of the different parameters
     */
    public void setMulCus() {
        multiplier = 0.0;
        if (repeated_colors) multiplier += 4.0;
        if (number_positions >= 4) multiplier += Math.pow(2, number_positions-4) - 1;
        else multiplier += Math.pow(2, number_positions-4);
        if (empty_colors) multiplier += 2.0;
        if (max_turns >= 8) multiplier += Math.pow(2.0, (max_turns - 8) * -0.55);
        else multiplier += Math.pow(2.0, (max_turns - 8) * -0.55) - 1;
    }
}