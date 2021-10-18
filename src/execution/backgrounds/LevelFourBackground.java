//ID: 204351670
package execution.backgrounds;

import biuoop.DrawSurface;
import execution.animation.Utils;
import gameelements.sprites.Sprite;
import geometry.Point;

import java.awt.Color;


/**
 * The class LevelFourBackground will construct the background for level 4 of the game.
 */
public class LevelFourBackground implements Sprite {


    private static final int NUMBER_OF_MINI_CLOUDS = 5;

    private static final double LEFT_RAIN_ANGLE = 10;
    private static final double RIGHT_RAIN_ANGLE = LEFT_RAIN_ANGLE * 2;
    private static final int NUMBER_OF_RAIN_RAYS_HALF = 6;
    private static final int RAIN_RAYS_GAP = 8;

    private static final Color BACKGROUND_COLOR = new Color(45, 126, 188);

    private static final Color DARK_GRAY = new Color(180, 180, 180);
    private static final Color REGULAR_GRAY = new Color(190, 190, 190);
    private static final Color LIGHT_GRAY = new Color(204, 208, 204);

    private static final double RADIUS_EXPANSION_RATE = 0.07;
    private static final double MAX_EXPANTION = 4;

    private static final double MOVING_RATE = 0.02;

    private static double radiusFactor = 1;
    private static boolean isExpanding = true;

    private static double movingFactor = 1;
    private static boolean isMovingRight = true;


    /**
     * Instantiates a new Level four background.
     */
    public LevelFourBackground() {

    }

    /**
     * Will draw rain onto the given surface.
     *
     * @param d the surface which will the rain will be draw on.
     * @param cloudCenterPoint the center of the clouds, which will also be the
     *                         center point of the rain rays.
     * @param rainAngle the angle which the rain rays will be drawn.
     */
    private void drawRain(DrawSurface d, Point cloudCenterPoint, double rainAngle) {
        double distanceCloudToBottom = Utils.getHeight() - cloudCenterPoint.getY();
        double horizontalIntervals = distanceCloudToBottom * Math.atan(Math.toRadians(rainAngle));

        if (isMovingRight) {
            movingFactor = movingFactor + 0.5;
            if (movingFactor + cloudCenterPoint.getX() >= cloudCenterPoint.getX() * 2) {
                isMovingRight = false;
            }
        } else {
            movingFactor = movingFactor - 0.5;
            if (movingFactor + cloudCenterPoint.getX() <= cloudCenterPoint.getX() * 0.5) {
                isMovingRight = true;
            }
        }


        int xCenterCloud = (int) (cloudCenterPoint.getX() + movingFactor);
        int yCenterCloud = (int) cloudCenterPoint.getY();

        int xCenterBottom = (int) (cloudCenterPoint.getX() - horizontalIntervals + movingFactor);
        int yCenterBottom = Utils.getHeight();

        d.setColor(Color.WHITE);

        d.drawLine(xCenterCloud , yCenterCloud, xCenterBottom , yCenterBottom);

        for (int i = 1; i < NUMBER_OF_RAIN_RAYS_HALF; i++) {
            d.drawLine(xCenterCloud + i * RAIN_RAYS_GAP, yCenterCloud, xCenterBottom + i * RAIN_RAYS_GAP,
                    yCenterBottom);
            d.drawLine(xCenterCloud - i * RAIN_RAYS_GAP, yCenterCloud, xCenterBottom - i * RAIN_RAYS_GAP,
                    yCenterBottom);
        }
    }

    /**
     * Will color the background.
     *
     * @param d the surface which will the background of the level.
     */
    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(BACKGROUND_COLOR);
        d.fillRectangle(0, 0, Utils.getWidth(), Utils.getHeight());
    }

    /**
     * Will draw "mini clouds" around the cloud center. onto the the given surface.
     *
     * @param d the surface which will the rain will be draw on.
     * @param cloudCenterPoint the center of the clouds, which will also be the
     */
    private void drawMiniClouds(DrawSurface d, Point cloudCenterPoint) {
        Point[] miniCloudsCenterPoints = new Point[NUMBER_OF_MINI_CLOUDS];

        if (isExpanding) {
            radiusFactor = radiusFactor + RADIUS_EXPANSION_RATE;
            if (radiusFactor >= MAX_EXPANTION) {
                isExpanding = false;
            }
        } else {
            radiusFactor = radiusFactor - RADIUS_EXPANSION_RATE;
            if (radiusFactor <= 0) {
                isExpanding = true;
            }
        }

        if (isMovingRight) {
            movingFactor = movingFactor + MOVING_RATE;
            if (movingFactor + cloudCenterPoint.getX() >= cloudCenterPoint.getX() * 2) {
                isMovingRight = false;
            }
        } else {
            movingFactor = movingFactor - 0.5;
            if (movingFactor + cloudCenterPoint.getX() <= cloudCenterPoint.getX() * 0.5) {
                isMovingRight = true;
            }
        }

        cloudCenterPoint = new Point((int) (cloudCenterPoint.getX() + movingFactor), (int) cloudCenterPoint.getY());

        miniCloudsCenterPoints[0] = new Point(cloudCenterPoint.getX() - 30, cloudCenterPoint.getY());
        miniCloudsCenterPoints[1] = new Point(cloudCenterPoint.getX() - 10, cloudCenterPoint.getY() + 20);
        miniCloudsCenterPoints[2] = new Point(cloudCenterPoint.getX(), cloudCenterPoint.getY() - 10);
        miniCloudsCenterPoints[3] = new Point(cloudCenterPoint.getX() + 30 , cloudCenterPoint.getY());
        miniCloudsCenterPoints[4] = new Point(cloudCenterPoint.getX() + 15 , cloudCenterPoint.getY() + 20);

        int[] miniCloudsRadii = new int[NUMBER_OF_MINI_CLOUDS];

        //radii of the mini clouds.
        miniCloudsRadii[0] = (int) (20 + radiusFactor);
        miniCloudsRadii[1] = (int) (20 + radiusFactor);
        miniCloudsRadii[2] = (int) (25 + radiusFactor);
        miniCloudsRadii[3] = (int) (30 + radiusFactor);
        miniCloudsRadii[4] = (int) (15 + radiusFactor);

        for (int i = 0; i < NUMBER_OF_MINI_CLOUDS; i++) {
            d.setColor(getGrayColor(i));
            d.fillCircle((int) miniCloudsCenterPoints[i].getX(), (int) miniCloudsCenterPoints[i].getY(),
                    miniCloudsRadii[i]);
        }
    }

    /**
     * will return a gray color according to a given integer.
     *
     * @param index the integer that will determine which color of gray will be returned.
     * @return a kind of gray color. (light, regular or dark)
     */
    private Color getGrayColor(int index) {
        switch (index) {
            case 0:
            case 1:
                return LIGHT_GRAY;
            case 2:
                return REGULAR_GRAY;
            case 3:
            case 4:
                return DARK_GRAY;
            default:
                return Color.WHITE;
        }
    }



    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);

        //clouds center points.
        Point leftCloudCenter = new Point(Utils.getWidth() * 0.15, Utils.getHeight() * 0.6);
        Point rightCloudCenter = new Point(Utils.getWidth() * 0.8, Utils.getHeight() * 0.8);

        //drawing rain
        drawRain(d, leftCloudCenter, LEFT_RAIN_ANGLE);
        drawRain(d, rightCloudCenter, RIGHT_RAIN_ANGLE);

        //drawing the clouds.
        drawMiniClouds(d, leftCloudCenter);
        drawMiniClouds(d, rightCloudCenter);
    }


    @Override
    public void timePassed() {

    }
}
