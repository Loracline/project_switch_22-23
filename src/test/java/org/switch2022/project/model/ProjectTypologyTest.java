package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyTest {
    @Test
    void ensureSameObjectEqualsItself() {
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        ProjectTypology other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
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
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        String other = new String("Fixed Cost");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

}