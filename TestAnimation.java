//import biuoop.DrawSurface;
//import biuoop.GUI;
//import biuoop.Sleeper;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class TestAnimation {
//
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 600;
//    private static final int MILLISECOND_BETWEEN_FRAMES = 20;
//
//    private static List<Collidable> getGameEdges() throws Exception {
//        Collidable left = new Block(new Point(0,0),0.1 * WIDTH,HEIGHT);
//        Collidable up = new Block(new Point(0 + 0.1*WIDTH,0),0.8 * WIDTH,0.1* HEIGHT);
//        Collidable right = new Block(new Point(0 + 0.9*WIDTH,0),0.1 * WIDTH,0.9 * HEIGHT);
//        Collidable down = new Block(new Point(0 + 0.1 * WIDTH,0 + 0.9 * HEIGHT), 0.9 * WIDTH, 0.1* HEIGHT);
//
//        List<Collidable> gameEdges = new ArrayList<>();
//        gameEdges.add(left);
//        gameEdges.add(up);
//        gameEdges.add(right);
//        gameEdges.add(down);
//
//        return gameEdges;
//    }
//
//    private static void drawEdges(List<Collidable> gameEdges, DrawSurface surface) {
//        for (Collidable c: gameEdges
//             ) {
//            c.DrawOn(surface);
//        }
//    }
//
//    private static void drawSomeCollidables(List<Collidable> collidablesArr, DrawSurface surface) throws Exception {
//        //generate randomm collidables on screen.
//        List<Collidable> someCollidable = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 15; i++) {
//            someCollidable.add((Collidable) new
//                    Block(new Point(random.nextInt(WIDTH),
//                    random.nextInt(HEIGHT)),
//                    random.nextInt(100),
//                    random.nextInt(100)));
//        }
//
//        for (int i = 0; i < collidablesArr.size(); i++) {
//            collidablesArr.get(i).DrawOn(surface);
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        GUI gui = new GUI("Testing some animation shit", WIDTH,HEIGHT);
//        Sleeper sleeper = new Sleeper();
//
//        Ball ball = new Ball(230 ,400,5, Color.red);
//        ball.setVelocity(10,13);
//
//
//
//        GameEnvironment game = new GameEnvironment(getGameEdges());
//        //GameEnvironment game = new GameEnvironment(someCollidable);
//        game.addListOfCollidables(someCollidable);
//
//        while(true) {
//
//            DrawSurface d = gui.getDrawSurface();
//
//            drawEdges(getGameEdges(),d);
//            drawSomeCollidables(someCollidable,d);
//
//            ball.moveOneStep(game);
//            if(ball.getY() > 445) {
//                int x = 5;
//            }
//            ball.drawOn(d);
//
//            gui.show(d);
//            sleeper.sleepFor(MILLISECOND_BETWEEN_FRAMES);
//        }
//    }
//
//
//}
