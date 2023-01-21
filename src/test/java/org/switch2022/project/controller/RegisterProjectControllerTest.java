package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterProjectControllerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo, accountThree;
  Profile profileOne, profileTwo, profileThree;
  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  ProjectTypology projectTypologyOne, projectTypologyTwo, projectTypology;
  Project projectOne, projectTwo, projectThree;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  List<ProjectTypology> typologies;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  ProjectDTO projectOneDTO, projectTwoDTO;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  List<Customer> customers;

  AccountInProject accountInProject;

  List<AccountInProject> accountsInProject;

  AccountInProjectContainer accountInProjectContainer;
  Company company;
  RegisterProjectController registerProjectController;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");
    profileThree = new Profile("Manager");
    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    businessSector = new BusinessSector("fishing");
    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    projectTypologyOne = new ProjectTypology("Fixed Cost");
    projectTypologyTwo = new ProjectTypology("Fixed time and materials");
    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer(typologies);
    typologies.add(projectTypologyOne);
    typologies.add(projectTypologyTwo);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customerOne = new Customer("ISEP");
    customerTwo = new Customer("PortoTech");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);

    projectTypology = new ProjectTypology("fixed cost");
    businessSector = new BusinessSector("IT Sector");
    costPerHour = 7.5f;
    percentageAllocation = 45.0f;
    startDate = LocalDate.of(2023,01,19);
    accountInProject = new AccountInProject(accountOne, projectOne, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    projectOne = new Project("AA001", "software development management", customerOne, projectTypologyOne,
            businessSector);
    projectTwo = new Project("AA002", "project software", customerTwo, projectTypologyTwo, businessSector);
    projectThree = new Project("AA003", "motor software", customerTwo, projectTypologyTwo, businessSector);
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);

    projectOneDTO = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwoDTO = new ProjectDTO("AA004", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

    registerProjectController = new RegisterProjectController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree = null;
    profileOne = null;
    profileTwo = null;
    profileThree = null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSector = null;
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