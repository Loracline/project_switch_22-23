package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypologyCreationDtoTest {

    /**
     * Ensure Typology Creation Dto works.
     */
    @Test
    public void ensureTypologyCreationDto() {
        // Arrange
        String name = "typologyOne";

        // Act
        TypologyCreationDto dto = new TypologyCreationDto(name);

        // Assert
        assertEquals(name, dto.typologyName);
    }

    /**
     * Method equals()
     * Scenario 1: Verify if the same object equals itself.
     * Extected return:true.
     */
    @Test
    void ensureSameTypologyCreationDtoEqualsItself() {
        // Arrange
        TypologyCreationDto reference = new TypologyCreationDto("typologyOne");
        TypologyCreationDto other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     * Extected return: false.
     */
    @Test
    void ensureTwoTypologyCreationDtosAreNotTheSame() {
        // Arrange
        TypologyCreationDto reference = new TypologyCreationDto("typologyOne");
        TypologyCreationDto other = new TypologyCreationDto("typologyTwo");

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Verify if two objects from different types are not
     * the same.
     * Extected return: false.
     */
    @Test
    void ensureTypologyCreationDtoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        TypologyCreationDto reference = new TypologyCreationDto("typologyOne");
        String other = "User";

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Verify if a TypologyDto object and a null object are not the same.
     * Extected return: false.
     */
    @Test
    void ensureTypologyCreationDtoDoesNotEqualNull() {
        // Arrange
        TypologyCreationDto reference = new TypologyCreationDto("typologyOne");
        TypologyCreationDto other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * Method: getTypologyName()
     * Scenario 1: returns the typology name.
     * Extected return: the correct typology name.
     */
    @Test
    void getTypologyName() {
        //Arrange
        String reference = "typologyOne";
        TypologyCreationDto typologyCreationDto = new TypologyCreationDto(reference);

        //Act
        String result = typologyCreationDto.getTypologyName();

        //Assert
        assertEquals(reference, result);
    }
}