//ID: 204351670
package execution.backgrounds;

import biuoop.DrawSurface;
import execution.animation.GameConstants;
import execution.levels.LevelInformation;
import execution.levels.LevelThree;
import gameelements.sprites.Sprite;
import geometry.Point;

import java.awt.Color;

/**
 * The class LevelThreeBackground will construct the background for level 3 of the game.
 */
public class LevelThreeBackground implements Sprite {


    private LevelInformation levelInformation = new LevelThree();

    //building const
    private static final double BUILDING_UPPERLEFT_X =  GameConstants.getWidth() * 0.09;
    private static final double BUILDING_UPPERLEFT_Y =  GameConstants.getHeight() * 0.7;
    private static final int BUILDING_WIDTH =  (int) (GameConstants.getWidth() * 0.125);
    private static final int BUILDING_HEIGHT =  (int) (GameConstants.getHeight() - BUILDING_UPPERLEFT_Y);

    //Window const
    private static final double WINDOW_WIDTH =  BUILDING_WIDTH * 0.1;
    private static final double WINDOW_HEIGHT =  BUILDING_HEIGHT * 0.14;
    private static final int WINDOW_GAP_FROM_BUILDING = 8;
    private static final double WINDOW_GAP_BETWEEN_WINDOWS_HORIZONTAL = 8.5;
    private static final double WINDOW_GAP_BETWEEN_WINDOWS_VERTICAL = 5.0;
    private static final int WINDOWS_IN_A_ROW = 5;
    private static final int NUMBER_OF_WINDOW_ROWS = 6;

    //Antenna base const
    private static final double ANTENNA_BASE_WIDTH = BUILDING_WIDTH * 0.25;
    private static final double ANTENNA_BASE_HEIGHT = GameConstants.getHeight() * 0.06;
    private static final double ANTENNA_BASE_UPPERLEFT_Y = BUILDING_UPPERLEFT_Y - ANTENNA_BASE_HEIGHT;
    private static final double ANTENNA_BASE_UPPERLEFT_X =  (BUILDING_UPPERLEFT_X
            + (BUILDING_WIDTH + BUILDING_UPPERLEFT_X)) / 2.0 - (ANTENNA_BASE_WIDTH / 2.0);

    //Antenna rod const
    private static final double ANTENNA_ROD_WIDTH = ANTENNA_BASE_WIDTH * 0.33;
    private static final double ANTENNA_ROD_HEIGHT = ANTENNA_BASE_HEIGHT * 4;
    private static final double ANTENNA_ROD_UPPERLEFT_Y = ANTENNA_BASE_UPPERLEFT_Y - ANTENNA_ROD_HEIGHT;
    private static final double ANTENNA_ROD_UPPERLEFT_X = ANTENNA_BASE_UPPERLEFT_X + ANTENNA_BASE_WIDTH * 0.3;

    //Antenna light const
    private static final int LIGHT_BIG_RADIUS = 10;
    private static final int LIGHT_MID_RADIUS = 7;
    private static final int LIGHT_SMALL_RADIUS = 4;

    private static final Color BACKGROUND_COLOR = new Color(0, 130, 0);
    private static final Color ANTENNA_BASE_COLOR = new Color(41, 41, 41);
    private static final Color ANTENNA_ROD_COLOR = new Color(61, 61, 61);

    private static double radiusFactor = 1;
    private static boolean isExpanding = true;

    private static final int LIGHT_WAVE_BIG_RADIUS = 25;
    private static final int LIGHT_WAVE_MID_RADIUS = 20;
    private static final int LIGHT_WAVE_SMALL_RADIUS = 15;
    private static final int LIGHT_WAVE_GAP = 2;





    /**
     * Instantiates a new Level three background.
     */
    public LevelThreeBackground() {
    }


    /**
     * Will draw Antenna Light onto the given surface.
     *
     * @param d the surface which will the antenna light will be draw on.
     */
    private void drawAntennaLight(DrawSurface d) {

        Point lightCenter = getLightCenter();

        //DRAWING WAVES.
        d.setColor(Color.BLACK);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) (LIGHT_WAVE_BIG_RADIUS * radiusFactor));
        d.setColor(BACKGROUND_COLOR);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) ((LIGHT_WAVE_BIG_RADIUS - LIGHT_WAVE_GAP)
                * radiusFactor));

        d.setColor(Color.BLACK);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) (LIGHT_WAVE_MID_RADIUS * radiusFactor));
        d.setColor(BACKGROUND_COLOR);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) ((LIGHT_WAVE_MID_RADIUS - LIGHT_WAVE_GAP)
                * radiusFactor));

        d.setColor(Color.BLACK);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) (LIGHT_WAVE_SMALL_RADIUS
                * radiusFactor));
        d.setColor(BACKGROUND_COLOR);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), (int) ((LIGHT_WAVE_SMALL_RADIUS
                - LIGHT_WAVE_GAP)
                * radiusFactor));

        if (isExpanding) {
            radiusFactor = radiusFactor + 0.02;
            if (radiusFactor >= 3) {
                isExpanding = false;
            }
        } else {
            radiusFactor = radiusFactor - 0.02;
            if (radiusFactor <= 0.75) {
                isExpanding = true;
            }
        }

        //drawing antenna rod
        Point antennaRodLeftCorner = new Point(ANTENNA_ROD_UPPERLEFT_X, ANTENNA_ROD_UPPERLEFT_Y);
        d.setColor(ANTENNA_ROD_COLOR);
        d.fillRectangle((int) antennaRodLeftCorner.getX(), (int) antennaRodLeftCorner.getY(),
                (int) ANTENNA_ROD_WIDTH , (int) ANTENNA_ROD_HEIGHT);

        //drawing sun layers
        d.setColor(Color.orange);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_BIG_RADIUS);
        d.setColor(Color.RED);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_MID_RADIUS);
        d.setColor(Color.WHITE);
        d.fillCircle((int) lightCenter.getX(), (int) lightCenter.getY(), LIGHT_SMALL_RADIUS);




    }

    /**
     * Will return the center point of the antenna's lights.
     *
     * @return the center point. of the antenna's lights.
     */
    private Point getLightCenter() {
        return new Point((int) (ANTENNA_ROD_UPPERLEFT_X + ANTENNA_ROD_WIDTH * 0.5), (int) ANTENNA_ROD_UPPERLEFT_Y);
    }

    /**
     * Will draw Antenna onto the given surface.
     *
     * @param d the surface which will the antenna will be draw on.
     */
    private void drawAntenna(DrawSurface d) {
        //drawing antenna base
        Point antennaBaseLeftCorner = new Point(ANTENNA_BASE_UPPERLEFT_X, ANTENNA_BASE_UPPERLEFT_Y);
        d.setColor(ANTENNA_BASE_COLOR);
        d.fillRectangle((int) antennaBaseLeftCorner.getX(), (int) antennaBaseLeftCorner.getY(),
                (int) ANTENNA_BASE_WIDTH , (int) ANTENNA_BASE_HEIGHT);

        //drawing antenna rod
        Point antennaRodLeftCorner = new Point(ANTENNA_ROD_UPPERLEFT_X, ANTENNA_ROD_UPPERLEFT_Y);
        d.setColor(ANTENNA_ROD_COLOR);
        d.fillRectangle((int) antennaRodLeftCorner.getX(), (int) antennaRodLeftCorner.getY(),
                (int) ANTENNA_ROD_WIDTH , (int) ANTENNA_ROD_HEIGHT);
    }

    /**
     * Will draw building onto the given surface.
     *
     * @param d the surface which will the building will be draw on.
     */
    private void drawBuilding(DrawSurface d) {
        //draw building
        Point buildingUpperLeft = new Point(BUILDING_UPPERLEFT_X, BUILDING_UPPERLEFT_Y);
        d.setColor(Color.BLACK);
        d.fillRectangle((int) buildingUpperLeft.getX(), (int) buildingUpperLeft.getY(), BUILDING_WIDTH ,
                BUILDING_HEIGHT);

        //draw windows
        Point upperLeftWindowCorner = new Point(buildingUpperLeft.getX() + WINDOW_GAP_FROM_BUILDING,
                buildingUpperLeft.getY() + WINDOW_GAP_FROM_BUILDING);
        double windowYCoordinate = upperLeftWindowCorner.getY();
        d.setColor(Color.WHITE);
        for (int i = 0; i < NUMBER_OF_WINDOW_ROWS; i++) {
            double windowXCoordinate = upperLeftWindowCorner.getX();

            for (int j = 0; j < WINDOWS_IN_A_ROW; j++) {
                d.fillRectangle((int) (windowXCoordinate + j * WINDOW_WIDTH),
                        (int) (windowYCoordinate + i * WINDOW_HEIGHT), (int) WINDOW_WIDTH, (int) WINDOW_HEIGHT);
                windowXCoordinate = windowXCoordinate + WINDOW_GAP_BETWEEN_WINDOWS_HORIZONTAL;
            }
            windowYCoordinate = windowYCoordinate + WINDOW_GAP_BETWEEN_WINDOWS_VERTICAL;
        }

    }

    /**
     * Will color the background.
     *
     * @param d the surface which will the background of the level.
     */
    private void drawBackgroundColor(DrawSurface d) {
        d.setColor(BACKGROUND_COLOR);
        d.fillRectangle(0, 0, GameConstants.getWidth(), GameConstants.getHeight());
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawBackgroundColor(d);
        drawBuilding(d);
        drawAntenna(d);
        drawAntennaLight(d);
    }

    /**
     * function that notify a sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
