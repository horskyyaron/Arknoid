package execution.listeners;

import execution.Game;
import gameelemnts.HitListener;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    //removed blocks = remaining blocks.
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //updating the remaining blocks counter
        this.remainingBalls.decrease(1);
        //removing Ball from game.
        hitter.removeFromGame(this.game);
    }
}
