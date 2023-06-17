package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.profile.Profile;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProfileRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
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
        reference.save(profileDouble);
        ProfileRepository other = new ProfileRepository();
        boolean expected = false;

        //Act
        boolean result = reference.equals(other);

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Scenario 05: Test to ensure that ProfileRepository doesn't equal a null object
     */
    @Test
    void ensureThatProfileDoesNotEqualNull() {
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
     * Scenario 02:Two objects with the different code and different hash codes are two
     * different objects
     */
    @Test
    void ensureTwoDifferentInstancesHaveDifferentHashCode() {
        //Arrange
        Profile profileOneDouble = mock(Profile.class);
        Profile profileTwoDouble = mock(Profile.class);
        ProfileRepository profileOne = new ProfileRepository();
        profileOne.save(profileOneDouble);
        ProfileRepository profileTwo = new ProfileRepository();
        profileTwo.save(profileTwoDouble);

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
        boolean result = repository.save(profileDouble);

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

        repository.save(profileDouble);
        String expected = "The profile already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.save(profileDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Method: count().
     * <p>
     * Scenario 01: return the number of profiles from a list
     * Expected result: the number of Profile instances in the list.
     */
    @Test
    void ensureThatTheNumberOfProfilesInTheRepoIsReturned() {
        //Arrange
        Profile profileOneDouble = mock(Profile.class);
        Profile profileTwoDouble = mock(Profile.class);

        ProfileRepository repository = new ProfileRepository();
        repository.save(profileOneDouble);
        repository.save(profileTwoDouble);

        int expected = 2;
        int result = repository.count();

        assertEquals(expected, result);
    }

    /**
     * Method: getProfileByName()
     * <p>
     * Scenario 01: tests getting a profile by name.
     * Expects the method to return the correct profile.
     */
    @Test
    void ensureProfileIsReturned() {
        // Arrange
        Profile profileOne = mock(Profile.class);
        Profile profileTwo = mock(Profile.class);

        ProfileRepository repository = new ProfileRepository();
        repository.save(profileOne);
        repository.save(profileTwo);

        Name profileName = mock(Name.class);
        when(profileOne.hasName(profileName)).thenReturn(true);

        // Act
        Profile result = repository.findByProfileName(profileName);

        // Assert
        assertEquals(profileOne, result);
    }

    /**
     * Scenario 02: tests retrieving a profile by name.
     * Expects the method to throw a NotFoundInRepoException
     * because the list is empty.
     */
    @Test
    void ensureThatProfileIsNotFoundBecauseListIsEmpty() {
        //Arrange
        ProfileRepository repository = new ProfileRepository();
        Name profileName = new Name("manager");
        String message = "There is no profile with the given name in the repository.";

        //Act
        NotFoundInRepoException result =
                assertThrows(NotFoundInRepoException.class,
                        () -> repository.findByProfileName(profileName));

        //Assert
        assertEquals(message, result.getMessage());
    }

    /**
     * Scenario 03: This method retrieves a profile by name from the repository.
     *
     * @return the profile with the given name
     * @throws NotFoundInRepoException if the profile is not found in the repository
     */
    @Test
    void ensureThatProfileIsNotFoundBecauseIsNotInTheList() {
        //Arrange
        Profile profileOne = mock(Profile.class);

        ProfileRepository repository = new ProfileRepository();
        Name profileName = new Name("manager");
        when(profileOne.hasName(profileName)).thenReturn(false);
        String message = "There is no profile with the given name in the repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> repository.findByProfileName(profileName));

        //Assert
        assertEquals(message, result.getMessage());
    }


    /**
     * Method: findByProfileName()
     * <p>
     * Scenario 1: finds a profile with the given name.
     * @returns the correspondent profile.
     */
    @Test
    void ensureProfileIsReturnedWithGivenProfileName() {
        // Arrange
        Profile profileOne = mock(Profile.class);
        Optional<Profile> expected = Optional.ofNullable(profileOne);
        ProfileRepository repository = new ProfileRepository();
        repository.save(profileOne);

        Name profileName = mock(Name.class);
        when(profileOne.hasName(profileName)).thenReturn(true);

        // Act
        Optional<Profile> result = repository.findByNameOfProfile(profileName);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: does not find a profile with the given name.
     * <p>
     * @throws NotFoundInRepoException if the profile is not found in the repository
     */
    @Test
    void ensureProfileIsNotReturnedWithGivenProfileName() {
        //Arrange
        Profile profileOne = mock(Profile.class);

        ProfileRepository repository = new ProfileRepository();
        Name profileName = new Name("manager");
        when(profileOne.hasName(profileName)).thenReturn(false);
        String message = "There is no profile with the given name in the repository.";

        //Act
        NotFoundInRepoException result = assertThrows(NotFoundInRepoException.class,
                () -> repository.findByProfileName(profileName));

        //Assert
        assertEquals(message, result.getMessage());
    }

}



