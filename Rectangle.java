//ID: 204351670

import java.util.LinkedList;
import java.util.Objects;

/**
 * Rectangle class supports methods that their goal is to represent a certain
 * rectangle on screen.
 */
public class Rectangle {

    //fields.
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor of the 'Rectangle' object.
     *
     * @param upperLeft a Point object which will be the top left corner of the
     *                frame.
     * @param width holds the rectangle width.
     * @param height holds the rectangle height.
     * @throws Exception if top left rectangle corner point gets negative
     *                   coordinates values.
     */
    public Rectangle(Point upperLeft, double width, double height)
            throws Exception {
        if (width < 0 || height < 0) {
            throw new Exception("can't have negative value for width or "
                    + "height");
        } else {
            this.upperLeft = upperLeft;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * returns the top left corner of the rectangle (Point object).
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
     * @param line the Line object which is being checked for intersetion points
     *            with the given rectangle.
     * @return Point object list which holds the intersection points with the
     *         line.
     * @throws Exception if the line has Points components with negative
     *         coordinates.
     */
    public java.util.List<Point> intersectionPoints(Line line)
            throws Exception {
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
     * @return Line array of the rectangles edges.
     * @throws Exception if one of the line has Points components with negative
     *         coordinates.
     */
    private Line[] rectangleToLinesArray() throws Exception {
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
     * @return Point array of the rectangle's frame corners.
     * @throws Exception if one of the line has Points components with negative
     *         coordinates.
     */
    private Point[] rectangleToEdgePointsArray() throws Exception {
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
     * @return Point array of the rectangle's frame corners.
     * @throws Exception if one of the line has Points components with negative
     *         coordinates.
     */
    public Point[] getRectangleToEdgePointsArray() throws Exception {
        return rectangleToEdgePointsArray();
    }

    /**
     * returns rectangle's edges, Line array.
     *
     * @return Line array of the rectangles edges.
     * @throws Exception if one of the line has Points components with negative
     *         coordinates.
     */
    public Line[] getRectangleToLinesArray() throws Exception {
        return rectangleToLinesArray();
    }

    /**
     * returns the intersection points of given rectangle with input line.
     *
     * @param line the Line object which is being checked for intersection
     *             points with rectangle.
     * @return Point object list which holds the intersection points with the
     *         line.
     * @throws Exception if the line has Points components with negative
     *         coordinates.
     */
    public java.util.List<Point> getIntersectionPoints(Line line)
            throws Exception {
        return intersectionPoints(line);
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
