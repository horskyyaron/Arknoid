//ID: 204351670

package execution.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import execution.levels.LevelInformation;
import execution.listeners.BallRemover;
import execution.listeners.BlockRemover;
import execution.listeners.ScoreTrackingListener;
import execution.screens.KeyPressStoppableAnimation;
import execution.screens.PauseScreen;
import gameelements.collidables.Collidable;
import gameelements.sprites.PaddleInfo;
import gameelements.sprites.Velocity;
import gameelements.sprites.ScoreIndicator;
import geometry.Point;
import gameelements.sprites.Ball;
import gameelements.sprites.Paddle;
import gameelements.sprites.Sprite;
import gameelements.sprites.SpriteCollection;
import gameelements.sprites.Block;

import java.awt.Color;
import java.util.List;
import java.util.Random;


/**
 * execution.Game class supports methods that their goal is to represent the game of
 * Arkanoid. The methods will create the game objects, and will animate the
 * movement of the objects.
 * <p>
 * the constants representing the border thickness, the ball's minimum and max
 * speed are randomly chosen so that the animation will look good.
 */
public class GameLevel implements Animation {

    private static final int GAME_WON_BONUS = 100;
    private static final int BALL_RADIUS = 5;
    private static final double COUNTDOWN_DURATION = 3;
    private static final int COUNT_FROM = 3;
    private static final int DEATH_REGION_THICKNESS = 1;
    private static PaddleInfo paddleInfo;

    //fields.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocksCounter;
    private Counter remainingBallsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * constructor of the 'execution.Game' object.
     * <p>
     * will create a new game elemnts.sprites.Sprite collection and a new execution.Game environment for the
     * game.
     *
     * @param levelInformation       the level information
     * @param totalScore             the total score
     * @param remainingBallsCounter  the remaining balls counter
     * @param remainingBlocksCounter the remaining blocks counter
     * @param ks                     the ks
     * @param ar                     the ar
     * @param gui                    the gui
     */
    public GameLevel(LevelInformation levelInformation, Counter totalScore, Counter remainingBallsCounter,
                     Counter remainingBlocksCounter, KeyboardSensor ks, AnimationRunner ar, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocksCounter = remainingBlocksCounter;
        this.remainingBallsCounter = remainingBallsCounter;
        this.score = totalScore;
        this.running = false;
        this.levelInfo = levelInformation;
        this.gui = gui;
        this.runner = ar;
        this.keyboard = ks;
    }

    /**
     * will add an input gameelemnts.collidables.Collidable into environment field -
     * (a execution.animation.GameEnvironment object).
     *
     * @param c input gameelemnts.collidables.Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.getCollidables().add(c);
    }

    /**
     * will add an input gameelemnts.sprites.Sprite into 'gameelemnts.sprites' field
     * (a gameelemnts.sprites.SpriteCollection object).
     *
     * @param s input gameelemnts.sprites.Sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game:
     * <p>
     * Generating: background block, blocks, game borders, balls and paddle.
     * and adding them to the game.
     */
    public void initialize() {

        //background
        generateBackground();

        //Same for all levels
        generateGameBorders();
        generateScoreIndicator(this.score);

        //level-unique
        generateLevelBlocks();
        generatePaddle();
        generateBalls();




    }

    /**
     * Generating the score indicator.
     *
     * @param currentScore the game score.
     */
    private void generateScoreIndicator(Counter currentScore) {
        //creating score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(currentScore, this.levelInfo);
        //adding to sprite collection
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountDownAnimation(COUNTDOWN_DURATION, COUNT_FROM, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    //generating elemnts of the game and adding them to gameelemnts.sprites and environment.
    /**
     * Generating the background block and adding it to game.
     */
    private void generateBackground() {
        Sprite background = this.levelInfo.getBackground();
        this.sprites.addSprite(background);
    }


    /**
     * Generating the game borders and adding them to game.
     *
     * the game borders will set the game-play zone, where the ball can move.
     */
    private void generateGameBorders() {
        Double thickness = Utils.getBorderThickness();

        //in case we random color background.
        //java.awt.Color backgroundColor = getRandomColor();

        java.awt.Color color = Color.gray;
        //generating border blocks.
        Block left = new Block(new Point(0, thickness), thickness,
                Utils.getHeight() - thickness, color);
        Block up = new Block(new Point(0, 0), Utils.getWidth(), thickness, color);
        Block right = new Block(new Point(Utils.getWidth() - thickness, thickness),
                thickness, Utils.getHeight() - thickness, color);

        //Death-Region Block
        Block down = new Block(new Point(0, Utils.getHeight()), Utils.getWidth(),
                DEATH_REGION_THICKNESS);
        BallRemover ballRemover = new BallRemover(this, this.remainingBallsCounter);
        down.addHitListener(ballRemover);


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
     */
    private void generateBalls() {
        //getting the level ball's information.
        List<Velocity> ballsVelocities = this.levelInfo.initialBallVelocities();

        //creating and adding the balls to the game.
        for (Velocity ballsVelocity : ballsVelocities) {
            //Ball ball = new Ball(BALLS_STARTING_POINT, BALL_RADIUS);
            Ball ball = createBallOnPaddle();
            ball.setVelocity(ballsVelocity);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        //updating balls counter.
        this.remainingBallsCounter.increase(ballsVelocities.size());
    }

    /**
     * Generating a Ball object at the location which is right above the level paddle position.
     *
     * @return a ball object which is located right above the paddle.
     */
    private Ball createBallOnPaddle() {
        Point centerOfPaddle = paddleInfo.getCenterOfPaddle();
        double heightOfPaddle = paddleInfo.getPaddleHeightCoordiante();
        Point ballInitPoint = new Point(centerOfPaddle.getX(), heightOfPaddle - 10);
        return new Ball(ballInitPoint, BALL_RADIUS);
    }


    /**
     * Generating game blocks.
     */
    private void generateLevelBlocks() {
        //creating the block remover.
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocksCounter);
        //creating score indicator listner.
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        //getting blocks of the specific level.
        List<Block> levelBlocks = this.levelInfo.blocks();

        for (Block b : levelBlocks) {
            //updating the number of blocks in the game:
            this.remainingBlocksCounter.increase(1);
            //adding block remover as a listener.
            b.addHitListener(blockRemover);
            //adding score indicator listener
            b.addHitListener(scoreTrackingListener);
            //adding block to game
            b.addToGame(this);
        }
    }



    /**
     * Generating the game paddle and adding it to game.
     */
    private void generatePaddle() {
        Paddle paddle = new Paddle(this.gui, this.levelInfo);
        paddleInfo = new PaddleInfo(paddle);
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


    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteElements().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //animation loop.
        while (this.running) {
            long startTime = System.currentTimeMillis(); // timing
            d = this.gui.getDrawSurface();

            // game-specific logic
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();

            // stopping situation
            //Paused
            if (this.keyboard.isPressed("p")) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new PauseScreen()));
            }
            //lost
            if (this.remainingBallsCounter.getValue() == 0) {
                //lost. stops animation.
                this.running = false;
            }
            //won
            if (this.remainingBlocksCounter.getValue() == 0) {
                //alert animation runner to stop becuase level is complete.
                this.running = false;
                //adding level completion bonus.
                this.score.increase(GAME_WON_BONUS);
            }

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}