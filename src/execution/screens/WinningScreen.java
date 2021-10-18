//ID: 204351670

package execution.screens;

import biuoop.DrawSurface;
import execution.animation.Animation;
import execution.animation.Counter;
import execution.animation.Utils;
import geometry.Point;

import java.awt.Color;
import java.util.Random;

/**
 * PauseScreen is an animation that will take place once a game is won.
 */
public class WinningScreen implements Animation {

    private static final int FONT_SIZE = 32;
    private static final double STRING_LEFT_EDGE_COORDINATE = Utils.getWidth() * 0.2;
    private static final double STRING_HEIGHT = Utils.getHeight() * 0.13;

    private static final int NUMBER_OF_JELLYFISHES_ON_SCREEN = 10;
    private static final int BIGGEST_JELLYFISH_RADIUS = 60;

    //fields
    private Counter score;

    /**
     * Instantiates a new Winning screen.
     *
     * @param totalScore the total score the player achieved in the game.
     */
    public WinningScreen(Counter totalScore) {
        this.score = totalScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //setting background color
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, Utils.getWidth(), Utils.getHeight());


        //drawing spongebob
        drawSpongebob(d);

        //drawing jelly fish
        drawJellyFish(d);


        d.setColor(Color.black);
        d.drawText((int) STRING_LEFT_EDGE_COORDINATE, (int) STRING_HEIGHT, "You Win! Your score is "
                + this.score.getValue(), FONT_SIZE);
    }

    /**
     * the function will draw jelly fish (plural) onto the given surface.
     *
     * @param d the surface which the drawing will take place in.
     */
    private void drawJellyFish(DrawSurface d) {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_JELLYFISHES_ON_SCREEN; i++) {
            drawJellyFish(d, random.nextInt(BIGGEST_JELLYFISH_RADIUS), Utils.getRandomColor(),
                    new Point(random.nextInt(Utils.getWidth()), random.nextInt(Utils.getHeight())));
        }
    }

    /**
     * the function will draw jelly fish (singular) onto the given surface.
     *
     * @param d the surface which the drawing will take place in.
     * @param radius the jelly fish radius
     * @param color the color of the jelly fish
     * @param jellyFishCenter the jelly fish body center.
     */
    private void drawJellyFish(DrawSurface d, int radius, Color color, Point jellyFishCenter) {
        Point centerOfJellyFish = jellyFishCenter;
        Color jellyFishColor = color;
        int jellyFishRadius = radius;

        d.setColor(jellyFishColor);
        d.fillCircle((int) centerOfJellyFish.getX(), (int) centerOfJellyFish.getY(), jellyFishRadius);
        d.setColor(Color.white);
        d.fillCircle((int) centerOfJellyFish.getX(), (int) centerOfJellyFish.getY(), jellyFishRadius - 3);

        d.setColor(Color.white);
        d.fillRectangle((int) (centerOfJellyFish.getX() - jellyFishRadius), (int) (centerOfJellyFish.getY() + 5),
                2 * jellyFishRadius, jellyFishRadius);

        d.setColor(jellyFishColor);
        for (int i = 0; i < 5; i++) {
            d.drawLine((int) centerOfJellyFish.getX() - jellyFishRadius, (int) centerOfJellyFish.getY() + i,
                    (int) centerOfJellyFish.getX() + jellyFishRadius, (int) centerOfJellyFish.getY() + i);
        }

        d.setColor(jellyFishColor);
        d.drawLine((int) centerOfJellyFish.getX() - (jellyFishRadius / 2), (int) centerOfJellyFish.getY(),
                (int) centerOfJellyFish.getX() - (jellyFishRadius / 2),
                (int) centerOfJellyFish.getY() + jellyFishRadius);
        d.drawLine((int) centerOfJellyFish.getX(), (int) centerOfJellyFish.getY(),
                (int) centerOfJellyFish.getX() , (int) centerOfJellyFish.getY() + jellyFishRadius);
        d.drawLine((int) centerOfJellyFish.getX() + (jellyFishRadius / 2), (int) centerOfJellyFish.getY(),
                (int) centerOfJellyFish.getX() + (jellyFishRadius / 2),
                (int) centerOfJellyFish.getY() + jellyFishRadius);



    }
    //for information about Spongebob (https://en.wikipedia.org/wiki/SpongeBob_SquarePants)

    /**
     * the function will draw "Spongebob" onto the given surface.
     *
     * @param d the surface which the drawing will take place in.
     */
    private void drawSpongebob(DrawSurface d) {
        /*
         * many of the drawing has no direct connection to screen size therefore there will be a lot of "random" numbers
         * so that the drawing will look good.
         */

        Point centerOfScreen = Utils.getCenterOfScreen();
        int xCord = (int) centerOfScreen.getX();
        int yCord = (int) centerOfScreen.getY();

        //mouth
        d.setColor(Color.BLACK);
        d.fillCircle(xCord + 70, yCord - 115, 200);
        d.setColor(Color.white);
        d.fillCircle(xCord + 70, yCord - 115, 195);

        //erase part of mouth.
        d.setColor(Color.white);
        d.fillRectangle(xCord + 74, 0 , 300, xCord);
        d.fillRectangle(xCord - 320, 0 , 300, xCord);
        d.fillRectangle(0, 0, Utils.getWidth(), 200);

        d.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            d.drawOval(xCord - 20 + i, yCord - 40, 90, 200);
        }

        d.setColor(Color.white);
        d.fillRectangle(xCord + 68, 0, 300, (int) (Utils.getHeight() * 0.63));
        d.setColor(Color.white);
        d.fillRectangle(xCord - 100, 0, 300, (int) (Utils.getHeight() * 0.60));

        d.setColor(Color.black);
        for (int i = 0; i < 5; i++) {
            d.drawLine(xCord + 70 + i, yCord + 60, xCord + 70 + i, yCord + 80);
        }

        //teeth
        d.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            d.drawLine(xCord + i, yCord + 70 + i, xCord + i, yCord + 100);
            d.drawLine(xCord, yCord + 100 + i, xCord + 50, yCord + 100 + i);
            d.drawLine(xCord + 48 + i, yCord + 80, xCord + 48 + i, yCord + 100);
            d.drawLine(xCord + 24 + i, yCord + 80, xCord + 24 + i, yCord + 100);
        }

        //nose

        d.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            d.drawOval(xCord + i , yCord + 30 + i, 150, 30);
            d.drawOval(xCord , yCord + 30 + i, 150, 30);
        }

        //erase part of nose.
        d.setColor(Color.white);
        d.fillRectangle(xCord - 5, yCord , 50 , 65);

        //eyes
        //right eye
        d.setColor(Color.BLACK);
        d.fillCircle(xCord + 70, yCord, 50);
        d.setColor(Color.white);
        d.fillCircle(xCord + 70, yCord, 45);

        Point rightEyeCenter = new Point(xCord + 70, yCord);
        int rightEyeXCord = (int) rightEyeCenter.getX();
        int rightEyeYCord = (int) rightEyeCenter.getY();

        d.setColor(Color.BLACK);
        d.fillCircle(rightEyeXCord + 15, rightEyeYCord + 28, 13);


        //left eye
        d.setColor(Color.BLACK);
        d.fillCircle(xCord, yCord, 50);
        d.setColor(Color.white);
        d.fillCircle(xCord, yCord, 45);

        Point leftEyeCenter = new Point(xCord, yCord);
        int leftEyeXCord = (int) leftEyeCenter.getX();
        int leftEyeYCord = (int) leftEyeCenter.getY();


        d.setColor(Color.BLACK);
        d.fillCircle(leftEyeXCord + 15, leftEyeYCord + 28, 13);

        //draw body
        d.setColor(Color.black);
        for (int i = 0; i < 5; i++) {
            d.drawRectangle(leftEyeXCord - 80 + i, leftEyeYCord - 80 + i, 240 - i, 250 - i);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
