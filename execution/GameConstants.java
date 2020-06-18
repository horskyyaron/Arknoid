package execution;

public class GameConstants {
    //screen size constants.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //the thickness is set to be a percentage of the screen width.
    private static final double BORDER_THICKNESS_FACTOR = 0.04;

    public GameConstants() {

    }

     public static int getWidth() {
        return WIDTH;
    }
     public static int getHeight() {
        return HEIGHT;
     }

    public static double getBorderThickness() {
        return BORDER_THICKNESS_FACTOR * WIDTH;
    }
}
