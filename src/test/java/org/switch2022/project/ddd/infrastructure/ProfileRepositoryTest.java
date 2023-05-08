package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ProfileRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the obect equals itself
     */

    @Test
    void ensureSameObjectEqualsItself() {
        //Arrange
        ProfileRepository profileRepositoryOne = new ProfileRepository();
        ProfileRepository profileRepositoryReference = profileRepositoryOne;
        boolean expected = true;

        //Act
        boolean result = profileRepositoryOne.equals(profileRepositoryReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        //Arrange
        ProfileRepository profileRepositoryOne = new ProfileRepository();
        Object profileRepository = new Object();
        boolean expected = false;

        //Act
        boolean result = profileRepositoryOne.equals(profileRepository);

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 03: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoInstancesAreEqual() {
        // Arrange
        ProfileRepository reference = new ProfileRepository();
        ProfileRepository other = new ProfileRepository();
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 05: Test to ensure that Repository doesn't equals a null object
     */
    @Test
    void ensureThatProfileDoesNotEqualsNull() {
        //Arrange
        ProfileRepository profileRepositoryOne = new ProfileRepository();
        ProfileRepository profileRepositoryTwo = null;

        boolean expected = false;
        //Act
        boolean result = profileRepositoryOne.equals(profileRepositoryTwo);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method:hashCode()
     * Scenario 01:Two equal ProfileRepository objects are the same.
     */
    @Test
    void ensureTwoEqualRepositoryInstancesHaveSameHashcode() {
        //Arrange
        ProfileRepository reference = new ProfileRepository();
        ProfileRepository other = new ProfileRepository();
        int expected = reference.hashCode();

        //Act
        int result = other.hashCode();

        //Assert
        assertEquals(expected, result);
    }

}



