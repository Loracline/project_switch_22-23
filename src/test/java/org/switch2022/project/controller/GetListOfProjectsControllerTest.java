package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListOfProjectsControllerTest {

  Account accountOne, accountManager;
  Customer customer;
  ProjectTypology projectTypology;
  BusinessSector businessSector;
  Project projectOne, projectTwo;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectContainer projectContainer;
  ProjectTypologyContainer projectTypologyContainer;
  BusinessSectorContainer businessSectorContainer;
  CustomerContainer customerContainer;
  Company company;
  GetListOfProjectsController getListOfProjectscontroller;

  @BeforeEach
  void setUp() {
    //accounts
    accountOne = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
    accountManager = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

    //account container
    accountContainer = new AccountContainer();
    accountContainer.addAccount("Paul", "paul@isep.ipp.pt", 939855689, null);
    accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);

    //profile container
    profileContainer = new ProfileContainer();
    profileContainer.createProfile("Administrator");
    profileContainer.createProfile("Manager");

    //customer
    customer = new Customer("isep", "222333444");
    customerContainer = new CustomerContainer();
    customerContainer.addCustomer(customer.getCustomerName(), customer.getCustomerNif());
    //project typology
    projectTypology = new ProjectTypology("Fixed cost");
    projectTypologyContainer = new ProjectTypologyContainer();
    projectTypologyContainer.createProjectTypology(projectTypology.getProjectTypologyName());
    //business sector
    businessSector = new BusinessSector("fishing");
    businessSectorContainer = new BusinessSectorContainer();
    businessSectorContainer.createBusinessSector(businessSector.getBusinessSectorName());
    //projects
    projectOne = new Project("AA001", "software development management",
            customer, projectTypology, businessSector);
    projectTwo = new Project("AA002", "project software", customer,
            projectTypology, businessSector);
    ProjectCreationDto projectOneDTO = new ProjectCreationDto(projectOne.getProjectCode(),
            projectOne.getProjectName(), projectOne.getCustomer().getCustomerName(),
            projectOne.getCustomer().getCustomerNif(),
            projectOne.getProjectTypology().getProjectTypologyName(),
            projectOne.getBusinessSector().getBusinessSectorName());
    ProjectCreationDto projectOneTwo = new ProjectCreationDto(projectTwo.getProjectCode(),
            projectTwo.getProjectName(), projectTwo.getCustomer().getCustomerName(),
            projectTwo.getCustomer().getCustomerNif(),
            projectTwo.getProjectTypology().getProjectTypologyName(),
            projectTwo.getBusinessSector().getBusinessSectorName());
    projectContainer = new ProjectContainer();
    projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer);
    projectContainer.registerProject(projectOneTwo, projectTypologyContainer, customerContainer,
            businessSectorContainer);
    //company
    company = new Company(accountContainer, profileContainer, null,
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
    accountContainer = null;
    projectContainer = null;
    profileContainer = null;
    company = null;
    getListOfProjectscontroller = null;
  }

  /**
   * Test that returns the list of all projects to an Account with the Manager profile.
   */

  @Test
  void ensureThatProjectsIsListedSuccessfully() {
    // Arrange
    company.changeProfile("mike@isep.ipp.pt", "Manager");
    ProjectDto projectDTOOne = new ProjectDto("AA001", "software " + "development " + "management", "isep", "planned", "fixed cost", "fishing");
    ProjectDto projectDTOTwo = new ProjectDto("AA002", "project " + "software",
            "isep", "planned", "fixed cost", "fishing");
    List<ProjectDto> expectDTOs = new ArrayList<>();
    expectDTOs.add(projectDTOOne);
    expectDTOs.add(projectDTOTwo);
    // Act
    List<ProjectDto> result = getListOfProjectscontroller.getListOfProjects("mike" + "@isep" + ".ipp.pt");
    // Assert
    assertEquals(expectDTOs, result);
  }

  /**
   * Test that returns an empty list when an unauthorized user access the list of projects.
   */
  @Test
  void ensureThatNoProjectsAreListedWhenRequestedByAnotherProfile() {
    // Arrange
    List<ProjectDto> expectDTOs = new ArrayList<>();
    // Act
    List<ProjectDto> result = getListOfProjectscontroller.getListOfProjects("paul" + "@isep" + ".ipp.pt");
    // Assert
    assertEquals(expectDTOs, result);
  }

}