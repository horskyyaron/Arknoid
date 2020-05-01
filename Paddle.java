import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private final double PADDLE_SIZE_FACTOR = 0.3;
    private final double PADDLE_SPEED = 5;


    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Rectangle paddleBody;

    public Paddle(GUI gui) throws Exception {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.paddleBody = buildPaddle();

    }

    private Rectangle buildPaddle() throws Exception {
        double paddleWidth = PADDLE_SIZE_FACTOR * Game.getWIDTH();
        double paddleHighet = 15;
        double rectUpperLeftXCord = Game.getWIDTH()/2.0 - paddleWidth/2.0;
        double rectUpperLeftYCord = Game.getHEIGHT()
                - Game.getBorderThickness() - paddleHighet;
        Point rectUpperLeft = new Point(rectUpperLeftXCord, rectUpperLeftYCord);
        return new Rectangle(rectUpperLeft, paddleWidth, paddleHighet);

    }

    public void moveLeft() throws Exception {
        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double puddleWidth = this.paddleBody.getWidth();
        double puddleHeight = this.paddleBody.getHeight();

        Point newUpperLeft = new Point(curUpperLeft.getX() - 3, curUpperLeft.getY());
        this.paddleBody = new Rectangle(newUpperLeft,puddleWidth,puddleHeight);
    }
    public void moveRight() throws Exception {

        Point curUpperLeft = this.paddleBody.getUpperLeft();
        double puddleWidth = this.paddleBody.getWidth();
        double puddleHeight = this.paddleBody.getHeight();


        Point newUpperLeft = new Point(curUpperLeft.getX() + PADDLE_SPEED, curUpperLeft.getY());
        this.paddleBody = new Rectangle(newUpperLeft,puddleWidth,puddleHeight);

    }

    // Sprite
    public void timePassed() throws Exception {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else {
            return;
        }
    }
    public void drawOn(DrawSurface surface) throws Exception {
        surface.setColor(Color.red);
        surface.fillRectangle((int) this.paddleBody.getUpperLeft().getX(),
                (int) this.paddleBody.getUpperLeft().getY(),
                (int) this.paddleBody.getWidth(), (int) this.paddleBody.getHeight());
        drawZones(surface);

    }



    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddleBody;
    }

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

    //returns what zone of the paddle (1-5) the collision occurred.
    private int getPaddleCollisionZone(Point collisionPoint) {
        double zoneSize = this.paddleBody.getWidth() / 5.0;

        double zone1 = this.paddleBody.getUpperLeft().getX();
        double zone2 = this.paddleBody.getUpperLeft().getX() + zoneSize;
        double zone3 = this.paddleBody.getUpperLeft().getX() + 2 * zoneSize;
        double zone4 = this.paddleBody.getUpperLeft().getX() + 3 * zoneSize;
        double zone5 = this.paddleBody.getUpperLeft().getX() + 4 * zoneSize;

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

    //if you want to see the zone seperation.
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

    // Add this paddle to the game.
    //public void addToGame(Game g);


}