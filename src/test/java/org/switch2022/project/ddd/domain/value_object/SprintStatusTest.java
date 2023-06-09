package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.sprint.Sprint;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class SprintStatusTest {

    /**
     * Tests for sameValueAs()
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

    @Test
    void ensureThatAnExceptionIsThrownBecauseTheSprintStatusToCompareIsNull() {
        // Arrange
        SprintStatus statusOne = SprintStatus.PLANNED;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> statusOne.sameValueAs(null));
    }

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

    @Test
    void ensureThatSprintStatusIsGeneratedSuccessfullyWhenIsInLowerCase() {
        // Arrange
        SprintStatus expected = SprintStatus.PLANNED;
        String statusString = "planned";

        // Act
        SprintStatus result = SprintStatus.generateSprintStatus(statusString);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureThatSprintStatusIsNotGeneratedSuccessfullyBecauseDoesNotExist() {
        // Arrange
        String stringStatus = "RUNNING";

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> SprintStatus.generateSprintStatus(stringStatus));


    }
}