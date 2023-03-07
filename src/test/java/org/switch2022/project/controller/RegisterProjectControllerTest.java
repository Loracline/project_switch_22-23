package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Profile;
import org.switch2022.project.dto.ProjectDtoAsManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running
     * the tests below.
     */

    Account accountOne;
    Profile profileOne, profileTwo, profileThree;
    BusinessSector businessSector;
    ProjectDtoAsManager projectOneDTO, projectTwoDTO;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;
    RegisterProjectController registerProjectController;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        projectTypologyContainer = new ProjectTypologyContainer();

        customerContainer = new CustomerContainer();

        businessSector = new BusinessSector("fishing");
        businessSectorContainer = new BusinessSectorContainer();

        projectOneDTO = new ProjectDtoAsManager("AA001", "software development " +
                "management", "Peter", "228674498",
                "Fixed cost", "Fishing");
        projectTwoDTO = new ProjectDtoAsManager("AA004", "software development " +
                "management", "Mary",
                "228674498", "Fixed cost", "Sports");
        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
                businessSectorContainer);

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, null, customerContainer);

        registerProjectController = new RegisterProjectController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projectContainer = null;
        projectOneDTO = null;
        projectTwoDTO = null;
        customerContainer = null;
        company = null;
    }

    /**
     * This test verifies if a new project is registered when the actor is an
     * account with the profile "Manager".
     */
    @Test
    void ensureProjectIsRegisteredWhenActorIsManager() {
        // Arrange
        company.changeProfile("mike@isep.ipp.pt", "manager");
        boolean expected = true;

        // Act
        boolean result = registerProjectController.registerProject(projectTwoDTO,
                accountOne.getEmail());

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies if a new project is not registered when it already
     * exists in the project container.
     */
    @Test
    void ensureProjectIsNotRegisteredWhenItAlreadyExists() {
        // Arrange
        company.changeProfile(accountOne.getEmail(), "manager");
        boolean expected = false;

        // Act
        boolean result = registerProjectController.registerProject(projectOneDTO,
                accountOne.getEmail());

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies if a new project is not registered when the actor is
     * an account with other profile than "Manager".
     */
    @Test
    void ensureProjectIsNotRegisteredWhenActorIsNotManager() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = registerProjectController.registerProject(projectTwoDTO,
                accountOne.getEmail());

        // Assert
        assertEquals(expected, result);
    }
}