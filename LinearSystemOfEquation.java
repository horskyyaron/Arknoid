public class LinearSystemOfEquation {
    private LineEquation equation1;
    private LineEquation equation2;
    private double systemDeterminant;

    public LinearSystemOfEquation(Line l1, Line l2) {
        this.equation1 = new LineEquation(l1);
        this.equation2 = new LineEquation(l2);
    }

    private double calculateDeterminant(double a, double b, double c, double d) {
        return (a * d - b * c);
    }

    private boolean isSystemSolutionUnique() {
        return !(Double.compare(getSystemDeterminant(),0) == 0);
    }

    private Point calculateSolution() throws Exception {
        double systemDeterminant = getSystemDeterminant();
        //-1 becuase the free coefficient "moved" to the right side of the line equation.
        double dX = -1 * calculateDeterminant(equation1.getFreeCoefficient(),
                equation1.getYCoefficient(), equation2.getFreeCoefficient(),
                equation2.getYCoefficient());
        double dY = -1 * calculateDeterminant(equation1.getXCoefficient(),
                equation1.getFreeCoefficient(), equation2.getXCoefficient(),
                equation2.getFreeCoefficient());
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
