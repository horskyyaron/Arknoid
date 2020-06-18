//ID: 204351670
package execution.levels;

import execution.GameConstants;
import execution.backgrounds.LevelFourBackground;
import gameelemnts.sprites.Sprite;
import gameelemnts.sprites.movingitems.ball.Velocity;
import gameelemnts.sprites.staticitems.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class LevelFour holds all of the information of level four:
 * number of balls, blocks, paddle speed and width etc..
 */
public class LevelFour implements LevelInformation {


    private static final double ROW_HEIGHT = GameConstants.getHeight() * 0.15;
    private static final int BLOCKS_IN_A_ROW = 15;
    private static final int NUMBER_OF_BLOCK_ROWS = 7;

    private static final double BLOCK_WIDTH = (GameConstants.getWidth() - 2
            * GameConstants.getBorderThickness()) / 15.0;
    private static final double BLOCK_HEIGHT = GameConstants.getHeight() / 22.2;


    private static final double INITIAL_BALLS_VELOCITY = 20;
    private static final int NUMBER_OF_BALLS_IN_GAME = 3;

    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = ((int) (GameConstants.getWidth() * 0.9));

    private static final int BALLS_INIT_ANGLE = 45;
    private static final int ANGLE_INCREMENT = 5;


//    //@@@@@@@@@@@@@22cheating for testing:@@@@@@@@@@@@@@@@@@@@2
//    private static final int PADDLE_WIDTH = 700;
//    private static final int NUMBER_OF_BALLS_IN_GAME = 300;

    /**
     * Will generate the level's blocks and add them to input block list.
     *
     * the level's blocks are in a single row.
     *
     * @param firstBlock upper left corner of the first block.
     * @param singleBlockWidth a block's width.
     * @param singleBlockHeight a block's height.
     * @param blocksCounter a counter that keeps track of how many more blocks needs to be created.
     * @param blockList will hold the information of the generated blocks.
     * @param color the color of the blocks.
     */
    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter, List<Block> blockList, Color color) {

        if (!(blocksCounter <= 0)) {
            //creating the block and adding it to game.
            Block block = new Block(firstBlock, singleBlockWidth,
                    singleBlockHeight, color);
            blockList.add(block);

            //checks if last block was added.
            if (blocksCounter - 1 == 0) {
                return;
            }

            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                            firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, blockList, color);
        }
    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS_IN_GAME;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int angle = BALLS_INIT_ANGLE;
        List<Velocity> ballVelocities = new LinkedList<>();

        for (int i = 0; i < NUMBER_OF_BALLS_IN_GAME / 2; i++) {
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed(angle, INITIAL_BALLS_VELOCITY));
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed((-1) * angle, INITIAL_BALLS_VELOCITY));

            //if we want to add more balls in future
            angle = angle + ANGLE_INCREMENT;
        }
        //ball that goes straight up.
        ballVelocities.add(new Velocity(0, (-1) * INITIAL_BALLS_VELOCITY));
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new LevelFourBackground();
    }

    @Override
    public List<Block> blocks() {

        List<Block> blockList = new LinkedList<>();

        Point firstRowFirstBlock = new Point(GameConstants.getWidth() - GameConstants.getBorderThickness()
                - BLOCK_WIDTH , ROW_HEIGHT);

        //generating each row separately.
        for (int i = 0; i < NUMBER_OF_BLOCK_ROWS; i++) {
            //each row first block is a one BLOCK_HIGHET lower on the sreen.
            Point firstBlock = new Point(firstRowFirstBlock.getX(),
                    firstRowFirstBlock.getY() + i * BLOCK_HEIGHT);

            Color backgroundColor = GameConstants.getRandomColor();

            //each row will have one block less than the row above it.
            generateBlockRow(firstBlock, BLOCK_WIDTH, BLOCK_HEIGHT,
                    BLOCKS_IN_A_ROW, blockList, backgroundColor);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_IN_A_ROW * NUMBER_OF_BLOCK_ROWS;
    }
}
