//ID: 204351670
package execution.levels;

import execution.animation.Utils;
import execution.backgrounds.LevelTwoBackground;
import gameelements.sprites.Sprite;
import gameelements.sprites.Velocity;
import gameelements.sprites.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class LevelTwo holds all of the information of level two:
 * number of balls, blocks, paddle speed and width etc..
 */
public class LevelTwo implements LevelInformation {

    private static final int NUMBER_OF_BLOCK_ROWS = 1;
    private static final int BLOCKS_IN_TOP_ROW = 15;
    private static final double BLOCK_WIDTH = (Utils.getWidth()
            - (2 * Utils.getBorderThickness())) / BLOCKS_IN_TOP_ROW;
    private static final double BLOCK_HEIGHT = Utils.getHeight() / 22.2;
    private static final double ROW_HEIGHT = Utils.getHeight() * 0.3;
    private static final int BLOCK_GROUP1 = BLOCKS_IN_TOP_ROW / 7;
    private static final int BLOCK_GROUP2 = BLOCK_GROUP1 + 1;
    private static final int PADDLE_WIDTH = ((int) (Utils.getWidth() * 0.7));
    private static final int PADDLE_SPEED = 2;
    private static final int NUMBER_OF_BALLS_IN_GAME = 10;
    private static final int BALLS_INIT_ANGLE = 10;
    private static final int ANGLE_INCREMENT = 10;
    private static final double INITIAL_BALLS_VELOCITY = 5.0;


    /**
     * Will generate a row of blocks and add them to input block list.
     *
     * @param firstBlock upper left corner of the first block.
     * @param singleBlockWidth a block's width.
     * @param singleBlockHeight a block's height.
     * @param blocksCounter a counter that keeps track of how many more blocks needs to be created.
     * @param blockList will hold the information of the generated blocks.
     */
    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter, List<Block> blockList) {

        if (!(blocksCounter <= 0)) {
            //creating the block and adding it to game.
            Block block = new Block(firstBlock, singleBlockWidth,
                    singleBlockHeight);
            block.setColor(paintBlock(blocksCounter));
            blockList.add(block);

            //check if last block.
            if (blocksCounter - 1 == 0) {
                return;
            }

            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                            firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, blockList);
        }
    }

    /**
     * will return a color for the block according to given blocks counter.
     *
     * the function will divide the blocks to groups according to the counter and will return a color
     * accordingly.
     *
     * @param blocksCounter a counter that keeps track of how many more blocks needs to be created.
     * @return a color for the block.
     */
    private Color paintBlock(int blocksCounter) {
        if (blocksCounter > BLOCKS_IN_TOP_ROW - BLOCK_GROUP1) {
            return Color.CYAN;
        } else if (blocksCounter > BLOCKS_IN_TOP_ROW - 2 * BLOCK_GROUP1) {
            return Color.pink;
        } else if (blocksCounter > BLOCKS_IN_TOP_ROW - 3 * BLOCK_GROUP1) {
            return Color.blue;
        } else if (blocksCounter > BLOCKS_IN_TOP_ROW - 3 * BLOCK_GROUP1 - BLOCK_GROUP2) {
            return Color.green;
        } else if (blocksCounter > BLOCKS_IN_TOP_ROW - 4 * BLOCK_GROUP1 - BLOCK_GROUP2) {
            return Color.yellow;
        } else if (blocksCounter > BLOCKS_IN_TOP_ROW - 5 * BLOCK_GROUP1 - BLOCK_GROUP2) {
            return Color.orange;
        } else {
            return Color.RED;
        }
    }


    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        int angle = BALLS_INIT_ANGLE;
        List<Velocity> ballVelocities = new LinkedList<>();

        for (int i = 0; i < (NUMBER_OF_BALLS_IN_GAME / 2); i++) {
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed(angle, INITIAL_BALLS_VELOCITY));
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed((-1) * angle, INITIAL_BALLS_VELOCITY));
            angle = angle + ANGLE_INCREMENT;
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
        return ("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return new LevelTwoBackground();
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
            //each row will have one block less than the row above it.
            generateBlockRow(firstBlock, BLOCK_WIDTH, BLOCK_HEIGHT,
                    BLOCKS_IN_TOP_ROW - i, blockList);
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCK_ROWS * BLOCKS_IN_TOP_ROW;
    }
}
