package gameelemnts.sprites.staticitems;

import biuoop.DrawSurface;
import execution.Game;
import execution.listeners.Counter;
import gameelemnts.sprites.Sprite;
import java.awt.Color;

public class ScoreIndicator implements Sprite {
    //const.
    private static final int FONT_SIZE = 15;

    //fields
    private Counter score;

    //constructor.
    public ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    /**
     * function that draws a sprite on a given surface.
     *
     * @param d the surface that the sprite will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(Game.getWIDTH()/2, ((int)Game.getBorderThickness()/2),
                "Score:" + this.score.getValue(), FONT_SIZE);
    }

    /**
     * function that notify a sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //do nothing.
    }

}
