package execution.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import execution.Animation;

public class KeyPressStoppableAnimation implements Animation {

    //fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if(this.sensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                return;
            }
        } else {
            isAlreadyPressed = false;
            this.animation.doOneFrame(d);
        }

    }

    @Override
    public boolean shouldStop() {
        if(this.sensor.isPressed(this.key)) {
            if (isAlreadyPressed) {
                return false;
            } else {
                return this.animation.shouldStop();
            }
        } else {
            return false;
        }
    }
}