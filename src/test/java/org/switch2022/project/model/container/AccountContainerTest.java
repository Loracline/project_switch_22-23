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
        /*
          Accounts created.
         */
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null);

        /*
          Container of accounts created.
         */
        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);

        /*
          Accounts added to the Container.
         */
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        /*
          Profiles created.
         */
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");
        profileThree = new Profile("Administrator");

        /*
          Container of profiles created.
         */
        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);

        /*
          Profiles added to the Container.
         */
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        /*
          Business sectors created.
         */
        businessSector = new BusinessSector("fishing");

        /*
          Business sectors container created.
         */
        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);

        /*
          Business sector added to the Container.
         */
        businessSectors.add(businessSector);

        /*
          Project typologies created.
         */
        projectTypology = new ProjectTypology("Fixed Cost");

        /*
          Project typologies container created.
         */
        List<ProjectTypology> typologies = new ArrayList<>();
        projectTypologyContainer = new ProjectTypologyContainer(typologies);

        /*
          Project typologies added to the Container.
         */
        typologies.add(projectTypology);

        /*
          Customers created.
         */
        customerOne = new Customer("ISEP");
        customerTwo = new Customer("PortoTech");

        /*
          Customers container created.
         */
        customers = new ArrayList<>();
        customerContainer = new CustomerContainer(customers);

        /*
          Customers added to the Container.
         */
        customers.add(customerOne);
        customers.add(customerTwo);

        /*
          Projects created.
         */
        project = new Project("proj001", "software development management", customerOne,
                projectTypology, businessSector);

        /*
          Project container created.
         */
        projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);

        /*
          Projects added to the Container.
         */
        projects.add(project);

        /*
          Accounts in Project created.
         */
        accountInProject1 = new AccountInProject(accountOne, project,
                costPerHour, percentageAllocation, startDate);
        accountInProject2 = new AccountInProject(accountTwo, project,
                costPerHour, percentageAllocation, startDate);

        /*
          Role set to the accounts in project.
         */
        accountInProject1.setRole("team member");
        accountInProject2.setRole("team member");

        /*
          Account in project container created.
         */
        accountsInProject = new ArrayList<>();
        accountInProjectContainer = new AccountInProjectContainer(accountsInProject);

        /*
          Accounts in project added to the Container.
         */
        accountsInProject.add(accountInProject1);
        accountsInProject.add(accountInProject2);

        /*
          Company created.
         */
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

    /**
     * Testing if one can get all accounts in container.
     */
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

    /**
     * Testing if one can get account by giving e-mail as parameter.
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
     * Testing if one can add account to the container.
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
     * Testing if one can validate profiles' account.
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
}