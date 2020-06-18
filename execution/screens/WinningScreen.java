package execution.screens;

import biuoop.DrawSurface;
import execution.Animation;
import execution.Counter;

public class WinningScreen implements Animation {

    //fields
    private Counter score;

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
