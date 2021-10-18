//ID: 204351670
package execution.backgrounds;

import biuoop.DrawSurface;
import execution.animation.Utils;
import execution.levels.LevelInformation;
import execution.levels.LevelTwo;
import gameelements.sprites.Sprite;
import geometry.Point;


import java.awt.Color;


/**
 * The class LevelTwoBackground will construct the background for level 2 of the game.
 */
public class LevelTwoBackground implements Sprite {

    private static final int SUN_BIG_RADIUS = 50;
    private static final int SUN_MID_RADIUS = 40;
    private static final int SUN_SMALL_RADIUS = 30;

    private static final Color INERR_SUN_CIRCLE_COLOR = new Color(213, 200, 18);
    private static final Color MIDDLE_SUN_CIRCLE_COLOR = new Color(192, 182, 46);
    private static final Color OUTTER_SUN_CIRCLE_COLOR = new Color(229, 224, 153);

    private static final Color SUN_RAYS_COLOR = new Color(229, 224, 153);
    private static final int NUM_OF_SUN_RAYS = 100;
    private static final int SUN_RAYS_GAP = 10;


    private LevelInformation levelInformation = new LevelTwo();

    /**
     * Will color the background.
     *
     * @param d the surface which will the background of the level.
     */
    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, Utils.getWidth(), Utils.getHeight());
    }

    /**
     * Will draw sun rays onto the given surface.
     *
     * @param d the surface which will the rain will be draw on.
     */
    private void drawRays(DrawSurface d) {
        Point sunCenter = getSunCenter();
        double rowHeight = this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getY();

        //drawing rays.
        d.setColor(SUN_RAYS_COLOR);
        for (int i = 0; i < NUM_OF_SUN_RAYS; i++) {
            Point blockPoint = new Point(i * SUN_RAYS_GAP, rowHeight);
            d.drawLine((int) blockPoint.getX(), (int) blockPoint.getY(), (int) sunCenter.getX(),
                    (int) sunCenter.getY());
        }

    }

    /**
     * Will return the center point of the sun.
     *
     * @return the center point of the sun.
     */
    private Point getSunCenter() {
        double rowHeight = this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getY();
        double topEdgeOfScreen = Utils.getBorderThickness();
        double heightOfSunCenter = (rowHeight + topEdgeOfScreen) / 2.0;
        return new Point(Utils.getWidth() * 0.2, heightOfSunCenter);

    }

    /**
     * Will draw the sun onto the given surface.
     *
     * @param d the surface which will the sun will be draw on.
     */
    private void drawSun(DrawSurface d) {
        Point sunCenter = getSunCenter();

        //drawing sun layers
        d.setColor(OUTTER_SUN_CIRCLE_COLOR);
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_BIG_RADIUS);
        d.setColor(MIDDLE_SUN_CIRCLE_COLOR);
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_MID_RADIUS);
        d.setColor(INERR_SUN_CIRCLE_COLOR);
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_SMALL_RADIUS);
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);
        drawRays(d);
        drawSun(d);
    }

    /**
     * function that notify a sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
