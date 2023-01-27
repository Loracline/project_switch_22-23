package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.AccountInProjectContainer;
import org.switch2022.project.model.container.ProjectContainer;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDTO;
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
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountDTO accountDTO;
    ProjectDTO projectDTO;
    AccountInProject accountInProject;
    List<Account> accounts;
    List<Project> projects;
    List<AccountInProject> accountsInProject;
    AllocationDTO allocationDTO;
    AccountContainer accountContainer;
    ProjectContainer projectContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    AddUserToProjectController addUserToProjectController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 972755689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);


        //accountDTO
        accountDTO = AccountMapper.accountToDTO(accountTwo);
        /*accountsDTO = new ArrayList<>();
        accountsDTO.add(accountDTO);*/

        //accountInProject
        accountInProject = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProject);

        //profile
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");

        //customer
        customerOne = new Customer("Genius Software", "234567890");

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne,
                projectTypologyOne, businessSectorOne);
        projects = new ArrayList<>();
        projects.add(projectOne);

        //projectDTO
        projectDTO = new ProjectDTO("1A", "Mobile Software", "Genius Software",
                "228674498","Fixed cost", "Fishing");

        //account in project dto
        allocationDTO = new AllocationDTO("Product Owner",7.5f,45.0f,LocalDate.of(2023,
                1, 19),null);


        //containers
        accountContainer = new AccountContainer(accounts);
        projectContainer = new ProjectContainer();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //company
        company = new Company(accountContainer, null, null,
                projectContainer, null, accountInProjectContainer, null);

        //controller
        addUserToProjectController = new AddUserToProjectController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        customerOne = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        accountDTO = null;
        projectDTO = null;
        accountInProject = null;
        accounts.clear();
        projects.clear();
        accountsInProject.clear();
        allocationDTO = null;
        accountContainer = null;
        projectContainer = null;
        accountInProjectContainer = null;
        company = null;
        addUserToProjectController = null;
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
        accountOne.setProfile(profileOne);
        String emailActor = accountOne.getEmail(); //Administrator

        //Act
        boolean result = addUserToProjectController.addUserToProject(emailActor,
                accountDTO,
                projectDTO,
                allocationDTO);

        //Assert
        assertFalse(result);
    }
}
