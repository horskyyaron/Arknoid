//ID: 204351670

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Line class supports methods that their goal is to represent a Line in 2D
 * space (XY plane).
 */
public class Line {

    //fields.
    private Point end;
    private Point start;

    /**
     * contructor function
     *
     * constructor of the 'Line' object.
     * a Line object has two components: a starting point (Point object)
     * and an ending point (Point object).
     *
     * @param a Point a.
     * @param b Point b.
     * @throws Exception when getting negative coordinates.
     */
    public Line(Point a, Point b) throws Exception {
        if (a.getX() < 0 || a.getY() < 0 || b.getX() < 0 || b.getY() < 0) {
            throw new Exception("Points cannot have negative coordinates!");
        }
        this.start = a;
        this.end = b;
    }

    /**
     * contructor function 2.
     *
     * constructor of the 'Line' object.
     * A line is constructed from 2 points in space which are four coordinates.
     * starting point: x and y coordinate. and ending point x and y coordinates.
     *
     * @param x1 x-coordinate of starting point.
     * @param y1 y-coordinate of starting point.
     * @param x2 x-coordinate of ending point.
     * @param y2 y-coordinate of ending point.
     * @throws Exception when getting negative coordinates.
     */
    public Line(double x1, double y1, double x2, double y2) throws Exception {
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
            throw new Exception("Points cannot have negative coordinates!");
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * returns the length of a line
     *
     * length of Line object is the distance between the two points:
     * starting point and ending point.
     * Using Point class method of measuring distance between two points will
     * give the Line's length.
     *
     * @return double, Line's length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * returns the middle point of a line.
     *
     * @return Point, Line's middle point.
     * @throws Exception when getting negative coordinates for the new Point.
     */
    public Point middle() throws Exception {
        return new Point((start.getX() + end.getX()) / 2,
                       (start.getY() + end.getY()) / 2);
    }

    /**
     * returns the starting point of a given line.
     *
     * @return Point, Line's starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the ending point of a given line.
     *
     * @return Point, Line's ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * checks if the given line is intersecting with another line.
     *
     * @param other the other line being checked for intersection point.
     * @return true, if lines are intersecting, 'false' otherwise.
     * @throws Exception if one of the lines have points with negative
     *                   coordinates.
     */
    public boolean isIntersecting(Line other) throws Exception {
        //getting intersection point, returning null if no intersection.
        Point intersection = this.intersectionWith(other);
        if (intersection == null) {
            return false;
        //check if the two lines have the same starting\ending points.
        } else if (this.hasSameEdge(other)) {
            return true;
            //check if intersection point is in range.
        } else {
            //if lines have different slopes, checks if the intersection point
            //is within both lines limits.
            return (belongs(intersection.getX(), 'x', this)
                    && belongs(intersection.getY(), 'y', this)
                    && belongs(intersection.getX(), 'x', other)
                    && belongs(intersection.getY(), 'y', other));
        }
    }

    /**
     * returns the intersection point of a given line with another line.
     *
     * @param other the other line which is being checked for the intersection
     *              point.
     * @return Point, the intersection point of the two lines.
     * @throws Exception if one of the lines have points with negative
     *                   coordinates.
     */
    public Point intersectionWith(Line other) throws Exception {
        //check two parallel lines case.
        if (Double.compare(this.calcLineSlope(),
                other.calcLineSlope()) == 0) {
            return checkLineContinuesLine(other);
        } else {
            //check if the lines have one mutual edges
            if (this.hasSameEdge(other)) {
                return this.getMutualEdge(other);
            }
            //calculating the x and y coordinates of the intersection point of two
            //straight lines.
            double xIntersection = (other.calcFreeCoefficient()
                    - this.calcFreeCoefficient()) / (this.calcLineSlope()
                    - other.calcLineSlope());
            double yIntersection = (this.calcLineSlope()
                    * other.calcFreeCoefficient() - this.calcFreeCoefficient()
                    * other.calcLineSlope()) / (this.calcLineSlope()
                    - other.calcLineSlope());

            //in case of out of screen intersection point!
            if (xIntersection < 0 || yIntersection < 0) {
                return null;
            } else {
                return new Point(xIntersection, yIntersection);
            }
        }
    }

    /**
     * checks if two line are equal.
     *
     * meaning they have the same two points constructing them.
     * (not sensitive of having both starting points and ending points to be
     * the same. one's starting point can be the same as the other's ending
     * point if the one's ending point is the same as the other's starting
     * point and vice versa.)
     *
     * @param other another line variable.
     * @return true, if both lines are equal, 'false' otherwise.
     * @throws Exception if one of the lines have points with negative
     *                   coordinates.
     */
    public boolean equals(Line other) throws Exception {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start)));

    }

    /**
     * checks if a Line is parallel to the x-axis.
     *
     * @return true, if the line is parallel to the x-axis, 'false' otherwise.
     */
    private boolean isParallelToXAxis() {
        return (Double.compare(this.end.getY(),
                this.start.getY()) == 0);
    }

    /**
     * checks if a Line is parallel to the y-axis.
     *
     * @return true, if the line is parallel to the y-axis, 'false' otherwise.
     */
    private boolean isParallelToYAxis() {
        return (Double.compare(this.end.getX(),
                this.start.getX()) == 0);
    }

    /**
     * calculating a line's slope using the line equation formula between
     * two points.
     *
     * y - y2 = ((y2 - y1)/(x2 - x1)) * (x - x2).
     * when the slope of the line equation is: ((y2 - y1)/(x2 - x1))
     * the slope will be defined as '0' if parallel to an axis.
     *
     * @return double, returning the slope of the Line's equation.
     */
    private double calcLineSlope() {
            //if a line is parallel to one of the axis we'll define the slope
            //as 0.
            if (this.isParallelToYAxis() || this.isParallelToXAxis()) {
                return 0;
            } else {
                return ((this.end.getY() - this.start.getY())
                        / (this.end.getX() - this.start.getX()));
            }

    }

    /**
     * calculating and returning line's free coefficient of the line equation
     * using the line equation formula.
     *
     * y = mx + b.
     * when the free coefficient of the line equation is: b and the slope is m.
     *
     * plugging starting point's x and y coordinate will satisfy
     * the equation.
     *
     * @return double, returning the slope of the Line's equation.
     */
    private double calcFreeCoefficient() {
        if (this.isParallelToXAxis()) {
            return this.start.getY();
        } else if (this.isParallelToYAxis()) {
            return this.start.getX();
        } else {
            return (this.end.getY() - this.calcLineSlope() * (this.end.getX()));
        }
    }

    /**
     * check if two lines are continuing each other, with an intersection point
     * between them.
     *
     * @param other another line variable. checked for intersection.
     * @return true, if the two lines are continuing each other, 'false'
     *               otherwise.
     * @throws Exception if the intersection point has negative coordinates.
     */
    private Point checkLineContinuesLine(Line other)
            throws Exception {
        //if both lines are the same, return null.
        if (this.equals(other)) {
            return null;
            //check if two lines are parallel.
        } else {
            //check if lines overlap
            if (areProjectionsOverlapping(this, other)) {
                return null;
                //check if there is only one intersection point.
            } else if (this.hasSameEdge(other)) {
                return this.getMutualEdge(other);
            }
        }
        return null;
    }

    /**
     * returns the mutual "base" point of two lines.
     * "base" point meaning starting/ending point of a line.
     *
     * @param other another line variable.
     * @return Point, returns the mutual "base" point.
     * @throws Exception if the mutual edge point has negative coordinates.
     */
    private Point getMutualEdge(Line other) throws Exception {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        } else {
            return null;
        }
    }

    /**
     * check if two lines have at least one same "base" point.
     * "base" point meaning starting/ending point of a line.
     *
     * @param other another line variable.
     * @return true, if the two lines have a mutual "base" point.
     * @throws Exception if the mutual edge point has negative coordinates.
     */
    private boolean hasSameEdge(Line other) throws Exception {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return true;
        } else if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * check if the projections of two lines are overlapping.
     *
     * each line will have a projection on both x and y axis.
     * the function will check if the projections are overlapping either in
     * both axis' or only one of them.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap.
     */
    private boolean areProjectionsOverlapping(Line l1, Line l2) {
        return (areXAxisProjectionsOverlap(l1, l2)
                && areYAxisProjectionsOverlap(l1, l2)
                || (areXAxisProjectionsOverlap(l2, l1)
                && areYAxisProjectionsOverlap(l2, l1)));
    }

    /**
     * check if the projections of two lines are overlapping on the x-axis.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap on the x axis..
     */
    private boolean areXAxisProjectionsOverlap(Line l1, Line l2) {
        double l1StartPointXCoordinate = l1.start().getX();
        double l1EndPointXCoordinate = l1.end().getX();
        double l2StartPointXCoordinate = l2.start().getX();
        double l2EndPointXCoordinate = l2.end().getX();
        //check overlap on the x-axis projections segments.
        return (belongs(l1StartPointXCoordinate, l2StartPointXCoordinate,
                l2EndPointXCoordinate) || (belongs(l1EndPointXCoordinate,
                l2StartPointXCoordinate, l2EndPointXCoordinate)));
    }

    /**
     * check if the projections of two lines are overlapping on the y-axis.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap on the y axis..
     */
    private static boolean areYAxisProjectionsOverlap(Line l1, Line l2) {
        double l1StartPointYCoordinate = l1.start().getY();
        double l1EndPointYCoordinate = l1.end().getY();
        double l2StartPointYCoordinate = l2.start().getY();
        double l2EndPointYCoordinate = l2.end().getY();
        //check overlap on the x-axis projections segments.
        return belongs(l1StartPointYCoordinate, l2StartPointYCoordinate,
                l2EndPointYCoordinate) || (belongs(l1EndPointYCoordinate,
                l2StartPointYCoordinate, l2EndPointYCoordinate));
    }

    /**
     * (Without limiting generality let b <= c).
     * check if a double value 'a' belongs in the section (b,c) of double
     * values.
     *
     * @param a double variable.
     * @param b double variable.
     * @param c double variable.
     * @return true, if a is in (b,c) and 'false' otherwise.
     */
    private static boolean belongs(double a, double b, double c) {
        if (a < max(b, c) && a > min(b, c)) {
            return true;
        }
        return false;
    }

    /**
     * checks if a coordinate from a given axis belongs to the section projected
     * by line on that same axis.
     *
     * @param coordinate an 'x' or 'y' coordinate.
     * @param axis a char variable that indicates if the coordinate is of
     *              x, or y axis.
     * @param l the line that the projected segment is being projected from.
     * @return true, if the coordinate is in the segment projected, 'false'
     *               otherwise.
     */
    private boolean belongs(double coordinate, char axis, Line l) {
        switch (axis) {
            case 'x':
                if (coordinate < max(l.start().getX(), l.end().getX())
                        && coordinate > min(l.start().getX(), l.end().getX())) {
                    return true;
                }
            case 'y':
                if (coordinate < max(l.start().getY(), l.end().getY())
                        && coordinate > min(l.start().getY(), l.end().getY())) {
                    return true;
                }
            default:
                return false;
        }
    }

    //FOR TESTING!!!
    public double getSlope() {
        return this.calcLineSlope();
    }

    public boolean getIsParallelToXAxis() {
        return this.isParallelToXAxis();
    }

    public boolean getIsParallelToYAxis() {
        return this.isParallelToYAxis();
    }

    public double getFreeCoefficient() {
        return this.calcFreeCoefficient();
    }

    public boolean getAreProjectionsOverlapping(Line l1, Line l2) {
        return this.areProjectionsOverlapping(l1, l2);
    }
}
