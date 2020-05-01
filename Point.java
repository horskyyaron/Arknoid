//ID: 204351670

import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.*;

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
     * @param obj another Point Object.
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

    public Point getClosetsFromPointList(List<Point> intersectionPointsList) {
        if(intersectionPointsList.isEmpty()) {
            return null;
        } else {

            int indexOfClosestPoint = 0;
            double minDistance = this.distance(intersectionPointsList.get(0));

            for (int i = 1; i < intersectionPointsList.size(); i++) {
                if (minDistance > this.distance(intersectionPointsList.get(i))) {
                    minDistance = this.distance(intersectionPointsList.get(i));
                    indexOfClosestPoint = i;
                }
            }
            return intersectionPointsList.get(indexOfClosestPoint);
        }

    }

    //check's if the point satisfying the line equation.
    public boolean isSatisfying(LineEquation equation) {
        return (this.x * equation.getXCoefficient() + this.y
                * equation.getYCoefficient()
                + equation.getFreeCoefficient() == 0);
    }

    public boolean isAGameCorner() throws Exception {
        int width = Game.getWIDTH();
        int height = Game.getHEIGHT();
        double thickness = Game.getBorderThicknessfactor() * width;

        List<Point> cornerPoints = getGameCornerPoints(width,height,thickness);
        return cornerPoints.contains(this);
    }

    //returning all the game screen corner points (the corners where the ball can move)
    private List<Point> getGameCornerPoints(int width, int height, double thickness) throws Exception {
        //calculating and adding to list all the corner points of the game screen (where the ball can move!).
        List<Point> cornerPoints = new ArrayList<>();
        cornerPoints.add(new Point(thickness,thickness));
        cornerPoints.add(new Point(width - thickness, thickness));
        cornerPoints.add(new Point(thickness,height - thickness));
        cornerPoints.add(new Point(width - thickness, height -thickness));

        return cornerPoints;
    }


//    /**
//     //     * (Without limiting generality let b <= c).
//     //     * check if a double value 'a' belongs in the section (b,c) of double
//     //     * values.
//     //     *
//     //     * @param a double variable.
//     //     * @param b double variable.
//     //     * @param c double variable.
//     //     * @return true, if a is in (b,c) and 'false' otherwise.
//     //     */
//    private static boolean belongs(double a, double b, double c) {
//        if (a < max(b, c) && a > min(b, c)) {
//            return true;
//        }
//        return false;
//    }
}
