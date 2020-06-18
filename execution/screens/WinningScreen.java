//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import execution.Animation;
import execution.Counter;

/**
 * PauseScreen is an animation that will take place once a game is won.
 */
public class WinningScreen implements Animation {

    //fields
    private Counter score;

    /**
     * Instantiates a new Winning screen.
     *
     * @param totalScore the total score the player achieved in the game.
     */
    public WinningScreen(Counter totalScore) {
        this.score = totalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
