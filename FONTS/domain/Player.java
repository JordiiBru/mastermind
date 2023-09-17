package domain;

import utils.*;

import domain.exceptions.NotEmptyColorsException;
import domain.exceptions.NotRepeatedColorsException;
import domain.exceptions.PositionOutOfBoundsException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author marcel
 */
public class Player {
    /**
     * Player's name
     */
    private String name;
    /**
     * PLayer's role
     */
    private Mode role;
    /**
     * Player's current code
     */
    private Ball_color[] current_code;

    /**
     *
     * @return If the current code has repeated colors
     */
    private boolean hasRepeatedColors() {
        HashSet<Ball_color> colors = new HashSet<>(List.of(current_code));
        return colors.size() < current_code.length;
    }

    /**
     *
     * @return If the current code has empty spaces
     */
    private boolean hasEmptyColors() {
        for (Ball_color s : current_code) {
            if (Objects.equals(s, Ball_color.Empty)) return true;
        }
        return false;
    }

    /**
     * Empty constructor
     */
    public Player(){}

    /**
     * Constructor that sets the player's name
     * @param name Name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Adds color color on the current code in the position position
     * @param position Position where to locate the color
     * @param color Color to entry
     * @throws PositionOutOfBoundsException The position indicated is out of bounds of the code
     */
    public void addColor(int position, Ball_color color) throws PositionOutOfBoundsException {
        if (position < 0 || position >= current_code.length) {
            throw new PositionOutOfBoundsException();
        }
        if (color == null) color = Ball_color.Empty;

        current_code[position] = color;
    }

    /**
     * Cleans the current code leaving it EMPTY
     * @throws PositionOutOfBoundsException The position indicated is out of bounds of the code
     */
    public void cleanCode() throws PositionOutOfBoundsException {
        for (int position = 0; position < current_code.length; ++position) {
            try {
                addColor(position,Ball_color.Empty);
            }
            catch (PositionOutOfBoundsException e) {
                throw new PositionOutOfBoundsException();
            }
        }
    }

    /**
     *
     * @return The confirmed code to add it in the board
     * @throws NotRepeatedColorsException Repeating colors is not available
     * @throws NotEmptyColorsException Leaving empty spaces is not available
     */
   public Ball_color[] confirmedCode(boolean repeatedColors, boolean emptyColors) throws NotRepeatedColorsException, NotEmptyColorsException {
        if (!repeatedColors && this.hasRepeatedColors()) {
            throw new NotRepeatedColorsException();
        }
        else if (!emptyColors && this.hasEmptyColors()) {
            throw new NotEmptyColorsException();
        }

        return this.current_code;
    }

    /**
     * Initialize the current code with the number of elements of the code.
     * @throws PositionOutOfBoundsException The position indicated is out of bounds of the code
     */
    public void initCode(int numElements) throws PositionOutOfBoundsException {
        this.current_code = new Ball_color[numElements];
        this.cleanCode();
    }

    /**
     * Name getter
     * @return Player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Role getter
     * @return Player's role
     */
    public Mode getRole() {
        return this.role;
    }

    /**
     * Current code getter
     * @return Player's current code
     */
    public Ball_color[] getCurrentCode() {
        return current_code;
    }

    /**
     * Setter player's role
     * @param role Player's role
     */
    public void setRole(Mode role) {
        this.role = role;
    }

    /**
     * Setter player's current code
     * @param code Player's current code
     */
    public void setCurrentCode(Ball_color[] code) {
        this.current_code = code;
    }

}

