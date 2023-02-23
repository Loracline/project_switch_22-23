package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProfileTest {
    /*
      METHOD equals()
     */

    /**
     * Scenario 1: Verify if the same object equals itself.
     */
    @Test
    void ensureSameProfileEqualsItself() {
        // Arrange
        Profile reference = new Profile("Admin");
        Profile other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if two objects of the same class are different from
     * each other.
     */
    @Test
    void ensureTwoProfilesAreNotTheSame() {
        // Arrange
        Profile reference = new Profile("User");
        Profile other = new Profile("Manager");
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if a Profile and a different type of object are not
     * the same.
     */
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureProfileDoesNotEqualOtherTypeOfObject() {
        // Arrange
        Profile reference = new Profile("User");
        String other = "User";
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if a Profile and a null object are not the same.
     */
    @Test
    void ensureProfileDoesNotEqualNull() {
        // Arrange
        Profile reference = new Profile("User");
        Profile other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }


    /*
      METHOD hashCode()
     */

    /**
     * Scenario 1: Two Profile objects are the same.
     */
    @Test
    public void ensureTwoProfilesHashcodeAreTheSame() {
        // Arrange
        Profile profileOne = new Profile("manager");
        Profile profileTwo = new Profile("manager");

        // Act
        int profileOneHashCode = profileOne.hashCode();
        int profileTwoHashCode = profileTwo.hashCode();

        // Assert
        assertEquals(profileOneHashCode, profileTwoHashCode);
    }

    /**
     * Scenario 2: Two Profile objects are not the same.
     */
    @Test
    public void ensureTwoProfilesHashcodeAreNotTheSame() {
        // Arrange
        Profile profileOne = new Profile("manager");
        Profile profileThree = new Profile("user");

        // Act
        int profileOneHashCode = profileOne.hashCode();
        int profileThreeHashCode = profileThree.hashCode();

        // Assert
        assertNotEquals(profileOneHashCode, profileThreeHashCode);
    }


    /*
      METHOD isProfileRequired()
     */

    /**
     * Scenario 1: Verify if profile is "Manager" by checking its profile name.
     */
    @Test
    void ensureProfileManagerIsRetrievedSuccessfully() {
        // Arrange
        Profile reference = new Profile("Manager");
        boolean expected = true;

        // Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 2: Verify if profile is not "Manager" when the profile name
     * passed by parameter is not the one correspondent.
     */
    @Test
    void ensureProfileManagerIsNotRetrievedWhenProfileNameIsNotCorrespondent() {
        // Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;

        // Act
        boolean result = reference.isProfileRequired(Profile.MANAGER);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 3: Verify if profile is "Administrator" by checking its profile
     * name.
     */
    @Test
    void ensureProfileAdministratorIsRetrievedSuccessfully() {
        // Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = true;

        // Act
        boolean result = reference.isProfileRequired(Profile.ADMINISTRATOR);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 4: Verify if profile is not "Administrator" when the profile
     * name passed by parameter is not the one correspondent.
     */
    @Test
    void ensureProfileAdministratorIsNotRetrievedWhenProfileNameIsNotCorrespondent() {
        // Arrange
        Profile reference = new Profile("Manager");
        boolean expected = false;

        // Act
        boolean result = reference.isProfileRequired(Profile.ADMINISTRATOR);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 5: Verify if profile is "User" by checking its profile name.
     */
    @Test
    void ensureProfileUserIsRetrievedSuccessfully() {
        // Arrange
        Profile reference = new Profile("User");
        boolean expected = true;

        // Act
        boolean result = reference.isProfileRequired(Profile.USER);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Scenario 6: Verify if profile is not "User" when the profile name passed
     * by parameter is not the one correspondent.
     */
    @Test
    void ensureProfileUserIsNotRetrievedWhenProfileNameIsNotCorrespondent() {
        // Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;

        // Act
        boolean result = reference.isProfileRequired(Profile.USER);

        // Assert
        assertEquals(expected, result);
    }
}

