package org.switch2022.project.utils;

/**
 * The class contains all the utility generic static methods that can be called
 * from another class of the project that needs it.
 */
public final class Util {

    /**
     * Ensuring exception is thrown when trying to instantiate class that can't
     * be instantiated.
     */
    public Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This static method checks if first number is lower than the second.
     *
     * @param first  integer number.
     * @param second integer number.
     * @return TRUE if first is lower than second, and FALSE otherwise.
     */
    public static boolean isLower(int first, int second) {
        return first < second;
    }
}
