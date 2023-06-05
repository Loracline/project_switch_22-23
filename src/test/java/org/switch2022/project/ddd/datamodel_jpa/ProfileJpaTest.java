package org.switch2022.project.ddd.datamodel_jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.typology.Typology;

import static org.junit.jupiter.api.Assertions.*;

class ProfileJpaTest {
    /**
     * Tests for the equals() method.
     *
     * Scenario 1: object equals itself, should return true.
     */
    @DisplayName("Equals test for ProfileJpa: profile equals itself")
    @Test
    void ensureThatSameObjectEqualsItself() {
        //Arrange
        ProfileJpa reference = new ProfileJpa("PRF005", "Director");
        boolean expected = true;

        //Act
        boolean result = reference.equals(reference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: two rofileJpa objects are not the same. Should return false.
     */
    @DisplayName("Equals test for ProfileJpa: two profiles are the same")
    @Test
    void ensureThatProfileJpaAreNotTheSame() {
        //Arrange
        ProfileJpa reference = new ProfileJpa("PRF005", "Director");
        ProfileJpa other = new ProfileJpa("PRF006", "Head of Software Development");
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
    @DisplayName("Equals test for ProfileJpa: not equals another object")
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureThatProfileJpaDoesNotEqualsOtherTypeOfObject() {
        //Arrange
        ProfileJpa reference = new ProfileJpa("PRF005", "Director");
        String other = "Head of Software Development";

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 4: object does not equal null. Should return false
     */
    @DisplayName("Equals test for ProfileJpa: not null")
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        //Arrange
        ProfileJpa reference = new ProfileJpa("PRF005", "Director");
        Typology other = null;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 5: object equals another with same id.
     */
    @DisplayName("Equals test for ProfileJpa: same id")
    @Test
    void ensureThatObjectEqualsAnotherWithSameId() {
        //Arrange
        ProfileJpa reference = new ProfileJpa("PRF005", "Director");
        ProfileJpa other = new ProfileJpa("PRF005", "Director");

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests for the hashCode() method.
     */
    @DisplayName("Hashcode test for ProfileJpa")
    @Test
    public void testHashCodeProjectTypology() {
        //Arrange
        ProfileJpa obj1 = new ProfileJpa("PRF005", "Director");
        ProfileJpa obj2 = new ProfileJpa("PRF005", "Director");
        ProfileJpa obj3 = new ProfileJpa("PRF005", "Head of Software Development");

        //Act and Assert
        assertEquals(obj1.hashCode(), obj2.hashCode());

        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }

    @DisplayName("ProfileId is retrieved successfully")
    @Test
    void ensureThatProfileIdISRetrieved() {
        // Arrange
        ProfileJpa profileJpa = new ProfileJpa("PRF005", "Director");
        String expected = "PRF005";

        // Act
        String result = profileJpa.getProfileId();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("ProfileName is retrieved successfully")
    @Test
    void ensureThatTypologyNameISRetrieved() {
        // Arrange
        ProfileJpa profileJpa = new ProfileJpa("PRF005", "Director");
        String expected = "Director";

        // Act
        String result = profileJpa.getProfileName();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Empty Constructor Testing")
    @Test
    void testEmptyConstructor() {
        // Create an instance using the empty constructor
        ProfileJpa profileJpa = new ProfileJpa();

        // Verify that the attributes are initialized with default values
        assertNull(profileJpa.getProfileId());
        assertNull(profileJpa.getProfileName());
    }

}