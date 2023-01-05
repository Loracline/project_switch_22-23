package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CompanyTest {

    /**
     * Testing the constructor
     */
    @Test
    void createContainer() {
        AccountContainer container = new AccountContainer();
    }

    /**
     * BeforeEach and AfterEach executes common code before running the tests below.
     */

    Account accountOne, accountTwo;
    Profile profileOne, profileTwo;
    AccountContainer accountContainer, accountContainerTwo;
    ProfileContainer profileContainer;
    Company company;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null, true);


        List<Account> accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");


        List<Profile> profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);

        company = new Company(accountContainer, profileContainer);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        profileOne = null;
        profileTwo = null;
        accountContainer = null;
        accountContainerTwo = null;
        profileContainer = null;
        company = null;
    }

    /**
     * The test getAccountsList and getAccountsListTwo, compare two lists contents.
     */

    @Test
    void ensureItGetsAccountContainer() {
        AccountContainer expected = accountContainer;
        AccountContainer result = company.getAccountContainer();
        assertEquals(expected, result);
    }

    @Test
    void ensureThatNewAccountIsRegisteredIfEmailIsUnique() {
        //ARRANGE
        //create empty repository with empty accountList
        Company repo = new Company();

        //expected result; if account is registered, returns TRUE
        boolean expected = true;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatNewAccountIsNotRegisteredIfEmailIsDuplicated() {
        //ARRANGE
        //create profiles to initialize profileList
        Profile user = new Profile("User");
        Profile administrator = new Profile("Administrator");
        Profile manager = new Profile("Manager");
        //create accounts to initialize accountList
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, null, true);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, null, true);

        List<Account> accountList = new ArrayList<>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);

        //create repository with initialized account and profile lists
        Company repo = new Company(accountList, profileList);

        //expected result; if account is not registered, returns FALSE
        boolean expected = false;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void getAccountHappy_Path() {
        //ARRANGE
        //create empty repository with empty accountList
        Company repo = new Company();

        //register new user
        repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        repo.registerAccount("Paulo", "paulo@mail.com", 23456789, null, true);
        repo.registerAccount("Sofia", "sofia@mail.com", 232423423, null, true);

        //Create account to compare with
        Account expected = new Account("Ana", "ana@mail.com", 12345678, null, true);

        //ACT
        Account result = repo.getAccount("ana@mail.com");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetAccountReturnsNull() {
        //ARRANGE
        //create empty repository with empty accountList
        Company repo = new Company();

        //register new user
        repo.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        repo.registerAccount("Paulo", "paulo@mail.com", 23456789, null, true);
        repo.registerAccount("Sofia", "sofia@mail.com", 232423423, null, true);

        //ACT
        Account result = repo.getAccount("anaPinho@mail.com");

        //ASSERT
        assertNull(result);
    }

    @Test
    void registerProfileHappy_Path() {
        //ARRANGE
        Profile one = new Profile("User");
        Profile two = new Profile("Manager");
        Profile three = new Profile("Administrator");
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(one);
        profilesList.add(two);
        profilesList.add(three);
        Company expected = new Company(profilesList);

        //ACT
        List<Profile> profilesListE = new ArrayList<>();
        profilesListE.add(one);
        profilesListE.add(two);
        Company result = new Company(profilesListE);
        result.registerProfile("Administrator");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void noRegisterProfile_ProfileAlreadyExist() {
        //ARRANGE
        Profile one = new Profile("User");
        Profile two = new Profile("Manager");
        Profile three = new Profile("Administrator");
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(one);
        profilesList.add(two);
        profilesList.add(three);
        Company expected = new Company(profilesList);

        //ACT
        List<Profile> profilesListE = new ArrayList<>();
        profilesListE.add(one);
        profilesListE.add(two);
        profilesListE.add(three);
        Company result = new Company(profilesListE);
        result.registerProfile("Administrator");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void getProfileHappy_Path() {
        //ARRANGE
        //create empty repository with empty accountList
        Company company = new Company();

        //register new Profile
        company.registerProfile("User");
        company.registerProfile("Manager");

        //Create profile to compare with
        Profile expected = new Profile("Manager");

        //ACT
        Profile result = company.getProfile("Manager");

        //ASSERT
        assertEquals(expected, result);
    }

    @Test
    void ensureThatGetProfileReturnsNull() {
        //ARRANGE
        //create empty repository with empty accountList
        Company company = new Company();

        //register new Profile
        company.registerProfile("User");
        company.registerProfile("Manager");

        //ACT
        Profile result = company.getProfile("Administrator");

        //ASSERT
        assertNull(result);
    }

    @Test
    void changeAccountProfileHappy_Path() {
        //ARRANGE
        //Create an account
        Account account = new Account("Ana", "ana@mail.com", 12345678, null, true);
        //Change Account Profile user to manager.
        account.updateAccountProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountListExpected = new ArrayList<>();
        accountListExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountListResult = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();
        Company company = new Company(accountListResult, profileList);
        company.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        company.registerProfile("Manager");

        //ACT
        company.changeAccountProfile("ana@mail.com", "Manager");

        //ASSERT
        assertEquals(accountListExpected, accountListResult);
    }


}