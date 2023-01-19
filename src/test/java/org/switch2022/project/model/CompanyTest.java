package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo;
  Profile profileOne, profileTwo;

  List<Account> accounts;
  List<Profile> profiles;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;

  Company company;


  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

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

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer= new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    company = new Company(accountContainer, profileContainer,businessSectorContainer);
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
    businessSector=null;
    businessSectors.clear();
    businessSectorContainer=null;
    company = null;
  }

  @Test
  void ensureAccountContainerIsRetrieved() {
    AccountContainer expected = accountContainer;
    AccountContainer result = company.getAccountContainer();
    assertEquals(expected, result);
  }


  @Test
  void ensureProfileIsCreatedSuccessfuly() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = company.createProfile("manager");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureProfileIsCreatedUnsuccessfuly() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.createProfile("user");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountStatusIsChangedToInactive() {
    boolean expected = false;
    accountContainer.changeStatus("mike@isep.ipp.pt", false);
    boolean result = accountOne.equals(accountTwo);
    assertEquals(expected, result);
  }

  @Test
  void ensureAccountStatusIsChangedToActive() {
    boolean expected = true;
    accountContainer.changeStatus("mike@isep.ipp.pt", true);
    boolean result = accountOne.equals(accountTwo);
    assertEquals(expected, result);
  }
  @Test
  void ensureBusinessSectorIsCreatedSuccessfuly() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = company.addBusinessSector("mining");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureBusinessSectorIsCreatedUnsuccessfuly() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.addBusinessSector("fishing");
    //Assert
    assertEquals(expected, result);
  }
}

    /*
    @Test
    void ensureAccountProfileIsChangedSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = company.changeProfile("mike@isep.ipp.pt", "Administrator");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.changeProfile("mike@isep.ipp.pt", "Manager");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.changeProfile("mikke@isep.ipp.pt", "Administrator");
        //Assert
        assertEquals(expected, result);
    }
    */
