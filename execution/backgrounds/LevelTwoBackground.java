package execution.backgrounds;

import biuoop.DrawSurface;
import execution.GameConstants;
import execution.levels.LevelInformation;
import execution.levels.LevelTwo;
import gameelemnts.sprites.Sprite;
import geometry.Point;


import java.awt.*;


public class LevelTwoBackground implements Sprite {

    private static final int SUN_BIG_RADIUS = 50;
    private static final int SUN_MID_RADIUS = 40;
    private static final int SUN_SMALL_RADIUS = 30;


    private LevelInformation levelInformation = new LevelTwo();

    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);
        drawRays(d);
        drawSun(d);
    }

    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
    }

    private void drawRays(DrawSurface d) {
        Point sunCenter = getSunCenter();
        double rowHeight = this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getY();
        d.setColor(new Color(229,224,153));
        for (int i = 0; i < 100; i++) {
            Point blockPoint = new Point(i * 10, rowHeight);
            d.drawLine((int)blockPoint.getX(),(int)blockPoint.getY(),(int)sunCenter.getX(),(int)sunCenter.getY());
        }

    }

    //getting the sun center point
    private Point getSunCenter() {
        double rowHeight = this.levelInformation.blocks().get(0).getCollisionRectangle().getUpperLeft().getY();
        double topEdgeOfScreen = GameConstants.getBorderThickness();
        double heightOfSunCenter = (rowHeight + topEdgeOfScreen) / 2.0;
        return new Point(GameConstants.getWidth() * 0.2, heightOfSunCenter);

    }

    private void drawSun(DrawSurface d) {
        Point sunCenter = getSunCenter();

        //drawing sun layers
        d.setColor(new Color(229,224,153));
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_BIG_RADIUS);
        d.setColor(new Color(192,182,46));
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_MID_RADIUS);
        d.setColor(new Color(213,200,18));
        d.fillCircle((int) sunCenter.getX(), (int) sunCenter.getY(), SUN_SMALL_RADIUS);
    }

    /**
     * function that notify a sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
