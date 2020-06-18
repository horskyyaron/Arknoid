//ID: 204351670
package execution.levels;

import execution.GameConstants;
import execution.backgrounds.LevelOneBackground;
import gameelements.sprites.Sprite;
import gameelements.sprites.movingitems.Velocity;
import gameelements.sprites.staticitems.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class LevelOne holds all of the information of level one:
 * number of balls, blocks, paddle speed and width etc..
 */
public class LevelOne implements LevelInformation {

    private static final double INITIAL_BALLS_VELOCITY = 10;
    private static final int NUMBER_OF_BALLS_IN_GAME = 20;

    private static final int PADDLE_SPEED = 8;
    private static final double PADDLE_WIDTH = GameConstants.getWidth() / 4.0;

    private static final int NUMBER_OF_BLOCKS_IN_GAME = 1;
    private static final double BLOCKS_DIMENSION = GameConstants.getHeight() * 0.065;
    private static final Point BLOCKS_UPPER_LEFT = new Point(GameConstants.getWidth() / 2.0 - 15,
            GameConstants.getHeight() / 4.0);




//    //@@@@@@@@@@@@@22cheating for testing:@@@@@@@@@@@@@@@@@@@@2
//    private static final double PADDLE_WIDTH = 700;
//    private static final int NUMBER_OF_BALLS_IN_GAME = 80;


    /**
     * Instantiates a new Level one.
     */
    public LevelOne() {

    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS_IN_GAME;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = Velocity.getVelocityFromAngleAndSpeed(0, INITIAL_BALLS_VELOCITY);
        List<Velocity> velocities = new LinkedList<>();
        velocities.add(v);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return (int) PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return ("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        return new LevelOneBackground();
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block(BLOCKS_UPPER_LEFT, BLOCKS_DIMENSION, BLOCKS_DIMENSION, Color.RED);

        //creating block list and adding the game's block to list.
        List<Block> blockList = new LinkedList<>();
        blockList.add(b);

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_IN_GAME;
    }
}
