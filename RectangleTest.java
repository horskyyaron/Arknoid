import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.*;
import java.util.LinkedList;


public class RectangleTest {
    @Test
    public void testRectangleToEdgePointsArray() throws Exception {
        Rectangle r = new Rectangle(new Point(0,0),100,100);
        Point[] pointArr = r.getRectangleToEdgePointsArray();
        assertTrue("supposed to be true", pointArr[0].equals(new Point(0,0)));
        assertTrue("supposed to be true", pointArr[1].equals(new Point(100,0)));
        assertTrue("supposed to be true", pointArr[2].equals(new Point(100,100)));
        assertTrue("supposed to be true", pointArr[3].equals(new Point(0,100)));
        assertFalse("supposed to be false", pointArr[3].equals(new Point(50,100)));

        r = new Rectangle(new Point(50,50),57,63);
        pointArr = r.getRectangleToEdgePointsArray();
        assertTrue("supposed to be true", pointArr[0].equals(new Point(50,50)));
        assertTrue("supposed to be true", pointArr[1].equals(new Point(107,50)));
        assertTrue("supposed to be true", pointArr[2].equals(new Point(107,113)));
        assertTrue("supposed to be true", pointArr[3].equals(new Point(50,113)));
        assertFalse("supposed to be false", pointArr[3].equals(new Point(50,100)));
    }

    @Test
    public void testRectangleToLinesArray() throws Exception {
        Rectangle r = new Rectangle(new Point(0,0),100,100);
        Line[] linesArr = r.getRectangleToLinesArray();

        assertTrue("supposed to be true", linesArr[0].equals(new Line(0,0,100,0)));
        assertTrue("supposed to be true", linesArr[1].equals(new Line(100,0,100,100)));
        assertTrue("supposed to be true", linesArr[2].equals(new Line(100,100,0,100)));
        assertTrue("supposed to be true", linesArr[3].equals(new Line(0,0,0,100)));

    }

    @Test
    public void testIntersectionPointsWithRectangle() throws Exception {

        Line l1 = new Line (0,0,100,100);
        Rectangle r = new Rectangle(new Point(60,20),20,40);
        java.util.List<Point> intersectionPoints = r.getIntersectionPoints(l1);
       assertTrue(intersectionPoints.contains(new Point (60,60)));

        l1 = new Line (0,0,2,2);
        r = new Rectangle(new Point(0.5,1),1,1);
        intersectionPoints = r.getIntersectionPoints(l1);
        assertTrue(intersectionPoints.size() == 2);
        assertTrue(intersectionPoints.contains(new Point(1,1)));

    }

    @Test
    public void testEquals () throws Exception {
        Rectangle r = new Rectangle(new Point(1,1),50,50);
        Rectangle r2 = new Rectangle(new Point(1,1),50,50);
        assertTrue(r.equals(r2));
        r2 = new Rectangle(new Point(1,1),50,100);
        assertTrue(!r.equals(r2));
    }

}
