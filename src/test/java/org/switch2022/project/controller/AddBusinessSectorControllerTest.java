package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.BusinessSectorContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddBusinessSectorControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne;
    Profile profileOne, profileTwo;
    List<Account> accounts;
    AccountContainer accountContainer;
    BusinessSector businessSectorOne;
    BusinessSectorContainer businessSectorContainer;
    Company company;
    AddBusinessSectorController addBusinessSectorController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //business sector
        businessSectorOne = new BusinessSector("Fishing");


        //containers
        accountContainer = new AccountContainer(accounts);
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");

        //company
        company = new Company(accountContainer, null, businessSectorContainer,
                null, null, null, null);

        //controller
        addBusinessSectorController = new AddBusinessSectorController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        profileTwo = null;
        accounts.clear();
        businessSectorOne = null;
        accountContainer = null;
        businessSectorContainer = null;
        company = null;
        addBusinessSectorController = null;
    }

    /**
     * US008
     * Tests if the addition of a business sector is performed successfully if the actor
     * is an administrator.
     * Expected return: true
     */
    @Test
    void ensureThatNewBusinessSectorIsAddedSuccessfullyIfActorIsAdministrator() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;
        //Act
        boolean result = addBusinessSectorController.addBusinessSector("mining",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * US008
     * Tests if the addition of a business sector is not performed if the
     * name of the business sector is invalid (empty or already existent).
     * Expected return = false
     */

    @Test
    void ensureThatNewBusinessSectorIsNotAddedIfNameIsInvalid() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        //Act
        //business sector "fishing" already exists
        boolean result = addBusinessSectorController.addBusinessSector("fishing",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * US008
     * Tests if the addition of a business sector is not performed if the
     * actor is not an administrator.
     * Expected return = false
     */
    @Test
    void ensureThatNewBusinessSectorIsNotAddedIfActorIsNotAdministrator() {
        //Arrange
        //set profileOne (Manager) to accountOne
        accountOne.setProfile(profileTwo);
        String emailActor = accountOne.getEmail(); //Manager
        boolean expected = false;
        //Act
        //"paul@isep.ipp.pt" is not the administrator
        boolean result = addBusinessSectorController.addBusinessSector("mining",
                emailActor);
        //Assert
        assertEquals(expected, result);
    }
}