//ID: 204351670

package geometry;

import execution.animation.GameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * geometry.Point class supports methods that their goal is to represent a point in 2D
 * space (XY plane).
 */
public class Point {

    //fields.
    private double x;
    private double y;

    /**
     * constructor of the 'geometry.Point' object.
     * a geometry.Point object has two fields: x- coordinate and y- coordinate.
     *
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public Point(double x, double y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("a point on screen cannot have "
                    + "negative coordinates");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * returns the x-coordinate of a geometry.Point object.
     *
     * @return double, returns the x-coordinate of the geometry.Point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the y-coordinate of a geometry.Point object.
     *
     * @return double, returns the y-coordinate of the geometry.Point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * returns the distance between a given geometry.Point object to another.
     * <p>
     * using the formula from geometry of calculating distance between two
     * points in space: let there be two points: a = (x1,y1), b = (x2,y2)
     * the distance between a and b is:
     * sqrt((y2-y1)^2 + (x2-x1)^2).
     *
     * @param other another geometry.Point Object.
     * @return double, returns the distance between the two Points objects.
     */
    public double distance(Point other) {
        return (sqrt(pow(this.y - other.getY(), 2)
                + pow(this.x - other.getX(), 2)));
    }

    /**
     * check if two Points are the same.
     *
     * meaning:
     * if the x-coordinate of a given point is equal to the
     * x-coordinate of the other point. and that the
     * y-coordinate of a given point equals to the y-coordinate
     * of the other point.
     *
     * @param obj another geometry.Point Object.
     * @return 'true', if points are the same (as mentioned in the description)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Point) {
            Point test = (Point) obj;
            if (Double.compare(test.x, this.x) == 0
                    && Double.compare(test.y, this.y) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * return the closest point, from the input intersection points list,
     * to a given point.
     *
     * @param intersectionPointsList list of intersection points.
     * @return the closest point to a given point.
     */
    public Point getClosetsFromPointList(List<Point> intersectionPointsList) {
        //if no intersection points.
        if (intersectionPointsList.isEmpty()) {
            return null;
        } else {
            //setting minumum distance.
            int indexOfClosestPoint = 0;
            double minDistance = this.distance(intersectionPointsList.get(0));

            //get the minimal distance out of all the points in the list.
            for (int i = 1; i < intersectionPointsList.size(); i++) {
                if (minDistance > this.distance(intersectionPointsList.get(i))) {
                    minDistance = this.distance(intersectionPointsList.get(i));
                    //remembering the index of the closest point.
                    indexOfClosestPoint = i;
                }
            }
            return intersectionPointsList.get(indexOfClosestPoint);
        }

    }

    /**
     * check if given point, satisfies the input geometry.Line Equation object.
     *
     * The line equation is ax + by + c = 0.
     * satisfying the equation means that if we enter the x and y coordinates
     * of the point instead of the x and y in the equation, the equation will
     * hold.
     *
     * @param equation geometry.Line Equation object.
     * @return 'true' if the point is satisfying the line equation, 'false'
     *          otherwise.
     */
    public boolean isSatisfying(LineEquation equation) {
        return (this.x * equation.getXCoefficient() + this.y
                * equation.getYCoefficient()
                + equation.getFreeCoefficient() == 0);
    }

    /**
     * check if given point is a corner point of the game-play zone.
     *
     * @return 'true' if given point is a corner point of the game-play zone.
     *          'false' otherwise.
     */
    public boolean isAGameCorner() {
        //getting the game-play zone corner points.
        List<Point> cornerPoints = getGameCornerPoints(GameConstants.getWidth(),
                GameConstants.getHeight(), GameConstants.getHeight());
        //check if given point is one of the corner points.
        return cornerPoints.contains(this);
    }

    /**
     * the function will return a list of corner points based on input of screen
     * width, height and thickness of the screen borders.
     *
     * @param width screen width.
     * @param height screen height.
     * @param thickness screen border thickness.
     * @return corner points (of the game-play zone, where the ball can move)
     *         list.
     */
    private List<Point> getGameCornerPoints(int width, int height,
                                            double thickness) {
        //calculating and adding all the corner points of the game-play zone
        // to a list.
        List<Point> cornerPoints = new ArrayList<>();
        //upper left corner.
        cornerPoints.add(new Point(thickness, thickness));
        //upper right corner.
        cornerPoints.add(new Point(width - thickness, thickness));
        //bottom left corner.
        cornerPoints.add(new Point(thickness, height - thickness));
        //bottom right corner.
        cornerPoints.add(new Point(width - thickness, height - thickness));

        return cornerPoints;
    }
}
