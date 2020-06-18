package execution;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class LosingScreen implements Animation {

    //fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    public LosingScreen(KeyboardSensor k, Counter totalScore) {
        this.keyboard = k;
        this.stop = false;
        this.score = totalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
