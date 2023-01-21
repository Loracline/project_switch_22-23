package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProfileContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Profile profileOne, profileTwo;
    List<Profile> profiles;
    ProfileContainer profileContainerReference;

    @BeforeEach
    void setUp() {
        /*
          Profiles created.
         */
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");

        /*
          Container of profiles created.
         */
        profiles = new ArrayList<>();
        profileContainerReference = new ProfileContainer(profiles);

        /*
          Profiles added to the Container.
         */
        profiles.add(profileOne);
        profiles.add(profileTwo);
    }

    @AfterEach
    void tearDown() {
        profileOne = null;
        profileTwo = null;
        profiles.clear();
        profileContainerReference = null;
    }

    /**
     * Testing if profile is created and added to the container.
     */
    @Test
    void ensureProfileIsNotAddedSuccessfully_CaseInsensitive() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = profileContainerReference.createProfile("administrator");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddProfileToProfilesListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = profileContainerReference.createProfile("manager");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddProfileToProfilesListUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = profileContainerReference.createProfile("user");
        //Assert
        assertEquals(expected, result);

    }

    /**
     * Testing if intended profile is retrieved by giving name.
     */
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
}