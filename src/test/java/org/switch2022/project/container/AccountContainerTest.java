package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountContainerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo, accountThree, accountFour;
  Profile profileOne, profileTwo, profileThree;
  ProjectTypology projectTypology;
  Project project;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectContainer projectContainer;
  BusinessSector businessSector;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  Customer customerOne, customerTwo;
  CustomerContainer customerContainer;
  List<Customer> customers;

  float costPerHour;
  float percentageAllocation;
  LocalDate startDate;
  AccountInProject accountInProject1, accountInProject2;
  List<AccountInProject> accountsInProject;
  AccountInProjectContainer accountInProjectContainer;
  Company company;

  @BeforeEach
  void setUp() {
    accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
    accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null);
    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("Manager");
    profileThree = new Profile("Administrator");


    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);
    profiles.add(profileThree);

    businessSector = new BusinessSector("fishing");

    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSector);

    projectTypology = new ProjectTypology("Fixed Cost");

    List<ProjectTypology> typologies = new ArrayList<>();
    typologies.add(projectTypology);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customerOne = new Customer("ISEP");
    customerTwo = new Customer("PortoTech");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);

    project = new Project("proj001", "software development management", customerOne,
            projectTypology, businessSector);

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    accountInProject1 = new AccountInProject(accountOne, project,
            costPerHour, percentageAllocation, startDate);
    accountInProject2 = new AccountInProject(accountTwo, project,
            costPerHour, percentageAllocation, startDate);
    accountInProject1.setRole("team member");
    accountInProject2.setRole("team member");
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject1);
    accountsInProject.add(accountInProject2);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
    //company = new Company(accountContainer, profileContainer, businessSectorContainer, projectContainer, projectTypologyContainer);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree = null;
    accountFour = null;
    profileOne = null;
    profileTwo = null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSector = null;
    businessSectors.clear();
    businessSectorContainer = null;
    project = null;
    projects.clear();
    projectContainer = null;
    customerOne = null;
    customerTwo = null;
    customers.clear();
    customerContainer = null;
    company = null;
  }

  @Test
  void ensureThatAccountListIsRetrieved() {
    List<Account> expected = new ArrayList<>();
    expected.add(accountOne);
    expected.add(accountTwo);
    expected.add(accountThree);

    List<Account> result = accountContainer.getAccounts();

    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountIsAddedIfEmailIsUnique() {
    boolean expected = true;

    boolean result = accountContainer.addAccount("Poppy", "poppy@isep.ipp.pt", 932755686, null);

    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountIsNotAddedIfEmailIsDuplicated() {
    boolean expected = false;

    boolean result = accountContainer.addAccount("Jane", "jane@isep.ipp.pt", 932755687, null);

    assertEquals(expected, result);
  }

  @Test
  void ensureThatCopyOfAccountListIsSuccessfullyGenerated() {
    AccountContainer listOfAccounts = new AccountContainer(accounts);

    List<Account> copy = new ArrayList<>();
    copy.add(accountOne);
    copy.add(accountTwo);
    copy.add(accountThree);

    List<Account> result = listOfAccounts.getAccounts();

    assertEquals(copy, result);
  }

  @Test
  void ensureThatModifiedCopyDoesNotEqualOriginal() {
    AccountContainer newAccountContainer = new AccountContainer(accounts);

    List<Account> copy = new ArrayList<>();
    copy.add(accountOne);

    List<Account> result = newAccountContainer.getAccounts();

    assertNotEquals(copy, result);
  }


  @Test
  void ensureAccountIsRetrievedSuccessfully() {
    Account result = accountContainer.getAccountByEmail("claire@isep.ipp.pt");

    assertEquals(accountOne, result);
  }

  @Test
  void ensureThatGetAccountReturnNull() {
    Account result = accountContainer.getAccountByEmail("cclaire@isep.ipp.pt");

    assertNull(result);
  }

  @Test
  void ensureThatAccountHasProfileManagerSuccessfully() {
    //Arrange
    accountOne.setProfile(profileTwo);
    boolean expected = true;
    //Act
    boolean result = accountContainer.validateManager("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileManagerUnsuccessfully() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = accountContainer.validateManager("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileAdministratorSuccessfully() {
    //Arrange
    accountOne.setProfile(profileThree);
    boolean expected = true;
    //Act
    boolean result = accountContainer.validateAdministrator("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileAdministratorUnsuccessfully() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = accountContainer.validateAdministrator("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileUserUnsuccessfully() {
    //Arrange
    accountOne.setProfile(profileTwo);
    boolean expected = false;
    //Act
    boolean result = accountContainer.validateUser("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileUserSuccessfully() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = accountContainer.validateUser("claire@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
}