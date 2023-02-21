package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.AllocationDTO;
import org.switch2022.project.utils.dto.ProjectDtoAsManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    AccountDTO accountTwoDTO;
    Profile profileOne, profileTwo, profileThree;
    Project projectOne, projectTwo, projectThree, project;
    ProjectDtoAsManager projectOneDTO, projectNonExistentDTO, projectTwoDTO;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    CustomerContainer customerContainer;
    Company company;

    @BeforeEach
    void setUp() {
        // Accounts created.
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);

        // AccountDTO created.
        accountTwoDTO = new AccountDTO(accountTwo.getAccountName(), accountTwo.getEmail(), accountTwo.hasAccountStatus());

        // Container of accounts created.
        accountContainer = new AccountContainer();

        // Accounts added to the Container.
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountContainer.addAccount("Jane", "jane@isep.ipp.pt", 932755687, null);

        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");

        // Container of profiles created.
        profileContainer = new ProfileContainer();

        // Profiles added to the Container.
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        // Container of business sectors created.
        businessSectorContainer = new BusinessSectorContainer();
        businessSectorContainer.createBusinessSector("fishing");
        businessSectorContainer.createBusinessSector("hunting");

        // Container of typologies created.
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        // Container of customers created.
        customerContainer = new CustomerContainer();
        customerContainer.addCustomer("ISEP", "222333444");
        customerContainer.addCustomer("PortoTech", "222333445");

        // Projects created.
        project = new Project("AA002", "software development management", new Customer("John", "228674498"), new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
        projectOne = new Project("AA001", "software development management", new Customer("John", "228674498"), new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
        projectTwo = new Project("AA002", "project software", new Customer("John", "228674498"), new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
        projectThree = new Project("AA001", "Aptoide", new Customer("John", "228674498"), new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

        // ProjectDTOs created.
        projectOneDTO = new ProjectDtoAsManager("AA001", "Aptoide", "John", "228674498", "Fixed cost", "Hunting");
        projectTwoDTO = new ProjectDtoAsManager("AA002", "software development management", "John", "228674498", "Fixed cost", "Hunting");
        projectNonExistentDTO = new ProjectDtoAsManager("AA004", "Aptoide", "John", "228674498", "Fixed cost", "Hunting");

        // Container of projects created.
        projectContainer = new ProjectContainer();

        // Projects added to the container.
        projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer, businessSectorContainer);
        projectContainer.registerProject(projectTwoDTO, projectTypologyContainer, customerContainer, businessSectorContainer);

        // Accounts allocated to project.
        accountInProject1 = new AccountInProject(accountOne, projectOne, "Team Member", costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, projectTwo, "Team Member", costPerHour, percentageAllocation, startDate);

        // Container of accounts allocated in projects created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        // Accounts added to the Container.
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);

        // Company created.
        company = new Company(accountContainer, profileContainer, businessSectorContainer, projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        project = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectTypologyContainer = null;
        projectContainer = null;
        projectOneDTO = null;
        projectNonExistentDTO = null;
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
    void ensureAccountContainerIsRetrievedSuccessfully() {
        // Arrange
        AccountContainer expected = accountContainer;

        // Act
        AccountContainer result = company.getAccountContainer();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * These tests verify if authorization process is successful regarding the
     * three account profile types of the actor performing tasks.
     */
    @Test
    void ensureManagerIsValidatedSuccessfully() {
        // Arrange
        boolean expected = true;
        company.changeProfile(accountOne.getEmail(), "manager");

        // Act
        boolean result = company.validateProfileRequired(accountOne.getEmail(), Profile.MANAGER);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileAdministratorSuccessfully() {
        //Arrange
        boolean expected = true;
        company.changeProfile(accountOne.getEmail(), "administrator");
        //Act
        boolean result = company.validateProfileRequired("mike@isep.ipp.pt", Profile.ADMINISTRATOR);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileAdministratorUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.validateProfileRequired("mike@isep.ipp.pt", Profile.ADMINISTRATOR);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileUserUnsuccessfully() {
        //Arrange
        boolean expected = false;
        company.changeProfile(accountOne.getEmail(), "manager");
        //Act
        boolean result = company.validateProfileRequired("mike@isep.ipp.pt", Profile.USER);
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
        boolean result = company.createProfile("user");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureProfileIsCreatedUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = company.createProfile("manager");
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
        boolean expected = true;

        // ACT
        company.changeProfile(accountTwo.getEmail(), "Administrator");
        boolean result = accountTwo.getProfile().equals(copyAccountTwo.getProfile());


        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * These tests verify if a list of accounts with the "User" profile is
     * retrieved successfully.
     */
    @Test
    void ensureUsersListIsRetrievedSuccessfully() {
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

    @Test
    void ensureEmptyListIsRetrievedWhenNoUserProfilesExistInContainer() {
        // ARRANGE
        company.changeProfile(accountTwo.getEmail(), "Administrator");
        company.changeProfile(accountOne.getEmail(), "Manager");
        company.changeProfile(accountThree.getEmail(), "Manager");
        List<Account> expected = Collections.emptyList();

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
     * These tests verify if a new project can be created successfully when
     * receiving a DTO carrying the relevant data.
     */
    @Test
    void ensureProjectIsRegisteredSuccessfully() {
        // Arrange
        boolean expected = true;

        // Act
        boolean result = company.registerProject(projectNonExistentDTO);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectRegistrationFailsBecauseItAlreadyExistsInContainer() {
        // Arrange
        boolean expected = false;

        // Act
        boolean result = company.registerProject(projectOneDTO);

        // Assert
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
     * These tests verify is allocation of account with a certain role is done
     * successfully to an already existent project.
     */
    @Test
    void ensureScrumMasterIsAssociatedToProjectSuccessfully() {
        // Arrange
        AllocationDTO scrumMasterDTO = new AllocationDTO("Scrum Master", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);

        // Act
        boolean result = company.addUserToProject(accountTwoDTO, projectOneDTO, scrumMasterDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    void ensureProductOwnerIsNotAssociatedBecauseProjectDoNotExist() {
        //Arrange
        AllocationDTO productOwnerDTO = new AllocationDTO("Product Owner", 7.5f, 45.0f, LocalDate.of(2023, 1, 19), null);

        //Act
        boolean result = company.addUserToProject(accountTwoDTO, projectNonExistentDTO, productOwnerDTO);

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
        assertEquals(expected, result);
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





