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
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);

    List<Account> accountList = new ArrayList<>();
    accountContainer = new AccountContainer(accountList);
    accountList.add(accountOne);

    List<Profile> profileList = new ArrayList<>();
    profileContainer = new ProfileContainer(profileList);

    company = new Company(accountContainer, profileContainer);

    accountStatusToBeChanged = new ChangeStatusController(company);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountContainer = null;
    company = null;
    accountStatusToBeChanged = null;
  }

  @Test
  void changeStatusAccountToInactive() {
    boolean result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);
    assertEquals(false, result);
  }

  @Test
  void changeStatusAccountToActive() {
    boolean result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);
    assertEquals(true, result);
  }

  @Test
  void statusPretendedIsEqualToActualStatusTrue() {
    boolean result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", true);
    assertEquals(true, result);
  }

  @Test
  void statusPretendedIsEqualToActualStatusFalse() {
    boolean result = accountStatusToBeChanged.changeStatus("mike@isep.ipp.pt", false);
    assertEquals(false, result);
  }
}