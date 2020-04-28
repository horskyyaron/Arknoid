import org.junit.*;

import static org.junit.Assert.*;

public class TestPoint {

    private double epsilon = 0.000_000_001;

    @Test
    public void testCoordinates() throws Exception {
        Point p = new Point(2,4);
        assertEquals(2.0,p.getX(),epsilon);

    }

    @Test
    public void testDistance() throws Exception {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,5);
        Point p3 = new Point(5,0);
        assertEquals(5,p1.distance(p2),epsilon);
        assertEquals(5,p1.distance(p3),epsilon);
        Point p4 = new Point(4,3);
        assertEquals(5,p1.distance(p4),epsilon);
        Point p5 = new Point(4,3);
//        Point p6 = new Point(1,-2);
//        assertEquals(5.830951895,p5.distance(p6),epsilon);
    }

    @Test
    public void testEquals() throws Exception {
        //same coordinates.
        Point p1 = new Point(1,1);
        Point p2 = new Point (1,1);
        assertEquals("supposed to be true",p1.equals(p2),true);

        //different y coordinate.
        Point p3 = new Point(1,0);
        assertEquals("supposed to be false", p1.equals(p3),false);

        //different y coordinate.
        Point p4 = new Point(0,1);
        assertEquals("supposed to be false", p1.equals(p4),false);

    }



}
