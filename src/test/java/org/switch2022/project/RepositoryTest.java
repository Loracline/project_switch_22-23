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
        Repository test = new Repository();

        //add user1 to accountList
        Account user1 = test.registerAccount("Ana", "ana@mail.com", 12345678, null);

        //register new user
        Account user2 = test.registerAccount("Paulo", "paulo@mail.com", 23456789,null);

        //create expected accountList
        List<Account> expectedAccountList = new ArrayList<>();
        expectedAccountList.add(user1);
        expectedAccountList.add(user2);

        //ACT
        List<Account> resultAccountList = test.getAccountsList();

        //ASSERT
        assertEquals(expectedAccountList, resultAccountList);
    }

    @Test
    void ensureThatNewAccountIsNotRegisteredIfEmailIsDuplicated() {
        //ARRANGE
        //create empty repository with empty accountList
        Repository test = new Repository();

        //add user1 to accountList
        Account user1 = test.registerAccount("Ana", "ana@mail.com", 12345678, null);

        //register new user
        test.registerAccount("Ana", "ana@mail.com", 12345678, null);

        //create expected accountList
        List<Account> expectedAccountList = new ArrayList<>();
        expectedAccountList.add(user1);

        //ACT
        List<Account> resultAccountList = test.getAccountsList();

        //ASSERT
        assertEquals(expectedAccountList, resultAccountList);
    }


}