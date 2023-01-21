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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests below.
 */

class ListAccountControllerTest {
  Account accountOne, accountTwo;
  Profile profileOne, profileTwo;
  List<Account> accounts;
  List<Profile> profiles;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  Project project;
  List<Project> projects;
  ProjectContainer projectContainer;
  Company company;
  ProjectTypology projectTypology;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  List<Customer> customers;
  ProjectTypologyContainer projectTypologyContainer;
  List<ProjectTypology> typologies = new ArrayList<>();

  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  AccountInProject accountInProject1, accountInProject2;
  List<AccountInProject> accountsInProject;
  AccountInProjectContainer accountInProjectContainer;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);


    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");


    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    customerOne = new Customer("ISEP");
    customerTwo = new Customer("PortoTech");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);


    accountInProject1 = new AccountInProject(accountOne, project,
            costPerHour, percentageAllocation, startDate);
    accountInProject2 = new AccountInProject(accountTwo, project,
            costPerHour, percentageAllocation, startDate);
    accountInProject1.setRole("team member");
    accountInProject2.setRole("team member");
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject1);
    accountsInProject.add(accountInProject2);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
  }



  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    profileOne = null;
    profileTwo = null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSector = null;
    businessSectors.clear();
    businessSectorContainer = null;
    project = null;
    projects.clear();
    projectContainer = null;
    customerOne = null;
    customerTwo = null;
    customers.clear();
    customerContainer = null;
    company = null;
  }

  @Test
  void ensureAllAccountsAreListedSuccessfully() {
    List<Account> expected = accountContainer.getAccounts();
    ListAccountController newListAccountController = new ListAccountController(company);

    // Act
    List<Account> result = newListAccountController.listAllAccounts();

    // Assert
    assertEquals(expected, result);
  }

}