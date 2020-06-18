package execution.backgrounds;

import biuoop.DrawSurface;
import execution.GameConstants;
import execution.levels.LevelInformation;
import execution.levels.LevelThree;
import gameelemnts.sprites.Sprite;
import geometry.Point;

import java.awt.Color;

public class LevelThreeBackground implements Sprite {



    private LevelInformation levelInformation = new LevelThree();

    //building const
    private static final double BUILDING_UPPERLEFT_X =  GameConstants.getWidth() * 0.09;
    private static final double BUILDING_UPPERLEFT_Y =  GameConstants.getHeight()*0.7;
    private static final int BUILDING_WIDTH =  (int)(GameConstants.getWidth() * 0.125);
    private static final int BUILDING_HEIGHT =  (int)(GameConstants.getHeight() - BUILDING_UPPERLEFT_Y);

    //Window const
    private static final double WINDOW_WIDTH =  BUILDING_WIDTH * 0.1;
    private static final double WINDOW_HEIGHT =  BUILDING_HEIGHT * 0.14;

    //Antenna base const
    private static final double ANTENNA_BASE_WIDTH = BUILDING_WIDTH * 0.25;
    private static final double ANTENNA_BASE_HEIGHT = GameConstants.getHeight() * 0.06;
    private static final double ANTENNA_BASE_UPPERLEFT_Y = BUILDING_UPPERLEFT_Y - ANTENNA_BASE_HEIGHT;
    private static final double ANTENNA_BASE_UPPERLEFT_X =  (BUILDING_UPPERLEFT_X + (BUILDING_WIDTH + BUILDING_UPPERLEFT_X)) / 2.0
            - (ANTENNA_BASE_WIDTH / 2.0);

    //Antenna rod const
    private static final double ANTENNA_ROD_WIDTH = ANTENNA_BASE_WIDTH * 0.33;
    private static final double ANTENNA_ROD_HEIGHT = ANTENNA_BASE_HEIGHT * 4;
    private static final double ANTENNA_ROD_UPPERLEFT_Y = ANTENNA_BASE_UPPERLEFT_Y - ANTENNA_ROD_HEIGHT;
    private static final double ANTENNA_ROD_UPPERLEFT_X = ANTENNA_BASE_UPPERLEFT_X + ANTENNA_BASE_WIDTH*0.3;

    //Antenna light const
    private static final int LIGHT_BIG_RADIUS = 10;
    private static final int LIGHT_MID_RADIUS = 7;
    private static final int LIGHT_SMALL_RADIUS = 4;



    public LevelThreeBackground() {
    }


    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);
        drawBuilding(d);
        drawAntenna(d);
        drawAntennaLight(d);
    }

    private void drawAntennaLight(DrawSurface d) {
        Point lightCenter = getLightCenter();

        //drawing sun layers
        d.setColor(Color.orange);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_BIG_RADIUS);
        d.setColor(Color.RED);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_MID_RADIUS);
        d.setColor(Color.WHITE);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_SMALL_RADIUS);
    }

    private Point getLightCenter() {
        return new Point((int)(ANTENNA_ROD_UPPERLEFT_X + ANTENNA_ROD_WIDTH * 0.5), (int)ANTENNA_ROD_UPPERLEFT_Y);
    }

    private void drawAntenna(DrawSurface d) {
        //drawing antenna base
        Point antennaBaseLeftCorner = new Point(ANTENNA_BASE_UPPERLEFT_X, ANTENNA_BASE_UPPERLEFT_Y);
        d.setColor(new Color(41,41,41));
        d.fillRectangle((int)antennaBaseLeftCorner.getX(),(int)antennaBaseLeftCorner.getY(),
                (int)ANTENNA_BASE_WIDTH , (int)ANTENNA_BASE_HEIGHT);

        //drawing antenna rod
        Point antennaRodLeftCorner = new Point(ANTENNA_ROD_UPPERLEFT_X, ANTENNA_ROD_UPPERLEFT_Y);
        d.setColor(new Color(61,61,61));
        d.fillRectangle((int)antennaRodLeftCorner.getX(),(int)antennaRodLeftCorner.getY(),
                (int)ANTENNA_ROD_WIDTH , (int)ANTENNA_ROD_HEIGHT);
    }

    private void drawBuilding(DrawSurface d) {
        //draw building
        Point buildingUpperLeft = new Point(BUILDING_UPPERLEFT_X, BUILDING_UPPERLEFT_Y);
        d.setColor(Color.BLACK);
        d.fillRectangle((int)buildingUpperLeft.getX(),(int)buildingUpperLeft.getY(), BUILDING_WIDTH , BUILDING_HEIGHT);

        //draw windows
        Point upperLeftWindowCorner = new Point(buildingUpperLeft.getX() + 8, buildingUpperLeft.getY() + 8);
        double windowYCoordinate = upperLeftWindowCorner.getY();
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            double windowXCoordinate = upperLeftWindowCorner.getX();

            for (int j = 0; j< 5; j++) {
                d.fillRectangle((int)(windowXCoordinate + j * WINDOW_WIDTH),
                        (int)(windowYCoordinate + i * WINDOW_HEIGHT),(int)WINDOW_WIDTH,(int)WINDOW_HEIGHT);
                windowXCoordinate = windowXCoordinate + 8.5;
            }
            windowYCoordinate= windowYCoordinate + 5;
        }

    }

    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(new Color(0, 130, 0));
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
    }

    /**
     * function that notify a sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
