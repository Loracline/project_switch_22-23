package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the implementation of the following US:
 * US005 - As Administrator, I want to inactivate a user account.
 * US006 - As Administrator, I want to activate a user account.
 */
class ChangeStatusControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    // accounts
    Account accountOne, accountTwo, accountThree, accountFour;
    AccountContainer accountContainer;

    // profiles
    Profile profileOne;
    ProfileContainer profileContainer;

    // business sectors
    BusinessSectorContainer businessSectorContainer;

    // customers
    CustomerContainer customerContainer;

    // project typologies
    ProjectTypologyContainer projectTypologyContainer;

    // projects
    ProjectContainer projectContainer;

    AccountInProjectContainer accountInProjectContainer;

    // company
    Company company;

    // controller
    ChangeStatusController changeStatusController;

    @BeforeEach
    void setUp() {
        // accounts
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountThree = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);
        accountFour = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);

        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Paul", "paul@isep.ipp.pt", 932755689, null);

        // profiles
        profileOne = new Profile("Administrator");

        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("User");

        // company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        // controller
        changeStatusController = new ChangeStatusController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        changeStatusController = null;
        customerContainer = null;
        company = null;
        projectTypologyContainer = null;
    }

    /**
     * changeStatus(String email, boolean status)
     */
    @Test
    void ensureAccountStatusIsChangedToInactive() {
        // ARRANGE
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;

        // ACT
        boolean result = changeStatusController.changeStatus(accountOne.getEmail(), false,emailActor);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountStatusIsNotChangedStillActive() {
        // ARRANGE
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;

        // ACT
        boolean result = changeStatusController.changeStatus(accountOne.getEmail(), true,emailActor);

        // ASSERT
        assertEquals(expected, result);
    }
    @Test
    void ensureAccountStatusIsNotChanged_ProfileNotAuthorized() {
        // ARRANGE
        boolean expected = false;

        // ACT
        boolean result = changeStatusController.changeStatus(accountOne.getEmail(), true,"mike@isep.ipp.pt");

        // ASSERT
        assertEquals(expected, result);
    }
}