import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

public class Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    //the thickness is set to be a percentage of the screen width.
    private static final double BORDER_THICKNESS_fACTOR = 0.04;

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
        //generateGameBlocks();
        generateGameEdges();
        generateBall();
        generatePaddle();

    }

    private void generatePaddle() throws Exception {
        Paddle paddle = new Paddle(this.gui);
        sprites.addSprite(paddle);
        environment.addCollidable(paddle);
    }

    // Run the game -- start the animation loop.
    public void run() throws Exception {

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            if(((Ball)sprites.getSprite(4)).getX() < getBorderThickness() + 4) {
                int x= 5;
            }
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
    private void generateGameEdges() throws Exception {
        Double thickness = BORDER_THICKNESS_fACTOR * WIDTH;
        Block left = new Block(new Point(0,thickness), thickness, HEIGHT - thickness);
        Block up = new Block(new Point(0,0),WIDTH,thickness);
        Block right = new Block(new Point(WIDTH - thickness,thickness),thickness,HEIGHT - thickness);
        Block down = new Block(new Point(thickness,HEIGHT - thickness),WIDTH - 2 * thickness, thickness);

        List<Block> gameEdges = new ArrayList<>();
        gameEdges.add(left);
        gameEdges.add(up);
        gameEdges.add(right);
        gameEdges.add(down);

        //casting block list to Sprite list using wildcard '?'.
        this.sprites.addSpritesList((List<Sprite>)(List<?>)gameEdges);
        this.environment.addCollidableList((List<Collidable>)(List<?>)gameEdges);

    }
    private void generateBall() throws Exception {
        Ball ball = new Ball(200,200,5);
        ball.setVelocity(0,7);
        ball.setGameEnvironment(this.environment);

        sprites.addSprite(ball);
    }
    private void generateGameBlocks() throws Exception {
        //Random for now.
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            Block gameBlock = new Block(new Point(random.nextInt(WIDTH),
                    random.nextInt(HEIGHT/2)),
                    random.nextInt(WIDTH/8),
                    random.nextInt(HEIGHT/4));

            //adding to sprites and environment.
            this.addCollidable(gameBlock);
            this.addSprite(gameBlock);
        }

    }


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

    //    private static void drawBlocks(List<Collidable> gameBlocks,DrawSurface surface) throws Exception {
//
//        //drawing the collidables.
//        for (int i = 0; i < gameBlocks.size(); i++) {
//            gameBlocks.get(i).DrawOn(surface);
//        }
//    }
//
//    private static void drawEdges(List<Collidable> gameEdges, DrawSurface surface) {
//        for (Collidable c: gameEdges
//        ) {
//            c.DrawOn(surface);
//        }
//    }
//
//    private static void drawBall(Ball ball, DrawSurface surface) {
//        ball.drawOn(surface);
//    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}