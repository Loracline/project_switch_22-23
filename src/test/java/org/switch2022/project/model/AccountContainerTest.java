package org.switch2022.project.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountContainerTest {
  Account accountOne, accountTwo;
  Profile profileOne, profileTwo;
  List<Account> accounts;
  List<Profile> profiles;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  Company company;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
    accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null, true);


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

    company = new Company(accountContainer, profileContainer);
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
    company = null;
  }
  @Test
  void ensureAccountContainerIsSuccessfullyCreated(){
    AccountContainer controller = new AccountContainer(accounts);
  }

  @Test
  void ensureSameObjectEqualsItself() {
    AccountContainer reference = new AccountContainer(accounts);
    AccountContainer other = reference;
    boolean expected = true;
    boolean result = reference.equals(other);
    assertEquals(expected, result);
  }
  @Test
  void ensureTwoProfilesAreNotTheSame() {
    AccountContainer reference = new AccountContainer(accounts);
    AccountContainer other = new AccountContainer();
    boolean expected = false;
    boolean result = reference.equals(other);
  }

  @Test
  void ensureObjectDoesNotEqualsOtherTypeOfObject() {
    AccountContainer reference = new AccountContainer(accounts);
    ProfileContainer other = new ProfileContainer(profiles);
    boolean expected = false;
    boolean result = reference.equals(other);
    assertEquals(expected, result);
  }


  @Test
  void ensureThatCopyOfAccountListIsSuccessfullyGenerated() {
    AccountContainer listOfAccounts = new AccountContainer(accounts);

    List<Account> copy = new ArrayList<>();
    copy.add(accountOne);
    copy.add(accountTwo);

    // Act
    List<Account> result = listOfAccounts.getAccounts();

    // Assert
    assertEquals(copy, result);
  }

  @Test
  void ensureThatModifiedCopyDoesNotEqualOriginal() {
    AccountContainer newAccountContainer = new AccountContainer(accounts);

    List<Account> copy = new ArrayList<>();
    copy.add(accountOne);

    // Act
    List<Account> result = newAccountContainer.getAccounts();

    // Assert
    assertNotEquals(copy, result);
  }

}