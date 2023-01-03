package org.switch2022.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountListControllerTest {
    @Test
    void createRepositorySuccessfully(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));

        Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
        List<Account> accountList = new ArrayList<>();
        accountList.add(0, account);

        Repository repositoryTest = new Repository(accountList, profileList);

        AccountListController controller = new AccountListController(repositoryTest);
    }

    @Test
    void getListOfAccountSuccessfully(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));

        Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
        List<Account> accountList = new ArrayList<>();
        accountList.add(0, account);

        Repository repositoryTest = new Repository(accountList, profileList);
        AccountListController controller = new AccountListController(repositoryTest);
        List<Account> expected = controller.requestAccountList();

        assertEquals(accountList, expected);
    }

    @Test
    void getEmptyListOfAccount(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));

        List<Account> accountList = new ArrayList<>();

        Repository repositoryTest = new Repository(accountList, profileList);
        AccountListController controller = new AccountListController(repositoryTest);
        List<Account> expected = controller.requestAccountList();

        assertEquals(accountList, expected);
    }
}