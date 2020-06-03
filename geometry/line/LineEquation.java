//ID: 204351670

package geometry.line;

/**
 * geometry.line.LineEquation class supports methods that their goal is to represent the line
 * equation.
 * The form of the equation is: ax + by + c = 0
 */
public class LineEquation {

    //fields.
    private Line line;
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    /**
     * constructor of the 'geometry.line.LineEquation' object.
     *
     * @param line the line that will be represented in the geometry.line.LineEquation object.
     */
    public LineEquation(Line line) {
        this.line = line;
        if (Double.compare(line.start().getX(), line.end().getX()) == 0) {
            // when it's parallel to y-axis.
            this.yCoefficient = 0;
            this.xCoefficient = 1;
            this.freeCoefficient = (-1) * line.start().getX();
        } else if (Double.compare(line.start().getY(), line.end().getY()) == 0) {
            //when it's parallel to x-axis.
            this.yCoefficient = 1;
            this.xCoefficient = 0;
            this.freeCoefficient = (-1) * line.start().getY();
        } else {
            //not parallel case.
            this.yCoefficient = 1;
            this.xCoefficient = -1 * line.getSlope();
            this.freeCoefficient = line.getSlope() * line.start().getX() - line.start().getY();
        }
    }

    /**
     * calculating and returning the x coefficient of the line equation.
     *
     * @return the x coefficient of the line equation.
     */
    public double getXCoefficient() {
        return this.xCoefficient;
    }

    /**
     * calculating and returning the y coefficient of the line equation.
     *
     * @return the y coefficient of the line equation.
     */
    public double getYCoefficient() {
        return this.yCoefficient;
    }

    /**
     * calculating and returning the free coefficient of the line equation.
     *
     * @return the free coefficient of the line equation.
     */
    public double getFreeCoefficient() {
        return this.freeCoefficient;
    }

    /**
     * returns the line field variable.
     *
     * @return the line that is represented in the equation.
     */
    public Line getLine() {
        return this.line;
    }
}
