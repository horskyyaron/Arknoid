

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ REMOVE IT IN THE END!!
package execution.listeners;

import gameelemnts.HitListener;
import gameelemnts.sprites.movingitems.ball.Ball;
import gameelemnts.sprites.staticitems.Block;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block of the color:" + beingHit.getColor() + "was hit.");
    }
}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ REMOVE IT IN THE END!!