package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import java.util.ArrayList;
import java.util.List;

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
    List<Account> accounts;
    List<Profile> profiles;
    List<Project> projects;
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
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");
        profiles = new ArrayList<>();
        profiles.add(profileOne);
        profiles.add(profileTwo);

        //customer
        customerOne = new Customer("Genius Software", "234567890");

        //projectTypology
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, new ProjectTypology("Fixed Cost"), businessSectorOne );
        projects = new ArrayList<>();
        projects.add(projectOne);

        //containers
        accountContainer = new AccountContainer(accounts);
        profileContainer = new ProfileContainer(profiles);

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
        accounts.clear();
        profiles.clear();
        projects.clear();
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
        accountOne.setProfile(profileOne);
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
        accountOne.setProfile(profileOne);
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