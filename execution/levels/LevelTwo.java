package execution.levels;

import execution.GameConstants;
import execution.backgrounds.LevelTwoBackground;
import gameelemnts.sprites.Sprite;
import gameelemnts.sprites.movingitems.ball.Velocity;
import gameelemnts.sprites.staticitems.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class LevelTwo implements LevelInformation {

    private static final int NUMBER_OF_BLOCK_ROWS = 1;
    private static final double BLOCK_WIDTH = (GameConstants.getWidth() - (2 * GameConstants.getBorderThickness())) / 15;
    private static final double BLOCK_HEIGHT = GameConstants.getHeight() / 22.2;
    private static final double ROW_HEIGHT = GameConstants.getHeight() * 0.3;
    private static final int BLOCKS_IN_TOP_ROW = 15;
    private static final double INITIAL_BALLS_VELOCITY = 5.0;
    //private static final int NUMBER_OF_BALLS_IN_GAME = 10;
    //private static final int PADDLE_WIDTH = ((int)(GameConstants.getWidth() * 0.7));
    private static final int PADDLE_SPEED = 4;

    private static final int BALLS_INIT_ANGLE = 10;
    private static final int ANGLE_INCREMENT = 10;

    //@@@@@@@@@@@@@22cheating for testing:@@@@@@@@@@@@@@@@@@@@2
    private static final int PADDLE_WIDTH = 700;
    private static final int NUMBER_OF_BALLS_IN_GAME = 200;




    public LevelTwo() {

    }

    public static double getRowHeight() {
        return ROW_HEIGHT;
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
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed((-1)*angle, INITIAL_BALLS_VELOCITY));
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

        Point firstRowFirstBlock = new Point(GameConstants.getWidth() - GameConstants.getBorderThickness()
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

    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter, List<Block> blockList) {

        if (!(blocksCounter <= 0)) {
            //creating the block and adding it to game.
            Block block = new Block(firstBlock, singleBlockWidth,
                    singleBlockHeight);
            block.setColor(paintBlock(blocksCounter));
            blockList.add(block);

            if(blocksCounter - 1 == 0) {
                return;
            }

            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                            firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, blockList);
        }
    }

    private Color paintBlock(int blocksCounter) {
        if (blocksCounter >= 14) {
            return Color.CYAN;
        } else if (blocksCounter >= 12) {
            return Color.pink;
        } else if (blocksCounter >= 10) {
            return Color.blue;
        } else if (blocksCounter >= 7) {
            return Color.green;
        } else if (blocksCounter >= 5) {
            return Color.yellow;
        } else if (blocksCounter >= 3) {
            return Color.orange;
        } else {
            return Color.RED;
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCK_ROWS * BLOCKS_IN_TOP_ROW;
    }
}
