//ID: 204351670
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Frame class supports methods that their goal is to represent a certain
 * rectangle on screen, which will hold animation within it's limits.
 */
public class Rectangle {

    //fields.
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;

    /**
     * constructor of the 'Frame' object.
     *
     * @param upperleft a Point object which will be the top left corner of the
     *                frame.
     * @param width holds the frame width.
     * @param height holds the frame height.
     * @param color holds the frame background color.
     * @throws Exception if top left frame corner point gets negative
     *                   coordinates values.
     */
    public Rectangle(Point upperleft, int width, int height,
                     java.awt.Color color) throws Exception {

        this.upperLeft = upperleft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * returns the top left corner of the frame - a Point object..
     *
     * @return the given frame top left corner point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * returns the frame width.
     *
     * @return the given frame width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the frame height.
     *
     * @return the given frame height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * returns the frame background color.
     *
     * @return the given frame background color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draws frame onto the screen.
     *
     * @param surface the surface which the drawing of frames will be on.
     */
    public void drawFrame(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getX(),(int) this.width,
                (int) this.height);
    }

    public java.util.List<Point> intersectionPoints(Line line)
            throws Exception {
        //getting an array of the rectangle Lines.
        Line[] rectangleEdges = rectangleToLinesArray();
        java.util.List<Point> pointList = new ArrayList<>();

        //if the line has an intersection point with the rectangle ledges,
        // add those points to the list..
        for (int i = 0; i < 4; i++) {
            if(line.isIntersecting(rectangleEdges[i])) {
                Point intersection = line.intersectionWith(rectangleEdges[i]);
                if(!pointList.contains(intersection)) {
                    pointList.add(intersection);
                }
            }
        }
        return pointList;
    }

    private Line[] rectangleToLinesArray() throws Exception {
        Point[] rectangleEdgePoints = rectangleToEdgePointsArray();
        Line[] lines = new Line[4];

        lines[0] = new Line(rectangleEdgePoints[0],rectangleEdgePoints[1]);
        lines[1] = new Line(rectangleEdgePoints[1],rectangleEdgePoints[2]);
        lines[2] = new Line(rectangleEdgePoints[2],rectangleEdgePoints[3]);
        lines[3] = new Line(rectangleEdgePoints[3],rectangleEdgePoints[0]);

        return lines;

    }

    private Point[] rectangleToEdgePointsArray() throws Exception {
        Point upperLeft = this.upperLeft;
        Point upperRight = new Point (upperLeft.getX() + this.width,
                upperLeft.getY());
        Point bottomRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + this.height);
        Point bottomLeft = new Point (upperLeft.getX(), upperLeft.getY()
                + this.height);

        Point[] arr = new Point[4];
        arr[0] = upperLeft;
        arr[1] = upperRight;
        arr[2] = bottomRight;
        arr[3] = bottomLeft;

        return arr;

    }


    //Getters for checking.
    public Point[] getRectangleToEdgePointsArray() throws Exception {
        return rectangleToEdgePointsArray();
    }

    public Line[] getRectangleToLinesArray() throws Exception {
        return rectangleToLinesArray();
    }

    public java.util.List<Point> getIntersectionPoints(Line line)
            throws Exception {
        return intersectionPoints(line);
    }

}
