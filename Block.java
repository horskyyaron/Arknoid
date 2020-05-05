//ID: 204351670

import biuoop.DrawSurface;
import java.awt.Color;
//import java.util.Random;

/**
 * Block class supports methods that their goal is to represent a block in the
 * Arkanoid game.
 */
public class Block implements Collidable, Sprite {
    //fields.
    private Rectangle block;
    private java.awt.Color color;

    /**
     * constructor of the 'Block' object.
     *
     * @param rect the block rectangle.
     */
    public Block(Rectangle rect) {
        this.block = rect;
        this.color = Color.black;
    }

    /**
     * constructor of the 'Block' object.
     *
     * @param upperLeft the block's upper left corner point.
     * @param width the block's width.
     * @param height the block's height.
     * @throws Exception if the upper left point object input has negative
     *                   coordinates.
     */
    public Block(Point upperLeft, double width, double height)
            throws Exception {
        this(new Rectangle(upperLeft, width, height));
    }

    /**
     * constructor of the 'Block' object.
     *
     * @param upperLeft the block's upper left corner point.
     * @param width the block's width.
     * @param height the block's height.
     * @param color the block's color.
     * @throws Exception if the upper left point object input has negative
     *                   coordinates.
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) throws Exception {
        this.block = new Rectangle(upperLeft, width, height);
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
        if (collisionPoint.isAGameCorner()) {
            return new Velocity(currentVelocity.getDx() * (-1),
                    currentVelocity.getDy() * (-1));
        }

        double hitAngle = currentVelocity.getAngleFromDxDy();
        Line impactLine = getImpactLineFromCollisionPoint(collisionPoint);
        Velocity newVelocity;

        //frontal hit case
        if (hitAngle == 0 || Double.compare(hitAngle, 90) == 0
                || Double.compare(hitAngle, 180) == 0
                || Double.compare(hitAngle, 270) == 0) {
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
            if (collisionPoint.isSatisfying(lineEquation)) {
                return blockEdges[i];
            }
        }
        return null;
    }

    //Block Methods.

    /**
     * the function will draw the given block onto input surface.
     *
     * @param surface the surface which will hold the drawing of the block.
     */
    private void drawBlock(DrawSurface surface) {
        //frame
        surface.setColor(Color.BLACK);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        //Block background color.
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX() + 1,
                (int) this.block.getUpperLeft().getY() + 1,
                (int) this.block.getWidth() - 2,
                (int) this.block.getHeight() - 2);
    }

    /**
     * the function will return the block's color.
     *
     * @return the block's color.
     */
    private java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function will change the given block's color to the input color.
     *
     * @param newColor the new color of the block.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * the function will add the block object to the input game.
     *
     * @param game the game which the block will be a part of.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
