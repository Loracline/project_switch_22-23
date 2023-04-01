package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PbIdTest {

    /**
     * METHOD getProductBacklogId()
     * <br>
     * Scenario 1: Verify thar the value returned os the same as the value of the id attribute of the PbId value object.
     */
    @Test
    void ensureProductBacklogIdIsRetrievedSuccessfully() {
        // Arrange
        PbId pbId = new PbId("P001");
        String expected = "p001_pb";

        // Act
        String result = pbId.getProductBacklogId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD sameValueAs()
     * <br>
     * Scenario 1: Verify that two instances of PbId are equal if the value of their attribute product backlog id is the
     * same.
     */
    @Test
    void ensureThatReturnsTrueIfTwoPbIdInstancesHaveTheSameIdValue() {
        // Arrange
        PbId pbId = new PbId("P001");
        PbId otherPbId = new PbId("P001");
        boolean expected = true;

        // Act
        boolean result = pbId.sameValueAs(otherPbId);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two instances of PbId are not equal if the value of their attribute product backlog id is
     * not the same.
     */
    @Test
    void ensureThatReturnsFalseIfTwoPbIdInstancesDoNotHaveTheSameIdValue() {
        // Arrange
        PbId pbId = new PbId("P001");
        PbId otherPbId = new PbId("P002");
        boolean expected = false;

        // Act
        boolean result = pbId.sameValueAs(otherPbId);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that same object equals itself.
     */
    @Test
    void ensureSamePbIdEqualsItself() {
        // Arrange
        PbId reference = new PbId("P001");
        PbId other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two objects with the same id are equal.
     */
    @Test
    void ensureTwoInstancesWithSameIdAreEqual() {
        // Arrange
        PbId reference = new PbId("P001");
        PbId other = new PbId("P001");
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify that two objects with different ids are not equal.
     */
    @Test
    void ensureTwoInstancesWithDifferentIdsAreNotEqual() {
        // Arrange
        PbId reference = new PbId("P001");
        PbId other = new PbId("P002");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify that the object PbId does not equal null.
     */
    @Test
    void ensurePbIdDoesNotEqualNull() {
        // Arrange
        PbId reference = new PbId("P001");
        boolean expected = false;

        // Act
        boolean result = reference.equals(null);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify that the object PbId does not equal other type of object.
     */
    @Test
    void ensurePbIdDoesNotEqualOtherTypeOfObject() {
        // Arrange
        PbId reference = new PbId("P001");
        UsId other = new UsId("P001", "US001");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     * <br>
     * Scenario 1: Verify that two equal PbId objects have the same hashcode.
     */
    @Test
    void ensureTwoEqualPbIdInstancesHaveTheSameHashcode() {
        // Arrange
        PbId reference = new PbId("P001");
        PbId other = new PbId("P001");
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify that two different PbId objects have different hashcode.
     */
    @Test
    void ensureTwoDifferentPbIdInstancesHaveDifferentHashcode() {
        // Arrange
        PbId reference = new PbId("P001");
        PbId other = new PbId("P002");
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }
}