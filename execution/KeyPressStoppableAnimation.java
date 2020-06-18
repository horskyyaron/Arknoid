package execution;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        boolean shouldStopIndicator = this.animation.shouldStop();
        while (!shouldStopIndicator)
        if (this.sensor.isPressed(this.key)) {
            shouldStopIndicator = shouldStop();
        } else {
            this.animation.doOneFrame(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }


}