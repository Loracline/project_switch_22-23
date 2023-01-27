package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;
import org.switch2022.project.utils.dto.ProjectDTO;

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
        //accounts
        accountOne = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountManager = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountManager.setProfile(new Profile("Manager"));

        accounts = new ArrayList<>();
        accounts.add(accountManager);
        accounts.add(accountOne);
        accountContainer = new AccountContainer(accounts);

        //customer
        customer = new Customer("isep", "222333444");
        CustomerContainer customerContainer = new CustomerContainer();
        customerContainer.addCustomer(customer.getCustomerName(), customer.getCustomerNif());

        //project typology
        projectTypology = new ProjectTypology("Fixed cost");
        ProjectTypologyContainer projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology(projectTypology.getProjectTypologyName());

        //business sector
        businessSector = new BusinessSector("fishing");
        BusinessSectorContainer businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector(businessSector.getBusinessSectorName());

        //projects
        projectOne = new Project("AA001", "software development management",
                customer, projectTypology, businessSector);
        projectTwo = new Project("AA002", "project software", customer,
                projectTypology, businessSector);

        projects = new ArrayList<>();
        projects.add(projectOne);
        projects.add(projectTwo);

        ProjectDTO projectOneDTO = new ProjectDTO(projectOne.getProjectCode(),
                projectOne.getProjectName(), projectOne.getCustomer().getCustomerName(),
                projectOne.getCustomer().getCustomerNif(),
                projectOne.getProjectTypology().getProjectTypologyName(),
                projectOne.getBusinessSector().getBusinessSectorName());

        ProjectDTO projectOneTwo = new ProjectDTO(projectTwo.getProjectCode(),
                projectTwo.getProjectName(), projectTwo.getCustomer().getCustomerName(),
                projectTwo.getCustomer().getCustomerNif(),
                projectTwo.getProjectTypology().getProjectTypologyName(),
                projectTwo.getBusinessSector().getBusinessSectorName());

        projectContainer = new ProjectContainer();
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer,
                customerContainer, businessSectorContainer);
        projectContainer.registerProject(projectOneTwo, projectTypologyContainer,
                customerContainer, businessSectorContainer);

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
    void ensureThatProjectsIsListedSuccessfully() {
        // Arrange
        ManagerListProjectsDTO projectDTOOne = new ManagerListProjectsDTO("AA001", "software " +
                "development " +
                "management", "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDTO projectDTOTwo = new ManagerListProjectsDTO("AA002", "project " +
                "software",
                "isep", "planned", "fixed cost", "fishing");

        List<ManagerListProjectsDTO> expectDTOs = new ArrayList<>();
        expectDTOs.add(projectDTOOne);
        expectDTOs.add(projectDTOTwo);

        // Act
        List<ManagerListProjectsDTO> result = getListOfProjectscontroller.getListOfProjects("mike" +
                "@isep" +
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
        List<ManagerListProjectsDTO> result = getListOfProjectscontroller.getListOfProjects("paul" +
                "@isep" +
                ".ipp.pt");

        // Assert
        assertEquals(expectDTOs, result);
    }

}