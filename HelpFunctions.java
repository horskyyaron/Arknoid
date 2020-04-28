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

//    /*
//     * creating a frame that will support the standard screen size.
//     * the static block is for coding style purposes and for handling the
//     * exception of Point class
//     */
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
