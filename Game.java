public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;

    public void addCollidable(Collidable c) {

    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize();

    // Run the game -- start the animation loop.
    public void run();
}