package execution;

import gameelemnts.HitListener;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    //removed blocks = remaining blocks.
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating the remaining blocks counter
        this.remainingBlocks.decrease(1);
        //removing listner from block.
        beingHit.removeHitListener(this);
        //removing block from game.
        this.game.removeCollidable(beingHit);
        this.game.removeSprite(beingHit);
    }
}