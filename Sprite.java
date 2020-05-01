import biuoop.DrawSurface;

public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d) throws Exception;

    // notify the sprite that time has passed
    void timePassed() throws Exception;
}