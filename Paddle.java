public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;

    public void moveLeft();
    public void moveRight();

    // Sprite
    public void timePassed();
    public void drawOn(DrawSurface d);

    // Collidable
    public Rectangle getCollisionRectangle();
    public Velocity hit(Point collisionPoint, Velocity currentVelocity);

    // Add this paddle to the game.
    public void addToGame(Game g);
}