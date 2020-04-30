public class CollisionInfo {

    //fields
    private Point collisionPoint;
    private Collidable collisionObject;

    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    public Line getCollisionImpactLine() throws Exception {

        return (this.collisionObject.getImpactLineFromCollisionPoint
                (this.collisionPoint));
    }
}