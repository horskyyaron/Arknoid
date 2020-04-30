import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameEnvironment {

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

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) throws Exception {

        List<Point> trajectoryCollisionPoints = new LinkedList<>();
        int indexOfClosestCollidable;

        //getting all "closests" collision points from all the collidables
        // in the trajectory line.
        for (Collidable c: collidables) {
            Point collidableClosestPoint = trajectory.
                    closestIntersectionToStartOfLine(c.getCollisionRectangle());
            trajectoryCollisionPoints.add(collidableClosestPoint);
        }

        //getting the closest point to the start point of the trajectory.
        Point closestCollisionPoint =
                trajectory.start().getClosetsFromPointList(trajectoryCollisionPoints);

        indexOfClosestCollidable =
                trajectoryCollisionPoints.indexOf(closestCollisionPoint);
        Collidable collidableStrucked =
                this.collidables.get(indexOfClosestCollidable);

        return new CollisionInfo(closestCollisionPoint, collidableStrucked);

    }

    public List<Collidable> getCollidables() {
        return collidables;
    }
}
