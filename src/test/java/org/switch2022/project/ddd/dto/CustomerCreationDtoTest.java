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
        CustomerCreationDto customer = new CustomerCreationDto(customerName, expectedTaxId);

        // Act
        String actualTaxId = customer.getTaxIdNumber();

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
        CustomerCreationDto customer = new CustomerCreationDto(expectedName, customerTaxId);

        // Act
        String actualName = customer.getName();

        // Assert
        assertEquals(expectedName.toLowerCase(), actualName);
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
        CustomerCreationDto customer = new CustomerCreationDto(expectedName, expectedTaxId);

        // Assert
        Assertions.assertEquals(expectedTaxId, customer.getTaxIdNumber());
        Assertions.assertEquals(expectedName.toLowerCase(), customer.getName());
    }
}