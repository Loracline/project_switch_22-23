package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyDtoTest {
    /**
     * Method equals()
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameTypologyDtoEqualsItself() {
        // Arrange
        TypologyDto reference = new TypologyDto("typologyOne");
        TypologyDto other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoTypologyDtosAreNotTheSame() {
        // Arrange
        TypologyDto reference = new TypologyDto("typologyOne");
        TypologyDto other = new TypologyDto("typologyTwo");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if two objects from different types are not
     * the same.
     */
    @Test
    void ensureTypologyDtoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        TypologyDto reference = new TypologyDto("typologyOne");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a TypologyDto object and a null object are not the same.
     */
    @Test
    void ensureTypologyDtoDoesNotEqualNull() {
        // Arrange
        TypologyDto reference = new TypologyDto("typologyOne");
        TypologyDto other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

}