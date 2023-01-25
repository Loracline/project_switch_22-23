package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterProjectControllerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne;
  Profile profileOne, profileTwo, profileThree;
  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  ProjectTypology projectTypologyOne, projectTypologyTwo;
  Project projectOne, projectTwo, projectThree;
  BusinessSector businessSectorOne;
  ProjectDTO projectOneDTO, projectTwoDTO;
  Customer customerOne, customerTwo;
  AccountInProject accountInProject;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  List<ProjectTypology> typologies;
  List<BusinessSector> businessSectors;
  List<Customer> customers;
  List<AccountInProject> accountsInProject;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  AccountInProjectContainer accountInProjectContainer;
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

    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);

    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);

    businessSectorOne = new BusinessSector("IT Sector");
    costPerHour = 7.5f;
    percentageAllocation = 45.0f;
    startDate = LocalDate.of(2023,01,19);
    accountInProject = new AccountInProject(accountOne, projectOne, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    projectOne = new Project("AA001", "software development management", new Customer(
            "Peter","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Fishing"));
    projectTwo = new Project("AA002", "project software", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Fishing"));
    projectThree = new Project("AA00", "motor software", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Fishing"));
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);

    projectOneDTO = new ProjectDTO("AA001", "software development management", "Peter",
            "Fixed cost", "Fishing");
    projectTwoDTO = new ProjectDTO("AA004", "software development management", "Mary",
            "Fixed cost", "Sports");

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

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
    businessSectorOne = null;
    businessSectors.clear();
    businessSectorContainer = null;
    projectTypologyOne = null;
    projectTypologyTwo = null;
    typologies.clear();
    projectTypologyContainer = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    customerOne = null;
    customerTwo = null;
    customers.clear();
    customerContainer = null;
    company = null;
  }

  @Test
  void projectRegistered() {
    accountOne.setProfile(profileThree);
    boolean expected = true;
    boolean result = registerProjectController.registerProject(projectThree, accountOne.getEmail());
    assertEquals(expected, result);
  }

  @Test
  void projectNotRegistered() {
    accountOne.setProfile(profileThree);
    boolean expected = false;
    boolean result = registerProjectController.registerProject(projectTwo, accountOne.getEmail());
    assertEquals(expected, result);
  }

  @Test
  void managerNotValid() {
    accountOne.setProfile(profileTwo);
    boolean expected = false;
    boolean result = registerProjectController.registerProject(projectThree, accountOne.getEmail());
    assertEquals(expected, result);
  }
}