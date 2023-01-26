package org.switch2022.project.utils;

/**
 * The class contains all the utility generic static methods that can be called
 * from another class of the project that needs it.
 */
public final class Util {

    /**
     * Utility classes, which are collections of static members, are not meant
     * to be instantiated.
     * Java adds an implicit public constructor to every class which does not
     * define at least one explicitly. Hence, at least one non-public
     * constructor should be defined.
     */
    private Util() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This static method checks if first number is lower than the second.
     *
     * @param first  integer number.
     * @param second integer number.
     * @return TRUE if first is lower than second, and FALSE otherwise.
     */
    public static boolean isLower(float first, float second) {
        return first < second;
    }

    /**
     * This static method checks if first number is lower or equals than the
     * second.
     *
     * @param first  integer number.
     * @param second integer number.
     * @return TRUE if first is lower or equal than second, and FALSE otherwise.
     */
    public static boolean isLowerOrEqual(float first, float second) {
        return first <= second;
    }

    /**
     * This static method sums two numbers.
     *
     * @param first  float number.
     * @param second float number.
     * @return the result of the sum.
     */
    public static float sum(float first, float second) {
        return first + second;
    }

    public static boolean areBothConditionsGranted(boolean first, boolean second) {
        return first && second;
    }
}
