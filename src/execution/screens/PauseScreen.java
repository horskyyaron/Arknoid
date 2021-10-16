//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import execution.animation.Animation;
import execution.animation.GameConstants;
import geometry.Point;

import java.awt.Color;

/**
 * PauseScreen is an animation that will take place once a game is stopped.
 */
public class PauseScreen implements Animation {

    private static final int FONT_SIZE = 32;
    private static final double STRING_LEFT_EDGE_COORDINATE = GameConstants.getWidth() * 0.2;
    private static final double STRING_HEIGHT = GameConstants.getHeight() * 0.13;

    private static final int BIG_RADIUS = 200;
    private static final int SMALL_RADIUS = 190;

    private static final double LINES_GAP = 30;
    private static final int LINES_HEIGHT = 150;
    private static final int LINES_WIDTH = 40;

    private static double radiusFactor = 1;
    private static boolean isExpanding = true;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        drawPauseSymbol(d);

        d.setColor(GameConstants.getRandomColor());
        d.drawText((int) STRING_LEFT_EDGE_COORDINATE, (int) STRING_HEIGHT, "paused -- press space to continue",
                FONT_SIZE);
    }

    /**
     * the function will draw the pause symbol onto the given surface.
     *
     * @param d the surface which the pause symbol will be draw on.
     */
    private void drawPauseSymbol(DrawSurface d) {
        Point centerOfScreen = GameConstants.getCenterOfScreen();
        d.setColor(Color.BLACK);
        d.fillCircle((int) centerOfScreen.getX(), (int) centerOfScreen.getY(), (int) (BIG_RADIUS * radiusFactor));
        d.setColor(Color.WHITE);
        d.fillCircle((int) centerOfScreen.getX(), (int) centerOfScreen.getY(), (int) (SMALL_RADIUS * radiusFactor));
        drawPauseMiddleLines(d);

        if (isExpanding) {
            radiusFactor = radiusFactor + 0.02;
            if (radiusFactor >= 1.4) {
                isExpanding = false;
            }
        } else {
            radiusFactor = radiusFactor - 0.02;
            if (radiusFactor <= 0.75) {
                isExpanding = true;
            }
        }
    }

    /**
     * the function will draw the pause symbol - lines onto the given surface.
     *
     * @param d the surface which the pause symbol- lines will be draw on.
     */
    private void drawPauseMiddleLines(DrawSurface d) {
        Point centerOfScreen = GameConstants.getCenterOfScreen();

        d.setColor(Color.BLACK);
        d.fillRectangle((int) (centerOfScreen.getX() - 1.8 * LINES_GAP),
                (int) (centerOfScreen.getY() - LINES_HEIGHT / 2),
                LINES_WIDTH, LINES_HEIGHT);
        d.fillRectangle((int) (centerOfScreen.getX() + 0.8 * LINES_GAP),
                (int) (centerOfScreen.getY() - LINES_HEIGHT / 2),
                LINES_WIDTH, LINES_HEIGHT);
    }


    @Override
    public boolean shouldStop() {
        return true;
    }
}
