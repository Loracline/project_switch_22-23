package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.AccountContainer;
import org.switch2022.project.model.container.ProjectContainer;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfProjectsControllerTest {

    Account accountOne, accountManager;
    Customer customer;
    ProjectTypology projectTypology;
    BusinessSector businessSector;
    Project projectOne, projectTwo;
    List<Account> accounts;
    List<Project> projects;
    AccountContainer accountContainer;
    ProjectContainer projectContainer;
    Company company;
    GetListOfProjectsController getListOfProjectscontroller;

    @BeforeEach
    void setUp() {
        //account
        accountOne = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountManager = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountManager.setProfile(new Profile("Manager"));

        //project
        customer = new Customer("isep", "222333444");
        projectTypology = new ProjectTypology("Fixed cost");
        businessSector = new BusinessSector("fishing");
        projectOne = new Project("AA001", "software development management",
                customer, projectTypology, businessSector);
        projectTwo = new Project("AA002", "project software", customer,
                projectTypology, businessSector);

        //container
        accounts = new ArrayList<>();
        accounts.add(accountManager);
        accounts.add(accountOne);
        accountContainer = new AccountContainer(accounts);

        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectContainer = new ProjectContainer(projects);

        //company
        company = new Company(accountContainer, null, null,
                projectContainer, null, null, null);

        //controller
        getListOfProjectscontroller = new GetListOfProjectsController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountManager = null;
        customer = null;
        projectTypology = null;
        businessSector = null;
        projectOne = null;
        projectTwo = null;
        accounts.clear();
        projects.clear();
        accountContainer = null;
        projectContainer = null;
        company = null;
        getListOfProjectscontroller = null;
    }

    /**
     * Test that returns the list of all projects to an Account with the Manager profile.
     */

    @Test
    void ensureThatAllProjectsAreListedSuccessfully() {
        // Arrange
        ManagerListProjectsDTO projectDTOOne = new ManagerListProjectsDTO("AA001", "software development " +
                "management",
                "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDTO projectDTOTwo = new ManagerListProjectsDTO("AA002", "project software", "isep",
                "planned",
                "fixed cost", "fishing");

        List<ManagerListProjectsDTO> expectDTOs = new ArrayList<>();
        expectDTOs.add(projectDTOOne);
        expectDTOs.add(projectDTOTwo);

        // Act
        List<ManagerListProjectsDTO> result = getListOfProjectscontroller.getListOfProjects("mike@isep" +
                ".ipp.pt");

        // Assert
        assertEquals(expectDTOs, result);
    }

    /**
     * Test that resturns an empty list when an unauthorized user access the list of projects.
     */
    @Test
    void ensureThatNoProjectsAreListedWhenRequestedByAnotherProfile() {
        // Arrange
        List<ManagerListProjectsDTO> expectDTOs = new ArrayList<>();

        // Act
        List<ManagerListProjectsDTO> result = getListOfProjectscontroller.getListOfProjects("paul@isep" +
                ".ipp.pt");

        // Assert
        assertEquals(expectDTOs, result);
    }

}