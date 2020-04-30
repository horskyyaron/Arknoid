//ID: 204351670

import java.util.List;
import java.util.Objects;

import static java.lang.Math.*;

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
     * <p>
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
     * <p>
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
     * <p>
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
                if(intersection == null) {
                    return false;
                    //check if the intersection point, is one of the lines base
                    // points.
                } else if (isIntersectionPointABasePoint(intersection,this,other)) {
                    //if the intersection belongs to a line, check if it's also
                    // in the second line range.
                    if(this.isPointALinesBasePoint(intersection)) {
                        return (belongsWithEdges(intersection.getX(),'x',other)
                                && belongsWithEdges(intersection.getY(),'y',other));
                    } else {
                        return (belongs(intersection.getX(),'x',this)
                                && belongs(intersection.getY(),'y',this));
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
     * @return Point, the intersection point of the two lines.
     * @throws Exception if one of the lines have points with negative
     *                   coordinates.
     */
    public Point intersectionWith (Line other) throws Exception {

        if(this.isIntersecting(other)) {
            //if one mutual base point.
            if(this.hasOneMutualBasePoint(other)) {
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

    //tell if a point is a start\end point of a given line.
    private boolean isPointALinesBasePoint(Point point) {
        return (point.equals(this.start) || point.equals(this.end));
    }

    private boolean isIntersectionPointABasePoint(Point intersection, Line line, Line other) {
        return (intersection.equals(line.start)
                || intersection.equals(line.end)
                || intersection.equals(other.end)
                || intersection.equals(other.start));
    }

    private boolean isIntersectionPointInLinesRange(Point intersection,
                                                    LinearSystemOfEquation system) {

        //declaring intersection point coordinate and Lines variable for
        // readability.
        double x = intersection.getX();
        double y = intersection.getY();
        Line l1 = system.getEquation1().getLine();
        Line l2 = system.getEquation2().getLine();

        //in case the two lines are parallel
        if(l1.isParallel() && l2.isParallel()) {
            if(l1.isParallelToX()) {
                //l2 is parallel to y.
                return (belongsWithEdges(intersection.getX(),'x',l1)
                        && belongsWithEdges(intersection.getY(),'y',l2));
            } else {
                //l1 is parallel to y-axis and l2 is parallel to x-axis.
                return (belongsWithEdges(intersection.getX(),'x',l2)
                        && belongsWithEdges(intersection.getY(),'y',l1));
            }
        }
        //in case one of the line is parallel to axis:
        //of the form y=c
        if(l1.isParallelToX()) {
            return (belongs(x,'x',l1) && belongs(x,'x',l2) && belongs(y,'y',l2));
            //of the form x=c
        } else if (l1.isParallelToY()) {
            return (belongs(y,'y',l1) && belongs(y,'y',l2) && belongs(x,'x',l2));
            //of the form y=c
        } else if (l2.isParallelToX()) {
            return (belongs(x,'x',l1) && belongs(x,'x',l2) && belongs(y,'y',l1));
            //of the form x=c
        } else if (l2.isParallelToY()) {
            return (belongs(y,'y',l1) && belongs(y,'y',l2) && belongs(x,'x',l1));
        } else {
            //check if the intersection point is within the lines range.
            return (belongs(x, 'x', l1) && belongs(x, 'x', l2) && belongs(y, 'y', l1)
                    && belongs(y, 'y', l2));
        }
    }

    private boolean isParallelToX() {
        LineEquation equation = new LineEquation(this);
        //if of the form y=c
        return (equation.getXCoefficient() == 0);
    }
    private boolean isParallelToY() {
        LineEquation equation = new LineEquation(this);
        //if of the form x=c
        return (equation.getYCoefficient() == 0);
    }
    private boolean isParallel() {
        return (this.isParallelToX() || this.isParallelToY());
    }

    private boolean checkContinuingLines(Line other) throws Exception {
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
        if (belongs(lineNonMutualPoint.getX(),'x',other)
                && belongs(lineNonMutualPoint.getY(),'y',other)) {
            return false;
        } else if (belongs(otherLineNonMutualPoint.getX(),'x',this)
                && belongs(otherLineNonMutualPoint.getY(),'y',this)) {
            return false;
        } else {
            return true;
        }
    }
    private Point getMutualBasePoint(Line other) {
        if (this.start.equals(other.start) && !this.end.equals(other.end)) {
            return this.start;
        } else if (this.start.equals(other.end) && !this.end.equals(other.start)) {
            return this.start;
        } else if (this.end.equals(other.end) && !this.start.equals(other.start)) {
            return this.end;
        } else if (this.end.equals(other.start) && !this.start.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    private boolean hasOneMutualBasePoint(Line other) {
        if (this.start.equals(other.start) && !this.end.equals(other.end)) {
            return true;
        } else if (this.start.equals(other.end) && !this.end.equals(other.start)) {
            return true;
        } else if (this.end.equals(other.end) && !this.start.equals(other.start)) {
            return true;
        } else if (this.end.equals(other.start) && !this.start.equals(other.end)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean belongs(double coordinate,char axis, Line line) {
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

    private boolean belongsWithEdges(double coordinate,char axis, Line line) {
        switch (axis) {
            case 'x':
                return belongs(coordinate,axis,line)
                        || Double.compare(coordinate,line.start.getX()) == 0
                        || Double.compare(coordinate,line.end.getX()) == 0;
            case 'y':
                return belongs(coordinate,axis,line)
                        || Double.compare(coordinate,line.start.getY()) == 0
                        || Double.compare(coordinate,line.end.getY()) == 0;
            default:
                return false;
        }
    }

    public double getSlope() {
        //if line is parallel, return 0;
        if(Double.compare(this.start().getX(), this.end().getX()) == 0 ||
            (Double.compare(this.start().getY(), this.end().getY()) == 0)) {
            return 0;
        } else {
            //calculating line slope from two point equation.
            return ((this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX()));
        }
    }

    public Point closestIntersectionToStartOfLine(Rectangle rect)
            throws Exception {
        //check if line is intersecting with given rectangle.
        if (!this.isIntersectingWithRec(rect)) {
            return null;
        } else {
            List<Point> intersectionPointsWithRec =
                    rect.getIntersectionPoints(this);
            return this.start.getClosetsFromPointList(intersectionPointsWithRec);
            }
    }

    private boolean isIntersectingWithRec(Rectangle r) throws Exception {
        Line[] recEdges = r.getRectangleToLinesArray();
        return (this.isIntersecting(recEdges[0])
                || this.isIntersecting(recEdges[1])
                || this.isIntersecting(recEdges[2])
                || this.isIntersecting(recEdges[3]));
    }

    public boolean isVertical() {
        return (Double.compare(this.start.getX(), this.end.getX()) == 0);
    }

    public boolean isHorizontal() {
        return (Double.compare(this.start.getY(), this.end.getY()) == 0);
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
