import biuoop.DrawSurface;

import java.awt.*;
import java.util.Random;

public class Block implements Collidable, Sprite {

    private Rectangle block;
    private java.awt.Color color;

    //constructors.
    public Block(Rectangle block) {
        this.block = block;
        this.color = Color.black;
    }

    public Block(Point upperLeft, double width, double height)
            throws Exception {
        this(new Rectangle(upperLeft,width,height));
    }

    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) throws Exception {
        this.block = new Rectangle(upperLeft,width,height);
        this.color = color;
    }

    //Sprite interface Methods
    @Override
    public void drawOn(DrawSurface surface) {
        drawBlock(surface);
    }

    @Override
    public void timePassed() {

    }

    //Collidable interface methods.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity)
            throws Exception {
        //in case the collision point is a game-play zone corner.
        if(collisionPoint.isAGameCorner()) {
            return new Velocity(currentVelocity.getDx() * (-1), currentVelocity.getDy() * (-1));
        }

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
            if (impactLine.isVertical()) {
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


    @Override
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

    private void drawBlock(DrawSurface surface) {
        //frame
        surface.setColor(Color.black);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        //Block background color.
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX() + 1,
                (int) this.block.getUpperLeft().getY() - 1,
                (int) this.block.getWidth() - 2, (int) this.block.getHeight() - 2);
    }

    //getters.
    private java.awt.Color getColor() {
        return this.color;
    }

    //setters.
    public void setColor(java.awt.Color color) {
        this.color = color;
    }


    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
