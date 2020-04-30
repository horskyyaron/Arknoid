public class Block implements Collidable {

    private Rectangle block;

    //constructors.
    public Block(Rectangle block) {
        this.block = block;
    }

    public Block(Point upperLeft, double width, double height) throws Exception {
        this(new Rectangle(upperLeft,width,height));
    }

    //Collidable interface methods.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity)
            throws Exception {
        double hitAngle = currentVelocity.getAngleFromDxDy();
        Line impactLine = getImpactLineFromCollisionPoint(collisionPoint);
        Velocity newVelocity;

        //frontal hit case
        if(hitAngle == 0 || Double.compare(hitAngle,90) == 0
                || Double.compare(hitAngle,180) == 0
                || Double.compare(hitAngle,270) == 0) {
            currentVelocity.applyFrontalHit();
            newVelocity = currentVelocity;
        } else {
            //angular hit.
            if (impactLine.isLineVertical()) {
                currentVelocity.applyVerticalSurfaceHit();
                newVelocity = currentVelocity;
            } else {
                //horizontal surface impact.
                currentVelocity.applyHorizontalSurfaceHit();
                newVelocity = currentVelocity;
            }
        }
        return newVelocity;
    }

    public Line getImpactLineFromCollisionPoint(Point collisionPoint)
            throws Exception {
        //getting an array constructed of the block lines.
        Line[] blockEdges = this.block.getRectangleToLinesArray();
        LineEquation lineEquation;
        //getting the block edges.
        for (int i = 0; i < blockEdges.length; i++) {
            //getting each line equation.
            lineEquation = new LineEquation(blockEdges[i]);
            //check if the collision point is on the line.
            if(collisionPoint.isSatisfying(lineEquation)) {
                return blockEdges[i];
            }
        }
        return null;
    }



    //Block Methods.

}
