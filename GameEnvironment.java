//ID: 204351670

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * GameEnvironment class supports methods that their goal is to hold all of the
 * game objects that the ball can collide with.
 */
public class GameEnvironment {

    private static final double EPSILON = 0.0001;
    //fields
    private List<Collidable> collidables;

    /**
     * constructor function.
     *
     * @param collidables a list of Collidable objects.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * constructor function2. initializing with an empty array list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * the function will add input Collidable object to the the Collidables
     * list field.
     *
     * @param c an input Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * the function will add input Collidable objects list to the the
     * Collidables list field.
     *
     * @param list an input Collidable object.
     */
    public void addCollidableList(List<Collidable> list) {
       this.collidables.addAll(list);
    }


    /**
     * return the information on the collision which is the closest to the
     * the starting point of the the trajectory.
     *
     * Assume an object moving from A to B.
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision to point A.
     *
     * @param trajectory the input line represents the ball trajectory.
     * @return the information on the closest collision. a CollisionInfo object.
     * @throws Exception if the collision point coordinates have negative
     *                   values.
     */
    public CollisionInfo getClosestCollision(Line trajectory) throws Exception {

        List<Point> trajectoryCollisionPoints = new LinkedList<>();
        List<Collidable> collidablesInTrajectory = new LinkedList<>();

        //getting all "closests" collision points from all the collidables
        // in the trajectory line.
        for (Collidable c: collidables) {
            Point collidableClosestPoint = trajectory.
                    closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //if there's no collision, don't add.
            if (collidableClosestPoint != null) {
                trajectoryCollisionPoints.add(collidableClosestPoint);
                collidablesInTrajectory.add(c);
            }
        }

        //if no collision, return null.
        if (trajectoryCollisionPoints.isEmpty()) {
            return null;
        } else {
            //getting the closest point to the start point of the trajectory.
            Point closestCollisionPoint =
                    trajectory.start().getClosetsFromPointList(trajectoryCollisionPoints);

            int indexOfClosestCollidable =
                    trajectoryCollisionPoints.indexOf(closestCollisionPoint);
            Collidable collidableStrucked =
                    collidablesInTrajectory.get(indexOfClosestCollidable);
            //in case of very-close collision point which not corresponds to the
            //collidable object.
            closestCollisionPoint =
                    updateCollisionPointFix(closestCollisionPoint,
                            collidableStrucked);

            return new CollisionInfo(closestCollisionPoint, collidableStrucked);
        }

    }


    /**
     * the function will return the correct collision point.
     *
     * because intersection point is being calculated using cramers rule,
     * sometimes inaccuracy with double variable will occur.
     * because we checked and confirmed that a collidable is intersecting with
     * the trajectory, it must have an intersection point on the collidable.
     *
     * @param closestCollisionPoint the closest collision point calculated.
     * @param collidableStrucked the Collidable object that is intersecting with
     *                           trajectory.
     * @return the correct collision point with Collidable object.
     * @throws Exception if the collision point has negative coordinate values.
     */
    private Point updateCollisionPointFix(Point closestCollisionPoint,
                                          Collidable collidableStrucked)
                                          throws Exception {
        //the rectangle that got hit.
        Rectangle r = collidableStrucked.getCollisionRectangle();

        //the x and y coordinates of the closest collision point calculated.
        double xCord = closestCollisionPoint.getX();
        double yCord = closestCollisionPoint.getY();

        //check for inaccuracy, and returning the correct Point object
        // accordingly.
        if (xCord > r.getUpperLeft().getX() - EPSILON
                && xCord < r.getUpperLeft().getX() + EPSILON) {
            return new Point(r.getUpperLeft().getX(), yCord);
        } else if (xCord > r.getUpperLeft().getX() + r.getWidth() - EPSILON
                && xCord < r.getUpperLeft().getX() + r.getWidth() + EPSILON) {
            return new Point(r.getUpperLeft().getX() + r.getWidth(), yCord);
        } else if (yCord > r.getUpperLeft().getY() - EPSILON
                && yCord < r.getUpperLeft().getY() + EPSILON) {
            return new Point(xCord, r.getUpperLeft().getY());
        } else if (yCord > r.getUpperLeft().getY() + r.getHeight() - EPSILON
                && yCord < r.getUpperLeft().getY() + r.getHeight() + EPSILON) {
            return new Point(xCord, r.getUpperLeft().getY() + r.getHeight());
        } else {
            return closestCollisionPoint;
        }
    }

    /**
     * the function will return the list of Collidable objects in the
     * GameEnvironment.
     *
     * @return the list of collidables in the game environment
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
}
