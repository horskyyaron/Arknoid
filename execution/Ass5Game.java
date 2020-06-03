package execution;//ID: 204351670

/**
 * Ass3Game class will initialize and run Arkanoid game.
 */
public class Ass5Game {

    /**
     * main function.
     * will create a new Arkanoid game, initialize it and run it.
     *
     * @param args input from user. (will not impact the program)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
