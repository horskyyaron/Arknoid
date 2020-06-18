//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import execution.Animation;
import execution.GameConstants;

/**
 * PauseScreen is an animation that will take place once a game is stopped.
 */
public class PauseScreen implements Animation {

    private static final int FONT_SIZE = 32;
    private static final double STRING_LEFT_EDGE_COORDINATE = GameConstants.getWidth() * 0.2;
    private static final double STRING_HEIGHT = GameConstants.getHeight() / 2.0;


    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText((int) STRING_LEFT_EDGE_COORDINATE, (int) STRING_HEIGHT, "paused -- press space to continue",
                FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
