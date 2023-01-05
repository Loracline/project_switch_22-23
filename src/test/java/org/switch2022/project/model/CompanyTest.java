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
    void ensureAccountContainerIsRetrieved() {
        AccountContainer expected = accountContainer;
        AccountContainer result = company.getAccountContainer();
        assertEquals(expected, result);
    }
    @Test
    void ensureProfileContainerIsRetrieved() {
        ProfileContainer expected = profileContainer;
        ProfileContainer result = company.getProfileContainer();
        assertEquals(expected, result);
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
        Company otherCompany = new Company(otherAccountContainer, profileContainer);
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
