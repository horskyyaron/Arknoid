//ID: 204351670

package execution.listeners;

import execution.Counter;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

/**
 * ScoreTrackingListener is a class that supports methods that will keep track of the game score,
 * will update the score whenever a block is removed.
 */
public class ScoreTrackingListener implements HitListener {
    //block points.
    private static final int BLOCK_POINTS = 5;

    //fields.
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //update score
       currentScore.increase(BLOCK_POINTS);
       //remove listener from block
       beingHit.removeHitListener(this);
    }

}