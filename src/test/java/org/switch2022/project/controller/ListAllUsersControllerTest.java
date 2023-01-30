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
        accountContainer = new AccountContainer();

        // Accounts added to the Container.
        accountContainer.addAccount("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountContainer.addAccount("Paul", "paul@isep.ipp.pt", 939855689, null);
        accountContainer.addAccount("John", "john@isep.ipp.pt", 159753654, null);

        // Profiles created.
        profileOne = new Profile("Administrator");
        profileTwo = new Profile("User");
        profileThree = new Profile("Manager");

        //profile container created.
        profileContainer = new ProfileContainer();
        profileContainer.createProfile("Administrator");
        profileContainer.createProfile("Manager");

        // Company created.
        company = new Company(accountContainer, profileContainer, null,
                null, null, null,
                null);

        // Controller created.
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
        accountContainer = null;
        profileContainer=null;
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
        company.changeProfile("mike@isep.ipp.pt", "administrator");// accountOne is an "Administrator".
        company.changeProfile("john@isep.ipp.pt", "Manager"); // accountThree is a "Manager".

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
        assertEquals(expected, result);
    }

    /**
     * Tests if the list of all accounts with profile User (default) is not
     * retrieved if the actor is not a manager.
     */

    @Test
    void listAllUsersUnsuccessfully_NotAManager() {
        // ARRANGE
        // Accounts' profiles
        company.changeProfile("mike@isep.ipp.pt", "administrator");// accountOne is an "Administrator".
        company.changeProfile("john@isep.ipp.pt", "Manager"); // accountThree is a "Manager".

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
        assertNotEquals(expected, result);
    }

    /**
     * Tests if an empty list is retrieved if there are no accounts with profile User.
     */
    @Test
    void listAllUsersUnsuccessfully_NoUsersListed() {
        // ARRANGE
        // Accounts' profiles
        company.changeProfile("mike@isep.ipp.pt", "administrator");// accountOne is an "Administrator".
        company.changeProfile("paul@isep.ipp.pt", "Administrator"); // accountTwo is an "Administrator".
        company.changeProfile("john@isep.ipp.pt", "Manager"); // accountThree is a "Manager".

        //Retrieve email of actor
        String emailActor = accountThree.getEmail();

        List<AccountDTO> expected = new ArrayList<>();

        // ACT
        List<AccountDTO> result = listAllUsersController.listAllUsers(emailActor);

        // ASSERT
        assertEquals(expected, result);
    }

}