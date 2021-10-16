//ID: 204351670
package gameelements.sprites;

import geometry.Point;

/**
 * The PaddleInfo class will hold information about the paddle.
 */
public class PaddleInfo {

    private Paddle paddle;

    /**
     * Instantiates a new Paddle info.
     *
     * @param levelPaddle the level paddle
     */
    public PaddleInfo(Paddle levelPaddle) {
        this.paddle = levelPaddle;
    }

    /**
     * Gets the center point of the paddle.
     *
     * @return the center of paddle.
     */
    public Point getCenterOfPaddle() {
        return this.paddle.getCollisionRectangle().getCenterOfRec();
    }

    /**
     * Gets paddle height coordiante.
     *
     * @return the paddle height coordiante
     */
    public double getPaddleHeightCoordiante() {
        return this.paddle.getUpperLeft().getY();
    }
}
