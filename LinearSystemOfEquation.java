public class LinearSystemOfEquation {
    private LineEquation equation1;
    private LineEquation equation2;
    private double systemDeterminant;

    public LinearSystemOfEquation(Line l1, Line l2) {
        this.equation1 = new LineEquation(l1);
        this.equation2 = new LineEquation(l2);
        this.systemDeterminant = getSystemDeterminant();
    }

    private double calculateDeterminant(double a, double b, double c, double d) {
        return (a * d - b * c);
    }

    private boolean isSystemSolutionUnique() {
        return !(Double.compare(getSystemDeterminant(),0) == 0);
    }

    private Point calculateSolution() throws Exception {
        double systemDeterminant = getSystemDeterminant();
        //[c1,c2] is the solution vector in the system.
        double c1 = equation1.getFreeCoefficient() * (-1);
        double c2 = equation2.getFreeCoefficient() * (-1);

        //handling -0 problem.
        if(c1 == -0) c1 = 0.0;
        if(c2 == -0) c2 = 0.0;

        //-1 becuase the free coefficient "moved" to the right side of the line equation.
        double dX = calculateDeterminant(c1,
                equation1.getYCoefficient(), c2,
                equation2.getYCoefficient());
        double dY = calculateDeterminant(equation1.getXCoefficient(),
                c1, equation2.getXCoefficient(),
                c2);

        //special case when getting the -0 at some coordinate:
        if (dX == -0) dX = 0.0;
        if (dY == -0) dY = 0.0;

        double solutionXCoordinate = dX/systemDeterminant;
        double solutionYCoordinate = dY/systemDeterminant;
        //if the intersection is with negative coordinates, in this project
        // it's as if no intersection.
        if (solutionXCoordinate < 0 || solutionYCoordinate < 0) {
            return null;
        }
        return new Point(solutionXCoordinate, solutionYCoordinate);

    }

    public LineEquation getEquation1() {
        return equation1;
    }

    public LineEquation getEquation2() {
        return equation2;
    }

    public double getSystemDeterminant() {
        return(equation1.getXCoefficient() * equation2.getYCoefficient()
                - equation1.getYCoefficient() * equation2.getXCoefficient());
    }

    public boolean getIsSystemSolutionUnique() {
        return isSystemSolutionUnique();
    }

    public Point getSystemSolution() throws Exception {
        return calculateSolution();
    }
}
