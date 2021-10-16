//ID: 204351670
package execution.animation;

import biuoop.DrawSurface;

/**
 * The interface Animation. Represents a type of animation that takes place during the game.
 */
public interface Animation {
    /**
     * run a frame of the animation.
     *
     * @param d the surface which the animation will be draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * checks if the animation should stop, according to given conditions.
     *
     * @return 'true' if the animation should stop, 'false' otherwise.
     */
    boolean shouldStop();
}
