//ID: 204351670
package execution.animation;

import biuoop.DrawSurface;
import gameelements.sprites.SpriteCollection;
import geometry.Point;

import java.awt.Color;

/**
 * CountDownAnimaiton is an animation that takes place before each level,
 * running a count down 3,2,1 before starting each level.
 */
public class CountDownAnimation implements Animation {

    //color variables.
    private static final int COLOR_INTERVAL = 8;
    private static final int MAX_RGB = 255;
    private static final int RADIUS = 80;
    private static int blackCounter = 0;

    //seconds constants.
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    //time variables.
    private long prevTime;
    private static long totTimePassed;
    private double screenTimeForDigitInMilliSeconds;

    //fields
    private double seconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;


    /**
     * Instantiates a new CountDownAnimation.
     *
     * @param numOfSeconds the num of seconds that will pass before the level will start.
     * @param countFrom    the number which the countdown will start from.
     * @param gameScreen   holds the information of the background elements of the coming level.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.prevTime = System.currentTimeMillis();
        this.running = true;

        this.screenTimeForDigitInMilliSeconds = numOfSeconds / countFrom * 1000;
        blackCounter = 0;
        totTimePassed = 0;
    }

    /**
     * a function that will draw a given number (integer) onto the given durface.
     *
     * @param d the surface which the drawing will be on.
     * @param digit    the number which will be drawn
     */
    private void drawNumber(DrawSurface d, int digit) {
        //converting digit to string.
        String str = "" + (char) (digit + 48);;

        d.setColor(getBlackColor(blackCounter));
        d.drawText((int) (GameConstants.getWidth() / 2) - 50, d.getHeight() / 2 - 30, "Get Ready" , 20);
        d.drawText((int) (GameConstants.getWidth() / 2) - 10, d.getHeight() / 2 + 40, str , 50);

    }

    /**
     * The function will return a black-ish color.
     *
     * each time the black color will be more bright.
     *
     * @param counter the bigger the counter, the brighter the black color will be.
     * @return a black-ish color.
     */
    private Color getBlackColor(int counter) {
        int num = COLOR_INTERVAL * blackCounter;
        if (num > MAX_RGB) {
            num = MAX_RGB;
        }
        blackCounter = counter + 1;
        //returning more bright color of black
        return new Color(num, num, num);

    }

    /**
     * The function will draw a circle onto the given durface.
     *
     * @param d the surface which the drawing will be on.
     */
    private void drawMassageCircle(DrawSurface d) {
        d.setColor(Color.BLACK);
        Point centerOfScreen = new Point((int) (GameConstants.getWidth() / 2.0),
                (int) (GameConstants.getHeight() / 2.0));
        d.fillCircle((int) centerOfScreen.getX(), (int) centerOfScreen.getY(), RADIUS);

        d.setColor(GameConstants.getRandomColor());
        d.fillCircle((int) centerOfScreen.getX(), (int) centerOfScreen.getY(), RADIUS - 5);

    }


    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        drawMassageCircle(d);

        totTimePassed = totTimePassed + (System.currentTimeMillis() - prevTime);
        prevTime = System.currentTimeMillis();

        if (totTimePassed < screenTimeForDigitInMilliSeconds) {
            drawNumber(d, THREE);
        } else if (totTimePassed < 2 * screenTimeForDigitInMilliSeconds) {
            drawNumber(d, TWO);
        } else if (totTimePassed < 3 * screenTimeForDigitInMilliSeconds) {
            drawNumber(d, ONE);
        } else {
            this.running = false;
        }
    }



    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
