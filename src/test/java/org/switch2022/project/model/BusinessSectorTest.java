package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}