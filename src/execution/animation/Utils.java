//ID: 204351670

package execution.animation;

import geometry.Point;

import java.awt.Color;
import java.util.Random;

/**
 * The class GameConstant will hold all kind of constant variable of the game that will not be changed.
 * also, will hold some generic function used in the project.
 */
public class Utils {
    //screen size constants.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //the thickness is set to be a percentage of the screen width.
    private static final double BORDER_THICKNESS_FACTOR = 0.04;

    /**
     * Gets the game screen width.
     *
     * @return the width
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * Gets the game screen height.
     *
     * @return the height
     */
    public static int getHeight() {
        return HEIGHT;
     }

    /**
     * Gets the game screen border thickness.
     *
     * @return the border thickness
     */
    public static double getBorderThickness() {
        return BORDER_THICKNESS_FACTOR * WIDTH;
    }

    /**
     * Gets the game screen center point.
     *
     * @return the middle of screen point.
     */
    public static Point getCenterOfScreen() {
        return new Point((int) (Utils.getWidth() / 2.0),
                (int) (Utils.getHeight() / 2.0));
    }

    /**
     * Gets a random color.
     *
     * @return a random color.
     */
    public static Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        return new Color(r, g, b);
    }
}
