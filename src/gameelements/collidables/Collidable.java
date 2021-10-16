//ID: 204351670

package gameelements.collidables;

import gameelements.sprites.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import gameelements.sprites.Velocity;

/**
 * gameelemnts.collidables.Collidable object is an object that's being drawn to screen, and that
 * the ball in the game can collide with.
 */
public interface Collidable {

    /**
     * return the gameelemnts.collidables.Collidable rectangle object.
     *
     * all the gameelemnts.collidables in the game are of a rectangle shape.
     *
     * @return the gameelemnts.collidables.Collidable rectangle object.
     */
    Rectangle getCollisionRectangle();

    /**
     * the function will calculate new velocity for the striking object that
     * hit the given gameelemnts.collidables.Collidable.
     *
     * @param hitter the ball that strikes the block.
     * @param collisionPoint the collision point.
     * @param currentVelocity the velocity of the striking object.
     * @return the new velocity for the hitting object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * calculate which of the given gameelemnts.collidables.Collidable's lines was a part of the
     * collision, then returns that line.
     *
     * @param collisionPoint the collision point.
     * @return the line which is involved in the collision.
     */
    Line getImpactLineFromCollisionPoint(Point collisionPoint);
}