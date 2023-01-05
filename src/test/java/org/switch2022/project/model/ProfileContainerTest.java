package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileContainerTest {


    Profile profileOne, profileTwo;
    AccountContainer accountContainer, accountContainerTwo;


    @BeforeEach
    void setUp() {


        List<Account> accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);


        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");


    }

    @AfterEach
    void tearDown() {
        profileOne = null;
        profileTwo = null;
        accountContainer = null;
        accountContainerTwo = null;

    }

    @Test
    void createEmptyProfileContainerSuccessfully() {
        List<Profile> profiles = new ArrayList<>();
        ProfileContainer profileContainerNew = new ProfileContainer(profiles);
    }

    @Test
    void createProfileContainerSuccessfully() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerNew = new ProfileContainer(profiles);
    }

    @Test
    void ensureSameObjectEqualsItself() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        ProfileContainer profileContainerNew = profileContainerReference;
        boolean expected = true;
        boolean result = profileContainerReference.equals(profileContainerNew);
        assertEquals(expected, result);
    }

    @Test
    void ensureTwoProfilesAreNotTheSame() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        List<Profile> profilesOne = new ArrayList<>();
        profilesOne.add(profileTwo);
        ProfileContainer profileContainerNew = new ProfileContainer(profilesOne);
        boolean expected = false;
        boolean result = profileContainerReference.equals(profileContainerNew);
        assertEquals(expected, result);
    }

    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        Profile other = new Profile("Admin");
        boolean expected = false;
        boolean result = profileContainerReference.equals(other);
        assertEquals(expected, result);
    }

    @Test
    void createProfileSuccessfully() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        Profile expected = new Profile("User");
        Profile result = profileContainerReference.createProfile("User");
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileExistSuccessfullyCaseInsensitive() {
        boolean expected = true;
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        Profile profileThree = new Profile("administrator");
        boolean result = profileContainerReference.doesProfileNameExists(profileThree);
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileDoesntExist() {
        boolean expected = false;
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        boolean result = profileContainerReference.doesProfileNameExists(profileTwo);
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileExistSuccessfully() {
        boolean expected = true;
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        boolean result = profileContainerReference.doesProfileNameExists(profileOne);
        assertEquals(expected, result);
    }

    @Test
    void ensureAddProfileToProfilesListSuccessfully() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        boolean expected = true;
        boolean result = profileContainerReference.addProfile(profileTwo);
        assertEquals(expected, result);

    }

    @Test
    void ensureAddProfileToProfilesListUnsuccessfully() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profileOne);
        ProfileContainer profileContainerReference = new ProfileContainer(profiles);
        boolean expected = false;
        boolean result = profileContainerReference.addProfile(profileOne);
        assertEquals(expected, result);

    }


}