package execution.listeners;

import execution.Game;
import gameelemnts.HitListener;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;
import gameelemnts.sprites.staticitems.ScoreIndicator;

public class ScoreTrackingListener implements HitListener {
    private final int BLOCK_POINTS = 5;

    private Counter currentScore;

    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        //update score
       currentScore.increase(BLOCK_POINTS);
       //remove listener from block
       beingHit.removeHitListener(this);
    }

    public Counter getCurrentScore() {
        return currentScore;
    }
}