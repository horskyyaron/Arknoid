public class LineEquation {
    private Line line;
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    public LineEquation(Line line) {
        this.line = line;
        this.yCoefficient = 1;
        this.xCoefficient = -1 * line.getSlope();
        this.freeCoefficient = line.getSlope() * line.start().getX() - line.start().getY();
    }

    public LineEquation(Point start, Point end) throws Exception {
        this.line = new Line(start,end);
        this.yCoefficient = 1;
        this.xCoefficient = -1 * line.getSlope();
        this.freeCoefficient = line.getSlope() * line.start().getX() - line.start().getY();
    }

    public double getXCoefficient() {
        return xCoefficient;
    }

    public double getYCoefficient() {
        return yCoefficient;
    }

    public double getFreeCoefficient() {
        return freeCoefficient;
    }
}
