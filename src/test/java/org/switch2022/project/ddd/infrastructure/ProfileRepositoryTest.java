package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


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
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoInstancesAreNotEqual() {
        //Arrange
        ProfileRepository reference = new ProfileRepository();
        Profile profileDouble = mock(Profile.class);
        reference.add(profileDouble);
        ProfileRepository other = new ProfileRepository();
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 05: Test to ensure that ProfileRepository doesn't equals a null object
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

    /**
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureTwoDifferentInstancesHaveTheSameHashCode() {
        //Arrange
        Profile profileOneDouble = mock(Profile.class);
        Profile profileTwoDouble = mock(Profile.class);
        ProfileRepository profileOne = new ProfileRepository();
        profileOne.add(profileOneDouble);
        ProfileRepository profileTwo = new ProfileRepository();
        profileTwo.add(profileTwoDouble);

        //Assert
        assertNotEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    /**
     * Method: add().
     * Scenario 01: make sure an instance of Profile is successfully added to the repository.
     * Expected return: TRUE.
     */

    @Test
    void ensureProfileIsAddedToRepository() {
        //Arrange
        Profile profileDouble = mock(Profile.class);
        ProfileRepository repository = new ProfileRepository();

        //Act
        boolean result = repository.add(profileDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: check that an instance of Profile is not added to the repository
     * if it already exists in the repository.
     * Expected return: AlreadyExistsInRepoException.
     */


    @Test
    void ensureThatAnExceptionIsThrownWhenProfileAlreadyExistsInRepo() {
        //Arrange
        Profile profileDouble = mock(Profile.class);
        ProfileRepository repository = new ProfileRepository();

        repository.add(profileDouble);
        String expected = "The profile already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.add(profileDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

}



