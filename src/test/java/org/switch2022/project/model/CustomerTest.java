package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


    /**
     * Testing the equals() method.
     */
    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        Customer reference = new Customer("ISEP", "222333444");
        Customer other = reference;
        boolean expected = true;
        //Act
        boolean result = reference.equals(other);
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void ensureThatTwoCustomersAreNotTheSame() {
        //Arrange
        Customer reference = new Customer("ISEP", "222333444");
        Customer other = new Customer("PortoTech", "222333445");
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatDifferentObjectsAreNotEqual() {
        //Arrange
        Customer reference = new Customer("ISEP", "222333444");
        Object other = new Object();
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatObjectDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        Customer reference = new Customer("ISEP", "222333444");
        String other = "222333444";
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSameObjectDoesNotEqualNull() {
        //Arrange
        Customer reference = new Customer("ISEP", "222333444");
        Customer other = null;
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void ensureThatTwoCustomersAreTheSame() {
        //Arrange
        Customer obj1 = new Customer("Critical", "200000000");
        Customer obj2 = new Customer("Critical", "200000000");
        boolean expected = true;

        //Act
        boolean result = obj1.equals(obj2);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests for hashCode() method.
     */


    @Test
    public void testHashCodeCustomer() {
        //Arrange
        Customer obj1 = new Customer("Critical", "200000000");
        Customer obj2 = new Customer("Critical", "200000000");
        Customer obj3 = new Customer("PortoTech", "222333444");

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());

        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    /**
     * Test to verify if a CustomerName is successfully retrieved. Should return CustomerName.
     */

    @Test
    void ensureThatCustomerNameIsSuccessfullyRetrieved() {
        //Arrange
        Customer customer = new Customer("john",null);
        String expected = "john";

        //Act
        String result = customer.getCustomerName();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify if a CustomerName is successfully retrieved. Should return false.
     */

    @Test
    void ensureThatCustomerNameNotIsNotRetrieved() {
        //Arrange
        Customer customer = new Customer("John",null);
        String expected = "Mary";

        //Act
        String result = customer.getCustomerName();

        //Assert
        assertNotEquals(expected, result);
    }

    @Test
    void ensureThatCustomerNifNotIsRetrieved() {
        Customer customer = new Customer("john","111222333");
        String expected = "111222333";

        //Act
        String result = customer.getCustomerNif();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatCustomerNifNotIsNotRetrieved() {
        Customer customer = new Customer("john","111222333");
        String expected = "111232333";

        //Act
        String result = customer.getCustomerNif();

        //Assert
        assertNotEquals(expected, result);
    }
}




