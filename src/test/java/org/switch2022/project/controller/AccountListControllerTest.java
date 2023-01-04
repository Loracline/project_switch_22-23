package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

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

        Company companyTest = new Company(accountList, profileList);

        AccountListController controller = new AccountListController(companyTest);
    }

    @Test
    void getListOfAccountSuccessfully(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));

        Account account = new Account("Mike", "mike@isep.ipp.pt", 932755689, null, true);
        List<Account> accountList = new ArrayList<>();
        accountList.add(0, account);

        Company companyTest = new Company(accountList, profileList);
        AccountListController controller = new AccountListController(companyTest);
        List<Account> expected = controller.requestAccountList();

        assertEquals(accountList, expected);
    }

    @Test
    void getEmptyListOfAccount(){
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile("Admin"));
        profileList.add(new Profile("User"));

        List<Account> accountList = new ArrayList<>();

        Company companyTest = new Company(accountList, profileList);
        AccountListController controller = new AccountListController(companyTest);
        List<Account> expected = controller.requestAccountList();

        assertEquals(accountList, expected);
    }
}