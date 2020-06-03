//ID: 204351670

package execution.listeners;

import execution.Game;
import gameelemnts.HitListener;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

/**
 * BlockRemover is a class that supports methods that will keep track of the number of blocks
 * in the game, and will remove them when needed.
 */
public class BlockRemover implements HitListener {

    //fields
    private Game game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating the remaining blocks counter
        this.remainingBlocks.decrease(1);
        //removing listner from block.
        beingHit.removeHitListener(this);
        //removing block from game.
        beingHit.removeFromGame(this.game);
    }
}