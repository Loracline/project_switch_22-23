package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyTest {

    /**
     * Tests for the equals() method.
     */

    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        boolean expected = true;

        //Act
        boolean result = reference.equals(reference);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatTwoProjectTypologiesAreNotTheSame() {
        //Arrange
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        ProjectTypology other = new ProjectTypology("Fixed time and materials");
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatProjectTypologyDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        String other = "Fixed Cost";
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureSameObjectDoesNotEqualNull() {
        //Arrange
        ProjectTypology reference = new ProjectTypology("Fixed Cost");
        ProjectTypology other = null;
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(expected);
    }

    /**
     * Tests for the hashCode() method.
     */
    @Test
    public void testHashCodeProjectTypology() {
        //Arrange
        ProjectTypology obj1 = new ProjectTypology("Fixed Cost");
        ProjectTypology obj2 = new ProjectTypology("Fixed Cost");
        ProjectTypology obj3 = new ProjectTypology("Fixed time and materials");

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());

        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

}