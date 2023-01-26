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
    CustomerContainer customerContainer;
    ProjectTypologyContainer projectTypologyContainer;
    AccountInProjectContainer accountInProjectContainer;
    ProjectContainer projectContainer;
    BusinessSectorContainer businessSectorContainer;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    Company company;

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

        // Container of profiles created.
        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);

        // Profiles added to the Container.
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        // Account's profiles changed.
        accountOne.setProfile(profileOne); // accountOne is an "Administrator".
        accountTwo.setProfile(profileTwo); // accountTwo is a "User".
        accountThree.setProfile(profileThree); // accountThree is a "Manager".

        // Company created.
        company = new Company(accountContainer, profileContainer, businessSectorContainer,
                projectContainer, projectTypologyContainer, accountInProjectContainer, customerContainer);
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
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSectorContainer = null;
        projectContainer = null;
        customerContainer = null;
        company = null;
    }

    /**
     * listAllUsers(String actorEmail)
     */
    @Test
    void listAllUsersSuccessfully() {
        // ARRANGE
        ListAllUsersController controller = new ListAllUsersController(company);
        AccountDTO accountTwoDTO = AccountMapper.accountToDTO(accountTwo);
        List<AccountDTO> expected = new ArrayList<>();
        expected.add(accountTwoDTO);

        // ACT
        List<AccountDTO> result = controller.listAllUsers(accountThree.getEmail());

        // ASSERT
        assertEquals(expected,result);
    }

    @Test
    void listAllUsersUnsuccessfully_NotAManager() {
        // ARRANGE
        ListAllUsersController controller = new ListAllUsersController(company);
        AccountDTO accountTwoDTO = AccountMapper.accountToDTO(accountTwo);
        List<AccountDTO> expected = new ArrayList<>();
        expected.add(accountTwoDTO);

        // ACT
        List<AccountDTO> result = controller.listAllUsers(accountOne.getEmail());

        // ASSERT
        assertNotEquals(expected, result);
    }

    @Test
    void listAllUsersUnsuccessfully_NoUsersListed() {
        // ARRANGE
        ListAllUsersController controller = new ListAllUsersController(company);
        accountTwo.setProfile(profileThree);
        List<AccountDTO> expected = new ArrayList<>();

        // ACT
        List<AccountDTO> result = controller.listAllUsers(accountThree.getEmail());

        // ASSERT
        assertEquals(expected, result);
    }
}