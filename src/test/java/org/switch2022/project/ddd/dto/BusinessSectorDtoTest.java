package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorDtoTest {

    /**
     * Ensure that a BusinessSectorDto object can be instantiated with
     * the specified name and that its name attribute is set correctly.
     */
    @Test
    public void ensureBusinessSectorDto() {
        // Arrange
        String name = "AAA";

        // Act
        BusinessSectorDto dto = new BusinessSectorDto(name);

        // Assert
        assertEquals(name, dto.name);
    }

    /**
     * equals (Object toCompare)
     * Scenario 1: ensure that a BusinessSectorDto instance is equal to itself.
     */
    @Test
    void ensureSameBusinessSectorDtoEqualsItself() {
        // ARRANGE
        BusinessSectorDto reference = new BusinessSectorDto("Technology");
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
        BusinessSectorDto reference = new BusinessSectorDto("Technology");
        BusinessSectorDto other = new BusinessSectorDto("Finance");

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
        BusinessSectorDto reference = new BusinessSectorDto("Technology");
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
        BusinessSectorDto businessSectorDto = new BusinessSectorDto("Technology");
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
        BusinessSectorDto dto = new BusinessSectorDto("Technology");

        // ACT
        boolean result = dto.equals(null);

        // ASSERT
        assertFalse(result);
    }



    /**
     * Scenario 6: ensure that two BusinessSectorDto instances with the same name are equal.
     */
    @Test
    void ensureBusinessSectorDtoWithSameNameAreEqual() {
        // ARRANGE
        BusinessSectorDto dto1 = new BusinessSectorDto("Technology");
        BusinessSectorDto dto2 = new BusinessSectorDto("Technology");

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
        BusinessSectorDto dto1 = new BusinessSectorDto("Technology");
        BusinessSectorDto dto2 = new BusinessSectorDto("Finance");

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
        BusinessSectorDto dto1 = new BusinessSectorDto("");
        BusinessSectorDto dto2 = new BusinessSectorDto("");

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
        BusinessSectorDto dto = new BusinessSectorDto("Technology");
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
        BusinessSectorDto dto = new BusinessSectorDto("Technology");

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
        BusinessSectorDto reference = new BusinessSectorDto("Technology");
        BusinessSectorDto other = new BusinessSectorDto("Technology");

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
        BusinessSectorDto reference = new BusinessSectorDto("Technology");
        BusinessSectorDto other = new BusinessSectorDto("Finance");

        // ACT
        int hashCodeReference = reference.hashCode();
        int hashCodeOther = other.hashCode();

        // ASSERT
        assertNotEquals(hashCodeOther, hashCodeReference);
    }

}