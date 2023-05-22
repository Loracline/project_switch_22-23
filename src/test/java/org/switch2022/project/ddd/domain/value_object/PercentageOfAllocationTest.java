package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class PercentageOfAllocationTest {

    /**
     * Testing if Percentage of Allocation is created when is equals to 100%.
     */
    @Test
    void ensureThatPercentageOfAllocationIsCreated_PercentAllocationEqualsOneHundred() {
        // Arrange
        final float MAXIMUM_PERCENTAGE = 100;

        // Act, Assert
        assertDoesNotThrow(() -> new PercentageOfAllocation(MAXIMUM_PERCENTAGE));
    }

    /**
     * Testing if percentage of allocation is not created when is 0%.
     */
    @Test
    void ensureThatPercentageOfAllocationIsNotCreated_PercentAllocationEqualsZero() {
        // Arrange
        final float PERCENTAGE_ZERO = 0;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new PercentageOfAllocation(PERCENTAGE_ZERO));
    }

    /**
     * Testing if percentage of allocation is not created when is above 100%.
     */
    @Test
    void ensureThatPercentageOfAllocationIsNotCreated_PercentAllocationAboveOneHundred() {
        // Arrange
        final float PERCENTAGE_ABOVE_ONE_HUNDRED = 151;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new PercentageOfAllocation(PERCENTAGE_ABOVE_ONE_HUNDRED));
    }

    /**
     * Testing if percentage of allocation is not created when is below 0%.
     */
    @Test
    void ensureThatPercentageOfAllocationIsNotCreated_PercentAllocationBelowZero() {
        // Arrange
        final float PERCENTAGE_BELOW_ZERO = -20;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new PercentageOfAllocation(PERCENTAGE_BELOW_ZERO));
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatPercentageOfAllocationEqualsItself() {
        // Arrange
        PercentageOfAllocation expected = new PercentageOfAllocation(10);
        PercentageOfAllocation result = new PercentageOfAllocation(10);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoPercentageOfAllocationAreNotEquals() {
        // Arrange
        PercentageOfAllocation expected = new PercentageOfAllocation(10);
        PercentageOfAllocation result = new PercentageOfAllocation(11);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @SuppressWarnings("all")
    @Test
    void ensureThatPercentageOfAllocationIsNotEqualOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        PercentageOfAllocation result = new PercentageOfAllocation(11);

        // Act, Assert
        assertNotEquals(expected, result);
    }
    @Test
    @SuppressWarnings("all")
    void ensureThatPercentageOfAllocationIsEqualToItself() {
        // Arrange
        PercentageOfAllocation expected = new PercentageOfAllocation(11);
        PercentageOfAllocation result = expected;

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    @SuppressWarnings("all")
    void ensureThatPercentageOfAllocationIsNotEqualToObjectFromADiferrentClass() {
        // Arrange
        PercentageOfAllocation expected = new PercentageOfAllocation(11);
        Code result = new Code(10);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoPercentageOfAllocationHaveSameHashCode() {
        // ARRANGE
        PercentageOfAllocation percentageOfAllocationOne = new PercentageOfAllocation(11);
        PercentageOfAllocation percentageOfAllocationTwo = new PercentageOfAllocation(11);

        // ACT
        int hashCodeReference = percentageOfAllocationOne.hashCode();
        int hashCodeOther = percentageOfAllocationTwo.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    @Test
    void ensureThatTwoPercentageOfAllocationHaveDifferentHashCode() {
        // ARRANGE
        PercentageOfAllocation percentageOfAllocationOne = new PercentageOfAllocation(10);
        PercentageOfAllocation percentageOfAllocationTwo = new PercentageOfAllocation(11);

        // ACT
        int hashCodeReference = percentageOfAllocationOne.hashCode();
        int hashCodeOther = percentageOfAllocationTwo.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Tests for sameValueAs()
     */
    @Test
    void ensureThatTwoPercentageOfAllocationHaveTheSameValue() {
        // Arrange
        PercentageOfAllocation percentageOfAllocationOne = new PercentageOfAllocation(10);
        PercentageOfAllocation percentageOfAllocationTwo = new PercentageOfAllocation(10);

        // Act
        boolean result = percentageOfAllocationOne.sameValueAs(percentageOfAllocationTwo);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureThatTwoPercentageOfAllocationDoesNotHaveTheSameValue() {
        // Arrange
        PercentageOfAllocation percentageOfAllocationOne = new PercentageOfAllocation(10);
        PercentageOfAllocation percentageOfAllocationTwo = new PercentageOfAllocation(11);

        // Act
        boolean result = percentageOfAllocationOne.sameValueAs(percentageOfAllocationTwo);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD getValue()
     */
    @DisplayName("Percentage of allocation is retrieved successfully")
    @Test
    void ensurePercentageOfAllocationIsRetrievedSuccessfully() {
        // Arrange
        float expected = 25.0F;
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(expected);

        // Act
        float result = percentageOfAllocation.getValue();

        // Assert
        assertEquals(expected, result);
    }
}