package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;
import org.switch2022.project.dto.AccountDto;
import org.switch2022.project.dto.AllocationDto;
import org.switch2022.project.dto.ProjectDtoAsManager;
import org.switch2022.project.dto.mapper.AccountMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddUserToProjectControllerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Account accountOne, accountTwo;
    AccountDto accountTwoDTO;
    Profile profileOne, profileTwo;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    ProjectDtoAsManager projectDTO;
    AccountInProject accountInProject;
    List<AccountInProject> accountsInProject;
    AllocationDto scrumMasterDTO;
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
        accountOne = new Account("Mike", "mike@isep.ipp.pt",
                932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt",
                972755689, null);

        //accountDTO
        accountTwoDTO = AccountMapper.accountToDTO(accountTwo);

        //accountInProject
        accountInProject = new AccountInProject(accountOne, projectOne,
                "Team Member", 1, 34f,
                LocalDate.of(2020, 1, 8), LocalDate.of(2021, 1, 8));
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
        projectDTO = new ProjectDtoAsManager("1A",
                "Mobile Software", "Genius Software",
                "228674498", "Fixed cost",
                "Fishing");

        //allocationDTO
        scrumMasterDTO = new AllocationDto("Scrum Master", 7.5f,
                45.0f,
                LocalDate.of(2023, 1, 19), LocalDate.of(2024, 1, 19));

        //containers
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("Genius Software",
                "234567890");
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");
        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectDTO);
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt",
                932755689, null);
        accountContainer.addAccount("Emma", "emma@isep.ipp.pt",
                972755689, null);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company
        company = new Company(accountContainer, profileContainer,
                businessSectorContainer, projectContainer,
                projectTypologyContainer, accountInProjectContainer,
                customerContainer);

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
        accountTwoDTO = null;
        projectDTO = null;
        accountInProject = null;
        accountsInProject.clear();
        scrumMasterDTO = null;
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
     * This test verifies if an account with the "Manager" profile can
     * successfully associate a Scrum Master to an existing project.
     */
    @Test
    void ensureScrumMasterIsAssociatedToProjectSuccessfully() {
        //Arrange
        company.changeProfile(accountOne.getEmail(), "Manager");
        String emailActor = accountOne.getEmail();

        //Act
        boolean result = addUserToProjectController.addUserToProject(emailActor,
                accountTwoDTO, projectDTO, scrumMasterDTO);

        //Assert
        assertTrue(result);
    }

    /**
     * This test verifies the failure of the allocation when the authorization
     * is not granted to other profile than "Manager" (in this specific case,
     * "Administrator").
     */
    @Test
    void ensureAllocationFailsBecauseProfileIsNotManager() {
        //Arrange
        company.changeProfile(accountOne.getEmail(), "Administrator");
        String emailActor = accountOne.getEmail();

        //Act
        boolean result = addUserToProjectController.addUserToProject(emailActor,
                accountTwoDTO, projectDTO, scrumMasterDTO);

        //Assert
        assertFalse(result);
    }
}
