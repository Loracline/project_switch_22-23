package org.switch2022.project.ddd.domain.model.typology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.value_object.Name;

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
        Typology other = null;

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
     * Method getTypologyName() returns typology name. Should return a String.
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

    /**
     * Method getTypologyId() returns typology id. Should return a String.
     */

    @Test
    public void ensureThatTypologyIdISRetrievedSuccessfully(){
        Name nameDouble = mock(Name.class);
        Typology typology = new Typology(8, nameDouble);
        String expected = "pt008";

        String result = typology.getTypologyId();

        assertEquals(expected, result);
    }

    /**
     * Constructor tests.
     *
     * Scenario 1: Typology is not created when the typologyNumber is negative. Should
     * throw an exception.
     */
    @Test
    public void ensureThatTypologyIsNotCreatedWithNullNumber() {
        Name nameDouble = mock(Name.class);
        String expected = "The typology id must not be negative";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Typology(-2, nameDouble));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Scenario 2: Typology is not created when the typologyName is null. Should
     * throw an exception.
     */
    @Test
    public void ensureThatTypologyIsNotCreatedWithNullName() {
        String expected = "Typology name can't be null";

        // Act
        IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Typology(1, null));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD sameIdentityAs()
     * <br>
     * Scenario 1: Check if two instances of Typology are equal if the value of their
     * typologyId are the same.
     */

    @Test
    void ensureThatTwoTypologiesAreTheSame() {
        //Arrange
        Name nameDouble = mock(Name.class);
        Typology reference = new Typology(8, nameDouble);
        Typology other = new Typology(8, nameDouble);
        boolean expected = true;

        //Act
        boolean result = reference.sameIdentityAs(other);

        //Assert
        assertEquals(expected, result);
    }
}