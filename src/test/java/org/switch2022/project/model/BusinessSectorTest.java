package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BusinessSectorTest {
    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        BusinessSector reference = new BusinessSector("fishing");
        BusinessSector other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoBusinessSectorsAreNotTheSame() {
        BusinessSector reference = new BusinessSector("mining");
        BusinessSector other = new BusinessSector("farming");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        BusinessSector reference = new BusinessSector("mining");
        String other = "mining";
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    public void testHashCodeBusinessSector() {
        BusinessSector obj1 = new BusinessSector("fishing");
        BusinessSector obj2 = new BusinessSector("fishing");
        BusinessSector obj3 = new BusinessSector("farming");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }


    @Test
    void getBusinessSectorNameSuccessfully() {
    BusinessSector businessSector = new BusinessSector("fishing");
    String expected = businessSector.getBusinessSectorName();
    String result = "fishing";
    assertEquals(expected, result);
    }

    @Test
    void getBusinessSectorNameNotSuccessfully() {
        BusinessSector businessSector = new BusinessSector("fishing");
        String expected = businessSector.getBusinessSectorName();
        String result = "mining";
        assertNotEquals(expected, result);
    }
}