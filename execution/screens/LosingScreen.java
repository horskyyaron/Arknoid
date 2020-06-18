//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import execution.Animation;
import execution.Counter;
import execution.GameConstants;

/**
 * LosingScreen is an animation that will take place once a game is lost.
 */
public class LosingScreen implements Animation {

    private static final int FONT_SIZE = 32;
    private static final double STRING_LEFT_EDGE_COORDINATE = GameConstants.getWidth() * 0.2;
    private static final double STRING_HEIGHT = GameConstants.getHeight() / 2.0;


    //fields
    private Counter score;

    /**
     * Instantiates a new Losing screen.
     *
     * @param totalScore the total score the player achieved until lost the game.
     */
    public LosingScreen(Counter totalScore) {
        this.score = totalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText((int) STRING_LEFT_EDGE_COORDINATE, (int) STRING_HEIGHT, "Game Over. Your score is "
                + this.score.getValue(), FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
