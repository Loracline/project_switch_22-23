package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

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
  BusinessSector businessSector;
  ProjectTypology projectTypology;
  Project project;
  Customer customer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSectorContainer businessSectorContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  Company company;
  ChangeProfileController controller;
  List<BusinessSector> businessSectors;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;

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

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    projectTypology = new ProjectTypology("Fixed Cost");

    List<ProjectTypology> typologies = new ArrayList<>();
    typologies.add(projectTypology);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customer = new Customer("ISEP");
    project = new Project("proj001", "software development management", customer,
            projectTypology, businessSector);

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
            projectContainer, projectTypologyContainer, accountInProjectContainer);

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
    businessSector = null;
    businessSectors.clear();
    businessSectorContainer = null;
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