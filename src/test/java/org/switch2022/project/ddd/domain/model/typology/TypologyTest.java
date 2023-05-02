package org.switch2022.project.ddd.domain.model.typology;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyTest {

    /**
     * Tests for the equals() method.
     *
     * Scenario 1: object equals itself, should return true.
     */

    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology reference = new Typology(8, nameDouble);
        boolean expected = true;

        //Act
        boolean result = reference.equals(reference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: two typology objects are not the same. Should return false.
     */

    @Test
    void ensureThatTwoProjectTypologiesAreNotTheSame() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology reference = new Typology(8, nameDouble);
        Typology other = new Typology(6, nameDouble);
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: typology object does not equal another type of object. Should return
     * false.
     */

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatTypologyDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology reference = new Typology(8, nameDouble);
        String other = "Fixed Cost";
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: object does not equal null. Should return false
     */
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology reference = new Typology(8, nameDouble);
        ProjectTypology other = null;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests for the hashCode() method.
     */
    @Test
    public void testHashCodeProjectTypology() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology obj1 = new Typology(8, nameDouble);
        Typology obj2= new Typology(8, nameDouble);
        Typology obj3 = new Typology(10, nameDouble);

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());

        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    /**
     * Method getTypologyName() return typology name. Should return a String.
     */

    @Test
    public void ensureThatTypologyNameISRetrievedSuccessfully(){
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology typology = new Typology(8, nameDouble);
        String expected = "fishing";
        when(nameDouble.getName()).thenReturn(expected);

        //Act
        String result = typology.getTypologyName();

        //Assert
        assertEquals(expected, result);
    }

}