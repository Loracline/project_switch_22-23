package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTOAsManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterProjectControllerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne;
  Profile profileOne, profileTwo, profileThree;
  Project projectOne, projectTwo, projectThree;
  ProjectDTOAsManager projectOneDTO, projectTwoDTO;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  List<ProjectTypology> typologies;
  List<Customer> customers;
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
    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");
    profileThree = new Profile("Manager");
    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer();

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer();

    businessSectorContainer = new BusinessSectorContainer();
    businessSector = new BusinessSector("fishing");
    projects = new ArrayList<>();
    projectOne = new Project("AA001", "software development management", new Customer(
            "Peter","228674498"), new ProjectTypology("Fixed cost"),
            new BusinessSector("Fishing"));
    projectTwo = new Project("AA002", "project software", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Fishing"));
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer();

    projectOneDTO = new ProjectDTOAsManager("AA001", "software development management", "Peter","228674498",
            "Fixed cost", "Fishing");
    projectTwoDTO = new ProjectDTOAsManager("AA004", "software development management", "Mary",
            "228674498","Fixed cost", "Sports");

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
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSectorContainer = null;
    typologies.clear();
    projectTypologyContainer = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    customers.clear();
    customerContainer = null;
    company = null;
  }
  /**
   * Test to ensure that a project is registered
   */
  @Test
  void projectRegistered() {
    // Arrange
    accountOne.setProfile(profileThree);

    // Act
    boolean expected = true;
    boolean result = registerProjectController.registerProject(projectOneDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that a project is not registered
   */
  @Test
  void projectNotRegistered() {
    // Arrange
    accountOne.setProfile(profileThree);

    // Act
    company.registerProject(projectOneDTO);
    boolean expected = false;
    boolean result = registerProjectController.registerProject(projectOneDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that the user is not able to register a project
   */
  @Test
  void managerNotValid() {
    // Arrange
    accountOne.setProfile(profileTwo);

    // Act
    boolean expected = false;
    boolean result = registerProjectController.registerProject(projectTwoDTO, accountOne.getEmail());

    // Assert
    assertEquals(expected, result);
  }
}