package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class ProjectResourceIdTest {

    /**
     * Testing if Project Resource ID is created when is above zero.
     */
    @Test
    void ensureThatProjectResourceIdIsCreated_Positive() {
        // Arrange
        final int VALID_ID = 1;

        // Act, Assert
        assertDoesNotThrow(() -> new ProjectResourceId(VALID_ID));
    }

    /**
     * Testing if Project Resource ID is not created when is equal to zero.
     */
    @Test
    void ensureThatProjectResourceIdIsCreated_EqualsZero() {
        // Arrange
        final int INVALID_ID = 0;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResourceId(INVALID_ID));
    }

    /**
     * Testing if Project Resource ID is not created when is below zero.
     */
    @Test
    void ensureThatProjectResourceIdIsNotCreated_BelowZero() {
        // Arrange
        final int INVALID_NEGATIVE_ID = -1;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new ProjectResourceId(INVALID_NEGATIVE_ID));
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatProjectResourceIdIsEqualsItself() {
        // Arrange
        ProjectResourceId expected = new ProjectResourceId(1);
        ProjectResourceId result = new ProjectResourceId(1);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoProjectResourceIdAreNotEquals() {
        // Arrange
        ProjectResourceId expected = new ProjectResourceId(1);
        ProjectResourceId result = new ProjectResourceId(10);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @Test
    @SuppressWarnings("all")
    void ensureThatProjectResourceIdIsNotEqualOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        ProjectResourceId result = new ProjectResourceId(10);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoProjectResourceIdHaveSameHashCode() {
        // ARRANGE
        ProjectResourceId expected = new ProjectResourceId(11);
        ProjectResourceId result = new ProjectResourceId(11);

        // ACT
        int hashCodeReference = expected.hashCode();
        int hashCodeOther = result.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }


    @Test
    void ensureThatTwoProjectResourceIdHaveDifferentHashCode() {
        // ARRANGE
        ProjectResourceId expected = new ProjectResourceId(10);
        ProjectResourceId result = new ProjectResourceId(11);

        // ACT
        int hashCodeReference = expected.hashCode();
        int hashCodeOther = result.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Tests for sameValueAs()
     */
    @Test
    void ensureThatTwoCostPerHourHaveTheSameValue() {
        // Arrange
        ProjectResourceId expected = new ProjectResourceId(11);
        ProjectResourceId result = new ProjectResourceId(11);

        // Act
        boolean other = expected.sameValueAs(result);

        // Assert
        assertTrue(other);
    }

    @Test
    void ensureThatTwoCostPerHourDoesNotHaveTheSameValue() {
        // Arrange
        ProjectResourceId expected = new ProjectResourceId(10);
        ProjectResourceId result = new ProjectResourceId(11);

        // Act
        boolean other = expected.sameValueAs(result);

        // Assert
        assertFalse(other);
    }

}