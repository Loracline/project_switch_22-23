package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTOAsManager;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * This class tests the implementation of these three US:
 * US011 - As Manager, I want to associate a user as Team Member of a project.
 * US012 - As Manager, I want to define the PO of a project.
 * US013 - As Manager, I want to define the SM of a project.
 */

class AddUserToProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    Profile profileOne, profileTwo;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountDTO accountDTO;
    ProjectDTOAsManager projectDTOAsManager;
    AccountInProject accountInProject;
    List<AccountInProject> accountsInProject;
    AllocationDTO allocationDTO;
    AccountContainer accountContainer;
    BusinessSectorContainer businessSectorContainer;
    CustomerContainer customerContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    ProfileContainer profileContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    AddUserToProjectController addUserToProjectController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 972755689, null);

        //accountDTO
        accountDTO = AccountMapper.accountToDTO(accountTwo);

        //accountInProject
        accountInProject = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");


        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //projectDTO
        projectDTOAsManager = new ProjectDTOAsManager("1A", "Mobile Software", "Genius Software",
                "228674498", "Fixed cost", "Fishing");
        projectDTOAsManager = new ProjectDTOAsManager("1A", "Mobile Software", "Genius Software",
                "228674498","Fixed cost", "Fishing");

        //account in project dto
        allocationDTO = new AllocationDTO("Product Owner", 7.5f, 45.0f, LocalDate.of(2023,
                1, 19), null);


        //containers

        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");

        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("Genius Software","234567890");

        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectDTOAsManager, projectTypologyContainer,
                customerContainer, businessSectorContainer);

        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Emma", "emma@isep.ipp.pt", 972755689, null);

        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company

        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

        //controller
        addUserToProjectController = new AddUserToProjectController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        accountDTO = null;
        projectDTOAsManager = null;
        accountInProject = null;
        accountsInProject.clear();
        allocationDTO = null;
        accountContainer = null;
        businessSectorContainer = null;
        customerContainer = null;
        projectTypologyContainer = null;
        projectContainer = null;
        accountInProjectContainer = null;
        company = null;
        addUserToProjectController = null;
        profileContainer = null;
    }

    /**
     * US011-US013
     * Tests if an account is successfully associated to a project if the actor
     * is an administrator.
     * Expected return: true
     */

    @Test
    void ensureAccountIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //set profileTwo (Manager) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Manager");
        String emailActor = accountOne.getEmail(); //Manager

        //Act
        boolean result = addUserToProjectController.addUserToProject(emailActor,
                accountDTO, projectDTOAsManager, allocationDTO);

        //Assert
        assertTrue(result);

    }
    /**
     * US011-US013
     * Tests if an account is not associated to a project if the actor
     * is not an administrator.
     * Expected return: false
     */
    @Test
    void ensureAllocationFailsBecauseProfileIsNotManager() {
        //Arrange
        //set profileOne (Administrator) to accountOne
        company.changeProfile("mike@isep.ipp.pt", "Administrator");
        String emailActor = accountOne.getEmail(); //Administrator

        //Act
        boolean result = addUserToProjectController.addUserToProject(emailActor,
                accountDTO,
                projectDTOAsManager,
                allocationDTO);

        //Assert
        assertFalse(result);
    }
}
