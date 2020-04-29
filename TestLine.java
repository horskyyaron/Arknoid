import org.junit.*;
import static org.junit.Assert.*;

public class TestLine {

    @Test
    public void testLength () throws Exception{
        Line l1 = new Line (new Point(0,0), new Point (0,1));
        assertTrue("supposed to be true", Double.compare(1.0,l1.length()) == 0);

        Line l2 = new Line (new Point(0,0), new Point (10,0));
        assertTrue(Double.compare(10,l2.length()) == 0);

    }

    @Test
    public void testGetMiddlePoint() throws Exception{
        //middle point on y-axis
        Line l = new Line (new Point (0,0),new Point (0,4));
        assertEquals("supposed to be true",l.middle().equals(new Point(0,2)),true);

        //middle point on x-axis
        Line l2 = new Line (new Point (10,0),new Point (0,0));
        assertEquals("supposed to be true",l2.middle().equals(new Point(5,0)),true);

        //middle point on xy plane.
        Line l3 = new Line (new Point (1,2),new Point (8,9));
        assertEquals("supposed to be true",l3.middle().equals(new Point(4.5,5.5)),true);

        l3 = new Line (10,3,2.5,4);
        assertEquals("supposed to be true",l3.middle().equals(new Point(6.25,3.5)),true);

        l3 = new Line (6.325,4.71,2.53,4.25);
        assertEquals("supposed to be true",l3.middle().equals(new Point(4.4275,4.48)),true);
    }

    @Test
    public void testEquals() throws Exception{
        Line l = new Line (new Point(0,0), new Point (10,0));
        Line l2 = new Line (0,0,10,0);
        assertEquals("supposed to be True",l.equals(l2),true);;

        //same line, different starting points (start is the ending point in the
        // other etc....
        l = new Line(0,0,10,0);
        l2 = new Line (10,0,0,0);
        assertEquals("supposed to be True",l.equals(l2),true);
        l = new Line(10,0,0,0);
        l2 = new Line (10,0,0,0);
        assertEquals("supposed to be True",l.equals(l2),true);

        //some false tests.
        l = new Line(10,3,0,0);
        l2 = new Line (10,0,0,0);
        assertEquals("supposed to be False",l.equals(l2),false);
        l = new Line(8,0,0,0);
        l2 = new Line (10,0,0,0);
        assertEquals("supposed to be False",l.equals(l2),false);

    }

    @Test
    public void testGets() throws Exception{
        Line l = new Line (0,1.52,5.23,3.97);
        assertTrue(Double.compare(l.start().getX(),0) == 0);
        assertTrue(Double.compare(l.start().getY(),1.52) == 0);
        assertTrue(Double.compare(l.end().getX(),5.23) == 0);
        assertTrue(Double.compare(l.end().getY(),3.97) == 0);
    }

    @Test
    public void testIntersectingLines () throws Exception {
        Line l1 = new Line (0,0,1,1);
        Line l2 = new Line (0,0,2,2);
        assertTrue("Shouldn't be intersecting",!l1.isIntersecting(l2));
        l2 = new Line (0,0,2,3);
        assertTrue(l1.isIntersecting(l2));
        assertTrue(l1.intersectionWith(l2).equals(new Point(0,0)));
        assertTrue(l2.isIntersecting(l1));
        l2 = new Line (1,1,2,2);
        assertTrue(l1.isIntersecting(l2));
        assertTrue(l1.intersectionWith(l2).equals(new Point(1,1)));
        l2 = new Line (2,2,1,1);
        assertTrue(l1.isIntersecting(l2));
        assertTrue(l1.intersectionWith(l2).equals(new Point(1,1)));
        assertTrue(l2.isIntersecting(l1));
        assertTrue(l2.isIntersecting(l1));
        assertTrue(l2.intersectionWith(l1).equals(new Point(1,1)));
        assertTrue(l1.isIntersecting(l2));
        l2 = new Line (2,2,1,1);
        assertTrue(l1.isIntersecting(l2));
        assertTrue(l1.intersectionWith(l2).equals(new Point(1,1)));
        assertTrue(l2.isIntersecting(l1));

        l2 = new Line (5,9,1,1);
        assertTrue(l1.isIntersecting(l2));
        assertTrue(l2.isIntersecting(l1));

        l2 = new Line (0,1,1,0);
        l1 = new Line(0,0,1,1);
        assertTrue("Should be (0.5,0.5):",l1.isIntersecting(l2));

        l1 = new Line(0,0,0.5,0.5);
        l2 = new Line(0,4,1,2);
        assertTrue("they dont intersect in the range of the lines", !l1.isIntersecting(l2));

        l1 = new Line(0,0,100,100);
        l2 = new Line(60,20,80,20);
        assertTrue("they dont intersect in the range of the lines", !l1.isIntersecting(l2));

        l1 = new Line(0,0,100,100);
        l2 = new Line(60,60,100,60);
        assertTrue("they intersect in (60,60)", l1.isIntersecting(l2));
        assertTrue("they intersect in (60,60)", l1.intersectionWith(l2).equals(new Point (60,60)));

        l2 = new Line(100,60,60,60);
        assertTrue("they intersect in (60,60)", l1.isIntersecting(l2));
        assertTrue("they intersect in (60,60)", l1.intersectionWith(l2).equals(new Point (60,60)));
        assertTrue("they intersect in (60,60)", l2.isIntersecting(l1));
        assertTrue("they intersect in (60,60)", l2.intersectionWith(l1).equals(new Point (60,60)));

        l1 = new Line(1,3,4.5,9);
        l2 = new Line(8,4,1,1.5);
        assertTrue("they intersect in (60,60)", !l1.isIntersecting(l2));
        l1 = new Line(1,3,8,4);
        l2 = new Line(4.5,9,1,1.5);
        assertTrue("they intersect in (60,60)", l1.isIntersecting(l2));
        l1 = new Line(1,3,4.5,9);
        l2 = new Line(8,4,0.5,8);
        assertTrue("they intersect in (60,60)", l1.isIntersecting(l2));



    }

    @Test
    public void testClosetsIntersection () throws Exception {
        Rectangle r = new Rectangle(new Point (10,10),10,20);
        Line l1 = new Line(50,20,0,20);
        Line l2 = new Line (0,20,50,20);
        assertTrue(l1.closestIntersectionToStartOfLine(r).equals(new Point(20,20)));
        assertTrue(l2.closestIntersectionToStartOfLine(r).equals(new Point(10,20)));
        l1 = new Line(20,20,15,20);
        l1.closestIntersectionToStartOfLine(r);
        l1 = new Line(20,20,30,20);
        assertTrue(l1.closestIntersectionToStartOfLine(r).equals(new Point(20,20)));
        l1 = new Line(7,5,20,20);
        l1.closestIntersectionToStartOfLine(r);


    }
}
