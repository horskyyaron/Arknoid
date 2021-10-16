//ID: 204351670

package gameelements.sprites;

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
     * @param d the surface that the sprite will be drawn on.
     */
    void drawOn(DrawSurface d);

    /**
     * function that notify a sprite that time has passed.
     */
    void timePassed();
}