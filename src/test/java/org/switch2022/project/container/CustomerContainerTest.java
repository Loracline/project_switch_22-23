package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.factories.FactoryCustomer;
import org.switch2022.project.factories.IFactoryCustomer;
import org.switch2022.project.model.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CustomerContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    CustomerContainer customerContainer;
    IFactoryCustomer iFactoryCustomer;

    @BeforeEach
    void setUp() {

        //Customer Container
        customerContainer = new CustomerContainer();
        iFactoryCustomer = new FactoryCustomer();
        customerContainer.addCustomer("ISEP", "222333444", iFactoryCustomer);
        customerContainer.addCustomer("PortoTech", "222333445", iFactoryCustomer);

    }


    @AfterEach
    void tearDown() {
        customerContainer = null;
    }


    /**
     * METHOD addCustomer(customerName, customerNif, iFactoryCustomer) adds a Customer
     * to the list of Customers.
     * <p>
     * Scenario 1: Check if Customer can be added with a Nif with less than nine digits
     * . Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasLessThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical", "2223334",
                iFactoryCustomer);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Check if Customer can be added with a Nif with more than nine digits
     * . Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasMoreThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical", "2223334488",
                iFactoryCustomer);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Check if Customer can be added with a Nif that contains
     * characters. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasCharacters() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical", "22233344@",
                iFactoryCustomer);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Check if Customer can be added with valid argument. Should return
     * TRUE.
     */

    @Test
    void ensureThatCustomerIsAddedToCustomersListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = customerContainer.addCustomer("Critical", "142356678",
                iFactoryCustomer);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Check if Customer that already exists in the Customer List can be
     * added again. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseItIsAlreadyThere() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("ISEP", "222333444",
                iFactoryCustomer);
        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 6: Check if Customer can be added with empty name. Should return FALSE.
     */

    @Test
    void ensureThatCustomerCustomerIsNotAddedToToCustomersBecauseCustomerNameIsEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("", "222333445", iFactoryCustomer);
        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 7: Check if Customer can be added with a Nif that has been already
     * registered. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseNIFAlreadyExist() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("", "222333444", iFactoryCustomer);
        //Assert
        assertEquals(expected, result);

    }

    /**
     * Scenario 8: Check if Customer can be added with an empty Nif. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseCustomerNIFIsEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("Critical", "", iFactoryCustomer);
        //Assert
        assertEquals(expected, result);

    }

    /**
     * METHOD getCustomer(customerName, nif) retrieves a Customer.
     * <p>
     * Scenario 1: customer is retrieved successfully.
     */

    @Test
    void ensureThatCustomerIsRetrievedSuccessfully() {
        Customer expected = iFactoryCustomer.createCustomer("ISEP", "222333444");
        Customer result = customerContainer.getCustomer("222333444");
        assertEquals(expected, result);
    }

    /**
     * Scenario 1: customer is not retrieved because it doesn't exist.
     */

    @Test
    void ensureThatCustomerIsNotRetrieved() {
        Customer expected = iFactoryCustomer.createCustomer("john", "222535777");
        Customer result = customerContainer.getCustomer("222333444");
        assertNotEquals(expected, result);
    }

    /**
     * Tests with isolation:
     * <p>
     * METHOD addCustomer(customerName, customerNif, iFactoryCustomer) adds a Customer
     * to the list of Customers.
     * <p>
     * Scenario 1: Check if Customer can be added with a Nif with less than nine digits
     * . Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasLessThanNineDigits_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("critical", "2223334",
                factoryCustomer);
        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 2: Check if Customer can be added with a Nif with more than nine digits
     * . Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasMoreThanNineDigits_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("critical", "2223334488",
                factoryCustomer);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 3: Check if Customer can be added with a Nif that contains
     * characters. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedSuccessfullyBecauseNifHasCharacters_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act

        boolean result = customerContainerIsolated.addCustomer("critical", "22233344@",
                factoryCustomer);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: Check if Customer can be added with valid argument. Should return
     * TRUE.
     */

    @Test
    void ensureThatCustomerIsAddedToCustomersListSuccessfully_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("Critical", "142356678",
                factoryCustomer);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 5: Check if Customer that already exists in the Customer List can be
     * added again. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseItIsAlreadyThere_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        Customer customerDouble = mock(Customer.class);
        customerContainerIsolated.addCustomerToList(customerDouble);

        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);
        when(factoryCustomer.createCustomer(any(),any())).thenReturn(customerDouble);
        when(customerDouble.hasCustomerNif(any())).thenReturn(true);

        //Act
        boolean result = customerContainerIsolated.addCustomer("ISEP", "222333444",
                factoryCustomer);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 6: Check if Customer can be added with empty name. Should return FALSE.
     */

    @Test
    void ensureThatCustomerCustomerIsNotAddedToToCustomersBecauseCustomerNameIsEmpty_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("", "222333445",
                factoryCustomer);

        //Assert
        assertFalse(result);

    }

    /**
     * Scenario 7: Check if Customer can be added with a Nif that has been already
     * registered. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseNIFAlreadyExist_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("", "222333444",
                factoryCustomer);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 8: Check if Customer can be added with an empty Nif. Should return FALSE.
     */

    @Test
    void ensureThatCustomerIsNotAddedToCustomersListBecauseCustomerNIFIsEmpty_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomer = mock(FactoryCustomer.class);

        //Act
        boolean result = customerContainerIsolated.addCustomer("Critical",
                "", factoryCustomer);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD getCustomer(customerName, nif) retrieves a Customer.
     * <p>
     * Scenario 1: customer is retrieved successfully.
     */

    @Test
    void ensureThatCustomerIsRetrievedSuccessfully_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        Customer customerDouble = mock(Customer.class);
        customerContainerIsolated.addCustomerToList(customerDouble);
        when(customerDouble.hasCustomerNif(any())).thenReturn(true);

        //Act
        Customer result = customerContainerIsolated.getCustomer("222333444");

        //Assert
        assertEquals(customerDouble, result);
    }

    /**
     * Scenario 1: customer is not retrieved because it doesn't exist.
     */

    @Test
    void ensureThatCustomerIsNotRetrieved_WithIsolation() {
        //Arrange
        CustomerContainer customerContainerIsolated = new CustomerContainer();
        IFactoryCustomer factoryCustomerDouble = mock(FactoryCustomer.class);
        Customer customerDouble = mock(Customer.class);
        when(factoryCustomerDouble.createCustomer(any(),any())).thenReturn(customerDouble);
        when(customerDouble.hasCustomerNif(any())).thenReturn(false);

        //Act
        Customer result = customerContainerIsolated.getCustomer("222333444");

        //Assert
        assertNotEquals(customerDouble, result);
    }
}


