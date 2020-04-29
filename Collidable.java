public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    Velocity hit(Point collisionPoint, Velocity currentVelocity) throws Exception;
}