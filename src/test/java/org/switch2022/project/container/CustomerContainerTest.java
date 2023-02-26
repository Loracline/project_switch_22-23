package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.CustomerContainer;
import org.switch2022.project.model.Customer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CustomerContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    CustomerContainer customerContainer;

    @BeforeEach
    void setUp() {

        //Customer Container
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("ISEP", "222333444");
        customerContainer.addCustomer("PortoTech", "222333445");

    }


    @AfterEach
    void tearDown() {
        customerContainer = null;
    }


    /**
     * Testing if one can add costumer to the container.
     */

    @Test
    void ensureCustomerIsNotAddedSuccessfully_LessThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical","2223334" );
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureCustomerIsNotAddedSuccessfully_MoreThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical","2223334488" );
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureCustomerIsNotAddedSuccessfullyNifWithCharacters() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("critical","22233344@");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddCustomerToCustomersListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = customerContainer.addCustomer("Critical", "142356678");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("ISEP", "222333444");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerNameEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("", "222333445");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerSameNIF() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("", "222333444");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerEmptyNIF() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainer.addCustomer("Critical", "");
        //Assert
        assertEquals(expected, result);

    }
    @Test
    void ensureCustomerIsRetrieved() {
        Customer expected = new Customer("john","222555777");
        Customer result = customerContainer.getCustomer("john","222555777");
        assertEquals(expected,result);
    }

    @Test
    void ensureCustomerIsNotRetrieved() {
        Customer expected = new Customer("john","222535777");
        Customer result = customerContainer.getCustomer("john","222555777");
        assertNotEquals(expected,result);
    }

}


