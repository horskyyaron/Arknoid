//ID: 204351670

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.toRadians;
import static java.lang.Math.sqrt;


/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * supports method of changing and getting the speed of a ball.
 */
public class Velocity {

    //Declaring constants of min and max velocity.
    public static final int MIM_SPEED = 1;
    public static final int MAX_SPEED = 50;

    //the different quadrants of the screen.
    private static final int FIRST_QUADRANT = 1;
    private static final int SECOND_QUADRANT = 2;
    private static final int THIRD_QUADRANT = 3;
    private static final int FOURTH_QUADRANT = 4;

    //fields. each is a change in position in the x (horizontal direction)
    // and y (vertical direction) axis accordingly.
    private double dx;
    private double dy;

    /**
     * constructor of the 'Velocity' object.
     * a Velocity object has two fields: dx- speed component in the horizontal
     * direction and dy- speed in the vertical direction.
     *
     * @param dx speed component in the horizontal direction.
     * @param dy speed component in the vertical direction.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * will change the ball direction in the horizontal direction.
     */
    public void changeDirectionHorizontal() {
        this.dx = this.dx * (-1);
    }

    /**
     * will change the ball direction in the vertical direction.
     */
    public void changeDirectionVertical() {
        this.dy = this.dy * (-1);
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p Point, represents the current location of the ball, before
     *           a change took place.
     * @return Point, a Point object which represents the new position of the
     *         ball.
     * @throws Exception when getting negative coordinates.
     */
    public Point applyToPoint(Point p) throws Exception {
        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }

    /**
     * setting ball speed.
     *
     * smaller radius => higher speed.
     *
     * @param ballRadius int, ball's radius.
     * @return int, ball's speed.
     */
    public static int setSpeed(int ballRadius) {
        //Client's requirement: if radius is larger than 50 then-> minimum speed
        if (ballRadius > 50) {
            //
            return MIM_SPEED;
        } else {
            //selected ratio for ball's speed.
            return ((100 - ballRadius) / 7);
        }
    }

    /**
     * setting velocity from given angle and speed.
     *
     * the 0 degree is set to be the up direction and growing clockwise.
     *
     * @param angle double, the ball's angle of movement.
     * @param speed double, the ball's speed.
     * @return Velocity returns velocity according to a given angle and speed.
     */
    public static Velocity getVelocityFromAngleAndSpeed(double angle, double speed) {
        double dx;
        double dy;
        //converting degree to radians.
        angle = toRadians(angle);

        //Trigonometry calculations
        if (angle >= 0 && angle <= 90) {
            dy = ((speed * cos(angle)) * (-1));
            dx = (speed * sin(angle));
        } else if (angle > 90 && angle <= 180) {
            dy = (speed * cos(180 - angle));
            dx = (speed * sin(180 - angle));
        } else if (angle > 180 && angle <= 270) {
            dy = (speed * cos(angle - 180));
            dx = (speed * sin(angle - 180) * (-1));
        } else if (angle > 270 && angle < 360) {
            dy = (speed * cos(360 - angle) * (-1));
            dx = (speed * sin(360 - angle) * (-1));
        } else {
            dx = 0;
            dy = 0;
        }

        return new Velocity(dx, dy);
    }

    /**
     * getting the angle of a ball's movement according to the velocity in
     * the axis. (from dx and dy).
     *
     * @return int returns the angle of the ball's movement.
     */
    public int getAngleFromDxDy() {

        /*

        *simple trigonometry calculations for getting the velocity angle in
        * the provided coordinated system (up is '0 degree' and growing clockwise.
        */
        if (this.dy == 0 && this.dx > 0) {
            return 90;
        } else if (this.dy == 0 && this.dx < 0) {
            return 270;
        } else if (this.dx == 0 && this.dy > 0) {
            return 180;
        } else if (this.dx == 0 && this.dy < 0) {
            return 0;
        } else if (this.dx > 0) {
            if (this.dy > 0) {
                return (int) (HelpFunctions.shiftTan(this.dx, this.dy) + 90);
            } else {
                return (int) (90 - HelpFunctions.shiftTan(abs(this.dy),
                        this.dx));
            }
        } else if (this.dx < 0) {
            if (this.dy > 0) {
                return (int) (270 - HelpFunctions.shiftTan(abs(this.dx),
                        this.dy));
            } else {
                return (int) (270 + HelpFunctions.shiftTan(abs(this.dx),
                        abs(this.dy)));
            }
        } else {
            return 0;
        }
}

    /**
     * returns the speed in the x-axis direction. (dx)
     *
     * @return double returns the speed in the x-axis direction. (dx)
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * returns the speed in the y-axis direction. (dy)
     *
     * @return double returns the speed in the x-axis direction. (dy)
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * returns the speed of the ball.
     * (size of the vector which is constructed from horizontal speed (dx)  and
     * vertical speed (dy))
     *
     * @return speed size.
     */
    public double getSpeed() {
        return sqrt(pow(this.dx, 2) + pow(this.dy, 2));
    }

    /**
     * The function will return a new velocity which it's components are
     * opposites.
     *
     * @return the new velocity.
     */
    public Velocity applyFrontalHit() {
        //a vertical hit.
        if (this.dx == 0) {
            this.changeDirectionVertical();
        } else {
            //a horizontal hit
            this.changeDirectionHorizontal();
        }
        return this;
    }

    /**
     * The function will flip the given velocity horizontal component.
     */
    public void applyVerticalSurfaceHit() {
        this.changeDirectionHorizontal();
    }

    /**
     * The function will will return ths angle quadrant.
     *
     * e.g. it the ball's velocity is to the upper left (0-90 degree) the function
     * will return 1 (first quadrant)
     *
     * @return the ball's velocity quadrant.
     */
    private int getAngleQuadrant() {
        double angle = this.getAngleFromDxDy();
        if (angle > 0 && angle < 90) {
            return FIRST_QUADRANT;
        } else if (angle > 90 && angle < 180) {
            return SECOND_QUADRANT;
        } else if (angle > 180 && angle < 270) {
            return THIRD_QUADRANT;
        } else {
            return FOURTH_QUADRANT;
        }
    }

    /**
     * The function will flip the given velocity vertical component.
     */
    public void applyHorizontalSurfaceHit() {
        this.changeDirectionVertical();
    }

    /**
     * Will compare given velocity to another object.
     *
     * @param obj the object being compared with given velocity.
     * @return 'true' if the other object is a Velocity object with same
     *          horizontal and vertical components. 'false' otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Velocity velocity = (Velocity) obj;
        return Double.compare(velocity.dx, dx) == 0
                && Double.compare(velocity.dy, dy) == 0;
    }

    /*

    looking online, i found that override hashCode function should not have
    documentation, plus checkstyle didn't raise error so i leave it as is.
    https://stackoverflow.com/questions/24355424/should-i-document-overriden-hashcode-and-tostring
    */
    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }
}
