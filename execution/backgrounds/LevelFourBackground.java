package execution.backgrounds;

import biuoop.DrawSurface;
import execution.GameConstants;
import gameelemnts.sprites.Sprite;
import geometry.Point;

import java.awt.Color;


public class LevelFourBackground implements Sprite {


    private static final int NUMBER_OF_MINI_CLOUDS = 5;
    public LevelFourBackground() {

    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);

        //clouds center points.
        Point leftCloudCenter = new Point(GameConstants.getWidth() * 0.15, GameConstants.getHeight()*0.6);
        Point rightCloudCenter = new Point(GameConstants.getWidth() * 0.8, GameConstants.getHeight()*0.8);

        //drawing rain
        drawRain(d, leftCloudCenter,10);
        drawRain(d, rightCloudCenter,20);

        //drawing the clouds.
        drawClouds(d,leftCloudCenter);
        drawClouds(d,rightCloudCenter);



    }

    private void drawRain(DrawSurface d, Point leftCloudCenter, double rainAngle) {
        double distanceCloudToBottom = GameConstants.getHeight() - leftCloudCenter.getY();
        double horizontalIntervals = distanceCloudToBottom * Math.atan(Math.toRadians(rainAngle));

        int xCenterCloud = (int)leftCloudCenter.getX();
        int yCenterCloud = (int)leftCloudCenter.getY();

        int xCenterBottom = (int)(leftCloudCenter.getX() - horizontalIntervals);
        int yCenterBottom = GameConstants.getHeight();

        d.setColor(Color.WHITE);

        d.drawLine(xCenterCloud , yCenterCloud, xCenterBottom , yCenterBottom);

        for (int i=1; i<6; i++) {
            d.drawLine(xCenterCloud + i * 8, yCenterCloud, xCenterBottom + i * 8, yCenterBottom);
            d.drawLine(xCenterCloud - i * 8, yCenterCloud, xCenterBottom - i * 8, yCenterBottom);
        }
    }

    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(new Color(45,126,188));
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
    }

    private void drawClouds(DrawSurface d, Point leftCloudCenter) {
        Point[] miniCloudsCenterPoints = new Point[NUMBER_OF_MINI_CLOUDS];
        miniCloudsCenterPoints[0] = new Point(leftCloudCenter.getX() - 30, leftCloudCenter.getY());
        miniCloudsCenterPoints[1] = new Point(leftCloudCenter.getX() - 10, leftCloudCenter.getY() + 20);
        miniCloudsCenterPoints[2] = new Point(leftCloudCenter.getX(), leftCloudCenter.getY() - 10);
        miniCloudsCenterPoints[3] = new Point(leftCloudCenter.getX() + 30 , leftCloudCenter.getY());
        miniCloudsCenterPoints[4] = new Point(leftCloudCenter.getX() + 15 , leftCloudCenter.getY() + 20);

        int[] miniCloudsRadii = new int[NUMBER_OF_MINI_CLOUDS];
        miniCloudsRadii[0] = 20;
        miniCloudsRadii[1] = 20;
        miniCloudsRadii[2] = 25;
        miniCloudsRadii[3] = 30;
        miniCloudsRadii[4] = 15;

        for (int i = 0; i < NUMBER_OF_MINI_CLOUDS; i++) {
            d.setColor(getGrayColor(i));
            d.fillCircle((int)miniCloudsCenterPoints[i].getX(), (int)miniCloudsCenterPoints[i].getY(), miniCloudsRadii[i]);
        }
    }


    private Color getGrayColor(int index) {
        switch (index) {
            case 0:
            case 1:
                return new Color(204,208,204);
            case 2:
                return new Color(190,190,190);
            case 3:
            case 4:
                return new Color(180,180,180);
            default:
                return Color.WHITE;
        }
    }


    @Override
    public void timePassed() {

    }
}
