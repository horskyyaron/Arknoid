//ID: 204351670
package execution.levels;

import gameelements.sprites.Sprite;
import gameelements.sprites.movingitems.Velocity;
import gameelements.sprites.staticitems.Block;

import java.util.List;

/**
 * The interface LevelInformation. A level information object will hold all of the methods needed to have
 * complete information on a levels characteristics, enough to create a level in the game.
 */
public interface LevelInformation {
    /**
     * will return the number of balls in level.
     *
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * will return the velocities of the balls in the game.
     *
     * @return the list of the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * will return the paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * will return the paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * will return the level name.
     *
     * @return the level name. a String.
     */
    String levelName();

    /**
     * will return a Sprite object which holds the information of the background.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * will return a list of the level blocks.
     *
     * @return the list of level's blocks.
     */
    List<Block> blocks();

    /**
     * will return the number of blocks needed to be removed in the game.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();
}