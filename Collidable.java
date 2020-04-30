import biuoop.DrawSurface;

public interface Collidable {
    // Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    Velocity hit(Point collisionPoint, Velocity currentVelocity) throws Exception;

//    void DrawOn(DrawSurface surface);

    Line getImpactLineFromCollisionPoint(Point collisionPoint) throws Exception;
}