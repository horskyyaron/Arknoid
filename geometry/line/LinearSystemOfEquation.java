//ID: 204351670

package geometry.line;

import geometry.Point;

/**
 * geometry.line.LinearSystemOfEquation class supports methods that their goal is to
 * represent the a linear system of equations. checking if a system has a
 * single solution or no solution ay all.
 * will calculate a system solution using Linear algebra, with cramer's rule.
 * the matrix multiplication looks like so:
 * |a b| * |x| = |c1|
 * |c d|   |y| = |c2|
 */
public class LinearSystemOfEquation {

    //fields.
    private LineEquation equation1;
    private LineEquation equation2;

    /**
     * constructor of the 'geometry.line.LinearSystemOfEquation' object.
     *
     * @param l1 the first line that is being represented by  a line equation.
     * @param l2 the second line that is being representer by a line equation
     */
    public LinearSystemOfEquation(Line l1, Line l2) {
        this.equation1 = new LineEquation(l1);
        this.equation2 = new LineEquation(l2);
    }

    /**
     * will calculate the system determinant. (a 2X2 matrix)
     *
     * @param a is the x coefficient in the first equation.
     * @param b is the y coefficient in the first equation
     * @param c is the x coefficient in the second equation
     * @param d is the y coefficient in the second equation
     *
     * @return the system determinant.
     */
    private double calculateDeterminant(double a, double b, double c,
                                        double d) {
        return (a * d - b * c);
    }

    /**
     * will tell if a system solution is unique. meaning, a single solution for
     * the equation.
     *
     * from Linear Algebra:
     * if the determinant is zero, the system has no unique solution.
     *
     * @return 'true' if the system has a uniqe solution. 'false' otherwise.
     */
    private boolean isSystemSolutionUnique() {
        return !(Double.compare(getSystemDeterminant(), 0) == 0);
    }

    /**
     * will calculate the system solution using cramer's rule.
     *
     * cramer's rule - https://en.wikipedia.org/wiki/Cramer%27s_rule
     *
     * @return a geometry.Point object with x and y coordinates which are the system
     *         solution.
     */
    private Point calculateSolution() {
        double systemDeterminant = getSystemDeterminant();
        //[c1,c2] is the solution vector in the system.
        double c1 = equation1.getFreeCoefficient() * (-1);
        double c2 = equation2.getFreeCoefficient() * (-1);

        //handling -0 problem.
        if (c1 == -0) {
            c1 = 0.0;
        }
        if (c2 == -0) {
            c2 = 0.0;
        }

        //-1 becuase the free coefficient "moved" to the right side of the line equation.
        double dX = calculateDeterminant(c1,
                equation1.getYCoefficient(), c2,
                equation2.getYCoefficient());
        double dY = calculateDeterminant(equation1.getXCoefficient(),
                c1, equation2.getXCoefficient(),
                c2);

        //special case when getting the -0 at some coordinate:
        if (dX == -0) {
            dX = 0.0;
        }
        if (dY == -0) {
            dY = 0.0;
        }

        double solutionXCoordinate = dX / systemDeterminant;
        double solutionYCoordinate = dY / systemDeterminant;
        //if the intersection is with negative coordinates, in this project
        // it's as if no intersection.
        if (solutionXCoordinate < 0 || solutionYCoordinate < 0) {
            return null;
        }
        return new Point(solutionXCoordinate, solutionYCoordinate);

    }

    /**
     * returning the system first equation.
     *
     * @return the system first equation
     */
    public LineEquation getEquation1() {
        return equation1;
    }

    /**
     * returning the system second equation.
     *
     * @return the system second equation
     */
    public LineEquation getEquation2() {
        return equation2;
    }

    /**
     * calculating and returning the system determinant.
     *
     * @return the system determinant.
     */
    public double getSystemDeterminant() {
        return (equation1.getXCoefficient() * equation2.getYCoefficient()
                - equation1.getYCoefficient() * equation2.getXCoefficient());
    }

    /**
     * the function will tell if the system is unique or not.
     *
     * @return 'true' if the system solution is unique. 'false' otherwise.
     */
    public boolean getIsSystemSolutionUnique() {
        return isSystemSolutionUnique();
    }

    /**
     * the function will return the system solution.
     *
     * @return the system solution. a geometry.Point object with x and y - the system
     *         solution.
     */
    public Point getSystemSolution() {
        return calculateSolution();
    }
}
