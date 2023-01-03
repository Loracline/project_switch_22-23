package org.switch2022.project.model;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Profile;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void createProfileSuccessfully() {
        String profileName = "Administrator";
        Profile profile = new Profile(profileName);
    }
    @Test
    void ensureSameObjectEqualsItself() {
        Profile reference = new Profile("Admin");
        Profile other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        assertEquals(expected, result);
    }
    @Test
    void ensureTwoArraysAreNotTheSame() {
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
}

