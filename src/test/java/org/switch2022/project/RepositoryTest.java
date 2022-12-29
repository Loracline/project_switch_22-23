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
        Account user1 = new Account("Ana", "ana@mail.com", 12345678, user);
        Account user2 = new Account("Paulo", "paulo@mail.com", 23456789, user);

        List<Account> accountList = new ArrayList<Account>();
        accountList.add(user1);
        accountList.add(user2);

        List<Profile> profileList = new ArrayList<Profile>();
        profileList.add(user);
        profileList.add(administrator);
        profileList.add(manager);
        Repository test = new Repository(accountList, profileList);

        List<Account> expected = accountList;
        List<Account> result = test.getAccountsList();

        assertEquals(expected, result);
    }
}