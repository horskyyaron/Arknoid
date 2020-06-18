package execution.levels;

import execution.GameConstants;
import execution.backgrounds.LevelThreeBackground;
import gameelemnts.sprites.Sprite;
import gameelemnts.sprites.movingitems.ball.Velocity;
import gameelemnts.sprites.staticitems.Block;
import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LevelThree implements LevelInformation {

    private static final int NUMBER_OF_BLOCK_ROWS = 5;
    private static final double BLOCK_WIDTH = GameConstants.getWidth() *0.06;
    private static final double BLOCK_HEIGHT = GameConstants.getHeight() / 22.2;
    private static final double ROW_HEIGHT = GameConstants.getHeight() * 0.3;
    private static final int BLOCKS_IN_TOP_ROW = 10;
    private static final double INITIAL_BALLS_VELOCITY = 4.0;
    //private static final int NUMBER_OF_BALLS_IN_GAME = 2;
    private static final int PADDLE_SPEED = 6;
    //private static final int PADDLE_WIDTH = ((int)(GameConstants.getWidth() * 0.1));

    //cheating for testing:
    private static final int PADDLE_WIDTH = 700;
    private static final int NUMBER_OF_BALLS_IN_GAME = 80;



    public LevelThree() {

    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        int angle = 45;
        List<Velocity> ballVelocities = new LinkedList<>();

        for (int i = 0; i < NUMBER_OF_BALLS_IN_GAME / 2; i++) {
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed(angle, INITIAL_BALLS_VELOCITY));
            ballVelocities.add(Velocity.getVelocityFromAngleAndSpeed((-1)*angle, INITIAL_BALLS_VELOCITY));
            angle = angle + 5;
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

        Point firstRowFirstBlock = new Point(GameConstants.getWidth() - GameConstants.getBorderThickness()
                - BLOCK_WIDTH , ROW_HEIGHT);

        //generating each row separately.
        for (int i = 0; i < NUMBER_OF_BLOCK_ROWS; i++) {
            //each row first block is a one BLOCK_HIGHET lower on the sreen.
            Point firstBlock = new Point(firstRowFirstBlock.getX(),
                    firstRowFirstBlock.getY() + i * BLOCK_HEIGHT);

            Color backgroundColor = getRandomColor();

            //each row will have one block less than the row above it.
            generateBlockRow(firstBlock, BLOCK_WIDTH, BLOCK_HEIGHT,
                    BLOCKS_IN_TOP_ROW - i, blockList, backgroundColor);
        }
        return blockList;
    }



    private void generateBlockRow(Point firstBlock,
                                  double singleBlockWidth,
                                  double singleBlockHeight, int blocksCounter, List<Block> blockList, Color color) {

        if (!(blocksCounter <= 0)) {
            //creating the block and adding it to game.
            Block block = new Block(firstBlock, singleBlockWidth,
                    singleBlockHeight,color);
            blockList.add(block);

            if(blocksCounter - 1 == 0) {
                return;
            }

            //recursively generating blocks in the left direction.
            generateBlockRow(new Point(firstBlock.getX() - singleBlockWidth,
                            firstBlock.getY()), singleBlockWidth, singleBlockHeight,
                    blocksCounter - 1, blockList, color);
        }
    }

    /**
     * Generating and returning a randome color.
     *
     * @return a random chosen RGB color.
     */
    private static Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        return new Color(r, g, b);
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
