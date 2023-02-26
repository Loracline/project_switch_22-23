package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import static org.junit.jupiter.api.Assertions.*;


class CreateProfileControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Profile profileOne, profileTwo;

    Account accountOne;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    BusinessSectorContainer businessSectorContainer;
    CustomerContainer customerContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    CreateProfileController createProfileController;


    @BeforeEach
    void setUp() {

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");

        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("User");

        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        createProfileController = new CreateProfileController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne=null;
        accountContainer=null;
        profileOne = null;
        profileTwo = null;
        profileContainer = null;
        company = null;
        createProfileController = null;
    }

    @Test
    void addNewProfileSuccessfully() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = true;
        //Act
        boolean result = createProfileController.createProfile("Manager",emailActor);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void addNewProfileUnsuccessfullyInvalidName() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator
        boolean expected = false;
        //Act
        boolean result = createProfileController.createProfile("User", emailActor);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void addNewProfileUnsuccessfully_ProfileNotAuthorized() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProfileController.createProfile("User", "mike@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
}