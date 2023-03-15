package org.switch2022.project.utils;

/**
 * Enum Effort, contains a set of integers based on the Fibonacci sequence
 * to be used for estimation of effort for User Stories and Tasks.
 */

public enum Effort {

    ONE(1),
    TWO(2),
    THREE(3),
    FIVE(5),
    EIGHT(8),
    THIRTEEN(13),
    THIRTY_FOUR(34);

    /**
     * Attributes of the enum Effort, according to the Class Diagram.
     */

    final int effortValue;

    /**
     * Constructor used to pass data to Enum constants.
     */

    Effort(int effortValue) {
        this.effortValue = effortValue;
    }
}