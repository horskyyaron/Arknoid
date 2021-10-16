//ID: 204351670

package gameelements.collidables;

import geometry.Line;
import geometry.Point;

/**
 * gameelemnts.collidables.CollisionInfo class supports methods that their goal is to represent
 * a collision that happen in the game.
 */
public class CollisionInfo {

    //fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of the 'gameelemnts.collidables.CollisionInfo' object.
     *
     * @param collisionPoint the point which the collision took place in.
     * @param collisionObject the gameelemnts.collidables.Collidable object that is involved with the
     *                        collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returns the collision point.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * returns the gameelemnts.collidables.Collidable object that is involved in the collision..
     *
     * @return the gameelemnts.collidables.Collidable object that is involved in the collision..
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * calculate which of the gameelemnts.collidables.Collidable's lines was a part of the collision,
     * then returns that line.
     *
     * @return the line which is involved in the collision.
     */
    public Line getCollisionImpactLine() {
        try {
            return (this.collisionObject.getImpactLineFromCollisionPoint(this.
                    collisionPoint));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}