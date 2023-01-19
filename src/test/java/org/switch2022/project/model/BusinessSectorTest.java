package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorTest {
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
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        BusinessSector reference = new BusinessSector("mining");
        String other = new String("mining");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
}