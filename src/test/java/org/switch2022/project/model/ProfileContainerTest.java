package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileContainerTest {


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

        /*@Test
        void createEmptyProfileContainerSuccessfully() {
            ProfileContainer profileContainerNew = new ProfileContainer();
        }*/

       /* @Test
        void createProfileContainerSuccessfully() {
            List<Profile> profiles = new ArrayList<>();
            profiles.add(profileOne);
            ProfileContainer profileContainerNew = new ProfileContainer(profiles);
        }*/

        @Test
        void ensureSameObjectEqualsItself() {
            ProfileContainer profileContainerNew = profileContainerReference;
            boolean expected = true;
            boolean result = profileContainerReference.equals(profileContainerNew);
            assertEquals(expected, result);
        }
        @Test
        void ensureTwoProfilesAreNotTheSame() {
            List<Profile> profilesOne = new ArrayList<>();
            profilesOne.add(profileTwo);
            ProfileContainer profileContainerNew = new ProfileContainer(profilesOne);
            boolean expected = false;
            boolean result = profileContainerReference.equals(profileContainerNew);
            assertEquals(expected, result);
        }
        @Test
        void ensureObjectDoesNotEqualsOtherTypeOfObject() {
            Profile other = new Profile("Admin");
            boolean expected = false;
            boolean result = profileContainerReference.equals(other);
            assertEquals(expected, result);
        }
        @Test
        void createProfileSuccessfully() {
            Profile expected = new Profile ("User");
            Profile result = profileContainerReference.createProfile("User");
            assertEquals(expected, result);
        }
        @Test
        void ensureProfileExistSuccessfullyCaseInsensitive() {
            boolean expected = true;
            Profile profileThree = new Profile("administrator");
            boolean result = profileContainerReference.doesProfileNameExists(profileThree);
            assertEquals(expected, result);
        }
        @Test
        void ensureProfileDoesntExist() {
            boolean expected = false;
            boolean result = profileContainerReference.doesProfileNameExists(profileTwo);
            assertEquals(expected, result);
        }
        @Test
        void ensureProfileExistSuccessfully() {
            boolean expected = true;
            boolean result = profileContainerReference.doesProfileNameExists(profileOne);
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
    void getProfileByName_HappyPath() {
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