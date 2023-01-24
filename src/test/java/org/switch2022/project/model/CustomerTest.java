package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        Customer reference = new Customer("ISEP");
        Customer other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
    @Test
    void ensureTwoCustomersAreNotTheSame() {
        Customer reference = new Customer("ISEP");
        Customer other = new Customer("Michael Brown Corp.");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    void ensureDifferentObjectsNotEqual() {
        Customer reference = new Customer("ISEP");
        Object other = new Object();
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }


    @Test
    public void testHashCodeCustomer() {
        Customer obj1 = new Customer("Critical");
        Customer obj2 = new Customer("Critical");
        Customer obj3 = new Customer("PortoTech");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

}




