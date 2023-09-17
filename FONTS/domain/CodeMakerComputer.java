package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CodeMakerComputer implements Computer{
    /**
     * Length code
     */
    private final int lengthCode;
    /**
     * Indicates if repeated colors are permitted
     */
    private final boolean repeatedColors;
    /**
     * The possible number of colors depending on the emptyColors value
     */
    private final int numColors;

    /**
     * CodeMakerComputer constructor
     * @param lengthCode Length code
     * @param emptyColors Indicates if empty spaces are permitted
     * @param repeatedColors Indicates if repeated colors are permitted
     */
    public CodeMakerComputer(int lengthCode, boolean emptyColors, boolean repeatedColors) {
        this.lengthCode = lengthCode;
        this.repeatedColors = repeatedColors;
        if (!emptyColors) numColors = 7;
        else numColors = 8;

    }

    /**
     * Generates a random secret code depending on the game options.
     * @param solution A null value - not used in the method.
     * @return A list containing a single list representing the random secret code.
     */
    public List<List<Integer>> solve(List<Integer> solution) {
        // Create a new empty list to hold the random secret code.
        List<List<Integer>> returnValue = new ArrayList<>();
        // Create a new empty list to hold the randomly generated integers.
        List<Integer> randomCode = new ArrayList<>();
        Random random = new Random();

        // Generate random integers until the desired length of the secret code is reached.
        while (randomCode.size() < lengthCode) {
            Integer color = random.nextInt(numColors+1);
            // If repeated colors are allowed or the color has not already been added to the list, add the color to the list.
            if (repeatedColors || !randomCode.contains(color)) randomCode.add(color);
        }
        // Shuffle the list of integers to ensure that the secret code is randomized.
        Collections.shuffle(randomCode);
        returnValue.add(randomCode);
        return returnValue;
    }

}
