import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestLinearSystemOfEquation {
    @Test
    public void testSystem () throws Exception {
        Line l1 = new Line(0,0,10,10);
        Line l2 = new Line(10,0,0,10);
        LinearSystemOfEquation system = new LinearSystemOfEquation(l1,l2);
        assertTrue(Double.compare(-2,system.getSystemDeterminant()) == 0);
        assertTrue(system.getIsSystemSolutionUnique());
        assertTrue(system.getSystemSolution().equals(new Point(5,5)));

        l1 = new Line (1,3,2,5);
        l2 = new Line (1,2,3,2);
        system = new LinearSystemOfEquation(l1,l2);
        assertTrue(Double.compare(-2,system.getSystemDeterminant()) == 0);
        assertTrue(system.getIsSystemSolutionUnique());
        assertTrue(system.getSystemSolution().equals(new Point(0.5,2)));

    }
}
