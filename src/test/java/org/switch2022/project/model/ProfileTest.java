package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

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
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        Profile reference = new Profile("User");
        String other = new String("User");
        boolean expected = false;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }

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

}

