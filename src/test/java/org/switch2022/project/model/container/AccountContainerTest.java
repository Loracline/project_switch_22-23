package org.switch2022.project.model.container;

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
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Account accountOne, accountTwo, accountThree, accountFour, accountFive,
            accountSix, accountSeven;
    Profile profileOne, profileTwo, profileThree;
    Project project;
    List<Account> accounts;
    List<Profile> profiles;
    List<Project> projects;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    ProjectTypologyContainer projectTypologyContainer;
    ProjectContainer projectContainer;
    BusinessSectorContainer businessSectorContainer;
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
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null);
        accountFive = new Account("John", "john@isep.ipp.pt", 951753258, null);
        accountSix = new Account("Tess", "tess@isep.ipp.pt", 753159852, null);
        accountSeven = new Account("Hans", "hans@isep.ipp.pt", 357159852, null);

        // Container of accounts created.
        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);

        // Accounts added to the Container.
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);
        accounts.add(accountFive);
        accounts.add(accountSix);
        accounts.add(accountSeven);

        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");
        profileThree = new Profile("Administrator");

        // Container of profiles created.
        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);

        // Profiles added to the Container.
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        // Business sectors container created.
        businessSectorContainer = new BusinessSectorContainer();

        // Project typologies container created.
        List<ProjectTypology> typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        // Customers container created.
        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);

        // Projects created.
        project = new Project("proj001", "software development manager",new Customer(
                "John","228674498"),
                new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );

        // Project container created.
        projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);

        // Projects added to the Container.
        projects.add(project);

        // Accounts in project created.
        accountInProject1 = new AccountInProject(accountOne, project, "Team Member",
                costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, project, "Team Member",
                costPerHour, percentageAllocation, startDate);

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
        accounts.clear();
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        project = null;
        projects.clear();
        projectContainer = null;
        customers.clear();
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
        AccountContainer listOfAccounts = new AccountContainer(accounts);

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
        AccountContainer newAccountContainer = new AccountContainer(accounts);

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
        assertEquals(expected,result);
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
        accountOne.setProfile(profileOne);
        accountTwo.setProfile(profileTwo);
        accountThree.setProfile(profileThree);

        List<Account> expected = new ArrayList<>();
        expected.add(accountFive);
        expected.add(accountSix);
        expected.add(accountSeven);

        // Act
        List<Account> result = accountContainer.listAllUsers();

        // Assert
        assertEquals(expected, result);
    }
}