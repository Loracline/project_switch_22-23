package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeStatusControllerTest {


  /**
   * BeforeEach and AfterEach executes common code before running the tests below.
   */


  Account accountOne, accountTwo, accountThree, accountFour;
  Profile profileOne, profileTwo;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  Company company;
  ChangeStatusController accountStatusToBeChanged;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
    accountTwo = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);
    accountThree = new Account("Paul", "paul@isep.ipp.pt", 932755689, null, false);
    accountFour = new Account("Paul", "paul@isep.ipp.pt", 932755689, null, true);

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

    company = new Company(accountContainer, profileContainer);

    accountStatusToBeChanged = new ChangeStatusController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    profileOne = null;
    accountContainer = null;
    profileContainer = null;
    company = null;
    accountStatusToBeChanged = null;
  }


  @Test
  void createCompanySuccessfully() {
    ChangeStatusController controller = new ChangeStatusController(company);
  }

  @Test
  void changeStatusAccountToInactive() {
    boolean expected = true;
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);
    boolean result = accountOne.equals(accountTwo);
    assertEquals(expected,result);
  }

  @Test
  void changeStatusAccountToActive() {
    boolean expected = true;
    accountStatusToBeChanged.changeStatus("paul@isep.ipp.pt", true);
    boolean result = accountThree.equals(accountFour);
    assertEquals(expected,result);
  }
}