package org.switch2022.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    @Test
    void getListAllAccounts() {
        Profile user = new Profile("User");
        Profile administrator = new Profile("Administrator");
        Profile manager = new Profile("Manager");
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, null);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, null);

        List<Account> accountList = new ArrayList<>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);
        Repository test = new Repository(accountList, profileList);

        List<Account> expected = accountList;
        List<Account> result = test.getAccountsList();

        assertEquals(expected, result);
    }

    @Test
    void ensureThatNewAccountIsRegisteredIfEmailIsUnique() {
        //ARRANGE
        //create empty repository with empty accountList
        Repository repo = new Repository();

        //expected result; if account is registered, returns TRUE
        boolean expected = true;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null);

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
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, null);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, null);

        List<Account> accountList = new ArrayList<>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);

        //create repository with initialized account and profile lists
        Repository repo = new Repository(accountList, profileList);

        //expected result; if account is not registered, returns FALSE
        boolean expected = false;

        //ACT
        //add user to accountList
        boolean result = repo.registerAccount("Ana", "ana@mail.com", 12345678, null);

        //ASSERT
        assertEquals(expected, result);
    }


}