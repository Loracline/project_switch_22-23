package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
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
    Project projectOne, projectTwo, projectThree, project;
    List<Account> accounts;
    List<Profile> profiles;
    List<Project> projects;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    ProjectDTO projectOneDTO, projectTwoDTO, projectThreeDTO;
    CustomerContainer customerContainer;
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


        // Container of business sectors created.
        businessSectorContainer= new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");
        businessSectorContainer.createBusinessSector("hunting");


        // Projects created.
        project = new Project("AA002", "software development management", new Customer(
                "John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );
        projectOne = new Project("AA001", "software development management",
                new Customer("John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );
        projectTwo = new Project("AA002", "project software", new Customer("John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );
        projectThree = new Project("AA001", "Aptoide", new Customer("John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );

        // Container of projects created.
        projects = new ArrayList<>();
        projectContainer = new ProjectContainer();

        // Projects added to the container.
        projects.add(projectOne);
        projects.add(projectTwo);
        projects.add(projectThree);

        // Container of typologies created.
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        // Container of customers created.
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("ISEP", "222333444");
        customerContainer.addCustomer("PortoTech", "222333445");



        // ProjectDTOs created.
        projectOneDTO = new ProjectDTO("AA001", "Aptoide", "John", "228674498","Fixed cost",
                "Hunting");
        projectTwoDTO = new ProjectDTO("AA004", "Aptoide", "John", "228674498","Fixed cost",
                "Hunting");
        projectThreeDTO = new ProjectDTO("AA002", "software development management", "John",
                "228674498","Fixed cost", "Hunting");

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
                projectContainer, projectTypologyContainer, accountInProjectContainer,
                customerContainer);
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
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projects.clear();
        projectContainer = null;
        projectOneDTO = null;
        projectTwoDTO = null;
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
        Company company = new Company(accountContainer, profileContainer,
                businessSectorContainer, projectContainer, projectTypologyContainer,
                accountInProjectContainer, customerContainer);
        boolean result = company.changeStatus("mike@isep.ipp.pt", true);
        assertFalse(result);
    }

    @Test
    void ensureChangeStatusReturnFalse() {
        Company company = new Company(accountContainer, profileContainer,
                businessSectorContainer, projectContainer, projectTypologyContainer,
                accountInProjectContainer, customerContainer);
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
        boolean result = company.registerProject(projectTwoDTO);
        assertEquals(expected, result);
    }

    @Test
    void projectNotRegistered() {
        accountOne.setProfile(profileThree);
        boolean expected = false;
        boolean result = company.registerProject(projectOneDTO);
        assertNotEquals(expected, result);
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

    @Test
    void ensureProjectTypologyIsCreatedSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = company.createProjectTypology("Fixed new typology");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyIsCreatedUnsuccessful_TheTypologyAlreadyExists() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.createProjectTypology("Fixed Cost");
        //Assert
        assertEquals(expected, result);
    }


    /**
     * addUserToProject(accountDTO, projectDTO, allocationDTO)
     */

    @Test
    void ensureAccountIsSuccessfullyAssociatedToAProject() {
        //Arrange
        //accountDTO
        AccountDTO accountDTO = new AccountDTO("Emma","emma@isep.ipp.pt", true);

        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("AA002", "software development management", "John",
                "228674498","Fixed cost", "Hunting");


        //account in project dto - product owner
        AllocationDTO allocationDTOPO = new AllocationDTO("Product Owner",7.5f, 45.0f,
                LocalDate.of(2023, 1, 19), null);

        //Act
        company.registerProject(projectDTO);
        boolean result = company.addUserToProject(accountDTO, projectDTO,
                allocationDTOPO);
        //Assert
        assertTrue(result);
    }

    @Test
    void ensureAccountIsNotAssociatedToAnNonExistentProject() {
        //Arrange
        //accountDTO
        AccountDTO accountDTO = new AccountDTO("Emma", "emma@isep.ipp.pt", true);

        //projectDTO
        ProjectDTO projectDTO = new ProjectDTO("AA056", "software development " +
                "management", "John",
                "228674498","Fixed cost", "Hunting");

        //account in project dto - product owner
        AllocationDTO allocationDTOPO = new AllocationDTO("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);
        
        //Act
        boolean result = company.addUserToProject(accountDTO, projectDTO,
                allocationDTOPO);
        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatAllAccountsByProjectAreListedSuccessfully() {
        //Arrange
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);

        //Act
        List<Account> result = company.listAccountsByProject("AA001");

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatListAccountsByProjectIsEmpty_NoProject() {
        //Arrange
        List<Account> expected = new ArrayList<>();

        //Act
        List<Account> result = company.listAccountsByProject("AA0099");

        //Assert
        assertEquals(expected, result);
    }



    @Test
    void ensureThatIsPossibleToListProjectsByAccount() {
        //Arrange
        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        //Act
        List<Project> result = company.listProjectsByAccount("mike@isep.ipp.pt");
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatListProjectsInAccountIsEmpty() {
        //Arrange
        List<Project> expected = new ArrayList<>();
        //Act
        List<Project> result = company.listProjectsByAccount("jane@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Testing if one can add costumer to the container.
     */
    @Test
    void ensureCustomerIsNotAddedSuccessfully_LessThanNineDigits() {
        //Arrange
        boolean expected = false;
        boolean result = company.addCustomer("critical", "2223334");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureCustomerIsNotAddedSuccessfully_MoreThanNineDigits() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.addCustomer("critical", "2223334488");
        //Assert
        assertEquals(expected, result);

    }
    @Test
    void ensureAddCustomerToCustomersListSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = company.addCustomer("Critical", "142356678");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerNameEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.addCustomer("", "222333445");
        //Assert
        assertEquals(expected, result);

    }

    @Test
    void ensureAddCustomerToCustomersListUnsuccessfullyCustomerSameNIF() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.addCustomer("", "222333444");
        //Assert
        assertEquals(expected, result);

    }

}





