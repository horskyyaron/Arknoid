//ID: 204351670

import biuoop.DrawSurface;

/**
 * Sprite object is an object that's being drawn to screen, and can be notified
 * that time has passed.
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * function that draws a sprite on a given surface.
     *
     * @param surface the surface that the sprite will be drawn on.
     * @throws Exception if a Sprite a has Point object component in it,
     *                   and the point will have negative values.
     */
    void drawOn(DrawSurface surface) throws Exception;

    /**
     * function that notify a sprite that time has passed.
     *
     * @throws Exception if a Sprite a has Point object component in it,
     *                   and the point will have negative values after time
     *                   passed.
     */
    void timePassed() throws Exception;
}