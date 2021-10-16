//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import execution.animation.Animation;

/**
 * KeyPressStoppableAnimation is a decorator of the "waiting" screens: pause, win and lost screens.
 * will add to those screens the ability of doing an operation once a key was pushed.
 */
public class KeyPressStoppableAnimation implements Animation {

    //fields
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the keyboard sensor.
     * @param key       the key which when pushed will stop the screen animation.
     * @param animation the animation that will gain the ability to act upon "key" being pushed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(this.key)) {
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
        if (this.sensor.isPressed(this.key)) {
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