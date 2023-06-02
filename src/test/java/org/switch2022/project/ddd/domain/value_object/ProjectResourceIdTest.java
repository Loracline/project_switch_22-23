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
     * Tests that a ProjectResourceId is equal to null.
     */
    @Test
    void ensureThatProjectResourceIdIsEqualToNull() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);

        // Act, Assert
        assertNotEquals(null, projectResourceId);
    }


    /**
     * Tests that a ProjectResourceId is not equal to an object of a different type but with the same value.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToDifferentTypeWithSameValue() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        String stringValue = "PR1";

        // Act, Assert
        assertNotEquals(projectResourceId, stringValue);
    }

    /**
     * Tests that a ProjectResourceId is not equal to an object of a different type with a different value.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToDifferentTypeWithDifferentValue() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        String stringValue = "PR2";

        // Act, Assert
        assertNotEquals(projectResourceId, stringValue);
    }

    /**
     * Tests that a ProjectResourceId is not equal to a different instance with a different value.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToDifferentInstanceWithDifferentValue() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        ProjectResourceId differentProjectResourceId = new ProjectResourceId(2);

        // Act, Assert
        assertNotEquals(projectResourceId, differentProjectResourceId);
    }
    /**
     * Tests that a ProjectResourceId is equal to another instance with the same value.
     */
    @Test
    void ensureThatProjectResourceIdIsEqualToInstanceWithSameValue() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        ProjectResourceId sameValueResourceId = new ProjectResourceId(1);

        // Act, Assert
        assertEquals(projectResourceId, sameValueResourceId);
    }

    /**
     * Tests that a ProjectResourceId is not equal to another instance with a different value.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToInstanceWithDifferentValue() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        ProjectResourceId differentValueResourceId = new ProjectResourceId(2);

        // Act, Assert
        assertNotEquals(projectResourceId, differentValueResourceId);
    }

    /**
     * Tests that a ProjectResourceId is equal to itself using the Object class.
     */
    @Test
    void ensureThatProjectResourceIdIsEqualToItselfUsingObjectClass() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);

        // Act, Assert
        assertEquals(projectResourceId, projectResourceId);
    }

    /**
     * Tests that a ProjectResourceId is not equal to null.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToNull() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);

        // Act, Assert
        assertNotEquals( null,projectResourceId);
    }

    /**
     * Tests that a ProjectResourceId is not equal to an object of a different type.
     */
    @Test
    void ensureThatProjectResourceIdIsNotEqualToDifferentTypeObject() {
        // Arrange
        ProjectResourceId projectResourceId = new ProjectResourceId(1);
        Object otherObject = new Object();

        // Act, Assert
        assertNotEquals(projectResourceId, otherObject);
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