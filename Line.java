//ID: 204351670

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
        //check if intersection point is in range.
        } else if (this.hasSameEdge(other)) {
            return true;
        } else {
            //check case when one of the lines is parallel to the axis:
            if (this.isParallelToYAxis() || this.isParallelToXAxis()
                    || other.isParallelToYAxis() || other.isParallelToXAxis()) {
                if (this.isParallelToYAxis()) {
                    return (HelpFunctions.belongs(intersection.getY(), 'y',
                            other));
                } else if (this.isParallelToXAxis()) {
                    return (HelpFunctions.belongs(intersection.getX(), 'x',
                            other));
                } else if (other.isParallelToYAxis()) {
                    return (HelpFunctions.belongs(intersection.getY(), 'y',
                            this));
                } else {
                    return (HelpFunctions.belongs(intersection.getX(), 'x',
                            this));
                }
            }
            return (HelpFunctions.belongs(intersection.getX(), 'x', this)
                    && HelpFunctions.belongs(intersection.getY(), 'y', this)
                    && HelpFunctions.belongs(intersection.getX(), 'x', other)
                    && HelpFunctions.belongs(intersection.getY(), 'y', other));
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
            return checkParallelIntersectingCase(other);
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
    public boolean isParallelToXAxis() {
        return (Double.compare(this.end.getY(),
                this.start.getY()) == 0);
    }

    /**
     * checks if a Line is parallel to the y-axis.
     *
     * @return true, if the line is parallel to the y-axis, 'false' otherwise.
     */
    public boolean isParallelToYAxis() {
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
    public double calcLineSlope() {
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
    public double calcFreeCoefficient() {
        if (this.isParallelToXAxis()) {
            return this.start.getY();
        } else if (this.isParallelToYAxis()) {
            return this.start.getX();
        } else {
            return (this.end.getY() - this.calcLineSlope() * (this.end.getX()));
        }
    }

    /**
     * check if two lines have the same slope.
     *
     * @param l2 the second line, which is being checked for haveing the same
     *           slope.
     * @return true, if both lines have the same slope, 'false' otherwise.
     */
    public boolean haveSameSlope(Line l2) {
        return (Double.compare(this.calcLineSlope(),
                l2.calcLineSlope()) == 0);
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
    public Point checkParallelIntersectingCase(Line other) throws Exception {
        //if both lines are the same, return null.
        if (this.equals(other)) {
            return null;
            //check if two lines are parallel.
        } else if (this.haveSameSlope(other)) {
            //check if lines overlap
            if (HelpFunctions.areProjectionsOverlapping(this, other)) {
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
     * check if two lines have a same "base" point.
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
}
