package org.switch2022.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AccountContainerTest {

  @Test
  void createListOfAccounts() {
    Account accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
    List<Account> accounts = new ArrayList<>();
    accounts.add(accountOne);
  }
}