package org.switch2022.project.ddd.domain.model.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class CustomerTest {

    /**
     * Constructor
     */
    @SuppressWarnings("all")
    @DisplayName("Customer's name is null")
    @Test
    void ensureACustomerIsNotCreatedBecauseNameIsNull() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerName = null;

        String expected = "Customer's name can't be null.";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer(customerTaxId, customerName));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

    @SuppressWarnings("all")
    @DisplayName("Customer's tax ID is null")
    @Test
    void ensureACustomerIsNotCreatedBecauseTaxIdIsNull() {
        // ARRANGE
        TaxId customerTaxId = null;
        Name customerName = mock(Name.class);

        String expected = "Customer's tax ID can't be null.";

        // ACT
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Customer(customerTaxId, customerName));

        // ASSERT
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Customer is created")
    @Test
    void ensureACustomerIsCreated() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerName = mock(Name.class);

        // ACT
        Customer customer = new Customer(customerTaxId, customerName);

        // ASSERT
        assertNotNull(customer.getName());
        assertNotNull(customer.getTaxId());
    }

    /**
     * METHOD sameIdentityAs()
     */
    @DisplayName("Two customers have same tax ID number")
    @Test
    void ensureThatReturnsTrueIfTwoCustomersHaveSameTaxId() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerNameOne = mock(Name.class);
        Name customerNameTwo = mock(Name.class);
        Customer customerOne = new Customer(customerTaxId, customerNameOne);
        Customer customerTwo = new Customer(customerTaxId, customerNameTwo);

        boolean expected = true;

        // ACT
        boolean result = customerOne.sameIdentityAs(customerTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Two customers have different tax ID number")
    @Test
    void ensureThatReturnsFalseIfTwoCustomersHaveDifferentTaxId() {
        // ARRANGE
        TaxId customerTaxIdOne = mock(TaxId.class);
        TaxId customerTaxIdTwo = mock(TaxId.class);
        Name customerName = mock(Name.class);
        Customer customerOne = new Customer(customerTaxIdOne, customerName);
        Customer customerTwo = new Customer(customerTaxIdTwo, customerName);

        boolean expected = false;

        // ACT
        boolean result = customerOne.sameIdentityAs(customerTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("One of the customers is null")
    @Test
    void ensureThatReturnsFalseIfOneOfTheCustomersIsNull() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerNameOne = mock(Name.class);
        Customer customerOne = new Customer(customerTaxId, customerNameOne);
        Customer customerTwo = null;

        boolean expected = false;

        // ACT
        boolean result = customerOne.sameIdentityAs(customerTwo);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD hasTaxId()
     */
    @SuppressWarnings("all")
    @DisplayName("Customer's tax id matches")
    @Test
    void ensureReturnsTrueWhenCustomerTaxIdEqualsGivenId() {
        // ARRANGE
        TaxId taxId = mock(TaxId.class);
        TaxId customerTaxId = taxId;
        Name customerName = mock(Name.class);
        Customer customer = new Customer(customerTaxId, customerName);

        boolean expected = true;

        // ACT
        boolean result = customer.hasTaxId(taxId);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Customer's tax id doesn't matches")
    @Test
    void ensureReturnsFalseWhenCustomerTaxIdDoesNotEqualGivenId() {
        // ARRANGE
        TaxId taxId = mock(TaxId.class);
        TaxId customerTaxId = mock(TaxId.class);
        Name customerName = mock(Name.class);
        Customer customer = new Customer(customerTaxId, customerName);

        boolean expected = false;

        // ACT
        boolean result = customer.hasTaxId(taxId);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getName()
     */
    @DisplayName("Customer's name is retrieved successfully")
    @Test
    void ensureNameIsRetrievedSuccessfully() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerName = mock(Name.class);
        Customer customer = new Customer(customerTaxId, customerName);

        // ACT
        String result = customer.getName();

        // ASSERT
        assertNotNull(result);
    }

    /**
     * METHOD getTaxId()
     */
    @DisplayName("Customer's tax ID is retrieved successfully")
    @Test
    void ensureTaxIdRetrievedSuccessfully() {
        // ARRANGE
        TaxId customerTaxId = mock(TaxId.class);
        Name customerName = mock(Name.class);
        Customer customer = new Customer(customerTaxId, customerName);

        // ACT
        String result = customer.getTaxId();

        // ASSERT
        assertNotNull(result);
    }
}