package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AccountInProjectDTO;
import org.switch2022.project.utils.dto.ProjectDTO;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Account accountOne, accountTwo, accountThree, accountFour;
  Profile profileOne, profileTwo, profileThree;
  ProjectTypology projectTypologyOne, projectTypologyTwo;
  Project projectOne, projectTwo, projectThree;
  List<Account> accounts;
  List<Profile> profiles;
  List<Project> projects;
  List<ProjectTypology> typologies;
  BusinessSector businessSectorOne, businessSectorTwo;
  List<BusinessSector> businessSectors;
  BusinessSectorContainer businessSectorContainer;
  AccountContainer accountContainer;
  ProfileContainer profileContainer;
  ProjectTypologyContainer projectTypologyContainer;
  Project project;
  ProjectContainer projectContainer;
  ProjectDTO projectOneDTO, projectTwoDTO;
  Customer customerOne, customerTwo, customerThree;
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
    accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
    accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
    accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
    accountFour = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

    accounts = new ArrayList<>();
    accountContainer = new AccountContainer(accounts);
    accounts.add(accountOne);
    accounts.add(accountTwo);
    accounts.add(accountThree);

    profileOne = new Profile("Administrator");
    profileTwo = new Profile("User");
    profileThree = new Profile("Manager");

    profiles = new ArrayList<>();
    profileContainer = new ProfileContainer(profiles);
    profiles.add(profileOne);
    profiles.add(profileTwo);

    businessSectorOne = new BusinessSector("fishing");
    businessSectorTwo = new BusinessSector("hunting");
    businessSectors = new ArrayList<>();
    businessSectorContainer = new BusinessSectorContainer(businessSectors);
    businessSectors.add(businessSectorOne);

    projects = new ArrayList<>();
    projectContainer = new ProjectContainer(projects);
    projects.add(project);

    projectTypologyOne = new ProjectTypology("Fixed Cost");
    projectTypologyTwo = new ProjectTypology("Fixed time and materials");
    typologies = new ArrayList<>();
    projectTypologyContainer = new ProjectTypologyContainer(typologies);
    typologies.add(projectTypologyOne);
    typologies.add(projectTypologyTwo);
    projectTypologyContainer = new ProjectTypologyContainer(typologies);

    customerOne = new Customer("ISEP");
    customerTwo = new Customer("PortoTech");
    customerThree = new Customer("John");

    customers = new ArrayList<>();
    customerContainer = new CustomerContainer(customers);
    customers.add(customerOne);
    customers.add(customerTwo);

    projectOne = new Project("AA001", "software development management", customerOne, projectTypologyOne,
            businessSectorOne);
    projectTwo = new Project("AA002", "project software", customerOne, projectTypologyTwo, businessSectorOne);
    projectThree = new Project("AA003", "motor software", customerOne, projectTypologyTwo, businessSectorOne);

    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projects.add(projectThree);
    projectContainer = new ProjectContainer(projects);

    projectOneDTO = new ProjectDTO("AA001", "Aptoide", customerThree, projectTypologyOne, businessSectorTwo);
    projectTwoDTO = new ProjectDTO("AA004", "Aptoide", customerThree, projectTypologyOne, businessSectorTwo);

    accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountInProject2 = new AccountInProject(accountTwo, project,  "Team Member",
            costPerHour, percentageAllocation, startDate);
    accountsInProject = new ArrayList<>();
    accountsInProject.add(accountInProject1);
    accountsInProject.add(accountInProject2);
    accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

    company = new Company(accountContainer, profileContainer, businessSectorContainer,
            projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
  }

  @AfterEach
  void tearDown() {
    accountOne = null;
    accountTwo = null;
    accountThree = null;
    profileOne = null;
    profileTwo = null;
    profileThree = null;
    accounts.clear();
    profiles.clear();
    accountContainer = null;
    profileContainer = null;
    businessSectorOne = null;
    businessSectors.clear();
    businessSectorContainer = null;
    projectTypologyOne = null;
    projectTypologyTwo = null;
    typologies.clear();
    projectTypologyContainer = null;
    project = null;
    projects.clear();
    projectContainer = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    customerOne = null;
    customerTwo = null;
    customers.clear();
    customerContainer = null;
    accountInProject1 = null;
    accountInProject2 = null;
    accountsInProject.clear();
    accountInProjectContainer = null;
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
  void ensureChangeStatusReturnTrue() {
    Company company = new Company(accountContainer,profileContainer,businessSectorContainer,projectContainer,projectTypologyContainer,accountInProjectContainer,customerContainer);
    boolean result = company.changeStatus("mike@isep.ipp.pt", true);
    assertTrue(result);
  }

  @Test
  void ensureChangeStatusReturnFalse() {
    Company company = new Company(accountContainer,profileContainer,businessSectorContainer,projectContainer,projectTypologyContainer,accountInProjectContainer,customerContainer);
    boolean result = company.changeStatus("mike@isep.ipp.pt", false);
    assertFalse(result);
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
    boolean expected = true;
    accountOne.setProfile(profileThree);
    //Act
    boolean result = company.validateManager("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileManagerUnsuccessfully() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.validateManager("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileAdministratorSuccessfully() {
    //Arrange
    boolean expected = true;
    accountOne.setProfile(profileOne);
    //Act
    boolean result = company.validateAdministrator("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileAdministratorUnsuccessfully() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.validateAdministrator("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileUserUnsuccessfully() {
    //Arrange
    boolean expected = false;
    accountOne.setProfile(profileThree);
    //Act
    boolean result = company.validateUser("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureThatAccountHasProfileUserSuccessfully() {
    //Arrange
    boolean expected = true;
    //Act
    boolean result = company.validateUser("mike@isep.ipp.pt");
    //Assert
    assertEquals(expected, result);
  }
  @Test
  void ensureProjectTypologyIsCreatedSuccessfully() {
    //Arrange
    boolean expected = true;
    accountThree.setProfile(profileOne);
    //Act
    boolean result = company.createProjectTypology("jane@isep.ipp.pt", "Fixed new typology");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectTypologyIsCreatedUnsuccessfully_NotAdministrator() {
    //Arrange
    boolean expected = false;
    //Act
    boolean result = company.createProjectTypology("mike@isep.ipp.pt", "Fixed new typology");
    //Assert
    assertEquals(expected, result);
  }
  @Test
  void ensureProjectTypologyIsCreatedUnsuccessfuly_TheTypologyAlreadyExists() {
    //Arrange
    boolean expected = false;
    accountOne.setProfile(profileOne);
    //Act
    boolean result = company.createProjectTypology("mike@isep.ipp.pt", "Fixed Cost");
    //Assert
    assertEquals(expected, result);
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

  @Test
  void ensureAllProjectsAreListedSuccessfully() {
    List<Project> expected = projectContainer.getProjects();

    // Act
    List<Project> result = company.getListAllProjects();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void projectRegistered() {
    accountOne.setProfile(profileThree);
    boolean expected = true;
    boolean result = company.registerProject(projectTwoDTO, accountOne.getEmail());
    assertEquals(expected, result);
  }

  @Test
  void projectNotRegistered() {
    accountOne.setProfile(profileThree);
    boolean expected = false;
    boolean result = company.registerProject(projectOneDTO, accountOne.getEmail());
    assertEquals(expected, result);
  }

  @Test
  void managerNotValidated() {
    accountOne.setProfile(profileOne);
    boolean expected = false;
    boolean result = company.registerProject(projectTwoDTO, accountOne.getEmail());
    assertEquals(expected, result);
  }

  @Test
  void ensureProductOwnerIsSuccessfullyAssociatedToAProject() {
    //Arrange
    //accountDTO
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.name = "John";
    accountDTO.email = "john@isep.ipp.pt";
    accountDTO.phoneNumber = 912345678;
    accountDTO.photo = null;
    //projectDTO
    Customer customer = new Customer("IT Customer");
    ProjectTypology projectTypology = new ProjectTypology("fixed cost");
    BusinessSector businessSector = new BusinessSector("IT Sector");
    ProjectDTO projectDTO = new ProjectDTO("id001","Test",customer,projectTypology, businessSector);

    //account in project dto - product owner
    AccountInProjectDTO accountInProjectDTOPO = new AccountInProjectDTO();
    accountInProjectDTOPO.accountDTO = accountDTO;
    accountInProjectDTOPO.projectDTO = projectDTO;
    accountInProjectDTOPO.role = "Product Owner";
    accountInProjectDTOPO.costPerHour = 7.5f;
    accountInProjectDTOPO.percentageAllocation = 45.0f;
    accountInProjectDTOPO.startDate = LocalDate.of(2023, 01, 19);
    accountInProjectDTOPO.endDate = LocalDate.of(2023, 01, 22);
    //Act
    boolean result = company.addUserToProject(accountInProjectDTOPO);
    //Assert
    assertTrue(result);
  }
}
