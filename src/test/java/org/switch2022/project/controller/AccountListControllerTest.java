package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountListControllerTest {
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

    @Test
    void ensureAccountContainerIsSuccessfullyCreated(){
        AccountListController controller = new AccountListController(company);
    }

    @Test
    void ensureSameObjectEqualsItself() {
        AccountListController reference = new AccountListController(company);
        AccountListController other = reference;
        boolean expected = true;
        boolean result = reference.equals(other);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void ensureTwoAccountListControllersAreNotTheSame() {
        AccountListController reference = new AccountListController(company);
        Company newCompany = new Company();
        AccountListController other = new AccountListController(newCompany);
        boolean expected = false;
        boolean result = reference.equals(other);
    }

    @Test
    void ensureObjectDoesNotEqualsOtherTypeOfObject() {
        AccountListController reference = new AccountListController(company);
        ProfileContainer other = new ProfileContainer(profiles);
        boolean expected = false;
        boolean result = reference.equals(other);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void ensureAllAccountsAreListedSuccessfully() {
        List<Account> expected = accountContainer.getAccounts();
        AccountListController newAccountListController = new AccountListController(company);

        // Act
        List<Account> result = newAccountListController.listAllAccounts();

        // Assert
        assertEquals(expected, result);
    }

}