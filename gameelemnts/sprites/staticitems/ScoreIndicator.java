//ID: 204351670

package gameelemnts.sprites.staticitems;

import biuoop.DrawSurface;
import execution.GameConstants;
import execution.levels.LevelInformation;
import execution.Counter;
import gameelemnts.sprites.Sprite;
import java.awt.Color;

/**
 * ScoreIndicator is an object that will keep the game score,
 * will support method that will draw it onto the screen.
 */
public class ScoreIndicator implements Sprite {
    //Font size.
    private static final int FONT_SIZE = 15;
    private static final int SCORE_TEXT_X_COORDINATE = GameConstants.getWidth() / 2 - 20;
    private static final int SCORE_TEXT_Y_COORDINATE = (int) GameConstants.getBorderThickness() / 2 + 5;

    //fields
    private Counter score;
    private LevelInformation levelInfo;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     */
    public ScoreIndicator(Counter scoreCounter, LevelInformation levelInformation) {
        this.score = scoreCounter;
        this.levelInfo = levelInformation;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        //will draw the score text onto the input surface.
        d.drawText(SCORE_TEXT_X_COORDINATE, SCORE_TEXT_Y_COORDINATE,
                "Score:" + this.score.getValue() +"                            Level Name:"
                        + this.levelInfo.levelName(), FONT_SIZE);

    }

    @Override
    public void timePassed() {
        //empty function. does nothing.
    }

}
