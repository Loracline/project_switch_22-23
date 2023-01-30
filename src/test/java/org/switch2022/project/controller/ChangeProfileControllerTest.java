package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.container.*;

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