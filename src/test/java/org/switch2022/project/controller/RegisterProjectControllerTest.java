package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTOAsManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterProjectControllerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne;
  Profile profileOne, profileTwo, profileThree;
  ProjectDTOAsManager projectOneDTO, projectTwoDTO;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  BusinessSector businessSector;
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

    businessSectorContainer = new BusinessSectorContainer();
    businessSector = new BusinessSector("fishing");

    projectContainer = new ProjectContainer();
    projectOneDTO = new ProjectDTOAsManager("AA001", "software development management", "Peter","228674498",
            "Fixed cost", "Fishing");
    projectTwoDTO = new ProjectDTOAsManager("AA004", "software development management", "Mary",
            "228674498","Fixed cost", "Sports");
    projectContainer.registerProject(projectOneDTO,projectTypologyContainer,customerContainer,businessSectorContainer);
    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, null, customerContainer);
    registerProjectController = new RegisterProjectController(company);
  }
  @AfterEach  void tearDown() {
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
   * Test to ensure that a project is registered
   */
  @Test
  void projectRegistered() {
    // Arrange
    company.changeProfile("mike@isep.ipp.pt", "manager");

    // Act
    boolean expected = true;
    boolean result = registerProjectController.registerProject(projectTwoDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that a project is not registered
   */
  @Test
  void projectNotRegistered() {

    // Arrange
    company.changeProfile("mike@isep.ipp.pt", "manager");
    boolean expected = false;

    //Act
    boolean result = registerProjectController.registerProject(projectOneDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that the user is not able to register a project
   */
  @Test
  void managerNotValid() {
    // Act
    boolean expected = false;
    boolean result = registerProjectController.registerProject(projectTwoDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
}