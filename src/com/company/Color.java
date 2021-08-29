package com.company;

/**
 * A class for color in console
 *
 * @author hosna
 * @version 2021.4
 */
public class Color {
    // Reset
    /**
     * RESET COLOR
     */
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    /**
     * BLACK
     */
    public static final String BLACK = "\033[0;30m";   // BLACK
    /**
     * RDE
     */
    public static final String RED = "\033[0;31m";     // RED
    /**
     * GREEN
     */
    public static final String GREEN = "\033[0;32m";   // GREEN
    /**
     * BLUE
     */
    public static final String BLUE = "\033[0;34m";    // BLUE

    /**
     * return valid string of a color to change text color in console
     *
     * @param color name of color
     * @return ascii code for showing in console
     */
    public static String colorToAscii(String color) {
        if (color.equals("red"))
            return Color.RED;
        if (color.equals("blue"))
            return Color.BLUE;
        if (color.equals("black"))
            return Color.BLACK;
        if (color.equals("green"))
            return Color.GREEN;
        return null;
    }

}
