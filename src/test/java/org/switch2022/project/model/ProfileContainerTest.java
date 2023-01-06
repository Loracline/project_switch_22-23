package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileContainerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Profile profileOne, profileTwo;
    List<Profile> profiles;
    ProfileContainer profileContainerReference;


    @BeforeEach
    void setUp() {

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");

        profiles = new ArrayList<>();
        profiles.add(profileOne);

        profileContainerReference = new ProfileContainer(profiles);

    }

    @AfterEach
    void tearDown() {
        profileOne = null;
        profileTwo = null;
        profiles.clear();
        profileContainerReference = null;

    }

    @Test
    void ensureProfileIsCreatedSuccessfully() {
        Profile expected = new Profile("User");
        Profile result = profileContainerReference.createProfile("User");
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileExistSuccessfullyCaseInsensitive() {
        boolean expected = true;
        Profile profileThree = new Profile("administrator");
        boolean result = profileContainerReference.doesProfileNameExist(profileThree);
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileDoesntExist() {
        boolean expected = false;
        boolean result = profileContainerReference.doesProfileNameExist(profileTwo);
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileExistSuccessfully() {
        boolean expected = true;
        boolean result = profileContainerReference.doesProfileNameExist(profileOne);
        assertEquals(expected, result);
    }

    @Test
    void ensureAddProfileToProfilesListSuccessfully() {
        boolean expected = true;
        boolean result = profileContainerReference.addProfile(profileTwo);
        assertEquals(expected, result);

    }

    @Test
    void ensureAddProfileToProfilesListUnsuccessfully() {
        boolean expected = false;
        boolean result = profileContainerReference.addProfile(profileOne);
        assertEquals(expected, result);

    }

    @Test
    void ensureProfileIsRetrievedSuccessfully() {
        //ARRANGE
        //Create profile to compare with
        Profile expected = new Profile("Administrator");

        //ACT
        Profile result = profileContainerReference.getProfileByName("Administrator");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetProfileReturnsNull() {
        //ACT
        Profile result = profileContainerReference.getProfileByName("Manager");

        //ASSERT
        assertNull(result);
    }

    @Test
    void ensureFirstIndexIsLowerThanSecond() {
        assertTrue(ProfileContainer.isLower(1,2));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanFirst() {
        assertFalse(ProfileContainer.isLower(2,1));
    }

    @Test
    void ensureSecondIndexIsNotLowerThanSecond() {
        assertFalse(ProfileContainer.isLower(2,2));
    }
}