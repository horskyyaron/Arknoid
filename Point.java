//ID: 204351670

import java.awt.geom.Arc2D;
import java.util.Objects;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

/**
 * Point class supports methods that their goal is to represent a point in 2D
 * space (XY plane).
 */
public class Point {

    private double x;
    private double y;

    /**
     * constructor of the 'Point' object.
     * a Point object has two fields: x- coordinate and y- coordinate.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @throws Exception when getting negative coordinates.
     */
    public Point(double x, double y) throws Exception {
        if (x < 0 || y < 0) {
            throw new Exception("a point on screen cannot have "
                    + "negative coordinates");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * returns the x-coordinate of a Point object.
     *
     * @return double, returns the x-coordinate of the Point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the y-coordinate of a Point object.
     *
     * @return double, returns the y-coordinate of the Point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * returns the distance between a given Point object to another.
     * <p>
     * using the formula from geometry of calculating distance between two
     * points in space: let there be two points: a = (x1,y1), b = (x2,y2)
     * the distance between a and b is:
     * sqrt((y2-y1)^2 + (x2-x1)^2).
     *
     * @param other another Point Object.
     * @return double, returns the distance between the two Points objects.
     */
    public double distance(Point other) {
        return (sqrt(pow(this.y - other.getY(), 2)
                + pow(this.x - other.getX(), 2)));
    }

    /**
     * check if two Points are the same.
     * <p>
     * meaning:
     * if the x-coordinate of a given point is equal to the
     * x-coordinate of the other point. and that the
     * y-coordinate of a given point equals to the y-coordinate
     * of the other point.
     *
     * @param other another Point Object.
     * @return 'true', if points are the same (as mentioned in the description)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Point) {
            Point test = (Point) obj;
            if(Double.compare(test.x,this.x) == 0
                    && Double.compare(test.y,this.y) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
