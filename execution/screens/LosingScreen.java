package execution.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import execution.Animation;
import execution.Counter;

public class LosingScreen implements Animation {

    //fields
    private Counter score;

    public LosingScreen(Counter totalScore) {
        this.score = totalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
