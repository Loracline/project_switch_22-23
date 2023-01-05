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
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    Company company;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null, true);


        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");


        profiles = new ArrayList<>();
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
        accounts.clear();
        profiles.clear();
        accountContainer = null;
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

    @Test
    void ensureSameObjectEqualsItself() {
        //ARRANGE
        //declare initialized company as reference
        Company reference = this.company;
        //initialized other company with the reference
        Company other = reference;
        boolean expected = true;
        //ACT
        boolean result = reference.equals(other);
        //ASSERT
        assertEquals(expected, result);
    }
    @Test
    void ensureTwoAccountsAreNotTheSame() {
        //ARRANGE
        //declare initialized company as reference
        Company reference = this.company;
        //create other company to compare with
        List<Account> otherAccounts = new ArrayList<>();
        otherAccounts.add(accountOne);
        otherAccounts.add(accountTwo);

        AccountContainer otherAccountContainer =
                new AccountContainer(otherAccounts);
        Company otherCompany = new Company(otherAccountContainer,profileContainer);
        boolean expected = false;
        //ACT
        boolean result = reference.equals(otherCompany);
        assertEquals(expected, result);
    }
    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        boolean expected = false;
        boolean result = this.company.equals(this.accountContainer);
        assertEquals(expected, result);
    }

}