//ID: 204351670

package gameelemnts.sprites.staticitems;

import biuoop.DrawSurface;
import execution.listeners.HitListener;
import execution.listeners.HitNotifier;
import gameelemnts.collidables.Collidable;
import execution.GameLevel;
import gameelemnts.sprites.movingitems.ball.Ball;
import geometry.line.Line;
import geometry.line.LineEquation;
import geometry.Point;
import geometry.Rectangle;
import gameelemnts.sprites.movingitems.ball.Velocity;
import gameelemnts.sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * gameelemnts.sprites.staticitems.Block class supports methods that their goal is to represent a block in the
 * Arkanoid game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields.
    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor of the 'gameelemnts.sprites.staticitems.Block' object.
     *
     * @param rect the block rectangle.
     */
    public Block(Rectangle rect) {
        this.block = rect;
        this.color = Color.black;
        this.hitListeners = new LinkedList<>();
    }

    /**
     * constructor of the 'gameelemnts.sprites.staticitems.Block' object.
     *
     * @param upperLeft the block's upper left corner point.
     * @param width the block's width.
     * @param height the block's height.
     */
    public Block(Point upperLeft, double width, double height) {
        this(new Rectangle(upperLeft, width, height));
    }

    /**
     * constructor of the 'gameelemnts.sprites.staticitems.Block' object.
     *
     * @param upperLeft the block's upper left corner point.
     * @param width the block's width.
     * @param height the block's height.
     * @param color the block's color.
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }

    //gameelemnts.sprites.Sprite interface Methods
    @Override
    public void drawOn(DrawSurface surface) {
        drawBlock(surface);
    }

    @Override
    public void timePassed() {

    }

    //gameelemnts.collidables.Collidable interface methods.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return newVelocity;
    }


    @Override
    public Line getImpactLineFromCollisionPoint(Point collisionPoint) {
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

    //gameelemnts.sprites.staticitems.Block Methods.

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
        //gameelemnts.sprites.staticitems.Block background color.
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
    public java.awt.Color getColor() {
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
     * @param gameLevel the game which the block will be a part of.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * the function will remove the block object from the input game.
     *
     * @param gameLevel the game which the block will be a part of.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * the function will notify all the listners assigned to the block that a hit has occurred.
     *
     * @param hitter the ball which stroked the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
