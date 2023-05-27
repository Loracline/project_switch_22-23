package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJpaTest {

    @DisplayName("Tax ID number retrieved in String format")
    @Test
    void ensureCustomerTaxIdIsRetrievedSuccessfully() {
        // Arrange
        String expected = "514024054";
        CustomerJpa customerJpa = new CustomerJpa(expected, "Partilha Cortesia, Lda.");

        // Act
        String result = customerJpa.getCustomerTaxId();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Name retrieved in String format")
    @Test
    void ensureCustomerNameIsRetrievedSuccessfully() {
        // Arrange
        String expected = "Partilha Cortesia, Lda.";
        CustomerJpa customerJpa = new CustomerJpa("514024054", expected);

        // Act
        String result = customerJpa.getCustomerName();

        // Assert
        assertEquals(expected, result);
    }
}