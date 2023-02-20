package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;
import org.switch2022.project.utils.mapper.ManagerListProjectsMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListProjectsInAccountControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo;
    ManagerListProjectsDTO projectDTOOne;
    Customer customerOne;
    ProjectTypology projectTypologyOne;
    BusinessSector businessSectorOne;
    Project projectOne;
    AccountInProject accountInProjectOne;
    List<ManagerListProjectsDTO> projectsDTO;
    List<AccountInProject> accountsInProject;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    AccountInProjectContainer accountInProjectContainer;
    Company company;
    ListProjectsInAccountController listProjectsInAccountController;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Mary", "mary@isep.ipp.pt", 939855689, null);


        //customer
        customerOne = new Customer("Genius Software", "234567890");


        //projectTypology
        projectTypologyOne = new ProjectTypology("Fixed Cost");


        //businessSector
        businessSectorOne = new BusinessSector("Fishing");

        //project
        projectOne = new Project("1A", "Mobile Software", customerOne, projectTypologyOne, businessSectorOne );

        //projectDTO
        projectDTOOne = ManagerListProjectsMapper.getDTOFromProject(projectOne);
        projectsDTO = new ArrayList<>();
        projectsDTO.add(projectDTOOne);

        //accountInProject
        accountInProjectOne = new AccountInProject(accountOne, projectOne, "Team Member", 1,
                34f, LocalDate.of(2020, 1, 8));
        accountsInProject = new ArrayList<>();
        accountsInProject.add(accountInProjectOne);

        //containers
        accountContainer = new AccountContainer();
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Mary", "mary@isep.ipp.pt", 939855689, null);
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        //company
        company = new Company(accountContainer, profileContainer, null,
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
        projectsDTO.clear();
        accountsInProject.clear();
        accountContainer = null;
        profileContainer=null;
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
        List<ManagerListProjectsDTO> expected = projectsDTO;

        //Act
        List<ManagerListProjectsDTO> result = listProjectsInAccountController.listProjectsByAccount(emailActor);

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

        List<ManagerListProjectsDTO> expected = new ArrayList<>();

        //Act
        List<ManagerListProjectsDTO> result =
                listProjectsInAccountController.listProjectsByAccount(emailActor);

        //Assert
        assertEquals(expected, result);
    }
}