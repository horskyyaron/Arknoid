import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestLineEquation {
    @Test
    public void testLineEquation() throws Exception {

        LineEquation le = new LineEquation(new Line(0,0,10,10));
        assertTrue("supposed to be true", Double.compare(le.getFreeCoefficient(),0) == 0);
        assertTrue("supposed to be true", Double.compare(le.getXCoefficient(),-1) == 0);
        assertTrue("supposed to be true", Double.compare(le.getYCoefficient(),1) == 0);

        le = new LineEquation(new Line(2.5,3,10,15));
        assertTrue("supposed to be true", Double.compare(le.getFreeCoefficient(),1) == 0);
        assertTrue("supposed to be true", Double.compare(le.getXCoefficient(),-1.6) == 0);
        assertTrue("supposed to be true", Double.compare(le.getYCoefficient(),1) == 0);

//        le = new LineEquation(new Line(7.1,2.6,3.1,4.4));
//        assertTrue("supposed to be true", Double.compare(le.getFreeCoefficient(),) == 0);
//        assertTrue("supposed to be true", Double.compare(le.getXCoefficient(),0.45) == 0);
//        assertTrue("supposed to be true", Double.compare(le.getYCoefficient(),1) == 0);


    }
}
