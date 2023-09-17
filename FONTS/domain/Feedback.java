package domain;

import utils.*;
import domain.exceptions.*;

/**
 * @author javier
 * Feedback class of the game. This class will store the secret code and will give the feedback to the input codes.
 * This class will also provide small information of the secret code when a hint is requested.
 */
public class Feedback {

    //Data
    private Ball_color[] secret_code;

    //Constructor
    /**
     * Creates an empty Feedback class
     */
    public Feedback() {}

    //Setter
    /**
     * Updates the parameter "secret_code" in Feedback with the sent "sc"
     * @param sc The secret code to be saved in Feedback
     */
    public void setSecretCode(Ball_color[] sc) {
        secret_code = sc;
    }

    //Getter
    /**
     * Get the secret code saved in Feedback
     * @return Returns the Ball_color[] of the secret code
     */
    public Ball_color[] getSecretCode() {
        return secret_code;
    }

    //Other
    /**
     * Compare your sent code "code" with the secret code saved in Feedback
     * @param code The code wished to be compared with the secret code
     * @return Returns a pair<int,int> with the difference between the 2 codes. The first int represents a correct color in a correct position. The second int represents a correct color in an incorrect position.
     * @throws FeedbackSecretCodeLengthMismatchException The length of the secret code and the length of the given code is not the same
     */
    public Pair<Integer,Integer> compareSecretCode(Ball_color[] code) throws FeedbackSecretCodeLengthMismatchException {
        if (secret_code.length != code.length) throw new FeedbackSecretCodeLengthMismatchException();
        int result1 = 0;
        int result2 = 0;
        boolean[] checked_code = new boolean[code.length];
        boolean[] checked_secret = new boolean[secret_code.length];
        boolean stop;

        for (int i = 0; i < code.length; ++i) {
            if (code[i] == secret_code[i]) {
                ++result1;
                checked_code[i] = true;
                checked_secret[i] = true;
            }
        }
        if (result1 < secret_code.length) {
            for (int i = 0; i < code.length; ++i) {
                stop = false;
                for (int i2 = 0; i2 < secret_code.length && !checked_code[i] && !stop; ++i2) {
                    if (i != i2 && !checked_secret[i2] && code[i] == secret_code[i2]) {
                        ++result2;
                        stop = true;
                        checked_code[i] = true;
                        checked_secret[i2] = true;
                    }
                }
            }
        }
        return new Pair<>(result1, result2);
    }

    /**
     * Get the information of a correct color in his correct position
     * @return Returns a pair<int,Ball_color> with the position and the color respectively
     * @throws FeedbackHintIndexOutOfBoundsException The position returned on the Pair is lower than 0 or greater than the length of the secret code
     */
    public Pair<Integer,Ball_color> askHint() throws FeedbackHintIndexOutOfBoundsException {
        int pos = (int) Math.floor(Math.random() * secret_code.length);
        if (pos < 0 || pos >= secret_code.length) throw new FeedbackHintIndexOutOfBoundsException();
        return new Pair<>(pos, secret_code[pos]);
    }
}
