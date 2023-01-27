package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
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
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSectorContainer businessSectorContainer;
  Project project;
  ProjectContainer projectContainer;
  Company company;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  ProjectTypologyContainer projectTypologyContainer;

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

    accountContainer = new AccountContainer();
    accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountContainer.addAccount("Paul", "paul@isep.ipp.pt", 939855689, null);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");


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
  }



  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    profileOne = null;
    profileTwo = null;
    accountContainer = null;
    profileContainer = null;
    businessSectorContainer = null;
    project = null;
    projectContainer = null;
    customerOne = null;
    customerTwo = null;
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