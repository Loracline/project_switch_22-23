package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.container.*;
import org.switch2022.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Account accountOne, accountTwo, accountThree, accountFour, accountFive,
            accountSix, accountSeven;
    Profile profileOne, profileTwo;
    Project project;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    BusinessSectorContainer businessSectorContainer;
    CustomerContainer customerContainer;
    float costPerHour;
    float percentageAllocation;
    LocalDate startDate;
    LocalDate endDate;
    AccountInProject accountInProject1, accountInProject2;
    List<AccountInProject> accountsInProject;
    AccountInProjectContainer accountInProjectContainer;
    Company company;


    @BeforeEach
    void setUp() {
        // Accounts created.
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null);
        accountFive = new Account("John", "john@isep.ipp.pt", 951753258, null);
        accountSix = new Account("Tess", "tess@isep.ipp.pt", 753159852, null);
        accountSeven = new Account("Hans", "hans@isep.ipp.pt", 357159852, null);

        // Container of accounts created.

        accountContainer = new AccountContainer();

        // Accounts added to the Container.
        accountContainer.addAccount("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountContainer.addAccount("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountContainer.addAccount("John", "john@isep.ipp.pt", 951753258, null);
        accountContainer.addAccount("Tess", "tess@isep.ipp.pt", 753159852, null);
        accountContainer.addAccount("Hans", "hans@isep.ipp.pt", 357159852, null);


        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");


        // Container of profiles created.

        profileContainer = new ProfileContainer();

        // Profiles added to the Container.
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        // Business sectors container created.
        businessSectorContainer = new BusinessSectorContainer();

        // Project typologies container created.
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");

        // Customers container created.
        customerContainer = new CustomerContainer();

        // Projects created.
        project = new Project("proj001", "software development manager", new Customer(
                "John", "228674498"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

        // Project container created.
        projectContainer = new ProjectContainer();

        // Accounts in project created.
        accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);
        accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
                costPerHour, percentageAllocation, startDate, endDate);

        // Container of accounts in project created.
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        // Accounts in project added to the Container.
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
        accountFour = null;
        profileOne = null;
        profileTwo = null;
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        project = null;
        projectContainer = null;
        projectTypologyContainer = null;
        customerContainer = null;
        company = null;
    }


    /**
     * getAccounts()
     */
    @Test
    void ensureThatAccountListIsRetrieved() {
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);
        expected.add(accountFive);
        expected.add(accountSix);
        expected.add(accountSeven);

        List<Account> result = accountContainer.getAccounts();

        assertEquals(expected, result);
    }

    @Test
    void ensureThatCopyOfAccountListIsSuccessfullyGenerated() {
        AccountContainer listOfAccounts = accountContainer;

        List<Account> copy = new ArrayList<>();
        copy.add(accountOne);
        copy.add(accountTwo);
        copy.add(accountThree);
        copy.add(accountFive);
        copy.add(accountSix);
        copy.add(accountSeven);

        List<Account> result = listOfAccounts.getAccounts();

        assertEquals(copy, result);
    }

    @Test
    void ensureThatModifiedCopyDoesNotEqualOriginal() {
        AccountContainer newAccountContainer = accountContainer;

        List<Account> copy = new ArrayList<>();
        copy.add(accountOne);

        List<Account> result = newAccountContainer.getAccounts();

        assertNotEquals(copy, result);
    }


    /**
     * getAccountByEmail(String email)
     */
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


    /**
     * validateManager(String email)
     * validateAdministrator(String email)
     * validateUser(String email)
     */
    @Test
    void ensureThatAccountHasProfileManagerSuccessfully() {
        //Arrange
        company.changeProfile("claire@isep.ipp.pt", "manager");
        boolean expected = true;
        //Act
        boolean result = accountContainer.validateProfileRequired("claire@isep.ipp.pt", Profile.MANAGER);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileManagerUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = accountContainer.validateProfileRequired("claire@isep.ipp.pt", Profile.MANAGER);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * addAccount(String name, String email, long phoneNumber, BufferedImage photo)
     */
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


    /**
     * changeStatus(String email, boolean status)
     */
    @Test
    void ensureStatusIsChangedSuccessfully() {
        // ARRANGE
        boolean expected = true;

        // ACT
        boolean result = accountContainer.changeStatus("claire@isep.ipp.pt", false);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureStatusIsNotChanged() {
        // ARRANGE
        boolean expected = false;

        // ACT
        boolean result = accountContainer.changeStatus("claire@isep.ipp.pt", true);

        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * listAllUsers()
     */
    @Test
    void ensureListIsRetrievedSuccessfully() {
        // Arrange
        company.changeProfile("claire@isep.ipp.pt", "administrator");
        company.changeProfile("emma@isep.ipp.pt", "manager");
        company.changeProfile("jane@isep.ipp.pt", "administrator");

        List<Account> expected = new ArrayList<>();
        expected.add(accountFive);
        expected.add(accountSix);
        expected.add(accountSeven);

        // Act
        List<Account> result = accountContainer.listAllUsers();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountExists() {
        //Arrange
        boolean expected = true;

        //Act
        boolean result = accountContainer.doesAccountExist(accountOne);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void ensureThatAccountDoesNotExist() {
        //Arrange
        boolean expected = false;

        //Act
        boolean result = accountContainer.doesAccountExist(accountFour);

        //Assert
        assertEquals(expected,result);
    }
}