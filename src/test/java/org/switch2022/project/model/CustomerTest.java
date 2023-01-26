package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;



class CustomerTest {

    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        Customer reference = new Customer("ISEP", "222333444");
        Customer other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
    @Test
    void ensureTwoCustomersAreNotTheSame() {
        Customer reference = new Customer("ISEP", "222333444");
        Customer other = new Customer("Michael Brown Corp.", "200000000");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    void ensureDifferentObjectsNotEqual() {
        Customer reference = new Customer("ISEP", "222333444");
        Object other = new Object();
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        Customer reference = new Customer("ISEP", "222333444");
        String other = "222333444";
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }


    @Test
    public void testHashCodeCustomer() {
        Customer obj1 = new Customer("Critical", "200000000");
        Customer obj2 = new Customer("Critical", "200000000");
        Customer obj3 = new Customer("PortoTech", "222333444");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    @Test
    void getCustomerNameSuccessfully() {
        Customer customer = new Customer("john",null);
        String expected = "john";
        String result = customer.getCustomerName();
        assertEquals(expected, result);
    }


    @Test
    void getCustomerNameNotSuccessfully() {
        Customer customer = new Customer("John",null);
        String expected = "Mary";
        String result = customer.getCustomerName();
        assertNotEquals(expected, result);
    }
}




