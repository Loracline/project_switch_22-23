package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PbIdTest {

    /**
     * Constructor
     * <br>
     * Scenario 1: An ID is not created because the project code is null.
     */
    @Test
    void ensureAnIdIsNotCreatedBecauseProjectCodeIsNull() {
        // Arrange
        String projectCode = null;
        String expected = "Project code can't be null.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PbId(projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Scenario 2: An ID is not created because the project code is empty.
     */
    @Test
    void ensureAnIdIsNotCreatedBecauseProjectCodeIsEmpty() {
        // Arrange
        String projectCode = "";
        String expected = "Project code can't be empty.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PbId(projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Scenario 3: An ID is not created because the project code is blank.
     */
    @Test
    void ensureAnIdIsNotCreatedBecauseProjectCodeIsBlank() {
        // Arrange
        String projectCode = "      ";
        String expected = "Project code can't be blank.";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new PbId(projectCode));

        // Assert
        assertEquals(expected, result.getMessage());
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
     * Test case for sameValueAs method.
     * Returns true for the same instance of PbId.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForSameInstance() {
        PbId pbId = new PbId("ABC");

        boolean result = pbId.sameValueAs(pbId);

        Assertions.assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns true for two instances with the same product backlog ID.
     */
    @Test
    public void testSameValueAs_ReturnsTrueForEqualProductBacklogId() {
        PbId pbId1 = new PbId("ABC");
        PbId pbId2 = new PbId("ABC");

        boolean result = pbId1.sameValueAs(pbId2);

        Assertions.assertTrue(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for null input.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForNullInput() {
        PbId pbId = new PbId("ABC");

        boolean result = pbId.sameValueAs(null);

        Assertions.assertFalse(result);
    }

    /**
     * Test case for sameValueAs method.
     * Returns false for two instances with different product backlog IDs.
     */
    @Test
    public void testSameValueAs_ReturnsFalseForDifferentProductBacklogId() {
        PbId pbId1 = new PbId("ABC");
        PbId pbId2 = new PbId("XYZ");

        boolean result = pbId1.sameValueAs(pbId2);

        Assertions.assertFalse(result);
    }
    /**
     * METHOD equals()
     * <br>
     * Scenario 1: Verify that the same object equals itself.
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

    /**
     * METHOD toString()
     * <br>
     * Scenario 1: Verify that the string returned is the same of the id attribute of PbId value object.
     */
    @Test
    void ensureProductBacklogIdStringIsRetrievedSuccessfully() {
        // Arrange
        PbId pbId = new PbId("P001");
        String expected = "p001_pb";

        // Act
        String result = pbId.toString();

        // Assert
        assertEquals(expected, result);
    }
}