package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorDtoTest {


    @Test
    void ensureDtoIsCreatedSuccessfully() {
        // Arrange
        String name = "Finance";
        String id = "BS001";

        // Act
        BusinessSectorDto dto = new BusinessSectorDto(name, id);

        // Assert
        assertEquals(name.toLowerCase(), dto.name);
        assertEquals(id.toLowerCase(), dto.id);
    }

    /**
     * equals (Object toCompare)
     * Scenario 1: ensure that a BusinessSectorDto instance is equal to itself.
     */
    @Test
    void ensureSameBusinessSectorDtoEqualsItself() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto other = reference;
        boolean expected = true;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: ensure that two different BusinessSectorDto instances are not equal.
     */
    @Test
    void ensureTwoBusinessSectorDtoAreNotEqual() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto other = new BusinessSectorDto("Finance", "BS001");

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: ensure that a BusinessSectorDto instance is not equal to an object of a different type.
     */
    @Test
    void ensureBusinessSectorDtoNotEqualsOtherTypeObject() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology", "BS001");
        String other = "User";

        boolean expected = false;

        // ACT
        boolean result = reference.equals(other);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: case to ensure that a BusinessSectorDto instance is not equal to an object of a different type.
     */
    @Test
    void ensureBusinessSectorDtoNotEqualsDifferentObjectType() {
        // ARRANGE
        BusinessSectorDto businessSectorDto = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto differentObject = null;

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
    void ensureBusinessSectorDtoNotEqualsNull() {
        // ARRANGE
        BusinessSectorDto dto = new BusinessSectorDto("Technology", "BS001");

        // ACT
        boolean result = dto.equals(null);

        // ASSERT
        assertFalse(result);
    }



    /**
     * Scenario 6: ensure that two BusinessSectorDto instances with the same name and Id are equal.
     */
    @Test
    void ensureBusinessSectorDtoWithSameNameAreEqual() {
        // ARRANGE
        BusinessSectorDto dto1 = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto dto2 = new BusinessSectorDto("Technology", "BS001");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 7: ensure that two BusinessSectorDto instances with different names are not equal.
     */
    @Test
    void ensureBusinessSectorDtoWithDifferentNamesAreNotEqual() {
        // ARRANGE
        BusinessSectorDto dto1 = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto dto2 = new BusinessSectorDto("Finance", "BS002");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertFalse(result);
    }

    /**
     * Scenario 8: ensure that two BusinessSectorDto instances with empty values are considered equal.
     */
    @Test
    void ensureBusinessSectorDtoWithEmptyValuesAreEqual() {
        // ARRANGE
        BusinessSectorDto dto1 = new BusinessSectorDto("", "");
        BusinessSectorDto dto2 = new BusinessSectorDto("", "");

        // ACT
        boolean result = dto1.equals(dto2);

        // ASSERT
        assertTrue(result);
    }

    /**
     * Scenario 9: ensure that a BusinessSectorDto instance is not equal to an object of a different class.
     */
    @Test
    void ensureBusinessSectorDtoNotEqualsDifferentClassObject() {
        // ARRANGE
        BusinessSectorDto dto = new BusinessSectorDto("Technology", "BS001");
        Object other = new Object();

        // ACT
        boolean result = dto.equals(other);

        // ASSERT
        assertFalse(result);
    }

    /**
     * Scenario 10: ensure that a BusinessSectorDto instance is equal to itself.
     */
    @Test
    void ensureBusinessSectorDtoEqualsItself() {
        // ARRANGE
        BusinessSectorDto dto = new BusinessSectorDto("Technology", "BS001");

        // ACT
        boolean result = dto.equals(dto);

        // ASSERT
        assertTrue(result);
    }


    /**
     * hashCode()
     * Scenario 1: ensure that two BusinessSectorDto instances with the same values have the same hash code.
     */
    @Test
    void ensureBusinessSectorDtoHaveSameHashCode() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto other = new BusinessSectorDto("Technology", "BS001");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertEquals(hashCodeOther, hashCodeReference);
    }

    /**
     * Scenario 2: ensure that two BusinessSectorDto instances with different values have different hash codes.
     */
    @Test
    void ensureBusinessSectorDtoHaveDifferentHashCode() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology", "BS001");
        BusinessSectorDto other = new BusinessSectorDto("Finance", "BS001");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

}