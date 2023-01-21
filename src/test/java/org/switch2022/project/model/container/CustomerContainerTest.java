package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Customer customerOne, customerTwo;
    List<Customer> customers;
    CustomerContainer customerContainerReference;

    @BeforeEach
    void setUp() {
        /*
          Costumers created.
         */
        customerOne = new Customer("ACD Laboratories");
        customerTwo = new Customer("Airgas");

        /*
          Container of costumers created.
         */
        customers = new ArrayList<>();
        customerContainerReference = new CustomerContainer(customers);

        /*
          Costumers added to the Container.
         */
        customers.add(customerOne);
        customers.add(customerTwo);
    }

    @AfterEach
    void tearDown() {
        customerOne = null;
        customerTwo = null;
        customers.clear();
        customerContainerReference = null;
    }

    /**
     * Testing if one can add costumer to the container.
     */
    @Test
    void ensureCustomerIsNotAddedSuccessfully_CaseInsensitive() {
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

    /**
     * Testing if one can get customer by searching the name.
     */
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
}


