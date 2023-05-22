package org.switch2022.project.ddd.domain.value_object;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

class CostPerHourTest {

    /**
     * Testing if Cost Per Hour is not created when is equals to zero.
     */
    @Test
    void ensureThatCostPerHourIsCreated_EqualsZero() {
        // Arrange
        final float COST_ZERO = 0;

        // Act, Assert
        assertDoesNotThrow(() -> new CostPerHour(COST_ZERO));
    }

    /**
     * Testing if Cost Per Hour is created when is above zero.
     */
    @Test
    void ensureThatCostPerHourIsCreated_Positive() {
        // Arrange
        final float COST_POSITIVE = 1;

        // Act, Assert
        assertDoesNotThrow(() -> new CostPerHour(COST_POSITIVE));
    }

    /**
     * Testing if Cost Per Hour is not created when is below zero.
     */
    @Test
    void ensureThatCostPerHourIsNotCreated_BelowZero() {
        // Arrange
        final float COST_NEGATIVE = -1;

        // Act, Assert
        assertThrows(InvalidInputException.class, () -> new CostPerHour(COST_NEGATIVE));
    }

    /**
     * Tests for the equals() method.
     */
    @Test
    void ensureThatCostPerHourIsEqualsItself() {
        // Arrange
        CostPerHour expected = new CostPerHour(10);
        CostPerHour result = new CostPerHour(10);

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoCostPerHourAreNotEquals() {
        // Arrange
        CostPerHour expected = new CostPerHour(10);
        CostPerHour result = new CostPerHour(11);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @Test
    @SuppressWarnings("all")
    void ensureThatCostPerHourIsNotEqualOtherTypeOfObject() {
        // Arrange
        String expected = "Hello World!";
        CostPerHour result = new CostPerHour(11);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    @Test
    @SuppressWarnings("all")
    void ensureThatCostPerHourIsEqualToItself() {
        // Arrange
        CostPerHour expected = new CostPerHour(10);
        CostPerHour result = expected;

        // Act, Assert
        assertEquals(expected, result);
    }

    @Test
    @SuppressWarnings("all")
    void ensureThatCostPerHourIsNotEqualToObjectFromADiferrentClass() {
        // Arrange
        CostPerHour expected = new CostPerHour(10);
        Code result = new Code(10);

        // Act, Assert
        assertNotEquals(expected, result);
    }

    /**
     * Tests for hashCode()
     */
    @Test
    void ensureThatTwoCostPerHourHaveSameHashCode() {
        // ARRANGE
        CostPerHour expected = new CostPerHour(11);
        CostPerHour result = new CostPerHour(11);

        // ACT
        int hashCodeReference = expected.hashCode();
        int hashCodeOther = result.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }


    @Test
    void ensureThatTwoCostPerHourHaveDifferentHashCode() {
        // ARRANGE
        CostPerHour expected = new CostPerHour(10);
        CostPerHour result = new CostPerHour(11);

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
        CostPerHour expected = new CostPerHour(11);
        CostPerHour result = new CostPerHour(11);

        // Act
        boolean other = expected.sameValueAs(result);

        // Assert
        assertTrue(other);
    }

    @Test
    void ensureThatTwoCostPerHourDoesNotHaveTheSameValue() {
        // Arrange
        CostPerHour expected = new CostPerHour(10);
        CostPerHour result = new CostPerHour(11);

        // Act
        boolean other = expected.sameValueAs(result);

        // Assert
        assertFalse(other);
    }
}