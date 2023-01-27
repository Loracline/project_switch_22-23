package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CreateProjectTypologyControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    Profile profileOne, profileTwo;
    Customer customerOne;
    Project projectOne;
    BusinessSector businessSectorOne;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectContainer projectContainer;
    ProjectTypologyContainer projectTypologyContainer;
    Company company;
    CreateProjectTypologyController createProjectTypologyController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);


        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //customer
        customerOne = new Customer("Genius Software", "234567890");

        //projectTypology
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //containers
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Mary", "mary@isep.ipp.pt", 939855689, null);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company
        company = new Company(accountContainer, profileContainer, null,
                null, projectTypologyContainer, null, null);

        //controller
        createProjectTypologyController = new CreateProjectTypologyController(company);
        accountTwo.setProfile(profileTwo);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        businessSectorOne =null;
        profileOne = null;
        profileTwo = null;
        customerOne = null;
        projectOne = null;
        accountContainer = null;
        profileContainer = null;
        projectContainer = null;
        projectTypologyContainer = null;
        company = null;
        createProjectTypologyController = null;
    }

    /**
     * Test to verify if a project typology is successfully created.
     */

    @Test
    void ensureThatNewProjectTypologyIsSuccessfullyAdded(){
        //Arrange
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        boolean expected = true;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed new typology");
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test if the desired typology already exists. If it does, new typology is not created.
     */

    @Test
    void ensureThatNewProjectTypologyIsNotCreatedIfItAlreadyExists(){
        //Arrange
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        boolean expected = false;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed Cost");
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that project typology is not added if account doesn't have authorization.
     */

    @Test
    void ensureThatNewProjectTypologyIsNotCreatedIfItAccountIsNotAuthorized(){
        //Arrange
        boolean expected = false;
        //Act
        boolean result = createProjectTypologyController.createProjectTypology("mike@isep.ipp.pt","Fixed new typology");
        //Assert
        assertEquals(expected,result);
    }

}