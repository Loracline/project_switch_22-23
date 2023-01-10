package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ChangeProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne;
    Profile profileOne;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    Company company;
    ChangeProfileController controller;
    List<Account> accounts;
    List<Profile> profiles;

    @BeforeEach
    void setUp() {

        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accounts.add(accountOne);

        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profileOne = new Profile("Administrator");
        profiles.add(profileOne);

        company = new Company(accountContainer, profileContainer);
        controller = new ChangeProfileController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        accountContainer = null;
        profileContainer = null;
        company = null;
        controller = null;
        accounts.clear();
        profiles.clear();
    }
    @Test
    void ensureAccountProfileIsChangedSuccessfully() {
        //ARRANGE
        boolean expected = true;

        //ACT
        boolean result = controller.changeProfile("mike@isep.ipp.pt", "Administrator");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
        //ARRANGE
        boolean expected = false;

        //ACT
        boolean result = controller.changeProfile("mike@isep.ipp.pt", "Manager");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
        //ARRANGE
        boolean expected = false;

        //ACT
        boolean result = controller.changeProfile("mikke@isep.ipp.pt", "Administrator");

        //ASSERT
        assertEquals(expected, result);
    }
}