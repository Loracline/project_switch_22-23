package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeProfileControllerTest {
  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne;
  Account accountTwo;
  Profile profileOne;
  Project project;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSectorContainer businessSectorContainer;
  /* Project typology Container*/
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  Company company;
  ChangeProfileController controller;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  CustomerContainer customerContainer;
  List<Customer> customers;

  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  AccountInProject accountInProject1, accountInProject2;
  List<AccountInProject> accountsInProject;
  AccountInProjectContainer accountInProjectContainer;


  @BeforeEach
  void setUp() {

    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accounts.add(accountOne);

    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profileOne = new Profile("Administrator");
    profiles.add(profileOne);

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    //Project typology
    projectTypologyContainer = new ProjectTypologyContainer();
    projectTypologyContainer.createProjectTypology("Fixed Cost");
    projectTypologyContainer.createProjectTypology("Fixed time and materials");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer();

    project = new Project("proj001", "software development management",new Customer(
            "ISEP","228674498"),
            new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));

    List<Project> projects = new ArrayList<>();
    projects.add(project);
    projectContainer = new ProjectContainer(projects);

    accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject1);
    accountsInProject.add(accountInProject2);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

    controller = new ChangeProfileController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    profileOne = null;
    accountContainer = null;
    profileContainer = null;
    company = null;
    controller = null;
    accounts.clear();
    profiles.clear();
    project = null;
    projects.clear();
    projectContainer = null;
    businessSectorContainer = null;
    customers.clear();
    customerContainer = null;
  }

  @Test
  void ensureAccountProfileIsChangedSuccessfully() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = controller.changeProfile("mike@isep.ipp.pt", "Administrator");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = controller.changeProfile("mike@isep.ipp.pt", "Manager");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = controller.changeProfile("mikke@isep.ipp.pt", "Administrator");
    //Assert
    assertEquals(expected, result);
  }
}