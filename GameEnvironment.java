import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class GameEnvironment {

    private static final double EPSILON = 0.0001;
    //fields
    private List<Collidable> collidables = new ArrayList<>();

    //constructor
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public void addListOfCollidables(List<Collidable> list) {
       this.collidables.addAll(list);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) throws Exception {

        List<Point> trajectoryCollisionPoints = new LinkedList<>();
        List<Collidable> collidablesInTrajectory = new LinkedList<>();


        //getting all "closests" collision points from all the collidables
        // in the trajectory line.
        for (Collidable c: collidables) {
            Point collidableClosestPoint = trajectory.
                    closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //if there's no collision, don't add.
            if(collidableClosestPoint != null) {
                trajectoryCollisionPoints.add(collidableClosestPoint);
                collidablesInTrajectory.add(c);
            }
        }

        //if no collision, return null.
        if(trajectoryCollisionPoints.isEmpty()) {
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

    //because intersection point is being calculated using cramers rule, sometimes i'll get a solution very close to the real one, therefore
    //i'll have very small issues of percision. this function fixes it.
    //becuase we checked and confirmed that an the collidable is intersection with the trajectory,
    // it must have an intersection point on the collidable.
    private Point updateCollisionPointFix(Point closestCollisionPoint, Collidable collidableStrucked) throws Exception {
        Rectangle r = collidableStrucked.getCollisionRectangle();
        double xCord = closestCollisionPoint.getX();
        double yCord = closestCollisionPoint.getY();
        if(xCord > r.getUpperLeft().getX() - EPSILON && xCord < r.getUpperLeft().getX() + EPSILON) {
            return new Point(r.getUpperLeft().getX(),yCord);
        } else if (xCord > r.getUpperLeft().getX() + r.getWidth() - EPSILON && xCord < r.getUpperLeft().getX() + r.getWidth() + EPSILON) {
            return new Point(r.getUpperLeft().getX() + r.getWidth(),yCord);
        } else if (yCord > r.getUpperLeft().getY() - EPSILON && yCord < r.getUpperLeft().getY() + EPSILON) {
            return new Point(xCord, r.getUpperLeft().getY());
        } else if (yCord > r.getUpperLeft().getY() + r.getHeight() - EPSILON && yCord < r.getUpperLeft().getY() + r.getHeight() + EPSILON) {
            return new Point(xCord, r.getUpperLeft().getY() + r.getHeight());
        } else {
            return closestCollisionPoint;
        }
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }
}
