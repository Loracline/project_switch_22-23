package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileTest {
    /**
     * Testing the equals() method.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        Profile reference = new Profile("Admin");
        Profile other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoProfilesAreNotTheSame() {
        Profile reference = new Profile("User");
        Profile other = new Profile("Manager");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        Profile reference = new Profile("User");
        String other = "User";
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "Manager" by checking its profile name.
     */
    @Test
    void validateIfIsManagerSuccessfully() {
        //Arrange
        Profile reference = new Profile("Manager");
        boolean expected = true;
        //Act
        boolean result = reference.isManager();
        assertEquals(expected, result);
    }

    @Test
    void validateIfIsManagerUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;
        //Act
        boolean result = reference.isManager();
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "Administrator" by checking its profile name.
     */
    @Test
    void validateIfIsAdministratorSuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = true;
        //Act
        boolean result = reference.isAdministrator();
        assertEquals(expected, result);
    }

    @Test
    void validateIfIsAdministratorUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Manager");
        boolean expected = false;
        //Act
        boolean result = reference.isAdministrator();
        assertEquals(expected, result);
    }

    /**
     * Testing if profile is "User" by checking its profile name.
     */
    @Test
    void validateIfIsUserSuccessfully() {
        //Arrange
        Profile reference = new Profile("User");
        boolean expected = true;
        //Act
        boolean result = reference.isUser();
        assertEquals(expected, result);
    }

    @Test
    void validateIfIsUserUnsuccessfully() {
        //Arrange
        Profile reference = new Profile("Administrator");
        boolean expected = false;
        //Act
        boolean result = reference.isUser();
        assertEquals(expected, result);
    }
}

