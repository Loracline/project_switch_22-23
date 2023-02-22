package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.BusinessSectorContainer;
import org.switch2022.project.model.container.Company;
import org.switch2022.project.model.container.ProfileContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddBusinessSectorControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne;
    Profile profileOne, profileTwo;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSector businessSectorOne;
    BusinessSectorContainer businessSectorContainer;
    Project projectOne;
    Company company;
    AddBusinessSectorController addBusinessSectorController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //business sector
        businessSectorOne = new BusinessSector("Fishing");


        //containers
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                null, null, null, null);

        //controller
        addBusinessSectorController = new AddBusinessSectorController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        profileTwo = null;
        profileContainer = null;
        projectOne = null;
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
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
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
     * name of the business sector already exists.
     * Expected return = false
     */

    @Test
    void ensureThatNewBusinessSectorIsNotAddedIfNameAlreadyExists() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
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
        company.changeProfile("mike@isep.ipp.pt", "Manager");
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