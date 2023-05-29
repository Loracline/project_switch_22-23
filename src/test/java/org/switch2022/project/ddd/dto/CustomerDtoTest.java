package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerDtoTest {

    @DisplayName("DTO created")
    @Test
    void ensureDtoIsCreatedSuccessfully() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514 024 054";

        // Act
        CustomerDto dto = new CustomerDto(name, taxIdNumber);

        // Assert
        assertEquals(name.toLowerCase(), dto.name);
        assertEquals(taxIdNumber.toLowerCase(), dto.taxIdNumber);
    }

    /**
     * METHOD equals()
     */
    @SuppressWarnings("all")
    @DisplayName("Same object equals itself")
    @Test
    void ensureSameDtoEqualsItself() {
        // Arrange
        CustomerDto reference = new CustomerDto("Partilha Cortesia, Lda.", "514024054");
        CustomerDto other = reference;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Two DTOs are equal")
    @Test
    void ensureTwoDtosAreEqual() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514024054";

        CustomerDto reference = new CustomerDto(name, taxIdNumber);
        CustomerDto other = new CustomerDto(name, taxIdNumber);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Two DTOs are different")
    @Test
    void ensureTwoDtosAreDifferent() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumberOne = "514024054";
        String taxIdNumberTwo = "514024053";

        CustomerDto reference = new CustomerDto(name, taxIdNumberOne);
        CustomerDto other = new CustomerDto(name, taxIdNumberTwo);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    @SuppressWarnings("all")
    @DisplayName("DTO does not equal null")
    @Test
    void ensureDtoDoesNotEqualNull() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514024054";

        CustomerDto reference = new CustomerDto(name, taxIdNumber);
        CustomerDto other = null;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    @SuppressWarnings("all")
    @DisplayName("DTO does not equal other type of object")
    @Test
    void ensureDtoDoesNotEqualOtherTypeOfObject() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514024054";

        CustomerDto reference = new CustomerDto(name, taxIdNumber);
        Customer other = mock(Customer.class);

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertFalse(result);
    }

    /**
     * METHOD hashcode()
     */
    @DisplayName("Two DTOs have same hashcode")
    @Test
    void ensureTwoEqualDtosHaveSameHashcode() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514024054";

        CustomerDto reference = new CustomerDto(name, taxIdNumber);
        CustomerDto other = new CustomerDto(name, taxIdNumber);

        int expected = other.hashCode();

        // Act
        int result = reference.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two DTOs have different hashcode")
    @Test
    void ensureTwoDifferentDtosHaveDifferentHashcode() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumberOne = "514024054";
        String taxIdNumberTwo = "514024053";

        CustomerDto reference = new CustomerDto(name, taxIdNumberOne);
        CustomerDto other = new CustomerDto(name, taxIdNumberTwo);

        int expected = other.hashCode();

        // Act
        int result = reference.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }
}