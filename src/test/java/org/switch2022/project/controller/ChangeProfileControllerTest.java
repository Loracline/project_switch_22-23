package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.Company;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeProfileControllerTest {
    @Test
    void changeProfileHappy_Path() {
        //ARRANGE
        //Create an account
        Account account = new Account("Ana", "ana@mail.com", 12345678, null, true);
        //Change Account Profile user to manager.
        account.setProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountListExpected = new ArrayList<>();
        accountListExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountListResult = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();
        Company company = new Company(accountListResult, profileList);
        company.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        company.registerProfile("Manager");
        ChangeProfileController controller = new ChangeProfileController(company);


        //ACT
        controller.changeProfile("ana@mail.com","Manager");

        //ASSERT
        assertEquals(accountListExpected, accountListResult);
    }
}