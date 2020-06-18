//ID: 204351670
package execution.backgrounds;

import biuoop.DrawSurface;
import execution.GameConstants;
import execution.levels.LevelInformation;
import execution.levels.LevelOne;
import gameelemnts.sprites.Sprite;
import geometry.Point;

import java.awt.Color;

/**
 * The class LevelOneBackground will construct the background for level 1 of the game.
 */
public class LevelOneBackground implements Sprite {

    private static final int BIG_RADIUS = 90;
    private static final int MID_RADIUS = 70;
    private static final int SMALL_RADIUS = 50;

    private static final int LINE_LENGTH = 110;
    private static final int DELETION_LINE_LENGTH = 30;


    //field.
    private LevelInformation levelInformation = new LevelOne();

    /**
     * Will draw target lines around the center block of the leve.
     *
     * @param d the surface which will the target lines will be draw on.
     */
    private void drawTargetLines(DrawSurface d) {
        Point centerOfBlock = getCenterOfLevelBlock();

        //draw long lines.
        d.setColor(Color.blue);
        d.drawCircle((int) centerOfBlock.getX(), (int) centerOfBlock.getY(), SMALL_RADIUS);
        d.drawCircle((int) centerOfBlock.getX(), (int) centerOfBlock.getY(), MID_RADIUS);
        d.drawCircle((int) centerOfBlock.getX(), (int) centerOfBlock.getY(), BIG_RADIUS);
        d.drawLine((int) centerOfBlock.getX() - LINE_LENGTH, (int) centerOfBlock.getY(),
                (int) centerOfBlock.getX() + LINE_LENGTH, (int) centerOfBlock.getY());
        d.drawLine((int) centerOfBlock.getX(), (int) centerOfBlock.getY() - LINE_LENGTH,
                (int) centerOfBlock.getX(), (int) centerOfBlock.getY() + LINE_LENGTH);

        //will draw black line to create gap from the center block.
        d.setColor(Color.BLACK);
        d.drawLine((int) centerOfBlock.getX() - DELETION_LINE_LENGTH, (int) centerOfBlock.getY(),
                (int) centerOfBlock.getX() + DELETION_LINE_LENGTH, (int) centerOfBlock.getY());
        d.drawLine((int) centerOfBlock.getX(), (int) centerOfBlock.getY() - DELETION_LINE_LENGTH,
                (int) centerOfBlock.getX(), (int) centerOfBlock.getY() + DELETION_LINE_LENGTH);
    }

    /**
     * Will return the center point of the level's block.
     *
     * @return the block's center point.
     */
    private Point getCenterOfLevelBlock() {
        return this.levelInformation.blocks().get(0).getCollisionRectangle().getCenterOfRec();
    }

    /**
     * Will color the background of the level.
     *
     * @param d the surface which the background of the level will be drawn on.
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
        drawTargetLines(d);
    }

    @Override
    public void timePassed() {

    }
}
