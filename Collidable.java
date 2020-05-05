//ID: 204351670

/**
 * Collidable object is an object that's being drawn to screen, and that
 * the ball in the game can collide with.
 */
public interface Collidable {

    /**
     * return the Collidable rectangle object.
     *
     * all the collidables in the game are of a rectangle shape.
     *
     * @return the Collidable rectangle object.
     */
    Rectangle getCollisionRectangle();

    /**
     * the function will calculate new velocity for the striking object that
     * hit the given Collidable.
     *
     * @param collisionPoint the collision point.
     * @param currentVelocity the velocity of the striking object.
     * @return the new velocity for the hitting object.
     * @throws Exception if the collision point has negative coordinates values.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity) throws Exception;

    /**
     * calculate which of the given Collidable's lines was a part of the
     * collision, then returns that line.
     *
     * @param collisionPoint the collision point.
     * @return the line which is involved in the collision.
     * @throws Exception if the collision point has a negative coordinate.
     */
    Line getImpactLineFromCollisionPoint(Point collisionPoint) throws Exception;
}