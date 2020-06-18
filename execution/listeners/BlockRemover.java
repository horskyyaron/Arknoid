//ID: 204351670

package execution.listeners;

import execution.Counter;
import execution.GameLevel;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

/**
 * BlockRemover is a class that supports methods that will keep track of the number of blocks
 * in the game, and will remove them when needed.
 */
public class BlockRemover implements HitListener {

    //fields
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating the remaining blocks counter
        this.remainingBlocks.decrease(1);
        //removing listner from block.
        beingHit.removeHitListener(this);
        //removing block from game.
        beingHit.removeFromGame(this.gameLevel);
    }
}