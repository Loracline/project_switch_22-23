package org.switch2022.project.model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  Account accountOne, accountTwo, accountThree, accountFour;
  List<Account> accounts;
  AccountContainer accountContainer;

  @BeforeEach
  void setUp() {
    //create users
    accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null, true);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null, false);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null, true);
    accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null, false);


    //add users to accounts list
    accounts = new ArrayList<>();
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    //initialize account container with filled account list
    accountContainer = new AccountContainer(accounts);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree = null;
    accountFour = null;

    accounts.clear();

    accountContainer = null;
  }

  @Test
  void ensureSameObjectEqualsItself() {
    //ARRANGE
    //declare initialized account container as reference
    AccountContainer reference = this.accountContainer;
    //initialized other company with the reference
    AccountContainer other = reference;
    boolean expected = true;
    //ACT
    boolean result = reference.equals(other);
    //ASSERT
    assertEquals(expected, result);
  }
  @Test
  void ensureTwoAccountsAreNotTheSame() {
    //ARRANGE
    //declare initialized account container as reference
    AccountContainer reference = this.accountContainer;
    //create other account container to compare with
    List<Account> otherAccounts = new ArrayList<>();
    otherAccounts.add(accountOne);
    otherAccounts.add(accountTwo);

    AccountContainer other =
            new AccountContainer(otherAccounts);

    boolean expected = false;
    //ACT
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
  void ensureThatAccountListIsRetrieved() {
    //ARRANGE
    //create reference list
    List<Account> expected = new ArrayList<>();
    expected.add(accountOne);
    expected.add(accountTwo);
    expected.add(accountThree);

    //ACT
    //get accounts list contained in account container
    List<Account> result = accountContainer.getAccounts();

    //ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountIsAddedIfEmailIsUnique() {
    //ARRANGE
    boolean expected = true;

    //ACT
    boolean result = accountContainer.addAccount(accountFour);

    //ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountIsNotAddedIfEmailIsDuplicated() {
    //ARRANGE
    boolean expected = false;

    //ACT
    boolean result = accountContainer.addAccount(accountThree);

    //ASSERT
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