//ID: 204351670

import java.awt.Color;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;

/**
 * HelpFunction class supports methods and constants that supports mathematical
 * calculations needed in other classes of the project.
 */
public class HelpFunctions {

    //Declaring the size of the standard screen size.
    public static final int STD_SCREEN_WIDTH = 700;
    public static final int STD_SCREEN_HEIGHT = 700;

    /*
     * creating a frame that will support the standard screen size.
     * the static block is for coding style purposes and for handling the
     * exception of Point class
     */
//    static final Frame STD_ANIMATION_FRAME;
//    static {
//        Frame f = null;
//        try {
//            f = new Frame(new Point(0, 0), STD_SCREEN_WIDTH, STD_SCREEN_HEIGHT,
//                    Color.white);
//        } catch (Exception e) {
//            f = null;
//        }
//        STD_ANIMATION_FRAME = f;
//    }

    //constant that helps measure equality between double variables.
    // (used in areTwoDoublesEqual method).
    public static final double EPSILLON = 0.000_000_000_1;

    /**
     * check if two double variables are equal.
     *
     * Two doubles will be define as equal if:
     * let d1, d2 be doubles. then d1 equals d2 if |d1-d2| < EPSILON.
     *
     * @param x a double variable.
     * @param y a double variable.
     * @return true, if doubles are equal, 'false' otherwise.
     */
    public static boolean areTwoDoublesEqual(double x, double y) {
        return (abs(x - y) < EPSILLON);
    }

    /**
     * check if the projections of two lines are overlapping.
     *
     * each line will have a projection on both x and y axis.
     * the function will check if the projections are overlapping either in
     * both axis' or only one of them.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap.
     */
    public static boolean areProjectionsOverlapping(Line l1, Line l2) {
        return (xAxisOverlappingCheck(l1, l2)
                && yAxisOverlappingCheck(l1, l2)
                || (xAxisOverlappingCheck(l2, l1)
                && yAxisOverlappingCheck(l2, l1)));
    }

    /**
     * check if the projections of two lines are overlapping on the x-axis.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap on the x axis..
     */
    public static boolean xAxisOverlappingCheck(Line l1, Line l2) {
        double l1StartX = l1.start().getX();
        double l1EndX = l1.end().getX();
        double l2StartX = l2.start().getX();
        double l2EndX = l2.end().getX();
        //check overlap on the x-axis projections segments.
        return (belongs(l1StartX, l2StartX, l2EndX)
                || (belongs(l1EndX, l2StartX, l2EndX)));
    }

    /**
     * check if the projections of two lines are overlapping on the y-axis.
     *
     * @param l1 a Line object.
     * @param l2 a Line object.
     * @return true, if the two lines projections overlap on the y axis..
     */
    private static boolean yAxisOverlappingCheck(Line l1, Line l2) {
        double l1StartY = l1.start().getY();
        double l1EndY = l1.end().getY();
        double l2StartY = l2.start().getY();
        double l2EndY = l2.end().getY();
        //check overlap on the x-axis projections segments.
        return belongs(l1StartY, l2StartY, l2EndY)
                || (belongs(l1EndY, l2StartY, l2EndY));
    }


    /**
     * (Without limiting generality let b <= c).
     * check if a double value belongs in the section (b,c) of double values.
     *
     * @param a double variable.
     * @param b double variable.
     * @param c double variable.
     * @return true, if a is in (b,c) and 'false' otherwise.
     */
    public static boolean belongs(double a, double b, double c) {
        if (a < max(b, c) && a > min(b, c)) {
            return true;
        }
        return false;
    }

    /**
     * checks if a coordinate from a given axis belongs to the section projected
     * by line on that same axis.
     *
     * @param coordinate an 'x' or 'y' coordinate.
     * @param axis a char variable that indicates if the coordinate is of
     *              x, or y axis.
     * @param l the line that the projected segment is being projected from.
     * @return true, if the coordinate is in the segment projected, 'false'
     *               otherwise.
     */
    public static boolean belongs(double coordinate, char axis, Line l) {
        switch (axis) {
            case 'x':
                if (coordinate < max(l.start().getX(), l.end().getX())
                        && coordinate > min(l.start().getX(), l.end().getX())) {
                    return true;
                }
                case 'y':
                if (coordinate < max(l.start().getY(), l.end().getY())
                        && coordinate > min(l.start().getY(), l.end().getY())) {
                    return true;
                }
                default:
                    return false;
        }
    }

    /**
     * calculates: SHIFT-TAN(y/x) as in the calculator.
     *
     * @param x double
     * @param y double
     * @return double the result of the operation.
     */
    public static double shiftTan(double x, double y) {
        double hypotenuse = sqrt(x * x + y * y);
        //get angle in radians.
        double angle = Math.acos(x / hypotenuse);
        //convert to degress.
        return angle * 180 / PI;
    }

    /**
     * getting a sub-array of an original array. from the index startIndex to
     * endIndex.
     *
     * sub-array[] = original[startIndex : endIndex]
     *
     * @param originalArr the original array.
     * @param startIndex the first index of the original array from which the
     *                   sub-array will "start".
     * @param endIndex the ending index of the original array from which the
     *      *                   sub-array will "end".
     * @return the sliced array.
     */
    public static String[] sliceArr(String[] originalArr, int startIndex,
                                    int endIndex) {
        String[] slicedArr = new String[endIndex - startIndex];
        int slicedArrIndexCounter = 0;
        for (int i = startIndex; i < endIndex; i++) {
            slicedArr[slicedArrIndexCounter] = originalArr[i];
            slicedArrIndexCounter++;
        }
        return slicedArr;
    }

}
