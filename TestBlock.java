import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestBlock {

    private static Point c1, c2, c3, c4;

    static {
        try {
            c1 = new Point(60, 50);
            c2 = new Point(70, 60);
            c3 = new Point(60, 70);
            c4 = new Point(50, 60);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetImpactLine() throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRight = new Velocity(1, 0);
        Velocity vLeft = new Velocity(-1, 0);
        Velocity vUp = new Velocity(0, 1);
        Velocity vDown = new Velocity(0, -1);

        Line l1 = block.getImpactLineFromCollisionPoint(c1);
        Line l2 = block.getImpactLineFromCollisionPoint(c2);
        Line l3 = block.getImpactLineFromCollisionPoint(c3);
        Line l4 = block.getImpactLineFromCollisionPoint(c4);

        assertTrue(l1.equals(new Line(50, 50, 70, 50)));
        assertTrue(l2.equals(new Line(70, 50, 70, 70)));
        assertTrue(l3.equals(new Line(70, 70, 50, 70)));
        assertTrue(l4.equals(new Line(50, 50, 50, 70)));
    }

    @Test
    public void testFrontalHit () throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRight = new Velocity(1, 0);
        Velocity vLeft = new Velocity(-1, 0);
        Velocity vUp = new Velocity(0, 1);
        Velocity vDown = new Velocity(0, -1);

        /**
         * checking frontal impact in a block.
         */

        //checking hit in the upper edge of the block (horizontal surface)
        Velocity vDownAfterCollision = block.hit(c1,vDown);
        assertTrue(vDownAfterCollision.equals(new Velocity(0,1)));

        Velocity vUpAfterCollision = block.hit(c1,vUp);
        assertTrue(vUpAfterCollision.equals(new Velocity(0,-1)));


        //what do i fo when hitting from the right in a horizontal line?
//        Velocity vRightAfterCollision = block.hit(c1,vRight);
//        assertTrue(vDownAfterCollision.equals(new Velocity(0,1));
//
//        Velocity vLeftAfterCollision = block.hit(c1,vLeft);
//        assertTrue(vDownAfterCollision.equals(new Velocity(0,1));

        Velocity vRightAfterCollision = block.hit(c2,vRight);
        assertTrue(vRightAfterCollision.equals(new Velocity(-1,0)));

        Velocity vLeftAfterCollision = block.hit(c2,vLeft);
        assertTrue(vLeftAfterCollision.equals(new Velocity(1,0)));

    }

    @Test
    public void testAngularHitUpperEdge() throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRU = new Velocity(-1, 1);
        Velocity vLU = new Velocity(1, 1);
        Velocity vRD = new Velocity(-1, -1);
        Velocity vLD = new Velocity(1, -1);

        /**
         * checking angular impact on a block upper edge..
         */
        Velocity vRUAfter = block.hit(c1,vRU);
        assertTrue(vRUAfter.equals(new Velocity(-1,-1)));

        Velocity vLUAfter = block.hit(c1,vLU);
        assertTrue(vLUAfter.equals(new Velocity(1,-1)));

        Velocity vRDAfter = block.hit(c1,vRD);
        assertTrue(vRDAfter.equals(new Velocity(-1,1)));

        Velocity vLDAfter = block.hit(c1,vLD);
        assertTrue(vLDAfter.equals(new Velocity(1,1)));


    }

    @Test
    public void testAngularHitRightEdge() throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRU = new Velocity(-1, 1);
        Velocity vLU = new Velocity(1, 1);
        Velocity vRD = new Velocity(-1, -1);
        Velocity vLD = new Velocity(1, -1);

        /**
         * checking angular impact on a block right edge..
         */
        Velocity vRUAfter = block.hit(c2,vRU);
        assertTrue(vRUAfter.equals(new Velocity(1,1)));

        Velocity vLUAfter = block.hit(c2,vLU);
        assertTrue(vLUAfter.equals(new Velocity(-1,1)));

        Velocity vRDAfter = block.hit(c2,vRD);
        assertTrue(vRDAfter.equals(new Velocity(1,-1)));

        Velocity vLDAfter = block.hit(c2,vLD);
        assertTrue(vLDAfter.equals(new Velocity(-1,-1)));
    }

    @Test
    public void testAngularHitBottomEdge() throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRU = new Velocity(-1, 1);
        Velocity vLU = new Velocity(1, 1);
        Velocity vRD = new Velocity(-1, -1);
        Velocity vLD = new Velocity(1, -1);

        /**
         * checking angular impact on a block lower edge..
         */
        Velocity vRUAfter = block.hit(c3,vRU);
        assertTrue(vRUAfter.equals(new Velocity(-1,-1)));

        Velocity vLUAfter = block.hit(c3,vLU);
        assertTrue(vLUAfter.equals(new Velocity(1,-1)));

        Velocity vRDAfter = block.hit(c3,vRD);
        assertTrue(vRDAfter.equals(new Velocity(-1,1)));

        Velocity vLDAfter = block.hit(c3,vLD);
        assertTrue(vLDAfter.equals(new Velocity(1,1)));

    }

    @Test
    public void testAngularHitLeftEdge() throws Exception {
        Block block = new Block(new Point(50, 50), 20, 20);
        Velocity vRU = new Velocity(-1, 1);
        Velocity vLU = new Velocity(1, 1);
        Velocity vRD = new Velocity(-1, -1);
        Velocity vLD = new Velocity(1, -1);

        /**
         * checking angular impact on a block left edge..
         */
        Velocity vRUAfter = block.hit(c4,vRU);
        assertTrue(vRUAfter.equals(new Velocity(1,1)));

        Velocity vLUAfter = block.hit(c4,vLU);
        assertTrue(vLUAfter.equals(new Velocity(-1,1)));

        Velocity vRDAfter = block.hit(c4,vRD);
        assertTrue(vRDAfter.equals(new Velocity(1,-1)));

        Velocity vLDAfter = block.hit(c4,vLD);
        assertTrue(vLDAfter.equals(new Velocity(-1,-1)));


    }
}
