//ID: 204351670
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Ball class supports methods that their goal is to represent a ball on the
 * screen animation.
 */
public class Ball implements Sprite {
    //Constants
    //for get near collision point.
    private static final double DELTA = 0.1;

    //fields.
    private Point center;
    private int radius;
    private Velocity velocity;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;

    //Constructors
    /**
     * constructor function
     *
     * constructor of the 'Ball' object.
     * a Ball object has three components: a center point (Point object), a
     * radius and background color.
     *
     * @param center the ball center point on screen.
     * @param radius the ball's radius.
     * @param color the ball's color.
     * @throws Exception if the point representing the center of the ball has
     *                   negative coordinates.
     */
    public Ball(Point center, int radius, java.awt.Color color)
            throws Exception {
        if (center.getY() <= 0 || center.getX() <= 0) {
            throw new Exception("Point coordinates cannot be non positive.");
        } else {
            this.center = center;
            this.radius = radius;
            this.color = color;
        }
    }

    /**
     * constructor function.
     *
     * @param x the ball center point's x-coordinate.
     * @param y the ball center point's y-coordinate.
     * @param radius the ball's radius.
     * @param color the ball's color.
     * @throws Exception if ball center point has negative coordinates.
     */
    public Ball(int x, int y, int radius, java.awt.Color color)
            throws Exception {
        this(new Point(x, y), radius, color);
    }

    /**
     * constructor function.
     *
     * @param center the ball's center point.
     * @param radius the ball's radius.
     */
    public Ball(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        //default color white
        this.color = Color.WHITE;
    }

    /**
     * constructor function.
     *
     * @param x the ball center point's x-coordinate.
     * @param y the ball center point's y-coordinate.
     * @param radius the ball's radius.
     * @throws Exception if ball center point has negative coordinates.
     */
    public Ball(int x, int y, int radius) throws Exception {
        this.center = new Point(x, y);
        this.radius = radius;
        //default color white
        this.color = Color.WHITE;

    }

    //Sprite Interface Methods

    @Override
    public void drawOn(DrawSurface surface) {
        //frame
//        surface.setColor(Color.black);
//        surface.fillCircle((int)this.getX(),(int) this.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
    }

    @Override
    public void timePassed() throws Exception {
        this.moveOneStep();
    }


    //getters
    /**
     * returns the Ball's center's point x-coordinate.
     *
     * @return the given Ball's center's point x-coordinate.
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * returns the Ball's center's point x-coordinate.
     *
     * @return the given Ball's center's point y-coordinate.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * returns the Ball's radius.
     *
     * @return the given Ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * returns the Ball's background color.
     *
     * @return the given Ball's background color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * returns the Ball's velocity.
     *
     * @return the given Ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    //setters
    /**
     * will set ball's velocity to a the input velocity.
     *
     * @param v the velocity that will be given to the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * will set ball's velocity according to the vertical and horizontal speed
     * components dx, dy.
     *
     * @param dx the ball's velocity in the horizontal direction.
     * @param dy the ball's velocity in the vertical direction.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * will set ball's color to the input color.
     *
     * @param newColor the input color.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * will preform a movement of the ball, by updating the ball's center point.
     *
     * @throws Exception if trying to move ball to a point with negative
     *                   coordinates.
     */
    public void moveOneStep() throws Exception {
        Line trajectory = computeBallTrajectory();
        //check if a collision occurred.
        if (gameEnvironment.getClosestCollision(trajectory) == null) {
            //no collision, move to end of trajectory.
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            //there was a hit with collidable, get information about the
            // collision.
            CollisionInfo collisionInfo =
                    gameEnvironment.getClosestCollision(trajectory);
            //move ball.
            moveNearCollisionPoint(collisionInfo.collisionPoint(), trajectory,
                    collisionInfo.getCollisionImpactLine());

            //update the collidable object about the hit.
            this.setVelocity(collisionInfo.collisionObject().
                    hit(collisionInfo.collisionPoint(), this.getVelocity()));
        }
    }

    /**
     * will move ball's center very close to the collision point.
     *
     * @param collisionPoint the collision point.
     * @param trajectory the ball's trajectory.
     * @param impactLine the line the ball is hitting.
     * @throws Exception if trying to move ball to a point with negative
     *                   coordinates.
     */
    private void moveNearCollisionPoint(Point collisionPoint,
                                        Line trajectory, Line impactLine)
            throws Exception {
        //if the collision point is a corner.
        if (collisionPoint.isAGameCorner()) {
            moveNearCorner(collisionPoint);
        }
        //if it' not a corner.
        if (impactLine.isHorizontal()) {
            moveNearHorizontalLine(collisionPoint, trajectory);
        } else {
            moveNearVerticalLine(collisionPoint, trajectory);
        }

    }

    /**
     * will move ball's center very close to game corner.
     *
     * @param cornerPoint the game corner point.
     * @throws Exception if trying to move ball to a point with negative
     *                   coordinates.
     */
    private void moveNearCorner(Point cornerPoint) throws Exception {
        /*

        check which corner point is the collision point, move ball center so
        that it will be inside game-play zone.

        check if it's in the left part of the game-play zone.
         */
        if (cornerPoint.getX() < (double) Game.getWIDTH() / 2) {
            //check if't the lower or upper corner in the left side of the
            // game-play zone.
            if (cornerPoint.getY() < (double) Game.getHEIGHT() / 2) {
                this.center = new Point(cornerPoint.getX() + DELTA,
                        cornerPoint.getY() + DELTA);
            } else {
                this.center = new Point(cornerPoint.getX() + DELTA,
                        cornerPoint.getY() - DELTA);
            }
        } else {
            //check if't the lower or upper corner in the right side of the
            // game-play zone.
            if (cornerPoint.getY() < (double) Game.getHEIGHT() / 2) {
                this.center = new Point(cornerPoint.getX() - DELTA,
                        cornerPoint.getY() + DELTA);
            } else {
                this.center = new Point(cornerPoint.getX() - DELTA,
                        cornerPoint.getY() - DELTA);
            }
        }
    }

    /**
     * will move ball's center very close to a vertical line.
     *
     * @param collisionPoint the collision point.
     * @param trajectory the ball's trajectory.
     * @throws Exception if trying to move ball to a point with negative
     *                   coordinates.
     */
    private void moveNearVerticalLine(Point collisionPoint, Line trajectory)
            throws Exception {
        if (trajectory.start().getX() > collisionPoint.getX()) {
            this.center = new Point(collisionPoint.getX() + DELTA, collisionPoint.getY());
        } else {
            this.center = new Point(collisionPoint.getX() - DELTA, collisionPoint.getY());
        }
    }

    /**
     * will move ball's center very close to a horizontal line.
     *
     * @param collisionPoint the collision point.
     * @param trajectory the ball's trajectory.
     * @throws Exception if trying to move ball to a point with negative
     *                   coordinates.
     */
    private void moveNearHorizontalLine(Point collisionPoint,
                                        Line trajectory) throws Exception {
        if (trajectory.start().getY() > collisionPoint.getY()) {
            this.center = new Point(collisionPoint.getX(),
                    collisionPoint.getY() + DELTA);
        } else {
            this.center = new Point(collisionPoint.getX(),
                    collisionPoint.getY() - DELTA);
        }
    }

    /**
     * will compute and return the ball's trajectory.
     *
     * a Line object represented by the ball current position as the starting
     * point and the it's next position as it's ending point, based on it's
     * velocity.
     *
     * @throws Exception if ball's trajectory has a point with negative
     *                   coordinates values.
     * @return ball's trajectory represented as a Line object.
     */
    private Line computeBallTrajectory() throws Exception {
        double endingPointXCoordinate = this.center.getX()
                + this.getVelocity().getDx();
        double endingPointYCoordinate = this.center.getY()
                + this.getVelocity().getDy();
        return (new Line(this.center,
                new Point(endingPointXCoordinate, endingPointYCoordinate)));
    }

    /**
     * will change the ball's color to a random RGB color.
     */
    public void changeToRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        this.color = new Color(r, g, b);
    }

    /**
     * will add the GameEnvironment object to ball's field.
     *
     * the ball must know about other objects in the game, that is why
     * we're adding GameEnvironment object to ball's field.
     *
     * @param environment the GameEnvironment object being added to the ball
     *                    fields.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * the function will add the Ball object to the input game.
     *
     * @param game the game which the ball will be a part of.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}