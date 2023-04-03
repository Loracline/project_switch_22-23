package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Status;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {
    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of Status are equal if their values are the same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoStatusInstancesHaveTheSameValue() {
        //Arrange
        Status status = Status.PLANNED;
        Status otherStatus = Status.PLANNED;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify that two instances of Status are not equal if their values are not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoStatusInstancesHaveDifferentValues() {
        //Arrange
        Status status = Status.PLANNED;
        Status otherStatus = Status.FINISHED;
        //Act
        boolean result = status.sameValueAs(otherStatus);
        //Assert
        assertFalse(result);
    }

}