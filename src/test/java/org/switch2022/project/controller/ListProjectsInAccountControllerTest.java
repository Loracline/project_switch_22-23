package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.UserListProjectsDTO;
import org.switch2022.project.utils.mapper.UserListProjectsMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListProjectsInAccountControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    UserListProjectsDTO projectDTOOne;
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountInProject accountInProjectOne;
    List<Account> accounts;
    List<UserListProjectsDTO> projectsDTO;
    List<ProjectTypology> typologies;
    List<Project> projects;
    List<Customer> customers;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    ListProjectsInAccountController listProjectsInAccountController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);

        //customer
        customerOne = new Customer("Genius Software", "234567890");
        customers = new ArrayList<>();
        customers.add(customerOne);

        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        typologies = new ArrayList<>();
        typologies.add(projectTypologyOne);

        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );
        projects = new ArrayList<>();
        projects.add(projectOne);

        //projectDTO
        projectDTOOne = UserListProjectsMapper.getDTOFromProject(projectOne);
        projectsDTO = new ArrayList<>();
        projectsDTO.add(projectDTOOne);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);

        //containers
        accountContainer = new AccountContainer(accounts);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        //company
        company = new Company(accountContainer, null, null,
                null, null, accountInProjectContainer, null);

        //controller
        listProjectsInAccountController = new ListProjectsInAccountController(company);

    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        projectDTOOne = null;
        customerOne = null;
        projectTypologyOne = null;
        businessSectorOne = null;
        projectOne = null;
        accountInProjectOne = null;
        accounts.clear();
        projectsDTO.clear();
        typologies.clear();
        projects.clear();
        accountsInProject.clear();
        customers.clear();
        accountContainer = null;
        accountInProjectContainer = null;
        company = null;
        listProjectsInAccountController = null;
    }

    /**
     * US016
     * Tests if the list of all projects to which a User is allocated is successfully
     * retrieved.
     */
    @Test
    void ensureAllProjectsByAccountAreListedSuccessfully() {
        //Arrange
        String emailActor = accountOne.getEmail(); //allocated to a
        // project = accountInProjectOne
        List<UserListProjectsDTO> expected = projectsDTO;

        //Act
        List<UserListProjectsDTO> result = listProjectsInAccountController.listProjectsByAccount(emailActor);

        //Assert
        assertEquals(expected, result);
    }
    /**
     * US016
     * Returns an empty list if the user is not allocated to any project.
     */

    @Test
    void ensureThatListProjectByAccountIsEmpty_NoPermission() {
        //Arrange
        String emailActor = accountTwo.getEmail(); //not allocated to a project

        List<UserListProjectsDTO> expected = new ArrayList<>();

        //Act
        List<UserListProjectsDTO> result =
                listProjectsInAccountController.listProjectsByAccount(emailActor);

        //Assert
        assertEquals(expected, result);
    }
}