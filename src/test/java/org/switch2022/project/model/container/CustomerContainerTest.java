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
        customerOne = new Customer("ISEP", "222333444");
        customerTwo = new Customer("PortoTech", "222333445");

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
    void ensureCustomerIsNotAddedSuccessfully_LessThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("critical","2223334" );
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureCustomerIsNotAddedSuccessfully_MoreThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("critical","2223334488" );
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddCustomerToCustomersListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = customerContainerReference.addCustomer("Critical", "142356678");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = customerContainerReference.addCustomer("ISEP", "222333444");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerNameEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("", "222333445");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerSameNIF() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = customerContainerReference.addCustomer("", "222333444");
        //Assert
        assertEquals(expected, result);

    }


}


