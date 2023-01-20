package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.container.ProjectContainer;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeStatusControllerTest {


  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */


  Account accountOne, accountTwo, accountThree, accountFour;
  Profile profileOne, profileTwo;
  BusinessSector businessSector;
  ProjectTypology projectTypology;
  Project project;
  Customer customer;
  List<Account> accounts;
  List<Profile> profiles;

  List<BusinessSector> businessSectors;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSectorContainer businessSectorContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  Company company;
  ChangeStatusController accountStatusToBeChanged;

  List<Project> projects;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountThree = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);
    accountFour = new Account("Paul", "paul@isep.ipp.pt", 932755689, null);

    List<Account> accountList = new ArrayList<>();
    accountContainer = new AccountContainer(accountList);
    accountList.add(accountOne);
    accountList.add(accountTwo);
    accountList.add(accountThree);
    accountList.add(accountFour);

    profileOne = new Profile("Administrator");

    List<Profile> profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer= new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    company = new Company(accountContainer, profileContainer,businessSectorContainer, projectContainer, projectTypologyContainer);
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

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer);

    accountStatusToBeChanged = new ChangeStatusController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    profileOne = null;
    accountContainer = null;
    profileContainer = null;
    businessSector=null;
    businessSectors.clear();
    businessSectorContainer=null;
    project = null;
    projects.clear();
    projectContainer = null;
    company = null;
    accountStatusToBeChanged = null;
  }

  @Test
  void ensureAccountStatusIsChangedToInactive() {
    boolean expected = false;
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);
    boolean result = accountOne.equals(accountTwo);
    assertEquals(expected,result);
  }

  @Test
  void ensureAccountStatusIsChangedToActive() {
    boolean expected = true;
    accountStatusToBeChanged.changeStatus("paul@isep.ipp.pt", true);
    boolean result = accountThree.equals(accountFour);
    assertEquals(expected,result);
  }
}