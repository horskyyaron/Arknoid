//ID: 204351670

/**
 * Ass3Game class will initialize and run Arkanoid game.
 */
public class Ass3Game {

    /**
     * main function.
     * will create a new Arkanoid game, initialize it and run it.
     *
     * @param args input from user. (will not impact the program)
     * @throws Exception if one of the game objects will have a point with
     *                   negative coordinates.
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
