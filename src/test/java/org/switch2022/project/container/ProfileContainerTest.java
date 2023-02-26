package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.model.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProfileContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    ProfileContainer profileContainerReference;

    @BeforeEach
    void setUp() {

        /*
          Container of profiles created.
         */
        profileContainerReference = new ProfileContainer();

        /*
          Profiles added to the Container.
         */
        profileContainerReference.createProfile("Administrator");
        profileContainerReference.createProfile("User");
    }

    @AfterEach
    void tearDown() {
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
    void ensureProfileIsNotAddedSuccessfully_WithSpaces() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = profileContainerReference.createProfile(" administrator ");
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