//ID: 204351670

package execution.listeners;

import execution.Counter;
import execution.GameLevel;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

/**
 * BallRemover is a class that supports methods that will keep track of the number of balls
 * in the game, and will remove them when needed.
 */
public class BallRemover implements HitListener {

    //fields
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the game which is being played.
     * @param removedBlocks the remaining balls (copied from the assignment page).
     */
//removed blocks = remaining blocks.
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating the remaining blocks counter
        this.remainingBalls.decrease(1);
        //removing Ball from game.
        hitter.removeFromGame(this.gameLevel);
    }
}
