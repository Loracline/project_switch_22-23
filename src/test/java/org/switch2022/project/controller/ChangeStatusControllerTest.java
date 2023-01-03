package org.switch2022.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeStatusControllerTest {

  @Test
  void changeStatusAccountToInactive() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);

    // Assert
    assertEquals(false, account.accountStatus());
  }

  @Test
  void changeStatusAccountToActive() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);

    // Assert
    assertEquals(true, account.accountStatus());
  }

  @Test
  void statusPretendedIsEqualToActualStatusTrue() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);

    // Assert
    assertEquals(true, account.accountStatus());
  }

  @Test
  void statusPretendedIsEqualToActualStatusFalse() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);

    // Assert
    assertEquals(false, account.accountStatus());
  }
  @Test
  void emailDoesNotExist() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    String expected = "E-mail does not exist";
    IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
            () -> {
              accountStatusToBeChanged.changeStatus("paul@isep.ipp.pt", false);
            });

    Assertions.assertEquals(expected, result.getMessage());
  }

  @Test
  void emailDoesNotExistTestTwo() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);

    List<Account> accountList = new ArrayList<>();
    List<Profile> profileList = new ArrayList<>();
    Company listOfAccounts = new Company(accountList, profileList);
    accountList.add(0, account);

    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account, listOfAccounts);

    // Act
    String expected = "E-mail does not exist";
    IllegalArgumentException result = Assertions.assertThrows(IllegalArgumentException.class,
            () -> {
              accountStatusToBeChanged.changeStatus("mike@isep.ipp.com", false);
            });

    Assertions.assertEquals(expected, result.getMessage());
  }
}