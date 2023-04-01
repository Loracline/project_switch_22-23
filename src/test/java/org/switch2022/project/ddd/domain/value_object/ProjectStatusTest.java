package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectStatusTest {
    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of ProjectStatus are equal if their values are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoProjectStatusInstancesHaveTheSameValue() {
        //Arrange
        ProjectStatus status = ProjectStatus.ELABORATION;
        ProjectStatus otherStatus = ProjectStatus.ELABORATION;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of ProjectStatus are not equal if their values are not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoProjectStatusInstancesHaveDifferentValues() {
        //Arrange
        ProjectStatus status = ProjectStatus.TRANSITION;
        ProjectStatus otherStatus = ProjectStatus.CLOSED;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Assert
        assertFalse(result);
    }
}