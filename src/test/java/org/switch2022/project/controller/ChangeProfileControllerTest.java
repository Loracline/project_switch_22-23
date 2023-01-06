package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeProfileControllerTest {
    @Test
    void ensureAccountProfileIsChangedSuccessfully() {
        //ARRANGE
        //Create an account
        Account account = new Account("Ana", "ana@mail.com", 12345678, null);
        //Change Account Profile user to manager.
        account.setProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountsExpected = new ArrayList<>();
        accountsExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountsResult = new ArrayList<>();
        List<Profile> profiles = new ArrayList<>();

        AccountContainer accountContainer = new AccountContainer(accountsResult);
        ProfileContainer profileContainer = new ProfileContainer(profiles);

        Company company = new Company(accountContainer, profileContainer);
        accountContainer.addAccount(new Account("Ana", "ana@mail.com", 12345678, null));
        profileContainer.addProfile(new Profile("Manager"));

        ChangeProfileController controller = new ChangeProfileController(company);

        //ACT
        controller.changeProfile("ana@mail.com", "Manager");

        //ASSERT
        assertEquals(accountsExpected, accountsResult);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_ProfileNotFound() {
        //ARRANGE
        //Create an account
        Account account = new Account("Ana", "ana@mail.com", 12345678, null);
        //Change Account Profile user to manager.
        account.setProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountsExpected = new ArrayList<>();
        accountsExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountsResult = new ArrayList<>();
        List<Profile> profiles = new ArrayList<>();

        AccountContainer accountContainer = new AccountContainer(accountsResult);
        ProfileContainer profileContainer = new ProfileContainer(profiles);

        Company company = new Company(accountContainer, profileContainer);
        accountContainer.addAccount(new Account("Ana", "ana@mail.com", 12345678, null));
        profileContainer.addProfile(new Profile("Manager"));

        ChangeProfileController controller = new ChangeProfileController(company);

        //ACT
        controller.changeProfile("ana@mail.com", "Administrator");

        //ASSERT
        assertNotEquals(accountsExpected, accountsResult);
    }

    @Test
    void ensureAccountProfileIsNotChangedSuccessfully_AccountNotFound() {
        //ARRANGE
        //Create an account
        Account account = new Account("Ana", "ana@mail.com", 12345678, null);
        //Change Account Profile user to manager.
        account.setProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountsExpected = new ArrayList<>();
        accountsExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountsResult = new ArrayList<>();
        List<Profile> profiles = new ArrayList<>();

        AccountContainer accountContainer = new AccountContainer(accountsResult);
        ProfileContainer profileContainer = new ProfileContainer(profiles);

        Company company = new Company(accountContainer, profileContainer);
        accountContainer.addAccount(new Account("Ana", "ana@mail.com", 12345678, null));
        profileContainer.addProfile(new Profile("Manager"));

        ChangeProfileController controller = new ChangeProfileController(company);

        //ACT
        controller.changeProfile("aana@mail.com", "Manager");

        //ASSERT
        assertNotEquals(accountsExpected,accountsResult);
    }
}