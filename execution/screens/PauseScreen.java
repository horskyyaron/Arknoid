package execution.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import execution.Animation;

public class PauseScreen implements Animation {

    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
