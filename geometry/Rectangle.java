//ID: 204351670
package geometry;

import execution.GameConstants;
import geometry.line.Line;

import java.util.LinkedList;
import java.util.Objects;

/**
 * geometry.Rectangle class supports methods that their goal is to represent a certain
 * rectangle on screen.
 */
public class Rectangle {

    //fields.
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor of the 'geometry.Rectangle' object.
     *
     * @param upperLeft a geometry.Point object which will be the top left corner of the                frame.
     * @param width     holds the rectangle width.
     * @param height    holds the rectangle height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        if (width < 0 || width > GameConstants.getWidth() || height < 0
                || height > GameConstants.getHeight()) {
            throw new IllegalArgumentException("can't have a rectangle with"
                    + "negative height or width or negative height or width.");
        } else {
            this.upperLeft = upperLeft;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * returns the top left corner of the rectangle (geometry.Point object).
     *
     * @return the given rectangle top left corner point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * returns the rectangle width.
     *
     * @return the given rectangle width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the rectangle height.
     *
     * @return the given rectangle height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns the intersection points of given rectangle with input line.
     *
     * @param line the geometry.line.Line object which is being checked for intersetion points
     *             with the given rectangle.
     * @return geometry.Point object list which holds the intersection points with the         line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //getting an array of the rectangle Lines.
        Line[] rectangleEdges = rectangleToLinesArray();
        java.util.List<Point> pointList = new LinkedList<>();

        /*

        if the line has an intersection point with the rectangle ledges,
        add those points to the list..
         */
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(rectangleEdges[i])) {
                Point intersection = line.intersectionWith(rectangleEdges[i]);
                //check if list already contains an intersection point.
                if (!pointList.contains(intersection)) {
                    pointList.add(intersection);
                }
            }
        }
        return pointList;
    }

    /**
     * creates an array of lines, which compose the rectangle's frame.
     *
     * @return geometry.line.Line array of the rectangles edges.
     */
    private Line[] rectangleToLinesArray() {
        Point[] rectangleEdgePoints = rectangleToEdgePointsArray();
        Line[] lines = new Line[4];

        //construction the rectangle's edges using the corner points.
        lines[0] = new Line(rectangleEdgePoints[0], rectangleEdgePoints[1]);
        lines[1] = new Line(rectangleEdgePoints[1], rectangleEdgePoints[2]);
        lines[2] = new Line(rectangleEdgePoints[2], rectangleEdgePoints[3]);
        lines[3] = new Line(rectangleEdgePoints[3], rectangleEdgePoints[0]);

        return lines;

    }

    /**
     * creates an array of Points, which compose the rectangle's frame corners
     * points.
     *
     * @return geometry.Point array of the rectangle's frame corners.
     */
    private Point[] rectangleToEdgePointsArray() {
        Point upperLeftCorner = this.upperLeft;
        Point upperRightCorner = new Point(upperLeftCorner.getX() + this.width,
                upperLeftCorner.getY());
        Point bottomRightCorner = new Point(upperLeftCorner.getX() + width,
                upperLeftCorner.getY() + this.height);
        Point bottomLeftCorner = new Point(upperLeftCorner.getX(),
                upperLeftCorner.getY() + this.height);

        Point[] arr = new Point[4];
        arr[0] = upperLeftCorner;
        arr[1] = upperRightCorner;
        arr[2] = bottomRightCorner;
        arr[3] = bottomLeftCorner;

        return arr;

    }


    /**
     * returns rectangle's corner points array.
     *
     * @return geometry.Point array of the rectangle's frame corners.
     */
    public Point[] getRectangleToEdgePointsArray() {
        return rectangleToEdgePointsArray();
    }

    /**
     * returns rectangle's edges, geometry.line.Line array.
     *
     * @return geometry.line.Line array of the rectangles edges.
     */
    public Line[] getRectangleToLinesArray() {
        return rectangleToLinesArray();
    }

    /**
     * returns the intersection points of given rectangle with input line.
     *
     * @param line the geometry.line.Line object which is being checked for intersection
     *             points with rectangle.
     * @return geometry.Point object list which holds the intersection points with the
     *         line.
     */
    public java.util.List<Point> getIntersectionPoints(Line line) {
        return intersectionPoints(line);
    }

    /**
     * returns the middle of a rectangle. (mid of width, mid of height)
     *
     * @return the center point of the rectangle.
     */
    public Point getCenterOfRec() {
        double x = this.getUpperLeft().getX() + this.width / 2.0;
        double y = this.getUpperLeft().getY() + this.height / 2.0;
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        Rectangle rectangle = (Rectangle) obj;
        return Double.compare(rectangle.width, width) == 0
                && Double.compare(rectangle.height, height) == 0
                && Objects.equals(upperLeft, rectangle.upperLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperLeft, width, height);
    }
}
