package org.switch2022.project;

import org.junit.jupiter.api.Test;

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
        account.updateAccountProfile(new Profile("Manager"));
        //Add account to accountListExpected
        List<Account> accountListExpected = new ArrayList<>();
        accountListExpected.add(account);

        //Create a repository to change account profile
        List<Account> accountListResult = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();
        Repository repository = new Repository(accountListResult, profileList);
        repository.registerAccount("Ana", "ana@mail.com", 12345678, null, true);
        repository.registerProfile("Manager");
        ChangeProfileController controller = new ChangeProfileController(repository);


        //ACT
        controller.changeProfile("ana@mail.com","Manager");

        //ASSERT
        assertEquals(accountListExpected, accountListResult);
    }
}