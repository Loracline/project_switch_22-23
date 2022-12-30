package org.switch2022.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeStatusControllerTest {

  @Test
  void changeStatusAccountToInactive() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account);

    // Act
    accountStatusToBeChanged.changeStatus(false);

    // Assert
    assertEquals(false, account.accountStatus());
  }

  @Test
  void changeStatusAccountToActive() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);
    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account);

    // Act
    accountStatusToBeChanged.changeStatus(true);

    // Assert
    assertEquals(true, account.accountStatus());
  }

  @Test
  void statusPretendedIsEqualToActualStatusTrue() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account);

    // Act
    accountStatusToBeChanged.changeStatus(true);

    // Assert
    assertEquals(true, account.accountStatus());
  }

  @Test
  void statusPretendedIsEqualToActualStatusFalse() {
    // Arrange
    Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, false);
    ChangeStatusController accountStatusToBeChanged = new ChangeStatusController(account);

    // Act
    accountStatusToBeChanged.changeStatus(false);

    // Assert
    assertEquals(false, account.accountStatus());
  }
}