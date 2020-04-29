//Line equation: ax + by + c = 0;
public class LineEquation {
    private Line line;
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    public LineEquation(Line line) {
        this.line = line;
        if(Double.compare(line.start().getX(), line.end().getX()) == 0) {
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

    public LineEquation(Point start, Point end) throws Exception {
        this(new Line(start,end));
    }

    public double getXCoefficient() {
        return this.xCoefficient;
    }

    public double getYCoefficient() {
        return this.yCoefficient;
    }

    public double getFreeCoefficient() {
        return this.freeCoefficient;
    }

    public Line getLine() {
        return this.line;
    }
}
