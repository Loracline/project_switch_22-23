package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectTypologyTest {
    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        boolean expected = true;
        boolean result = reference.equals(reference);
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoProfilesAreNotTheSame() {
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        ProjectTypology other = new ProjectTypology("Fixed time and materials");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        String other = "Fixed Cost";
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
    @Test
    public void testHashCodeProjectTypology() {
        ProjectTypology obj1 = new ProjectTypology("Fixed Cost");
        ProjectTypology obj2 = new ProjectTypology("Fixed Cost");
        ProjectTypology obj3 = new ProjectTypology("Fixed time and materials");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

}