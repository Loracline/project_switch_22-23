package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreationDtoTest {

    /**
     * Method: getCustomerTaxId()
     * Scenario 1: ensure to get Customer ID.
     */
    @Test
    public void ensureGetCustomerTaxId_ShouldReturnCorrectTaxId() {
        // Arrange
        String expectedTaxId = "123456789";
        String customerName = "John Doe";
        CustomerCreationDto customer = new CustomerCreationDto(expectedTaxId, customerName);

        // Act
        String actualTaxId = customer.getCustomerTaxId();

        // Assert
        assertEquals(expectedTaxId, actualTaxId);
    }
    /**
     * Method: getCustomerName()
     * Scenario 1: ensure to get Customer Name.
     */
    @Test
    public void ensureGetCustomerName_ShouldReturnCorrectName() {
        // Arrange
        String customerTaxId = "123456789";
        String expectedName = "John Doe";
        CustomerCreationDto customer = new CustomerCreationDto(customerTaxId, expectedName);

        // Act
        String actualName = customer.getCustomerName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    /**
     * Method: CustomerCreationDto().
     * Scenario 1: ensure
     */
    @Test
    public void ensureConstructorCreatesDto() {
        // Arrange
        String expectedTaxId = "123456789";
        String expectedName = "John Doe";

        // Act
        CustomerCreationDto customer = new CustomerCreationDto(expectedTaxId, expectedName);

        // Assert
        Assertions.assertEquals(expectedTaxId, customer.getCustomerTaxId());
        Assertions.assertEquals(expectedName, customer.getCustomerName());
    }
}