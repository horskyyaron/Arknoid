//ID: 204351670

package execution.listeners;

import gameelements.sprites.Ball;
import gameelements.sprites.Block;

/**
 * The interface Hit listener. will start a hit event when a hit is preformed between a ball and a block.
 */
public interface HitListener {
    /**
     * when beingHit object is hit, the methods will arise, will preform operation according to
     * the listener purpose.
     *
     * @param beingHit the block that is being strucked.
     * @param hitter   the ball that hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
