package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeStatusControllerTest {

  Account accountOne;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  Company company;
  ChangeStatusController accountStatusToBeChanged;

  @BeforeEach
  void setUp(){
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    accountContainer = new AccountContainer(accountList);
    accountList.add(accountOne);

    List<Profile> profileList = new ArrayList<>();
    profileContainer = new ProfileContainer(profileList);

    company = new Company(accountContainer,profileContainer);

    accountStatusToBeChanged = new ChangeStatusController(company);
  }

  @AfterEach
  void tearDown(){
    accountOne = null;
    accountContainer = null;
    company = null;
    accountStatusToBeChanged = null;
  }

  @Test
  void changeStatusAccountToInactive() {
    // Arrange
    /*Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    accountList.add(account);
    AccountContainer listOfAccounts = new AccountContainer(accountList);

    List<Profile> profileList = new ArrayList<>();
    ProfileContainer listOfProfiles = new ProfileContainer(profileList);

    Company company = new Company(listOfAccounts, listOfProfiles);*/


    // Act
    int result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);

    // Assert
    assertEquals(0, result);
  }

  @Test
  void changeStatusAccountToActive() {
    /*// Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    accountList.add(account);
    AccountContainer listOfAccounts = new AccountContainer(accountList);

    List<Profile> profileList = new ArrayList<>();
    ProfileContainer listOfProfiles = new ProfileContainer(profileList);

    Company company = new Company(listOfAccounts, listOfProfiles);*/

    //ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(company);
    // Act
    int result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);

    // Assert
    assertEquals(1, result);
  }

  @Test
  void statusPretendedIsEqualToActualStatusTrue() {
    /*// Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    accountList.add(account);
    AccountContainer listOfAccounts = new AccountContainer(accountList);

    List<Profile> profileList = new ArrayList<>();
    ProfileContainer listOfProfiles = new ProfileContainer(profileList);

    Company company = new Company(listOfAccounts, listOfProfiles);*/

    //ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(company);
    // Act
    int result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);

    // Assert
    assertEquals(1, result);
  }

  @Test
  void statusPretendedIsEqualToActualStatusFalse() {
    /*// Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    accountList.add(account);
    AccountContainer listOfAccounts = new AccountContainer(accountList);

    List<Profile> profileList = new ArrayList<>();
    ProfileContainer listOfProfiles = new ProfileContainer(profileList);

    Company company = new Company(listOfAccounts, listOfProfiles);*/

    //ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(company);
    // Act
    int result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);

    // Assert
    assertEquals(0, result);
  }
}