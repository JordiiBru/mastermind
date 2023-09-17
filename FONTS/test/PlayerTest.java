package test;

import utils.Ball_color;
import domain.Player;

import domain.exceptions.NotEmptyColorsException;
import domain.exceptions.PositionOutOfBoundsException;
import domain.exceptions.NotRepeatedColorsException;

import org.junit.*;


import static org.junit.Assert.*;

/**
 * @author marcel
 */
public class PlayerTest {
    private Player p;
    private Ball_color[] colors_array;

    /**
     * Sets up everithing a Player test needs
     */
    @Before
    public void setUp() {
        p = new Player();
        colors_array = new Ball_color[]{Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
    }

    // addColor Tests

    /**
     * Test adding a usual color on a usual position
     * @throws PositionOutOfBoundsException
     */
    @Test
    public void addColorUsual() throws PositionOutOfBoundsException {
        p.setCurrentCode(colors_array);

        final int pos = 3;
        final Ball_color col = Ball_color.Red;

        p.addColor(pos,col);
        assertEquals(Ball_color.Red,p.getCurrentCode()[pos]);
    }

    /**
     * Add color on a negative position of the current code
     * @throws PositionOutOfBoundsException
     */
    @Test(expected = PositionOutOfBoundsException.class)
    public void addColorNegative() throws PositionOutOfBoundsException {
        p.setCurrentCode(colors_array);

        final int pos = -1;
        final Ball_color col = Ball_color.Red;

        p.addColor(pos,col);
    }

    /**
     * Add color on a position already modified
     * @throws PositionOutOfBoundsException
     */
    @Test
    public void addColorModified() throws PositionOutOfBoundsException {
        colors_array = new Ball_color[]{Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Blue};
        p.setCurrentCode(colors_array);

        final int pos = 3;
        final Ball_color col = Ball_color.Red;

        p.addColor(pos,col);
        assertEquals(Ball_color.Red,p.getCurrentCode()[pos]);
    }

    /**
     * Add color on an outer bounds positions
     * @throws PositionOutOfBoundsException
     */
    @Test(expected = PositionOutOfBoundsException.class)
    public void addColorOutOfBounds() throws PositionOutOfBoundsException {
        p.setCurrentCode(colors_array);

        final int pos = 5;
        final Ball_color col = Ball_color.Red;

        p.addColor(pos,col);
    }

    /**
     * Add color on an outer bounds positions
     * @throws PositionOutOfBoundsException
     */
    @Test
    public void addColorBigNumber() throws PositionOutOfBoundsException {
        colors_array = new Ball_color[]{Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty,Ball_color.Empty, Ball_color.Empty, Ball_color.Empty, Ball_color.Empty};
        p.setCurrentCode(colors_array);

        final int pos = 10;
        final Ball_color col = Ball_color.Red;

        p.addColor(pos,col);
    }

    /**
     * Add null color
     * @throws PositionOutOfBoundsException
     */
    @Test
    public void addColorNullColor() throws PositionOutOfBoundsException {
        p.setCurrentCode(colors_array);

        final int pos = 0;
        Ball_color col = null;

        p.addColor(pos,col);
        assertEquals(Ball_color.Empty,p.getCurrentCode()[pos]);
    }

    //confirmedCode Tests

    /**
     * Confirm code with an empty color while not permitted
     * @throws NotEmptyColorsException
     * @throws NotRepeatedColorsException
     */
    @Test(expected = NotEmptyColorsException.class )
    public void confirmedCodeNotEmptyColor() throws NotEmptyColorsException, NotRepeatedColorsException {
        colors_array = new Ball_color[]{Ball_color.Red, Ball_color.Blue, Ball_color.Orange, Ball_color.Empty};
        p.setCurrentCode(colors_array);
        boolean empty_colors = false;
        boolean repeated_colors = true;

        final Ball_color[] result = p.confirmedCode(repeated_colors, empty_colors);
    }

    /**
     * Confirm code with empty color while permitted
     * @throws NotEmptyColorsException
     * @throws NotRepeatedColorsException
     */
    @Test
    public void confirmedCodeEmptyColor() throws NotEmptyColorsException, NotRepeatedColorsException {
        colors_array = new Ball_color[]{Ball_color.Red, Ball_color.Blue, Ball_color.Orange, Ball_color.Empty};
        p.setCurrentCode(colors_array);
        boolean empty_colors = true;
        boolean repeated_colors = true;

        Ball_color[] expected = new Ball_color[]{Ball_color.Red, Ball_color.Blue, Ball_color.Orange, Ball_color.Empty};

        assertEquals(expected,p.confirmedCode(repeated_colors, empty_colors));
    }

    /**
     * Confirm code with repeated color while not permitted
     * @throws NotEmptyColorsException
     * @throws NotRepeatedColorsException
     */
    @Test(expected = NotRepeatedColorsException.class)
    public void confirmedCodeNotRepeatedColor() throws NotEmptyColorsException ,NotRepeatedColorsException {
        colors_array = new Ball_color[]{Ball_color.Red, Ball_color.Empty, Ball_color.Orange, Ball_color.Empty};
        p.setCurrentCode(colors_array);
        boolean empty_colors = true;
        boolean repeated_colors = false;

        final Ball_color[] result = p.confirmedCode(repeated_colors, empty_colors);
    }

    /**
     * Usual confirmed color with empty and repeated permitted
     * @throws NotEmptyColorsException
     * @throws NotRepeatedColorsException
     */
    @Test
    public void confirmedCodeRepeatedColor() throws NotEmptyColorsException ,NotRepeatedColorsException {
        colors_array = new Ball_color[]{Ball_color.Red, Ball_color.Empty, Ball_color.Orange, Ball_color.Empty};
        p.setCurrentCode(colors_array);
        boolean empty_colors = true;
        boolean repeated_colors = true;

        final Ball_color[] expected = new Ball_color[]{Ball_color.Red, Ball_color.Empty, Ball_color.Orange, Ball_color.Empty};

        assertEquals(expected,p.confirmedCode(repeated_colors, empty_colors));
    }
}
