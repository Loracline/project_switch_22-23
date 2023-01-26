package org.switch2022.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListAllUsersControllerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    Account accountOne, accountTwo, accountThree;
    Profile profileOne, profileTwo, profileThree;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    Company company;
    ListAllUsersController listAllUsersController;

    @BeforeEach
    void setUp() {
        // Accounts created.
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountThree = new Account("John", "john@isep.ipp.pt", 159753654, null);

        // Container of accounts created.
        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);

        // Accounts added to the Container.
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");

        // Company created.
        company = new Company(accountContainer, null, null,
                null, null, null,
                null);
        // Controller created
        listAllUsersController = new ListAllUsersController(company);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        profileOne = null;
        profileTwo = null;
        profileThree = null;
        accounts.clear();
        accountContainer = null;
        company = null;
        listAllUsersController = null;
    }

    /**
     * Tests if the list of all accounts with profile User (default) is successfully
     * retrieved if the actor is a manager.
     */

    @Test
    void ensureThatListOfAllUsersIsSuccessfullyRetrievedIfActorIsManager() {
        // ARRANGE
        // Accounts' profiles
        accountOne.setProfile(profileOne); // accountOne is an "Administrator".
        accountTwo.setProfile(profileTwo); // accountTwo is a "User".
        accountThree.setProfile(profileThree); // accountThree is a "Manager".

        //Retrieve email of actor (manager)
        String emailActor = accountThree.getEmail();

        //AccountDTO of only account with profile User (accountTwo)
        AccountDTO accountTwoDTO = AccountMapper.accountToDTO(accountTwo);
        //Add accountTwoDTO to empty list
        List<AccountDTO> expected = new ArrayList<>();
        expected.add(accountTwoDTO);

        // ACT
        List<AccountDTO> result = listAllUsersController.listAllUsers(emailActor);

        // ASSERT
        assertEquals(expected,result);
    }

    /**
     * Tests if the list of all accounts with profile User (default) is not
     * retrieved if the actor is not a manager.
     */

    @Test
    void listAllUsersUnsuccessfully_NotAManager() {
        // ARRANGE
        // Accounts' profiles
        accountOne.setProfile(profileOne); // accountOne is an "Administrator".
        accountTwo.setProfile(profileTwo); // accountTwo is a "User".
        accountThree.setProfile(profileThree); // accountThree is a "Manager".

        //Retrieve email of actor (administrator)
        String emailActor = accountOne.getEmail();

        //AccountDTO of only account with profile User (accountTwo)
        AccountDTO accountTwoDTO = AccountMapper.accountToDTO(accountTwo);
        //Add accountTwoDTO to empty list
        List<AccountDTO> expected = new ArrayList<>();
        expected.add(accountTwoDTO);

        // ACT
        List<AccountDTO> result = listAllUsersController.listAllUsers(emailActor);

        // ASSERT
        assertNotEquals(expected,result);
    }

    /**
     * Tests if an empty list is retrieved if there are no accounts with profile User.
     */
    @Test
    void listAllUsersUnsuccessfully_NoUsersListed() {
        // ARRANGE
        // Accounts' profiles
        accountOne.setProfile(profileOne); // accountOne is an "Administrator".
        accountTwo.setProfile(profileOne); // accountTwo is an "Administrator".
        accountThree.setProfile(profileThree); // accountThree is a "Manager".

        //Retrieve email of actor
        String emailActor = accountThree.getEmail();

        List<AccountDTO> expected = new ArrayList<>();

        // ACT
        List<AccountDTO> result = listAllUsersController.listAllUsers(emailActor);

        // ASSERT
        assertEquals(expected,result);
    }

}