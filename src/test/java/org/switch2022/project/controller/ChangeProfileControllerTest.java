package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeProfileControllerTest {
  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne;
  Project project;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  BusinessSectorContainer businessSectorContainer;
  /* Project typology Container*/
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  Company company;
  ChangeProfileController controller;
  CustomerContainer customerContainer;
  AccountInProjectContainer accountInProjectContainer;


  @BeforeEach
  void setUp() {

    accountContainer = new AccountContainer();
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
    profileContainer = new ProfileContainer();
    profileContainer.createProfile("Administrator");

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);

    controller = new ChangeProfileController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountContainer = null;
    profileContainer = null;
    company = null;
    controller = null;
    project = null;
    projectContainer = null;
    businessSectorContainer = null;
    customerContainer = null;
  }

  @Test
  void ensureAccountProfileIsChangedSuccessfully() {
    //Arrange
    //set profileOne (Administrator) to accountOne
    company.changeProfile("mike@isep.ipp.pt", "Administrator");
    String emailActor = accountOne.getEmail(); //Administrator
    boolean expected = true;
    //Act
    boolean result = controller.changeProfile("mike@isep.ipp.pt", "Administrator",emailActor);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
    //Arrange
    //set profileOne (Administrator) to accountOne
    company.changeProfile("mike@isep.ipp.pt", "Administrator");
    String emailActor = accountOne.getEmail(); //Administrator
    boolean expected = false;
    //Act
    boolean result = controller.changeProfile("mike@isep.ipp.pt", "Manager",emailActor);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
    //Arrange
    //set profileOne (Administrator) to accountOne
    company.changeProfile("mike@isep.ipp.pt", "Administrator");
    String emailActor = accountOne.getEmail(); //Administrator
    boolean expected = false;
    //Act
    boolean result = controller.changeProfile("mikke@isep.ipp.pt", "Administrator",emailActor);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotAuthorized() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = controller.changeProfile("mike@isep.ipp.pt", "Manager","mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
}