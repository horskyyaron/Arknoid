//204351670

package gameelements.sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import execution.animation.Utils;
import execution.levels.LevelInformation;
import gameelements.collidables.Collidable;
import execution.animation.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * gameelemnts.sprites.movingitems.Paddle class supports methods that their goal is to represent the game
 * paddle which will be controlled by the user.
 */
public class Paddle implements Sprite, Collidable {

    private static final double PADDLE_HEIGHT_FACTOR = 0.03;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleBody;
    private LevelInformation levelInformation;

    /**
     * constructor of the 'gameelemnts.sprites.movingitems.Paddle' object.
     *
     * @param gui the game Gui.
     * @param levelInfo the information about the level which the paddle will be created in.
     */
    public Paddle(GUI gui, LevelInformation levelInfo) {
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = levelInfo;
        this.paddleBody = buildPaddle();

    }


    /**
     * the function builds the paddle body - a rectangle.
     *
     * @return a geometry.Rectangle object, which is the paddle body.
     */
    private Rectangle buildPaddle() {
        //calculating the width and height of the paddle based on the size
        // factors.
        double paddleWidth = this.levelInformation.paddleWidth();
        double paddleHeight = PADDLE_HEIGHT_FACTOR * Utils.getHeight();

        //calculating and creating the paddle top left corner point.
        double rectUpperLeftXCord = Utils.getWidth() / 2.0 - paddleWidth / 2.0;
        double rectUpperLeftYCord = Utils.getHeight()
                - Utils.getBorderThickness() - 1;
        Point rectUpperLeft = new Point(rectUpperLeftXCord, rectUpperLeftYCord);

        return new Rectangle(rectUpperLeft, paddleWidth, paddleHeight);

    }

    /**
     * Moves the paddle to the left.
     *
     * The movement is created by changing the given paddle body, which is
     * a rectangle, to a new rectangle at the new location to the left.
     */
    private void moveLeft() {
        //for readability.
        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double paddleWidth = this.paddleBody.getWidth();
        double paddleHeight = this.paddleBody.getHeight();

        //check if the paddle is in the edge of the game-play zone.
        if (isNextMoveOutOfBounds(curUpperLeft.getX() - this.levelInformation.paddleSpeed())) {
            //move to edge
            Point newUpperLeft = new Point(Utils.getBorderThickness(), curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        } else {
            //move to the left.
            Point newUpperLeft = new Point(curUpperLeft.getX() - this.levelInformation.paddleSpeed(),
                    curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        }
    }

    /**
     * Moves the paddle to the right.
     *
     * The movement is created by changing the given paddle body, which is
     * a rectangle, to a new rectangle at the new location to the right.
     */
    private void moveRight() {

        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double paddleWidth = this.paddleBody.getWidth();
        double paddleHeight = this.paddleBody.getHeight();


        if (isNextMoveOutOfBounds(curUpperLeft.getX() + this.levelInformation.paddleSpeed())) {
            //move to edge
            Point newUpperLeft = new Point(Utils.getWidth()
                    - Utils.getBorderThickness() - paddleWidth,
                    curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth,
                    paddleHeight);
        } else {
            //move to the right.
            Point newUpperLeft = new Point(curUpperLeft.getX() + this.levelInformation.paddleSpeed(),
                    curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth,
                    paddleHeight);
        }

    }

    /**
     * check if the next move, will move the paddle out of bounds.
     *
     * @param nextPositionOfUpperLeftXCoordinate the next position of the x
     *                                           coordinate ot the upper left
     *                                           corner of the paddle.
     * @return 'true' if the next move gets out of bounds, meaning, anywhere in
     *          the border area and outwards, 'false' otherwise.
     */
    private boolean isNextMoveOutOfBounds(double nextPositionOfUpperLeftXCoordinate) {
        //out of game-play zone to the right.
        if (nextPositionOfUpperLeftXCoordinate < Utils.getBorderThickness()) {
            return true;
            //out of game-play zone to the left.
        } else {
            return nextPositionOfUpperLeftXCoordinate + this.getPaddleWidth()
                > Utils.getWidth() - Utils.getBorderThickness();
        }
    }

    /**
     * return which zone of the paddle got hit.
     *
     * @param collisionPoint the point which the collision accord.
     * @return integer 1 to 5, each representing a zone on the paddle.
     */
    private int getPaddleCollisionZone(Point collisionPoint) {
        //calculating each zone width.
        double zoneSize = this.paddleBody.getWidth() / 5.0;

        double[] zones = getDifferentZoneLeftMargins();

        //returning which zone got hit.
        if (collisionPoint.getX() >= zones[0]
                && collisionPoint.getX() < zones[1]) {
            return 1;
        } else if (collisionPoint.getX() >= zones[1]
                && collisionPoint.getX() < zones[2]) {
            return 2;
        } else if (collisionPoint.getX() >= zones[2]
                && collisionPoint.getX() < zones[3]) {
            return 3;
        } else if (collisionPoint.getX() >= zones[3]
                && collisionPoint.getX() < zones[4]) {
            return 4;
        } else if (collisionPoint.getX() >= zones[4]
                && collisionPoint.getX() <= zones[4] + zoneSize) {
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * getting the x coordinates of the different zones.
     *
     * @return the different zones x coordinates in a double array.
     */
    private double[] getDifferentZoneLeftMargins() {
        double[] zones = new double[5];

        //calculating each zone width.
        double zoneSize = this.paddleBody.getWidth() / 5.0;

        for (int i = 0; i < zones.length; i++) {
            zones[i] =  this.paddleBody.getUpperLeft().getX() + i * zoneSize;
        }
        return zones;
    }

    /**
     * draws the different zone margins on the paddle.
     *
     * @param surface the surface which holds all the drawing.
     */
    private void drawZones(DrawSurface surface) {
        surface.setColor(Color.black);
        double zoneSize = this.paddleBody.getWidth() / 5.0;
        double[] zones = new double[5];
        zones = getDifferentZoneLeftMargins();

        //readabillty.

        double yDown = this.paddleBody.getUpperLeft().getY() + this.paddleBody.getHeight();
        double yUp = this.paddleBody.getUpperLeft().getY();
        Line z1 = new Line(zones[0], yDown, zones[0], yUp);
        Line z2 = new Line(zones[1], yDown, zones[1], yUp);
        Line z3 = new Line(zones[2], yDown, zones[2], yUp);
        Line z4 = new Line(zones[3], yDown, zones[3], yUp);
        Line z5 = new Line(zones[4], yDown, zones[4], yUp);

        //drawing zones borders.
        z1.drawLine(surface);
        z2.drawLine(surface);
        z3.drawLine(surface);
        z4.drawLine(surface);
        z5.drawLine(surface);
    }

    /**
     * the function will return the paddle width.
     *
     * @return the paddle width.
     */
    public double getPaddleWidth() {
        return this.paddleBody.getWidth();
    }

    /**
     * the function will return the paddle height.
     *
     * @return the paddle height.
     */
    public double getPaddleHeight() {
        return this.paddleBody.getHeight();
    }

    /**
     * the function will return the paddle upper left corner point.
     *
     * @return the upper left point.
     */
    public Point getUpperLeft() {
        return this.paddleBody.getUpperLeft();
    }


    /**
     * the function will add the given paddle to the game gameelemnts.collidables.Collidable
     * and Sprites lists.
     *
     * @param g class object that holds all of the game component,
     *             paddle included.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    // gameelemnts.sprites.Sprite Interface methods.
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else {
            return;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        //frame
        d.setColor(Color.black);
        d.fillRectangle((int) this.paddleBody.getUpperLeft().getX(),
                (int) this.paddleBody.getUpperLeft().getY(),
                (int) this.paddleBody.getWidth(),
                (int) this.paddleBody.getHeight());
        //background color.
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.paddleBody.getUpperLeft().getX() + 1,
                (int) this.paddleBody.getUpperLeft().getY() + 1 ,
                (int) this.paddleBody.getWidth() - 2 ,
                (int) this.paddleBody.getHeight() - 2);

        //for checking different zones collision areas. (Possible cheat :) )
        //drawZones(surface);
    }

    // gameelemnts.collidables.Collidable Interface methods.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBody;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double speed = currentVelocity.getSpeed();

        //changing the ball's velocity angle based on collision zone in the
        // paddle.
        switch (getPaddleCollisionZone(collisionPoint)) {
            case 1:
                return Velocity.getVelocityFromAngleAndSpeed(300, speed);
            case 2:
                return Velocity.getVelocityFromAngleAndSpeed(330, speed);
            case 3:
                return new Velocity(currentVelocity.getDx(),
                        currentVelocity.getDy() * (-1));
            case 4:
                return Velocity.getVelocityFromAngleAndSpeed(30, speed);
            case 5:
                return Velocity.getVelocityFromAngleAndSpeed(60, speed);
            default:
                return new Velocity(0, 0);
        }
    }

    @Override
    public Line getImpactLineFromCollisionPoint(Point collisionPoint) {
        //creating a block with the same specs as the paddle body to
        // use gameelemnts.sprites.staticitems.Block's function.
        Block b = new Block(this.paddleBody);
        return b.getImpactLineFromCollisionPoint(collisionPoint);
    }
}