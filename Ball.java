//ID: 204351670
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Ball class supports methods that their goal is to represent a ball on the
 * screen animation.
 */
public class Ball {

    //fields.
    private Point center;
    private int radius;
    private Velocity velocity;
    private java.awt.Color color;

    /**
     * contructor function
     *
     * constructor of the 'Ball' object.
     * a Ball object has three components: a center point (Point object), a
     * radius and background color.
     *
     * @param center the ball center point on screen..
     * @param radius the ball's radius.
     * @param color the ball's color.
     * @throws Exception if the point representing the center of the ball has
     *                   negative coordinates.
     */
    public Ball(Point center, int radius, java.awt.Color color)
            throws Exception {
        if (center.getY() <= 0 || center.getX() <= 0) {
            throw new Exception("Point coordinates cannot be non positive.");
        } else {
            this.center = center;
            this.radius = radius;
            this.color = color;
        }
    }

    /**
     * contructor function2
     *
     * constructor of the 'Ball' object.
     * a Ball object has four components: a center point x-coordinate, a center
     * point y-coordinate, a radius and a background color.
     *
     * @param x the ball center point's x-coordinate.
     * @param y the ball center point's y-coordinate.
     * @param radius the ball's radius.
     * @param color the ball's color.
     * @throws Exception if ball center point has negative coordinates.
     */
    public Ball(int x, int y, int radius, java.awt.Color color)
            throws Exception {
        this(new Point(x, y), radius, color);
    }


    /**
     * will draw a given ball onto an input surface.
     *
     * @param surface the surface which the ball will be drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }





    /**
     * will change the ball's position according to the limits of a given
     * animation frame.
     *
     * @param movementFrame the frame which limits the balls movement
     *                      possibilities to a certain area on the screen.
     * @throws Exception if frame's top left corner point has negative coordinates.
     */
    public void moveOneStep(Frame movementFrame) throws Exception {
        //The ball is at an edge of the frame.
        if (this.isInEdgeMove(movementFrame)) {
            this.center = this.getVelocity().applyToPoint(this.center);
            //if the next move will get the ball out of bounds.
        } else if (this.isOutOfBoundsMove(movementFrame, "dx")
                || this.isOutOfBoundsMove(movementFrame, "dy")) {
            //1D case.
            if (this.isOneDimensinalMovement()) {
                //when the ball moves up and down
                if (this.getVelocity().getDx() == 0) {
                    //when tha ball moves down
                    if (this.getVelocity().getDy() > 0) {
                        this.moveToEdge("down", movementFrame);
                        return;
                    } else {
                        this.moveToEdge("up", movementFrame);
                        return;
                    }
                    //when the ball moves to the sides.
                } else {
                    if (this.getVelocity().getDx() > 0) {
                        this.moveToEdge("right", movementFrame);
                        return;
                    } else {
                        this.moveToEdge("left", movementFrame);
                        return;
                    }
                }
            //2D case.
            } else {
                //handling the horizontal movement
                if (this.getVelocity().getDy() > 0
                        && this.isOutOfBoundsMove(movementFrame, "dy")) {
                    this.moveToEdge("down", movementFrame);

                } else if (this.getVelocity().getDy() < 0
                        && this.isOutOfBoundsMove(movementFrame, "dy")) {
                    this.moveToEdge("up", movementFrame);
                }
                //handling the vertical movement.
                if (this.getVelocity().getDx() > 0
                        && this.isOutOfBoundsMove(movementFrame, "dx")) {
                    this.moveToEdge("right", movementFrame);

                } else if (this.getVelocity().getDx() < 0
                        && this.isOutOfBoundsMove(movementFrame, "dx")) {
                    this.moveToEdge("left", movementFrame);
                }
            }
        //regular move.
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * will move the ball so that it will touch the edge input edge.
     *
     * @param edge string that tells which edge the ball will go to.
     * @param movementFrame the frame which limits the balls movement
     *                      possibilities to a certain area on the screen.
     * @throws Exception if frame's top left corner point has negative
     *                   coordinates.
     */
    private void moveToEdge(String edge, Frame movementFrame)
            throws Exception {
        //for code comprehension and readability.
        int leftEdge = (int) movementFrame.getTopLeft().getX();
        int rightEdge = (int) movementFrame.getTopLeft().getX()
                + movementFrame.getFrameWidth();
        int upperEdge = (int) movementFrame.getTopLeft().getY();
        int lowerEdge = (int) movementFrame.getTopLeft().getY()
                + movementFrame.getFrameHeight();

        switch (edge) {
            case "up":
                this.center = new Point(this.center.getX(), upperEdge
                        + this.radius);
                return;
            case "down":
                this.center = new Point(this.center.getX(), lowerEdge
                        - this.radius);
                return;
            case "left":
                this.center = new Point(leftEdge + this.radius,
                        this.center.getY());
                return;
            case "right":
                this.center = new Point(rightEdge - this.radius,
                        this.center.getY());
                return;
            default:
                return;
        }
    }

    /**
     * check if the ball is moving in a one dimentional movement. (up-down,
     * left-right).
     *
     * @return true if one of the speed components is zero.
     */
    private boolean isOneDimensinalMovement() {
        return (this.getVelocity().getDx() == 0
                ^ this.getVelocity().getDy() == 0);
    }

    /**
     * check if the current move will get the ball out of the frame borders.
     *
     * @param speedComponet string that tells which movement to check:
     *                      horizontal or vertical movement.
     * @param movementFrame the frame which limits the balls movement
     *                      possibilities to a certain area on the screen.
     * @return 'true' if the current movement of the ball will move the ball
     *          outside the frame borders.
     */
    private boolean isOutOfBoundsMove(Frame movementFrame,
                                      String speedComponet) {
        //for code comprehension and readability.
        int leftEdge = (int) movementFrame.getTopLeft().getX();
        int rightEdge = (int) movementFrame.getTopLeft().getX()
                + movementFrame.getFrameWidth();
        int upperEdge = (int) movementFrame.getTopLeft().getY();
        int lowerEdge = (int) movementFrame.getTopLeft().getY()
                + movementFrame.getFrameHeight();

        int dx = (int) this.getVelocity().getDx();
        int dy = (int) this.getVelocity().getDy();

        if (speedComponet.equals("dx")) {
            if (dx < 0 && this.center.getX() - radius + dx < leftEdge) {
                return true;
            } else if (dx > 0 && this.center.getX() + radius + dx > rightEdge) {
                return true;
            }
        //y comp.
        } else {
            if (dy < 0 && this.center.getY() - radius + dy < upperEdge) {
                return true;
            } else if (dy > 0 && this.center.getY() + radius + dy > lowerEdge) {
                return true;
            }
        }
        return false;
    }


    /**
     * will check if a move is starting from one of the frame boeders.
     * preform the move, and will notify that the move happend.
     *
     * @param movementFrame the frame which limits the balls movement
     *                      possibilities to a certain area on the screen.
     * @return true if a ball moved from one of the frame edges. 'false'
     *         otherwise.
     */
    public boolean isInEdgeMove(Frame movementFrame) {
        //for code comprehension and readability.
        int leftEdge = (int) movementFrame.getTopLeft().getX();
        int rightEdge = (int) movementFrame.getTopLeft().getX() + movementFrame.getFrameWidth();
        int upperEdge = (int) movementFrame.getTopLeft().getY();
        int lowerEdge = (int) movementFrame.getTopLeft().getY() + movementFrame.getFrameHeight();

        //indicator for having a ball in edge case.
        int indicator = 0;

        //right edge
        if (this.getX() + this.radius == rightEdge) {
            if (this.velocity.getDx() > 0) {
                this.velocity.changeDirectionHorizontal();
            }
            indicator++;
        //left edge.
        } else if (this.getX() - this.radius == leftEdge) {
            if (this.velocity.getDx() < 0) {
                this.velocity.changeDirectionHorizontal();
            }
            indicator++;
        }
        //lower edge.
        if (this.getY() + this.radius == lowerEdge) {
            this.velocity.changeDirectionVertical();
            indicator++;
        //upper edge.
        } else if (this.getY() - this.radius == upperEdge) {
            this.velocity.changeDirectionVertical();
            indicator++;
        }
        return (indicator != 0);

    }



    /**
     * Will change the initial ball position so that the initial ball's position
     * will corresponds to the animation frame limitation.
     *
     * @param ball a ball object.
     * @param movementFrame the frame which limits the new inital position of
     *                      the ball.
     * @return a new ball object, with an initial position that corresponds to
     *         the given frame.
     * @throws Exception if ball's center point have negative coordinates.
     */
    public static Ball fixOutOfScreenInitPosition(Ball ball,
                                                  Frame movementFrame)
                                                  throws Exception {
        Random random = new Random();
        int width = movementFrame.getFrameWidth();
        int height = movementFrame.getFrameHeight();
        Point topLeft = movementFrame.getTopLeft();

        //check for out of bounds.
        while (((ball.getX() - ball.getSize()) <= topLeft.getX())
                || ((ball.getX() + ball.getSize()) >= topLeft.getX() + width)
                || (ball.getY() - ball.getSize()) <= topLeft.getY()
                || (ball.getY() + ball.getSize()) >= topLeft.getY() + height) {
            //changing to a valid initial position.
            Point newInitPosition = new Point(random.nextInt(width) + 1
                    + topLeft.getX(), random.nextInt(height) + 1
                    + topLeft.getY());
            ball = new Ball(newInitPosition, ball.getSize(), Color.BLACK);
        }
        return ball;
    }


    /**
     * check if the ball is inside the frame.
     *
     * @param x ball's center point's x- coordinate.
     * @param y ball's center point's y- coordinate.
     * @param radius ball's radius.
     * @param animationFrame the frame which limits the balls movement
     *                      possibilities to a certain area on the screen.
     * @return true if the ball is in a valid position under the frame
     *         constraints, 'false' otherwise.
     */
    public static boolean isBallPositionIsValidInFrame(int x, int y, int radius,
                                                Frame animationFrame) {
        return !(x - radius < animationFrame.getTopLeft().getX()
                || x + radius > animationFrame.getTopLeft().getX()
                + animationFrame.getFrameWidth()
                || y - radius < animationFrame.getTopLeft().getY()
                || y + radius > animationFrame.getTopLeft().getY()
                + animationFrame.getFrameHeight());
    }

    /**
     * Fixes the situation of a ball stuck to the edge of the screen.
     *
     * initial position of the ball and certain velocity components may cause
     * an animation of a ball stuck to an edge of the frame. the function will
     * change the direction so it will not happen.
     *
     * @param ball the ball being checked.
     * @param animationFrame the frame which the ball will be animated on.
     */
    public static void fixBallStuckInEdgeOfScreen(Ball ball,
                                                  Frame animationFrame) {
        //for code comprehension.
        int x = ball.getX();
        int y = ball.getY();
        Velocity v = ball.getVelocity();
        int radius = ball.getSize();

        if (x - radius == animationFrame.getTopLeft().getX() && v.getDx() > 0) {
            ball.getVelocity().changeDirectionHorizontal();
        }
        if (x + radius == animationFrame.getTopLeft().getX()
                + animationFrame.getFrameWidth() && v.getDx() < 0) {
            ball.getVelocity().changeDirectionHorizontal();
        }
        if (y - radius == animationFrame.getTopLeft().getY() && v.getDy() > 0) {
            ball.getVelocity().changeDirectionVertical();
        }
        if (y + radius == animationFrame.getTopLeft().getY()
                + animationFrame.getFrameHeight() && v.getDy() < 0) {
            ball.getVelocity().changeDirectionVertical();
        }
    }



    //Other Methods
    /**
     * will change the ball's color to a random RGB color.
     */
    public void changeToRandomColor() {
        Random random = new Random();
        int r = random.nextInt(255) + 1;
        int g = random.nextInt(255) + 1;
        int b = random.nextInt(255) + 1;
        this.color = new Color(r, g, b);
    }

    /**
     * will prints ball's information. testing purposes.
     */
    public void printBall() {
        System.out.println("ball center point: (" + this.getX() + ","
                + this.getY() + ")");
        System.out.println("ball radius is: " + this.getSize());
        System.out.println("ball color is: " + this.color);
    }

    //getters
    /**
     * returns the Ball's center's point x-coordinate.
     *
     * @return the given Ball's center's point x-coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * returns the Ball's center's point x-coordinate.
     *
     * @return the given Ball's center's point y-coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * returns the Ball's radius.
     *
     * @return the given Ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * returns the Ball's background color.
     *
     * @return the given Ball's background color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * returns the Ball's velocity.
     *
     * @return the given Ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    //setters
    /**
     * will set ball's velocity to a the input velocity.
     *
     * @param v the velocity that will be given to the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * will set ball's velocity according to the vertical and horizontal speed
     * components dx, dy.
     *
     * @param dx the ball's velocity in the horizontal direction.
     * @param dy the ball's velocity in the vertical direction.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


}