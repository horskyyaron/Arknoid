//ID: 204351670
package execution.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class will take care of the animation process, meaning will draw each give frame
 * per second given.
 */
public class AnimationRunner {

    //fields
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui             the gui which holds the game.
     * @param framesPerSecond the frames per second ratio given.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }


    /**
     * will run a given animation according to the frame per seconds decided on construction.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            //draw frame.
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
