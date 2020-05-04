//204351670

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * Paddle class supports methods that their goal is to represent the game
 * paddle which will be controlled by the user.
 */
public class Paddle implements Sprite, Collidable {

    //Paddle specs (Constants) to make it look good on screen.
    private final double PADDLE_SIZE_FACTOR = 0.2;
    private final double PADDLE_HEIGHT_FACTOR = 0.03;
    private final double PADDLE_SPEED = Game.getWIDTH() * 0.01;


    //fields.
    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Rectangle paddleBody;

    /**
     * constructor of the 'Paddle' object.
     *
     * @param gui the game Gui.
     * @throws Exception if the paddle is somehow constructed with points that
     *                   has negative values.
     */
    public Paddle(GUI gui) throws Exception {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.paddleBody = buildPaddle();

    }

    //Paddle methods.

    /**
     * the function builds the paddle body - a rectangle.
     *
     * @return a Rectangle object, which is the paddle body.
     * @throws Exception if the the paddle rectangle gets a point with negative
     *                   coordinates.
     */
    private Rectangle buildPaddle() throws Exception {
        //calculating the width and height of the paddle based on the size
        // factors.
        double paddleWidth = PADDLE_SIZE_FACTOR * Game.getWIDTH();
        double paddleHeight = PADDLE_HEIGHT_FACTOR * Game.getHEIGHT();
        //calculating and creating the paddle top left corner point.
        double rectUpperLeftXCord = Game.getWIDTH()/2.0 - paddleWidth/2.0;
        double rectUpperLeftYCord = Game.getHEIGHT()
                - Game.getBorderThickness() - paddleHeight;
        Point rectUpperLeft = new Point(rectUpperLeftXCord, rectUpperLeftYCord);

        return new Rectangle(rectUpperLeft, paddleWidth, paddleHeight);

    }

    /**
     * Moves the paddle to the left.
     *
     * The movement is created by changing the given paddle body, which is
     * a rectangle, to a new rectangle at the new location to the left.
     *
     * @throws Exception if the the paddle moves to a point which has negative
     *                   coordinates.
     */
    private void moveLeft() throws Exception {
        //for readability.
        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double paddleWidth = this.paddleBody.getWidth();
        double paddleHeight = this.paddleBody.getHeight();

        //check if the paddle is in the edge of the game-play zone.
        if(isNextMoveOutOfBounds(curUpperLeft.getX() - PADDLE_SPEED)) {
            //move to edge
            Point newUpperLeft = new Point(Game.getBorderThickness(), curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        } else {
            //move to the left.
            Point newUpperLeft = new Point(curUpperLeft.getX() - PADDLE_SPEED, curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        }
    }

    /**
     * Moves the paddle to the right.
     *
     * The movement is created by changing the given paddle body, which is
     * a rectangle, to a new rectangle at the new location to the right.
     *
     * @throws Exception if the the paddle moves to a point which has negative
     *                   coordinates.
     */
    private void moveRight() throws Exception {

        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double paddleWidth = this.paddleBody.getWidth();
        double paddleHeight = this.paddleBody.getHeight();


        if(isNextMoveOutOfBounds(curUpperLeft.getX() + PADDLE_SPEED)) {
            //move to edge
            Point newUpperLeft = new Point(Game.getWIDTH() - Game.getBorderThickness() - paddleWidth, curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        } else {
            //move to the right.
            Point newUpperLeft = new Point(curUpperLeft.getX() + PADDLE_SPEED, curUpperLeft.getY());
            this.paddleBody = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        }

    }

    /**
     * check if the next move, will move the paddle out of bounds.
     *
     * @return 'true' if the next move gets out of bounds, meaning, anywhere in
     *          the border area and outwards, 'false' otherwise.
     */
    private boolean isNextMoveOutOfBounds(double nextPositionOfUpperLeftXCoordinate) {
        //out of game-play zone to the right.
        if(nextPositionOfUpperLeftXCoordinate < Game.getBorderThickness()) {
            return true;
            //out of game-play zone to the left.
        } else if (nextPositionOfUpperLeftXCoordinate + this.getPaddleWidth()
                > Game.getWIDTH() - Game.getBorderThickness()) {
            return true;
        } else {
            //legal move.
            return false;
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

        //getting each zone starting point (the left border of the zone).
        double zone1 = this.paddleBody.getUpperLeft().getX();
        double zone2 = this.paddleBody.getUpperLeft().getX() + zoneSize;
        double zone3 = this.paddleBody.getUpperLeft().getX() + 2 * zoneSize;
        double zone4 = this.paddleBody.getUpperLeft().getX() + 3 * zoneSize;
        double zone5 = this.paddleBody.getUpperLeft().getX() + 4 * zoneSize;

        //returning which zone got hit.
        if(collisionPoint.getX() >= zone1 && collisionPoint.getX() < zone2) {
            return 1;
        } else if (collisionPoint.getX() >= zone2 && collisionPoint.getX() < zone3) {
            return 2;
        } else if (collisionPoint.getX() >= zone3 && collisionPoint.getX() < zone4) {
            return 3;
        } else if (collisionPoint.getX() >= zone4 && collisionPoint.getX() < zone5) {
            return 4;
        } else if (collisionPoint.getX() >= zone5 && collisionPoint.getX() <= zone5 + zoneSize) {
            return 5;
        } else {
            return 0;
        }
    }

    private double[] getDifferentZoneMargins() {
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
     * @throws Exception if somehow one of the points of the different zones
     *                   have negative coordinates.
     */
    private void drawZones(DrawSurface surface) throws Exception {
        surface.setColor(Color.black);
        double zoneSize = this.paddleBody.getWidth() / 5.0;

        double zone1 = this.paddleBody.getUpperLeft().getX();
        double zone2 = this.paddleBody.getUpperLeft().getX() + zoneSize;
        double zone3 = this.paddleBody.getUpperLeft().getX() + 2 * zoneSize;
        double zone4 = this.paddleBody.getUpperLeft().getX() + 3 * zoneSize;
        double zone5 = this.paddleBody.getUpperLeft().getX() + 4 * zoneSize;

        double yDown = this.paddleBody.getUpperLeft().getY() + this.paddleBody.getHeight();
        double yUp = this.paddleBody.getUpperLeft().getY();
        Line z1 = new Line(zone1,yDown,zone1,yUp);
        Line z2 = new Line(zone2,yDown,zone2,yUp);
        Line z3 = new Line(zone3,yDown,zone3,yUp);
        Line z4 = new Line(zone4,yDown,zone4,yUp);
        Line z5 = new Line(zone5,yDown,zone5,yUp);

        z1.drawLine(surface);
        z2.drawLine(surface);
        z3.drawLine(surface);
        z4.drawLine(surface);
        z5.drawLine(surface);
    }

    public double getPaddleWidth() {
        return this.paddleBody.getWidth();
    }


    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    // Sprite Interface methods.
    @Override
    public void timePassed() throws Exception {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else {
            return;
        }
    }
    @Override
    public void drawOn(DrawSurface surface) throws Exception {
        //frame
        surface.setColor(Color.BLACK);
        surface.fillRectangle((int) this.paddleBody.getUpperLeft().getX(),
                (int) this.paddleBody.getUpperLeft().getY(),
                (int) this.paddleBody.getWidth(), (int) this.paddleBody.getHeight());
        //background color.
        surface.setColor(Color.YELLOW);
        surface.fillRectangle((int) this.paddleBody.getUpperLeft().getX() + 2,
                (int) this.paddleBody.getUpperLeft().getY() + 2 ,
                (int) this.paddleBody.getWidth() - 3 , (int) this.paddleBody.getHeight() - 3);

        //for checking different zones collision areas. (Possible cheat :) )
        //drawZones(surface);
    }

    // Collidable Interface methods.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBody;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        double speed = currentVelocity.getSpeed();
//        double angle = currentVelocity.getAngleFromDxDy();

        switch (getPaddleCollisionZone(collisionPoint)) {
            case 1:
                return Velocity.getVelocityFromAngleAndSpeed(300,speed);
            case 2:
                return Velocity.getVelocityFromAngleAndSpeed(330,speed);
            case 3:
                return new Velocity(currentVelocity.getDx(),
                        currentVelocity.getDy() * (-1));
            case 4:
                return Velocity.getVelocityFromAngleAndSpeed(30,speed);
            case 5:
                return Velocity.getVelocityFromAngleAndSpeed(60,speed);
            default:
                return new Velocity(0,0);
        }
    }

    @Override
    public void DrawOn(DrawSurface surface) {
        surface.setColor(Color.YELLOW);
        surface.fillRectangle((int) this.paddleBody.getUpperLeft().getX(),
                (int) this.paddleBody.getUpperLeft().getY(),
                (int) this.paddleBody.getWidth(), (int) this.paddleBody.getHeight());
    }

    @Override
    public Line getImpactLineFromCollisionPoint(Point collisionPoint) throws Exception {
        //creating a block with the same specs as the paddle body to
        // use Block's function.
        Block b = new Block(this.paddleBody);
        return b.getImpactLineFromCollisionPoint(collisionPoint);
    }
}