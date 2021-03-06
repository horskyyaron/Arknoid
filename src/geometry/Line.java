//ID: 204351670

package geometry;

import biuoop.DrawSurface;

import java.util.List;
import java.util.Objects;
import static java.lang.Math.min;
import static java.lang.Math.max;

/**
 * geometry.Line class supports methods that their goal is to represent a geometry.Line in 2D
 * space (XY plane).
 */
public class Line {

    //fields.
    private Point end;
    private Point start;

    /**
     * contructor function
     *
     * constructor of the 'geometry.Line' object.
     * a geometry.Line object has two components: a starting point (geometry.Point object)
     * and an ending point (geometry.Point object).
     *
     * @param a geometry.Point a.
     * @param b geometry.Point b.
     */
    public Line(Point a, Point b) {
        this.start = a;
        this.end = b;
    }

    /**
     * contructor function 2.
     *
     * constructor of the 'geometry.Line' object.
     * A line is constructed from 2 points in space which are four coordinates.
     * starting point: x and y coordinate. and ending point x and y coordinates.
     *
     * @param x1 x-coordinate of starting point.
     * @param y1 y-coordinate of starting point.
     * @param x2 x-coordinate of ending point.
     * @param y2 y-coordinate of ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * returns the length of a line
     * <p>
     * length of geometry.Line object is the distance between the two points:
     * starting point and ending point.
     * Using geometry.Point class method of measuring distance between two points will
     * give the geometry.Line's length.
     *
     * @return double, geometry.Line's length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * returns the middle point of a line.
     *
     * @return geometry.Point, geometry.Line's middle point.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2);
    }

    /**
     * returns the starting point of a given line.
     *
     * @return geometry.Point, geometry.Line's starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the ending point of a given line.
     *
     * @return geometry.Point, geometry.Line's ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * checks if the given line is intersecting with another line.
     *
     * @param other the other line being checked for intersection point.
     * @return true, if lines are intersecting, 'false' otherwise.
     */
    public boolean isIntersecting(Line other) {
        LinearSystemOfEquation system = new LinearSystemOfEquation(this, other);
        //check if the system have one unique solution.
        if (system.getIsSystemSolutionUnique()) {
            //check for one mutual base point case
            if (hasOneMutualBasePoint(other)) {
                return true;
                //check regular case.
            } else {
                //check if intersection is within the lines range.
                Point intersection = system.getSystemSolution();
                if (intersection == null) {
                    return false;
                    //check if the intersection point, is one of the lines base
                    // points.
                } else if (isIntersectionPointABasePoint(intersection, this, other)) {
                    //if the intersection belongs to a line, check if it's also
                    // in the second line range.
                    if (this.isPointALinesBasePoint(intersection)) {
                        return (belongsWithEdges(intersection.getX(), 'x',
                                other)
                                && belongsWithEdges(intersection.getY(), 'y',
                                other));
                    } else {
                        return (belongs(intersection.getX(), 'x', this)
                                && belongs(intersection.getY(), 'y', this));
                    }
                } else {
                    return (isIntersectionPointInLinesRange(intersection, system));
                }
            }
        } else {
            //check for continuing lines case:
            return checkContinuingLines(other);
        }
    }

    /**
     * returns the intersection point of a given line with another line.
     *
     * @param other the other line which is being checked for the intersection
     *              point.
     * @return geometry.Point, the intersection point of the two lines.
     */
    public Point intersectionWith(Line other) {

        if (this.isIntersecting(other)) {
            //if one mutual base point.
            if (this.hasOneMutualBasePoint(other)) {
                return this.getMutualBasePoint(other);
            }
            //if no mutual base point.
            LinearSystemOfEquation system = new LinearSystemOfEquation(this,
                    other);
            return system.getSystemSolution();
        } else {
            return null;
        }
    }


    /**
     * tells if a point is a base point of the given line.
     *
     * base point - either the starting or the ending point of a line.
     *
     * @param point the point being checked for being a base point.
     * @return 'true' if the input point is a base point in the given line.
     */
    private boolean isPointALinesBasePoint(Point point) {
        return (point.equals(this.start) || point.equals(this.end));
    }

    /**
     * tells if an intersection point is a base point of either two input lines.
     *
     * base point - either the starting or the ending point of a line.
     *
     * @param intersection the intersection point being checked for being a
     *                     base point in one of the lines.
     * @param line a geometry.Line object.
     * @param other another geometry.Line object.
     * @return 'true' if the input point is a base point in either on of the two
     *          input lines.
     */
    private boolean isIntersectionPointABasePoint(Point intersection, Line line, Line other) {
        return (intersection.equals(line.start)
                || intersection.equals(line.end)
                || intersection.equals(other.end)
                || intersection.equals(other.start));
    }

    /**
     * tells if an intersection point is in the lines range, and not in their
     * continuation.
     *
     * @param intersection the intersection point being checked for being a
     *                     base point in one of the lines.
     * @param system system of two line equations.
     * @return 'true' if the intersection point is within the lines range.
     *          'false' therwise.
     */
    private boolean isIntersectionPointInLinesRange(Point intersection,
                                                    LinearSystemOfEquation system) {

        //declaring intersection point coordinate and Lines variable for
        // readability.
        double x = intersection.getX();
        double y = intersection.getY();
        Line l1 = system.getEquation1().getLine();
        Line l2 = system.getEquation2().getLine();

        //in case the two lines are parallel
        if (l1.isParallel() && l2.isParallel()) {
            if (l1.isParallelToX()) {
                //l2 is parallel to y.
                return (belongsWithEdges(intersection.getX(), 'x', l1)
                        && belongsWithEdges(intersection.getY(), 'y', l2));
            } else {
                //l1 is parallel to y-axis and l2 is parallel to x-axis.
                return (belongsWithEdges(intersection.getX(), 'x', l2)
                        && belongsWithEdges(intersection.getY(), 'y', l1));
            }
        }
        //in case one of the line is parallel to axis:
        //of the form y=c
        if (l1.isParallelToX()) {
            return (belongsWithEdges(x, 'x', l1) && belongsWithEdges(x, 'x', l2)
                    && belongsWithEdges(y, 'y', l2));
            //of the form x=c
        } else if (l1.isParallelToY()) {
            return (belongsWithEdges(y, 'y', l1) && belongsWithEdges(y, 'y', l2)
                    && belongsWithEdges(x, 'x', l2));
            //of the form y=c
        } else if (l2.isParallelToX()) {
            return (belongsWithEdges(x, 'x', l1) && belongsWithEdges(x, 'x', l2)
                    && belongsWithEdges(y, 'y', l1));
            //of the form x=c
        } else if (l2.isParallelToY()) {
            return (belongsWithEdges(y, 'y', l1) && belongsWithEdges(y, 'y', l2)
                    && belongsWithEdges(x, 'x', l1));
        } else {
            //check if the intersection point is within the lines range.
            return (belongsWithEdges(x, 'x', l1) && belongsWithEdges(x, 'x', l2) && belongsWithEdges(y, 'y', l1)
                    && belongsWithEdges(y, 'y', l2));
        }
    }

    /**
     * tells if the given line is parallel to the x-axis.
     *
     * meaning, of the form y = c, when c is some constant number.
     *
     * @return 'true' if the line is parallel to the x-axis. 'false' otherwise.
     */
    private boolean isParallelToX() {
        LineEquation equation = new LineEquation(this);
        //if of the form y=c
        return (equation.getXCoefficient() == 0);
    }

    /**
     * tells if the given line is parallel to the y-axis.
     *
     * meaning, of the form x = c, when c is some constant number.
     *
     * @return 'true' if the line is parallel to the y-axis. 'false' otherwise.
     */
    private boolean isParallelToY() {
        LineEquation equation = new LineEquation(this);
        //if of the form x=c
        return (equation.getYCoefficient() == 0);
    }

    /**
     * tells if the given line is parallel to one of the axes.
     *
     * @return 'true' if the line is parallel to on the axes.
     */
    private boolean isParallel() {
        return (this.isParallelToX() || this.isParallelToY());
    }

    /**
     * check if two lines can be a continua of each another.
     *
     * the case when line1 is from points A->B and line2 is from point B->C
     * and they have the same slope.
     *
     * @param other the input line.
     * @return 'true' the given line and the input ine are a continua of one
     *          another.
     */
    private boolean checkContinuingLines(Line other) {
        //same line
        if (this.equals(other)) {
            return false;
            //check if there is one intersection point.
        } else if (hasOneMutualBasePoint(other)) {
            //check continuing case.
            return (isBasePointValidForContinuingLineCase(other,
                    getMutualBasePoint(other)));
        }
        //parallel lines case.
        return false;
    }

    /**
     * check if a base point is a valid intersection point, meaning,
     * that both lines continue each other with same slope.
     *
     * @param other the input line.
     * @param mutualBasePoint the mutual base point.
     * @return 'true' if the mutual base point is a valid intersection point
     *          of the given line and the input line. 'false' otherwise.
     */
    private boolean isBasePointValidForContinuingLineCase(Line other,
                                                      Point mutualBasePoint) {
        //getting the non mutual points in each of the lines.
        Point lineNonMutualPoint, otherLineNonMutualPoint;
        if (this.start.equals(mutualBasePoint)) {
            lineNonMutualPoint = this.end;
        } else {
            lineNonMutualPoint = this.start;
        }
        if (other.start.equals(mutualBasePoint)) {
            otherLineNonMutualPoint = other.end;
        } else {
            otherLineNonMutualPoint = other.start;
        }

        //checks if the non mutual base point, is within the other line's range,
        // therefore one line is a sub-line of the other.
        if (belongs(lineNonMutualPoint.getX(), 'x', other)
                && belongs(lineNonMutualPoint.getY(), 'y', other)) {
            return false;
        } else {
            return !belongs(otherLineNonMutualPoint.getX(), 'x', this)
                    || !belongs(otherLineNonMutualPoint.getY(), 'y', this);
        }
    }

    /**
     * will return the mutual base point of the given line and the input
     * line.
     *
     * @param other input line.
     * @return the mutual base point if the given line and the input line.
     *          if the lines don't have a mutual base point, the function will
     *          return null.
     */
    private Point getMutualBasePoint(Line other) {
        if (this.start.equals(other.start) && !this.end.equals(other.end)) {
            return this.start;
        } else if (this.start.equals(other.end)
                && !this.end.equals(other.start)) {
            return this.start;
        } else if (this.end.equals(other.end)
                && !this.start.equals(other.start)) {
            return this.end;
        } else if (this.end.equals(other.start)
                && !this.start.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    /**
     * check if the given line and the input line has one mutual base point.
     *
     * @param other the input line.
     * @return 'true' if the lines have one mutual base point. 'false'
     *          otherwise.
     */
    private boolean hasOneMutualBasePoint(Line other) {
        if (this.start.equals(other.start) && !this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end)
                && !this.end.equals(other.start)) {
            return true;
        } else if (this.end.equals(other.end)
                && !this.start.equals(other.start)) {
            return true;
        } else {
            return this.end.equals(other.start)
                    && !this.start.equals(other.end);
        }
    }

    /**
     * check if the input coordinate value is within the input line range in
     * the input axis. (not including the edges of the line)
     *
     * @param coordinate the coordinate value.
     * @param axis the axis being checked.
     * @param line the input line.
     * @return 'true' if the coordinate value is within the range of the input
     *          line on the input axis.
     */
    public boolean belongs(double coordinate, char axis, Line line) {
        double a, b;
        switch (axis) {
            case 'y':
                a = line.start.getY();
                b = line.end.getY();
                return (coordinate < max(a, b) && coordinate > min(a, b));
            case 'x':
                a = line.start.getX();
                b = line.end.getX();
                return (coordinate < max(a, b) && coordinate > min(a, b));
            default:
                return false;
        }
    }

    /**
     * check if the input coordinate value is within the input line range in
     * the input axis. (including the edges of the line)
     *
     * @param coordinate the coordinate value.
     * @param axis the axis being checked.
     * @param line the input line.
     * @return 'true' if the coordinate value is within the range of the input
     *          line on the input axis.
     */
    private boolean belongsWithEdges(double coordinate, char axis, Line line) {
        switch (axis) {
            case 'x':
                return belongs(coordinate, axis, line)
                        || Double.compare(coordinate, line.start.getX()) == 0
                        || Double.compare(coordinate, line.end.getX()) == 0;
            case 'y':
                return belongs(coordinate, axis, line)
                        || Double.compare(coordinate, line.start.getY()) == 0
                        || Double.compare(coordinate, line.end.getY()) == 0;
            default:
                return false;
        }
    }

    /**
     * calculating and returning the given line slope.
     *
     * @return given line's slope.
     */
    public double getSlope() {
        //if line is parallel, return 0;
        if (Double.compare(this.start().getX(), this.end().getX()) == 0
                || (Double.compare(this.start().getY(),
                this.end().getY()) == 0)) {
            return 0;
        } else {
            //calculating line slope from two point equation.
            return ((this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX()));
        }
    }

    /**
     * will calculate the intersection points with an input rectangle, and will
     * return the closets intersection point to the starting point of the given
     * line.
     *
     * @param rect the input rectangle.
     * @return the closest intersection point to the starting point of the given
     *         line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //check if line is intersecting with given rectangle.
        if (!this.isIntersectingWithRec(rect)) {
            return null;
        } else {
            List<Point> intersectionPointsWithRec =
                    rect.getIntersectionPoints(this);
            return this.start.getClosetsFromPointList(
                    intersectionPointsWithRec);
            }
    }

    /**
     * check if the given line is intersection with the input rectangle.
     *
     * @param rect the input rectangle.
     * @return 'true' if the line intersects with the input rectangle.
     */
    private boolean isIntersectingWithRec(Rectangle rect) {
        Line[] recEdges = rect.getRectangleToLinesArray();
        return (this.isIntersecting(recEdges[0])
                || this.isIntersecting(recEdges[1])
                || this.isIntersecting(recEdges[2])
                || this.isIntersecting(recEdges[3]));
    }

    /**
     * checks if the given line is a vertical line.
     *
     * meaning of the form: x = c.
     *
     * @return 'true' if the line is parallel to y axis.
     */
    public boolean isVertical() {
        return this.isParallelToY();
    }

    /**
     * checks if the given line is a horizontal line.
     *
     * meaning of the form: y = c.
     *
     * @return 'true' if the line is parallel to x axis.
     */
    public boolean isHorizontal() {
        return this.isParallelToX();
    }

    /**
     * will draw line on the input DrawSurface object.
     *
     * @param surface the input surface.
     */
    public void drawLine(DrawSurface surface) {
        surface.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
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
     * @param obj another line variable.
     * @return true, if both lines are equal, 'false' otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Line) {
            Line test = (Line) obj;
            if (this.start.equals(test.start) && this.end.equals(test.end)
                    || this.start.equals(test.end)
                    && this.end.equals(test.start)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(end, start);
    }
}
