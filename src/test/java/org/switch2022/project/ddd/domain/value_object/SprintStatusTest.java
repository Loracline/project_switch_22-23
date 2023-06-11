package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class SprintStatusTest {

    /**
     * Method sameValueAs()
     *<br>
     * Scenario 1: Verify that two instances of SprintStatus are equal if their values are the same.
     * It should assert true.
     */
    @Test
    void ensureThatTwoSprintStatusHaveTheSameValue() {
        // Arrange
        SprintStatus statusOne = SprintStatus.PLANNED;
        SprintStatus statusTwo = SprintStatus.PLANNED;

        // Act
        boolean result = statusOne.sameValueAs(statusTwo);

        // Assert
        assertTrue(result);
    }

    /**
     * Method sameValueAs()
     *<br>
     * Scenario 2: Verify that two instances of SprintStatus are different if their values are not the same.
     * It should assert false.
     */
    @Test
    void ensureThatTwoSprintStatusDoNotHaveTheSameValue() {
        // Arrange
        SprintStatus statusOne = SprintStatus.PLANNED;
        SprintStatus statusTwo = SprintStatus.CLOSED;

        // Act
        boolean result = statusOne.sameValueAs(statusTwo);

        // Assert
        assertFalse(result);
    }

    /**
     * Method sameValueAs()
     *<br>
     * Scenario 2: Verify that two instances of SprintStatus are different if one of them is null.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatAnExceptionIsThrownBecauseTheSprintStatusToCompareIsNull() {
        // Arrange
        SprintStatus statusOne = SprintStatus.PLANNED;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> statusOne.sameValueAs(null));
    }

    /**
     * Method generateSprintStatus()
     *<br>
     * Scenario 1: Verify that an instance of SprintStatus is successfully generated from a valid string (in uppercase).
     * It should assert equals.
     */
    @Test
    void ensureThatSprintStatusIsGeneratedSuccessfullyWhenIsInUpperCase() {
        // Arrange
        SprintStatus expected = SprintStatus.OPEN;
        String statusString = "OPEN";

        // Act
        SprintStatus result = SprintStatus.generateSprintStatus(statusString);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method generateSprintStatus()
     *<br>
     * Scenario 2: Verify that an instance of SprintStatus is successfully generated from a valid string (in lowercase).
     * It should assert equals.
     */
    @Test
    void ensureThatSprintStatusIsGeneratedSuccessfullyWhenIsInLowerCase() {
        // Arrange
        SprintStatus expected = SprintStatus.PLANNED;
        String statusString = "PLANNED";

        // Act
        SprintStatus result = SprintStatus.generateSprintStatus(statusString);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method generateSprintStatus()
     *<br>
     * Scenario 3: Verify that an instance of SprintStatus is not generated because the input string is invalid.
     * It should throw an InvalidInputException.
     */
    @Test
    void ensureThatSprintStatusIsNotGeneratedSuccessfullyBecauseDoesNotExist() {
        // Arrange
        String stringStatus = "RUNNING";

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> SprintStatus.generateSprintStatus(stringStatus));
    }

    /**
     * Method getStatus()
     *<br>
     * Scenario 1: Verify that the string representation of an instance of SprintStatus is successfully returned
     * (open).
     * It should assert equals.
     */
    @Test
    void ensureThatTheStringRepresentationOfTheSprintStatusIsSuccessfullyReturnedWhenIsOpen() {
        //ARRANGE
        String expected ="OPEN";
        //ACT
        String result = SprintStatus.OPEN.getStatus();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method getStatus()
     *<br>
     * Scenario 2: Verify that the string representation of an instance of SprintStatus is successfully returned
     * (planned).
     * It should assert equals.
     */
    @Test
    void ensureThatTheStringRepresentationOfTheSprintStatusIsSuccessfullyReturnedWhenIsPlanned() {
        //ARRANGE
        String expected ="PLANNED";
        //ACT
        String result = SprintStatus.PLANNED.getStatus();
        //ASSERT
        assertEquals(expected, result);
    }

    /**
     * Method getStatus()
     *<br>
     * Scenario 3: Verify that the string representation of an instance of SprintStatus is successfully returned
     * (closed).
     * It should assert equals.
     */
    @Test
    void ensureThatTheStringRepresentationOfTheSprintStatusIsSuccessfullyReturnedWhenIsClosed() {
        //ARRANGE
        String expected ="CLOSED";
        //ACT
        String result = SprintStatus.CLOSED.getStatus();
        //ASSERT
        assertEquals(expected, result);
    }
}