package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorCreationDtoTest {
    /**
     * Ensure Business Sector Creation Dto works.
     */
    @Test
    public void ensureBusinessSectorCreationDto() {
        // Arrange
        String name = "AAA";

        // Act
        BusinessSectorCreationDto dto = new BusinessSectorCreationDto(name);

        // Assert
        assertEquals(name, dto.name);
    }

    /**
     * equals (Object toCompare)
     * Scenario 1: ensure that a BusinessSectorCreationDto instance is equal to itself.
     */
    @Test
    void ensureSameBusinessSectorCreationDtoEqualsItself() {
        // ARRANGE
        BusinessSectorCreationDto reference = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto other = reference;

        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that two different BusinessSectorCreationDto instances are not equal.
     */
    @Test
    void ensureTwoBusinessSectorCreationDtoAreNotEqual() {
        // ARRANGE
        BusinessSectorCreationDto reference = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto other = new BusinessSectorCreationDto("Finance");

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: ensure that a BusinessSectorCreationDto instance is not equal to an object of a different type.
     */
    @Test
    void ensureBusinessSectorCreationDtoNotEqualsOtherTypeObject() {
        // ARRANGE
        BusinessSectorCreationDto reference = new BusinessSectorCreationDto("Technology");
        String other = "User";

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: case to ensure that a BusinessSectorCreationDto instance is not equal to an object of a different type.
     */
    @Test
    void ensureBusinessSectorCreationDtoNotEqualsDifferentObjectType() {
        // ARRANGE
        BusinessSectorCreationDto businessSectorDto = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto differentObject = null;

        boolean expected = false;

        // ACT
        boolean result = businessSectorDto.equals(differentObject);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Test case to ensure that a BusinessSectorCreationDto instance is not equal to null.
     */
    @Test
    void ensureBusinessSectorCreationDtoNotEqualsNull() {
        // ARRANGE
        BusinessSectorCreationDto dto = new BusinessSectorCreationDto("Technology");

        // ACT
        boolean result = dto.equals(null);

        // ASSERT
        assertFalse(result);
    }



    /**
     * Scenario 6: ensure that two BusinessSectorCreationDto instances with the same name are equal.
     */
    @Test
    void ensureBusinessSectorCreationDtoWithSameNameAreEqual() {
        // ARRANGE
        BusinessSectorCreationDto dto1 = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto dto2 = new BusinessSectorCreationDto("Technology");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 7: ensure that two BusinessSectorCreationDto instances with different names are not equal.
     */
    @Test
    void ensureBusinessSectorCreationDtoWithDifferentNamesAreNotEqual() {
        // ARRANGE
        BusinessSectorCreationDto dto1 = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto dto2 = new BusinessSectorCreationDto("Finance");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertFalse(result);
    }

    /**
     * Scenario 8: ensure that two BusinessSectorCreationDto instances with empty values are considered equal.
     */
    @Test
    void ensureBusinessSectorCreationDtoWithEmptyValuesAreEqual() {
        // ARRANGE
        BusinessSectorCreationDto dto1 = new BusinessSectorCreationDto("");
        BusinessSectorCreationDto dto2 = new BusinessSectorCreationDto("");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 9: ensure that a BusinessSectorCreationDto instance is not equal to an object of a different class.
     */
    @Test
    void ensureBusinessSectorCreationDtoNotEqualsDifferentClassObject() {
        // ARRANGE
        BusinessSectorCreationDto dto = new BusinessSectorCreationDto("Technology");
        Object other = new Object();

        // ACT
        boolean result = dto.equals(other);

        // ASSERT
        assertFalse(result);
    }

    /**
     * Scenario 10: ensure that a BusinessSectorCreationDto instance is equal to itself.
     */
    @Test
    void ensureBusinessSectorCreationDtoEqualsItself() {
        // ARRANGE
        BusinessSectorCreationDto dto = new BusinessSectorCreationDto("Technology");

        // ACT
        boolean result = dto.equals(dto);

        // ASSERT
        assertTrue(result);
    }


    /**
     * hashCode()
     * Scenario 1: ensure that two BusinessSectorCreationDto instances with the same values have the same hash code.
     */
    @Test
    void ensureBusinessSectorCreationDtoHaveSameHashCode() {
        // ARRANGE
        BusinessSectorCreationDto reference = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto other = new BusinessSectorCreationDto("Technology");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Scenario 2: ensure that two BusinessSectorCreationDto instances with different values have different hash codes.
     */
    @Test
    void ensureBusinessSectorCreationDtoHaveDifferentHashCode() {
        // ARRANGE
        BusinessSectorCreationDto reference = new BusinessSectorCreationDto("Technology");
        BusinessSectorCreationDto other = new BusinessSectorCreationDto("Finance");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }
}

