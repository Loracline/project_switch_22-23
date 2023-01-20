package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.AccountContainer;
import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.ProfileContainer;
import org.switch2022.project.container.ProjectTypologyContainer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo, accountThree;
  Profile profileOne, profileTwo, profileThree;
  ProjectTypology projectTypologyOne, projectTypologyTwo;
  List<Account> accounts;
  List<Profile> profiles;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  List<ProjectTypology> typologies;
  Company company;


  @BeforeEach
  void setUp() {
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);

    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");
    profileThree= new Profile ("Manager");


    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer= new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    company = new Company(accountContainer, profileContainer,businessSectorContainer);

    projectTypologyOne = new ProjectTypology("Fixed Cost");
    projectTypologyTwo= new ProjectTypology("Fixed time and materials");

    typologies = new ArrayList<>();
    projectTypologyContainer= new ProjectTypologyContainer(typologies);
    typologies.add(projectTypologyOne);
    typologies.add(projectTypologyTwo);

  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree= null;
    profileOne = null;
    profileTwo = null;
    profileThree=null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSector=null;
    businessSectors.clear();
    businessSectorContainer=null;
    projectTypologyOne= null;
    projectTypologyTwo= null;
    typologies.clear();
    projectTypologyContainer=null;

    company = null;
  }

  @Test
  void ensureAccountContainerIsRetrieved() {
    AccountContainer expected = accountContainer;
    AccountContainer result = company.getAccountContainer();
    assertEquals(expected, result);
  }


  @Test
  void ensureProfileIsCreatedSuccessfully() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = company.createProfile("manager");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureProfileIsCreatedUnsuccessfully() {
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
    boolean expected = false;
    accountContainer.changeStatus("mike@isep.ipp.pt", true);
    boolean result = accountOne.equals(accountTwo);
    assertEquals(expected, result);
  }
  @Test
  void ensureBusinessSectorIsCreatedSuccessfully() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = company.addBusinessSector("mining");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureBusinessSectorIsCreatedUnsuccessfully() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.addBusinessSector("fishing");
    //Assert
    assertEquals(expected, result);
  }

    @Test
    void ensureThatAccountHasProfileManagerSuccessfully() {
    //Arrange
      boolean expected= true;
      accountOne.setProfile(profileThree);
      //Act
      boolean result= company.validateManager("mike@isep.ipp.pt");
      //Assert
      assertEquals(expected, result);
    }
  @Test
  void ensureThatAccountHasProfileManagerUnsuccessfully() {
    //Arrange
    boolean expected= false;
    //Act
    boolean result= company.validateManager("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
  @Test
  void ensureThatAccountHasProfileAdministratorSuccessfully() {
    //Arrange
    boolean expected= true;
    accountOne.setProfile(profileOne);
    //Act
    boolean result= company.validateAdministrator("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
  @Test
  void ensureThatAccountHasProfileAdministratorUnsuccessfully() {
    //Arrange
    boolean expected= false;
    //Act
    boolean result= company.validateAdministrator("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileUserUnsuccessfully() {
    //Arrange
    boolean expected= false;
    accountOne.setProfile(profileThree);
    //Act
    boolean result= company.validateUser("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
  @Test
  void ensureThatAccountHasProfileUserSuccessfully() {
    //Arrange
    boolean expected= true;
    //Act
    boolean result= company.validateUser("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
  /*@Test
  void ensureProjectTypologyIsCreatedSuccessfully() {
    //Arrange
    boolean expected = true;
    accountThree.setProfile(profileOne);
    //Act
    boolean result = company.createProjectTypology("jane@isep.ipp.pt", "Fixed new typology");
    //Assert
    assertEquals(expected, result);
  }*/

  @Test
  void ensureProjectTypologyIsCreatedUnsuccessfully_NotAdministrator() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.createProjectTypology("mike@isep.ipp.pt", "Fixed new typology");
    //Assert
    assertEquals(expected, result);
  }
/*  @Test
  void ensureProjectTypologyIsCreatedUnsuccessfuly_TheTypologyAlreadyExists() {
    //Arrange
    boolean expected = false;
    accountOne.setProfile(profileOne);
    //Act
    boolean result = company.createProjectTypology("mike@isep.ipp.pt", "Fixed Cost");
    //Assert
    assertEquals(expected, result);
  }*/

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
