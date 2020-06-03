//ID: 204351670

package gameelemnts.sprites.staticitems;

import biuoop.DrawSurface;
import execution.Game;
import execution.listeners.Counter;
import gameelemnts.sprites.Sprite;
import java.awt.Color;

/**
 * ScoreIndicator is an object that will keep the game score,
 * will support method that will draw it onto the screen.
 */
public class ScoreIndicator implements Sprite {
    //Font size.
    private static final int FONT_SIZE = 15;

    //fields
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        //will draw the score text onto the input surface.
        d.drawText(Game.getWIDTH() / 2, ((int) Game.getBorderThickness() / 2),
                "Score:" + this.score.getValue(), FONT_SIZE);
    }

    @Override
    public void timePassed() {
        //empty function. does nothing.
    }

}
