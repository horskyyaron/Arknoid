import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestGameEnvironment {

    @Test
    public void tesAddCollidable() throws Exception {

        List<Collidable> collidableList = new LinkedList<>();
        Collidable c1 = new Block(new Point(20,20),10,10);
        Collidable c2 = new Block(new Point(40,40),10,10);
        Collidable c3 = new Block(new Point(10,25),10,10);
        Collidable c4 = new Block(new Point(25,89),10,10);
        collidableList.add(c1);
        collidableList.add(c2);
        collidableList.add(c3);
        collidableList.add(c4);

        GameEnvironment game = new GameEnvironment(collidableList);
        assertTrue(game.getCollidables().get(0).getCollisionRectangle().equals(new Rectangle(new Point(20,20),10,10)));
        assertTrue(game.getCollidables().get(1).getCollisionRectangle().equals(new Rectangle(new Point(40,40),10,10)));
        assertTrue(game.getCollidables().get(2).getCollisionRectangle().equals(new Rectangle(new Point(10,25),10,10)));
        assertTrue(game.getCollidables().get(3).getCollisionRectangle().equals(new Rectangle(new Point(25,89),10,10)));



    }
}
