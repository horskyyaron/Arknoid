package execution.levels;

import execution.GameConstants;
import execution.backgrounds.LevelOneBackground;
import gameelemnts.sprites.Sprite;
import gameelemnts.sprites.movingitems.ball.Velocity;
import gameelemnts.sprites.staticitems.Block;
import geometry.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class LevelOne implements LevelInformation {

    private static final double INITIAL_BALLS_VELOCITY = 5.0;
    private static final int PADDLE_SPEED = 8;
    private static final int NUMBER_OF_BLOCKS_IN_GAME = 1;
//    private static final int NUMBER_OF_BALLS_IN_GAME = 20;
//    private static final double PADDLE_WIDTH = GameConstants.getWidth()/4.0;

    //cheating for testing:
    private static final double PADDLE_WIDTH = 700;
    private static final int NUMBER_OF_BALLS_IN_GAME = 80;


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
        return (int)PADDLE_WIDTH;
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
        Point upperLeft = new Point(GameConstants.getWidth()/2.0 - 15, GameConstants.getHeight()/4.0);
        Block b = new Block(upperLeft, 38, 38, Color.RED);

        List<Block> blockList = new LinkedList<>();
        blockList.add(b);


        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_IN_GAME;
    }
}
