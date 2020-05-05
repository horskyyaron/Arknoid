//ID: 204351670

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Double.min;
import static java.lang.Math.abs;

/**
 * Game class supports methods that their goal is to represent the game of
 * Arkanoid. The methods will create the game objects, and will animate the
 * movement of the objects.
 *
 * the constants representing the border thickness, the ball's minimum and max
 * speed are randomly chosen so that the animation will look good.
 */
public class Game {

    //screen size constants.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //the thickness is set to be a percentage of the screen width.
    private static final double BORDER_THICKNESS_FACTOR = 0.04;

    //number of balls in game.
    private static final int BALLS_IN_GAME_INITIAL = 2;

    //Ball Max and Min speed:
    private static final int MAX_SPEED = 10;
    private static final int MIN_SPEED = -10;

    //the blocks size will be determined by the screen size.
    private static final double BLOCK_WIDTH =  getWIDTH() / 16.0;
    private static final double BLOCK_HEIGHT = getHEIGHT() / 22.2;

    //number of blocks in the first row of blocks.
    private static final int BLOCKS_IN_TOP_ROW = 12;
    private static final int NUMBER_OF_BLOCKS_ROW = 6;


    //Block top row height in proportion to the border thickness.
    private static final double TOP_BLOCK_ROW_HEIGHT = 5.2
                                                        * getBorderThickness();

    private static final java.awt.Color BACKGROUND_COLOR = new Color(0, 0, 102);

    /*

    declaring a constant of the balls initial position.

    because Point may raise exception, we declare the constant using try and
    catch in a static block.
     */
    private static final Point BALLS_STARTING_POINT;

    static {
        Point temp = null;
        try {
            temp = new Point(WIDTH / 2.0, (int) (HEIGHT * 0.7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BALLS_STARTING_POINT = temp;
    }

    //fields.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * constructor of the 'Game' object.
     *
     * will create a new Sprite collection and a new Game environment for the
     * game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * will add an input Collidable into environment field - (a GameEnvironment
     * object).
     *
     * @param c input Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.getCollidables().add(c);
    }
    /**
     * will add an input Sprite into 'sprites' field (a SpriteCollection object).
     *
     * @param s input Sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game:
     *
     * Generating: background block, blocks, game borders, balls and paddle.
     * and adding them to the game.
     *
     * @throws Exception if in one of the generating of the game objects, a
     *                   Point object will receive negative coordinates.
     */
    public void initialize() throws Exception {

        this.gui = new GUI("GAME!", WIDTH, HEIGHT);
        generateBackground();
        generateGameBlocks();
        generateGameBorders();
        generateBalls();
        generatePaddle();

    }

    /**
     * Run the game -- start the animation loop.
     *
     * @throws Exception if one of the game object will have a point with
     *                   negative coordinates.
     */
    public void run() throws Exception {

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //animation loop.
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    //generating elemnts of the game and adding them to sprites and environemt.
    /**
     * Generating the background block and adding it to game.
     *
     * @throws Exception if a background block corner point have negative
     *                   coorinates.
     */
    private void generateBackground() throws Exception {
        //in case we random color background.
        //java.awt.Color backgroundColor = getRandomColor();
        java.awt.Color color = BACKGROUND_COLOR;
        Block backgroundBlock = new Block(new Point(0, 0), WIDTH, HEIGHT,
                color);

        //adding to game.
        backgroundBlock.addToGame(this);
    }


    /**
     * Generating the game borders and adding them to game.
     *
     * the game borders will set the game-play zone, where the ball can move.
     *
     * @throws Exception if one of the border block has a point with negative
     *                   coordinates.
     */
    private void generateGameBorders() throws Exception {
        Double thickness = getBorderThickness();

        //in case we random color background.
        //java.awt.Color backgroundColor = getRandomColor();

        java.awt.Color color = Color.gray;
        //generating border blocks.
        Block left = new Block(new Point(0, thickness), thickness,
                HEIGHT - thickness, color);
        Block up = new Block(new Point(0, 0), WIDTH, thickness, color);
        Block right = new Block(new Point(WIDTH - thickness, thickness),
                thickness, HEIGHT - thickness, color);
        Block down = new Block(new Point(thickness, HEIGHT - thickness),
                WIDTH - 2 * thickness, thickness, color);

        //adding to game.
        left.addToGame(this);
        up.addToGame(this);
        right.addToGame(this);
        down.addToGame(this);

    }

    /**
     * Generating the game balls and adding them to game.
     *
     * the balls starting location is set to be in the middle, below the blocks.
     * their speed is randomly chosen.
     *
     * @throws Exception if one of balls generated, has a center point with
     *                   negative coordinates.
     */
    private void generateBalls() throws Exception {
        //generating balls.
        List<Ball> gameBalls = generateRandomBalls();

        //adding balls to game.
        for (int i = 0; i < gameBalls.size(); i++) {
            gameBalls.get(i).addToGame(this);
        }
    }

    /**
     * Generating the game balls, and returning a list that holds all the ball
     * objects created.
     *
     * @return list of Ball objects. (the game balls).
     * @throws Exception if one of balls generated, has a center point with
     *                   negative coordinates.
     */
    private List<Ball> generateRandomBalls() throws Exception {
        Random random = new Random();
        List<Ball> ballsList = new ArrayList<>();
        for (int i = 0; i < BALLS_IN_GAME_INITIAL; i++) {
            //initial point in the middle of the screen, near the paddle.
            Ball ball = new Ball(BALLS_STARTING_POINT, 5);

            ball.setVelocity(0, 0);
            //not allowing ball moving horizontally without having vertical
            // speed as well.
            while (ball.getVelocity().getDy() == 0) {
                //ball velocity is a random number between the maximum and the
                // minimum speed constants.
                ball.setVelocity(random.nextInt(MAX_SPEED + abs(MIN_SPEED))
                                + MIN_SPEED, random.nextInt(MAX_SPEED
                                + abs(MIN_SPEED)) + MIN_SPEED);
            }
            //in case of speed higher than the screen size.
            if (ball.getVelocity().getSpeed() > min(getWIDTH(), getHEIGHT())) {
                throw new Exception("speed cannot be greater than screen "
                        + "width or height!!!");
            }
            //adding the game environment to ball's data.
            ball.setGameEnvironment(this.environment);
            ballsList.add(ball);
        }
        return ballsList;
    }

    /**
     * Generating game blocks.
     *
     * @throws Exception if one of the blocks generated, has a corner point with
     *                   negative coordinates.
     */
    private void generateGameBlocks() throws Exception {
        Point firstRowFirstBlock = new Point(WIDTH - getBorderThickness()
                - BLOCK_WIDTH , TOP_BLOCK_ROW_HEIGHT);

        //generating each row separately.
        for (int i = 0; i < NUMBER_OF_BLOCKS_ROW; i++) {
            //each row first block is a one BLOCK_HIGHET lower on the sreen.
            Point firstBlock = new Point(firstRowFirstBlock.getX(),
                    firstRowFirstBlock.getY() + i * BLOCK_HEIGHT);
            //each row will have one block less than the row above it.
            generateBlockRow(firstBlock, BLOCK_WIDTH, BLOCK_HEIGHT,
                    BLOCKS_IN_TOP_ROW - i, getRandomColor());
        }

    }

    /**
     * Generating a row of blocks recursively and adding them to game.
     *
     * @param firstBlock a block to the right of the generated block.
     * @param singleBlockWidth block's width.
     * @param singleBlockHeight block's height.
     * @param blocksCounter counter of the remaining blocks to be printed in
     *                      the row.
     * @param color the blocks color in the current row.
     * @throws Exception if one of the blocks generated, has a corner point with
     *                   negative coordinates.
     */
    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter,
                                  java.awt.Color color) throws Exception {

        if (blocksCounter <= 0) {
            return;
        } else {
            //creating the block and adding it to game.
            Block block = new Block(firstBlock, singleBlockWidth,
                    singleBlockHeight);
            block.setColor(color);
            block.addToGame(this);
            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                    firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, color);
        }
    }

    /**
     * Generating the game paddle and adding it to game.
     *
     * @throws Exception if one the paddle body generated, a rectangle, has a
     *                   corner point with negative coordinates.
     */
    private void generatePaddle() throws Exception {
        Paddle paddle = new Paddle(this.gui);
        paddle.addToGame(this);
    }

    /**
     * Generating and returning a randome color.
     *
     * @return a random chosen RGB color.
     */
    public java.awt.Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        return new Color(r, g, b);
    }


    //getters.

    /**
     * returning the game screen width.
     *
     * @return screen width.
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * returning the game screen height.
     *
     * @return screen height.
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * returning the game border thickness.
     *
     * @return border thickness.
     */
    public static double getBorderThickness() {
        return BORDER_THICKNESS_FACTOR * WIDTH;
    }
}