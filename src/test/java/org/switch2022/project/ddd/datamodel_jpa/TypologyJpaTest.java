package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

class TypologyJpaTest {
    /**
     * Tests for the equals() method.
     *
     * Scenario 1: object equals itself, should return true.
     */
    @DisplayName("Equals test for TypologyJpa: typology equals itself")
    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        TypologyJpa reference = new TypologyJpa("PT003", "Gaming");
        boolean expected = true;

        //Act
        boolean result = reference.equals(reference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: two typology objects are not the same. Should return false.
     */
    @DisplayName("Equals test for TypologyJpa two typologies are the same")
    @Test
    void ensureThatTwoProjectTypologiesAreNotTheSame() {
        //Arrange
        TypologyJpa reference = new TypologyJpa("PT003", "Gaming");
        TypologyJpa other = new TypologyJpa("PT004", "Crocheting");
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
    @DisplayName("Equals test for TypologyJpa: not equals another object")
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatTypologyDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        TypologyJpa reference = new TypologyJpa("PT003", "Gaming");
        String other = "Fixed Cost";

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: object does not equal null. Should return false
     */
    @DisplayName("Equals test for TypologyJpa: not null")
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        //Arrange
        TypologyJpa reference = new TypologyJpa("PT003", "Gaming");
        Typology other = null;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: object equals another with same id.
     */
    @DisplayName("Equals test for TTypologyJpa: same id")
    @Test
    void ensureThatObjectEqualsAnotherWithSameId() {
        //Arrange
        TypologyJpa reference = new TypologyJpa("PT003", "Gaming");
        TypologyJpa other = new TypologyJpa("PT003", "Gaming");

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests for the hashCode() method.
     */
    @DisplayName("Hashcode test for TTypologyJpa")
    @Test
    public void testHashCodeProjectTypology() {
        //Arrange
        TypologyJpa obj1 = new TypologyJpa("PT003", "Gaming");
        TypologyJpa obj2 = new TypologyJpa("PT003", "Gaming");
        TypologyJpa obj3 = new TypologyJpa("PT004", "Photography");

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());

        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    /**
     * Test for method getTypologyId().
     */
    @DisplayName("TypologyId is retrieved successfully")
    @Test
    void ensureThatTypologyIdISRetrieved() {
        // Arrange
        TypologyJpa typologyJpa = new TypologyJpa("PT003", "Gaming");
        String expected = "PT003";

        // Act
        String result = typologyJpa.getTypologyId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for method getTypologyMane().
     */
    @DisplayName("TypologyName is retrieved successfully")
    @Test
    void ensureThatTypologyNameISRetrieved() {
        // Arrange
        TypologyJpa typologyJpa = new TypologyJpa("PT003", "Gaming");
        String expected = "Gaming";

        // Act
        String result = typologyJpa.getTypologyName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for no args constructor.
     */
    @DisplayName("Empty Constructor Testing")
    @Test
    void testEmptyConstructor() {
        // Create an instance using the empty constructor
        TypologyJpa typologyJpa = new TypologyJpa();

        // Verify that the attributes are initialized with default values
        assertNull(typologyJpa.getTypologyId());
        assertNull(typologyJpa.getTypologyName());
    }
}