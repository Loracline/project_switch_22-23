package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerContainerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Customer customerOne, customerTwo;
    List<Customer> customers;
    CustomerContainer customerContainerReference;

    @BeforeEach
    void setUp() {

        customerOne = new Customer("ACD Laboratories");
        customerTwo = new Customer("Airgas");

        customers = new ArrayList<>();
        customers.add(customerOne);
        customers.add(customerTwo);

        customerContainerReference = new CustomerContainer(customers);

    }

    @AfterEach
    void tearDown() {
        customerOne = null;
        customerTwo = null;
        customers.clear();
        customerContainerReference = null;

    }

    @Test
    void ensureCustomerIsntAddedSuccessfully_CaseInsensitive() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("airgas");
        //Assert
        assertEquals(expected, result);
    }


    @Test
    void ensureAddCustomerToCustomersListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = customerContainerReference.addCustomer("Michael Brown Corp.");
        //Assert
        assertEquals(expected, result);

    }


    @Test
    void ensureAddCustomerToCustomersListUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("airgas");
        //Assert
        assertEquals(expected, result);

    }


    @Test
    void ensureCustomerIsRetrievedSuccessfully() {
        //ARRANGE
        //Create customer to compare with
        Customer expected = new Customer("ACD Laboratories");
        //ACT
        Customer result = customerContainerReference.getCustomerByName("ACD Laboratories");
        //ASSERT
        assertEquals(expected, result);
    }


    @Test
    void ensureThatGetCustomerReturnsNull() {
        //ACT
        Customer result = customerContainerReference.getCustomerByName("Michael Brown");
        //ASSERT
        assertNull(result);
    }

    @Test
    void ensureFirstIndexIsLowerThanSecond() {
        assertTrue(CustomerContainer.isLower(1,2));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanFirst() {
        assertFalse(CustomerContainer.isLower(2,1));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanSecond() {
        assertFalse(CustomerContainer.isLower(2,2));
    }
}


