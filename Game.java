import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Game {

    //screen size.
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    //the thickness is set to be a percentage of the screen width.
    private static final double BORDER_THICKNESS_fACTOR = 0.04;
    //number of balls in game (Clients requirment)
    private static final int BALLS_IN_GAME_INITIAL = 2;
    //Ball Max and Min speed:
    private static final int MAX_SPEED = 15;
    private static final int MIN_SPEED = -15;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    //constructor
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c) {
        this.environment.getCollidables().add(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    private void initialize() throws Exception {

        this.gui = new GUI("GAME!", WIDTH,HEIGHT);
        generateBackground();
        generateGameBlocks();
        generateGameEdges();
        generateBalls();
        generatePaddle();

    }



    // Run the game -- start the animation loop.
    public void run() throws Exception {

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

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
    private void generateBackground() throws Exception {
        //in case we random color background.
        //java.awt.Color backgroundColor = getRandomColor();
        java.awt.Color color = new Color(0,0,102);
        Block backgroundBlock = new Block(new Point(0, 0), WIDTH, HEIGHT,
                color);

        backgroundBlock.addToGame(this);
    }

    private void generateGameEdges() throws Exception {
        Double thickness = getBorderThickness();

        //in case we random color background.
        //java.awt.Color backgroundColor = getRandomColor();

        java.awt.Color color = Color.gray;
        Block left = new Block(new Point(0, thickness), thickness,
                HEIGHT - thickness,color);
        Block up = new Block(new Point(0, 0), WIDTH, thickness, color);
        Block right = new Block(new Point(WIDTH - thickness, thickness),
                thickness, HEIGHT - thickness, color);
        Block down = new Block(new Point(thickness, HEIGHT - thickness),
                WIDTH - 2 * thickness, thickness,color);

        //adding to game.
        left.addToGame(this);
        up.addToGame(this);
        right.addToGame(this);
        down.addToGame(this);

    }

    private void generateBalls() throws Exception {
        List<Ball> gameBalls = generateRandomBalls();
        for (int i = 0; i < gameBalls.size(); i++) {
            gameBalls.get(i).addToGame(this);
        }
    }

    private List<Ball> generateRandomBalls() throws Exception {
        Random random = new Random();
        List<Ball> ballsList = new ArrayList<>();
        for (int i = 0; i < BALLS_IN_GAME_INITIAL; i++) {
            Ball ball = new Ball(WIDTH / 2, (int) (HEIGHT * 0.7), 5);
            //ball velocity is a random number between the maximum and the
            // minimum speed constants.
            ball.setVelocity(random.nextInt(MAX_SPEED + abs(MIN_SPEED)) + MIN_SPEED,
                    random.nextInt(MAX_SPEED + abs(MIN_SPEED)) + MIN_SPEED);
            ball.setGameEnvironment(this.environment);
            ballsList.add(ball);
        }
        return ballsList;
    }

    private void generateGameBlocks() throws Exception {

        //the blocks size will be determined by the screen size.
        double singleBlockWidth = getWIDTH() / 16.0;
        double singleBlockHeight = getHEIGHT() / 22.0;
        Point firstRowFirstBlock = new Point(WIDTH - getBorderThickness() - singleBlockWidth , 5 * getBorderThickness());


        for (int i = 0; i < 6; i++) {
            Point firstBlock = new Point (firstRowFirstBlock.getX(),firstRowFirstBlock.getY() + i * singleBlockHeight);
            generateBlockRow(firstBlock, singleBlockWidth, singleBlockHeight, 12 - i, getRandomColor());
        }

    }

    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter,
                                  java.awt.Color color) throws Exception {

        if (blocksCounter == 0) {
            return;
        } else {
            Block block = new Block(firstBlock, singleBlockWidth, singleBlockHeight);
            block.setColor(color);
            block.addToGame(this);
            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                    firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, color);
        }
    }

    private void generatePaddle() throws Exception {
        Paddle paddle = new Paddle(this.gui);
        paddle.addToGame(this);
    }

    public java.awt.Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        return new Color(r, g, b);
    }


    //getters.

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static double getBorderThicknessfactor() {
        return BORDER_THICKNESS_fACTOR;
    }

    public static double getBorderThickness() {
        return BORDER_THICKNESS_fACTOR * WIDTH;
    }



    //other methods

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}