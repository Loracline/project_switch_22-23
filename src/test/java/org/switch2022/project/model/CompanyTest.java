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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Account accountOne, accountTwo, accountThree, accountFour;
    Profile profileOne, profileTwo, profileThree;
    ProjectTypology projectTypologyOne, projectTypologyTwo;
    Project projectOne, projectTwo, projectThree, project;
    List<Account> accounts;
    List<Profile> profiles;
    List<Project> projects;
    List<ProjectTypology> typologies;
    BusinessSector businessSectorOne, businessSectorTwo, businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    ProjectDTO projectOneDTO, projectTwoDTO;
    Customer customerOne, customerTwo, customerThree, customer;
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
        // Accounts created.
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

        // Container of accounts created.
        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);

        // Accounts added to the Container.
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");

        // Container of profiles created.
        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);

        // Profiles added to the Container.
        profiles.add(profileOne);
        profiles.add(profileTwo);

        // Business sectors created.
        businessSectorOne = new BusinessSector("fishing");
        businessSectorTwo = new BusinessSector("hunting");

        // Container of business sectors created.
        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);

        // Business sectors added to the Container.
        businessSectors.add(businessSectorOne);

        // Projects created.
        project = new Project("AA001", "software development management", customer, projectTypologyOne,
                businessSector);
        projectOne = new Project("AA001", "software development management", customerOne, projectTypologyOne,
                businessSectorOne);
        projectTwo = new Project("AA002", "project software", customerOne, projectTypologyTwo, businessSectorOne);
        projectThree = new Project("AA003", "motor software", customerOne, projectTypologyTwo, businessSectorOne);

        // Container of projects created.
        projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);

        // Projects added to the container.
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);

        // Typologies created.
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo = new ProjectTypology("Fixed time and materials");

        // Container of typologies created.
        typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        // Typologies added to the Container.
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);

        // Customers created.
        customerOne = new Customer("ISEP");
        customerTwo = new Customer("PortoTech");
        customerThree = new Customer("John");

        // Container of customers created.
        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);

        // Customers added to the Container.
        customers.add(customerOne);
        customers.add(customerTwo);

        // ProjectDTOs created.
        projectOneDTO = new ProjectDTO("AA001", "Aptoide", customerThree, projectTypologyOne, businessSectorTwo);
        projectTwoDTO = new ProjectDTO("AA004", "Aptoide", customerThree, projectTypologyOne, businessSectorTwo);

        // Accounts allocated to project.
        accountInProject1 = new AccountInProject(accountOne, projectOne, "Team Member",
                costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, projectTwo, "Team Member",
                costPerHour, percentageAllocation, startDate);

        // Container of accounts allocated in projects created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        // Accounts added to the Container.
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);

        // Company created.
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
        project = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorOne = null;
        businessSectors.clear();
        businessSectorContainer = null;
        businessSector = null;
        projectTypologyOne = null;
        projectTypologyTwo = null;
        typologies.clear();
        projectTypologyContainer = null;
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


    /**
     * getAccountContainer()
     */
    @Test
    void ensureAccountContainerIsRetrieved() {
        AccountContainer expected = accountContainer;
        AccountContainer result = company.getAccountContainer();
        assertEquals(expected, result);
    }


    /**
     * validateManager(String email)
     * validateUser(String email)
     * validateAdministrator(String email)
     */
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


    /**
     * createProfile(String profileName)
     */
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


    /**
     * changeProfile(String email, String profileName)
     */
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
        boolean result = company.changeProfile("mike@isep.ipp.pt", "Director");
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

    @Test
    void ensureProfileIsNotChangedByComparingAccountAndCopyAccount() {
        // ARRANGE
        Account copyAccountTwo = new Account(accountTwo);
        accountTwo.setProfile(profileTwo);
        copyAccountTwo.setProfile(profileTwo);
        boolean expected = true;

        // ACT
        company.changeProfile(accountTwo.getEmail(), "User");
        boolean result = accountTwo.getProfile().equals(copyAccountTwo.getProfile());


        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileIsChangedByComparingAccountAndCopyAccount() {
        // ARRANGE
        Account copyAccountTwo = new Account(accountTwo);
        copyAccountTwo.setProfile(profileTwo);
        boolean expected = false;

        // ACT
        company.changeProfile(accountTwo.getEmail(), "Administrator");
        boolean result = accountTwo.getProfile().equals(copyAccountTwo.getProfile());


        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * listAllUsers()
     */
    @Test
    void ensureIsRetrievedEmptyList() {
        // ARRANGE
        accountOne.setProfile(profileOne);
        accountTwo.setProfile(profileThree);
        accountThree.setProfile(profileOne);
        accountFour.setProfile(profileThree);
        List<Account> expected = Collections.emptyList();

        // ACT
        List<Account> result = company.listAllUsers();

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureIsRetrievedListNotEmpty() {
        // ARRANGE
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        // ACT
        List<Account> result = company.listAllUsers();

        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * changeStatus(String email, boolean status)
     */
    @Test
    void ensureStatusIsNotChanged() {
        Company company = new Company(accountContainer, profileContainer, businessSectorContainer, projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
        boolean result = company.changeStatus("mike@isep.ipp.pt", true);
        assertFalse(result);
    }

    @Test
    void ensureChangeStatusReturnFalse() {
        Company company = new Company(accountContainer, profileContainer, businessSectorContainer, projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
        boolean result = company.changeStatus("mike@isep.ipp.pt", true);
        assertFalse(result);
    }

    @Test
    void ensureStatusIsChanged() {
        // ARRANGE
        accountOne.setStatus(true);
        boolean expected = true;

        // ACT
        boolean result = company.changeStatus(accountOne.getEmail(), false);

        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * registerProject(ProjectDTO projectDTO, String email)
     */
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


    /**
     * listAllProjects()
     */
    @Test
    void ensureAllProjectsAreListedSuccessfully() {
        List<Project> expected = projectContainer.getProjects();

        // Act
        List<Project> result = company.listAllProjects();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * addBusinessSector(String businessSectorName)
     */
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


    /**
     * createProjectTypology(String email, String projectTypology)
     */
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
    void ensureProjectTypologyIsCreatedUnsuccessful_TheTypologyAlreadyExists() {
        //Arrange
        boolean expected = false;
        accountOne.setProfile(profileOne);
        //Act
        boolean result = company.createProjectTypology("mike@isep.ipp.pt", "Fixed Cost");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * addUserToProject(AccountInProjectDTO accountInProjectDTO)
     */
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
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customer, projectTypology, businessSector);

        //account in project dto - product owner
        AccountInProjectDTO accountInProjectDTOPO = new AccountInProjectDTO();
        accountInProjectDTOPO.accountDTO = accountDTO;
        accountInProjectDTOPO.projectDTO = projectDTO;
        accountInProjectDTOPO.role = "Product Owner";
        accountInProjectDTOPO.costPerHour = 7.5f;
        accountInProjectDTOPO.percentageAllocation = 45.0f;
        accountInProjectDTOPO.startDate = LocalDate.of(2023, 1, 19);
        accountInProjectDTOPO.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = company.addUserToProject(accountInProjectDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureTeamMemberIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //accountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.name = "Anna";
        accountDTO.email = "anna@isep.ipp.pt";
        accountDTO.phoneNumber = 912345688;
        accountDTO.photo = null;
        //projectDTO
        Customer customer = new Customer("IT Customer");
        ProjectTypology projectTypology = new ProjectTypology("fixed cost");
        BusinessSector businessSector = new BusinessSector("IT Sector");
        ProjectDTO projectDTO = new ProjectDTO("id001", "Test", customer, projectTypology, businessSector);

        //account in project dto - product owner
        AccountInProjectDTO accountInProjectDTOTM = new AccountInProjectDTO();
        accountInProjectDTOTM.accountDTO = accountDTO;
        accountInProjectDTOTM.projectDTO = projectDTO;
        accountInProjectDTOTM.role = "Team Member";
        accountInProjectDTOTM.costPerHour = 7.5f;
        accountInProjectDTOTM.percentageAllocation = 45.0f;
        accountInProjectDTOTM.startDate = LocalDate.of(2023, 1, 19);
        accountInProjectDTOTM.endDate = LocalDate.of(2023, 1, 22);
        //Act
        boolean result = company.addUserToProject(accountInProjectDTOTM);
        //Assert
        assertTrue(result);
    }
    @Test
    void ensureThatAllAccountsByProjectAreListedSuccessfully() {
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);

        //Act
        List<Account> result = company.listAccountsByProject("AA001");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListAccountsByProjectIsEmpty_NoProject() {
        List<Account> expected = new ArrayList<>();

        //Act
        List<Account> result = company.listAccountsByProject("AA0099");

        //Assert
        assertEquals(expected, result);
    }
}

