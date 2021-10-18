//ID: 204351670
package execution.levels;

import execution.animation.Utils;
import execution.backgrounds.LevelThreeBackground;
import gameelements.sprites.Sprite;
import gameelements.sprites.Velocity;
import gameelements.sprites.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class LevelThree holds all of the information of level three:
 * number of balls, blocks, paddle speed and width etc..
 */
public class LevelThree implements LevelInformation {

    private static final int NUMBER_OF_BLOCK_ROWS = 5;
    private static final double BLOCK_WIDTH = Utils.getWidth() * 0.06;
    private static final double BLOCK_HEIGHT = Utils.getHeight() / 22.2;
    private static final double ROW_HEIGHT = Utils.getHeight() * 0.3;
    private static final int BLOCKS_IN_TOP_ROW = 10;
    private static final double INITIAL_BALLS_VELOCITY = 5.0;
    private static final int NUMBER_OF_BALLS_IN_GAME = 3;
    private static final double BALLS_ANGLE = 45.0;
    private static final double BALLS_ANGLE_GAP = 5.0;
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = ((int) (Utils.getWidth() * 0.1));

    /**
     * Instantiates a new Level three.
     */
    public LevelThree() {

    }

    /**
     * Will generate a row of blocks and add them to input block list.
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

            //check if it's the last block to be generated int the row.
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

        int angle = (int) BALLS_ANGLE;
        List<Velocity> ballVelocities = new LinkedList<>();

        for (int i = 0; i < NUMBER_OF_BALLS_IN_GAME / 2; i++) {
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed(angle, INITIAL_BALLS_VELOCITY));
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed((-1) * angle, INITIAL_BALLS_VELOCITY));
            angle = angle + (int) BALLS_ANGLE_GAP;
        }
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
        return ("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return new LevelThreeBackground();
    }

    @Override
    public List<Block> blocks() {

        List<Block> blockList = new LinkedList<>();

        Point firstRowFirstBlock = new Point(Utils.getWidth() - Utils.getBorderThickness()
                - BLOCK_WIDTH , ROW_HEIGHT);

        //generating each row separately.
        for (int i = 0; i < NUMBER_OF_BLOCK_ROWS; i++) {
            //each row first block is a one BLOCK_HIGHET lower on the sreen.
            Point firstBlock = new Point(firstRowFirstBlock.getX(),
                    firstRowFirstBlock.getY() + i * BLOCK_HEIGHT);

            Color backgroundColor = Utils.getRandomColor();

            //each row will have one block less than the row above it.
            generateBlockRow(firstBlock, BLOCK_WIDTH, BLOCK_HEIGHT,
                    BLOCKS_IN_TOP_ROW - i, blockList, backgroundColor);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        int blocks = 0;
        //counting blocks.
        for (int i = 0; i < NUMBER_OF_BLOCK_ROWS; i++) {
            blocks = blocks + BLOCKS_IN_TOP_ROW - i;
        }
        return blocks;
    }
}
