package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.container.*;
import org.switch2022.project.utils.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountMapperTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */

    // accounts
    Account accountOne, accountTwo, accountThree;
    List<Account> accounts;
    AccountContainer accountContainer;

    // profiles
    Profile profileOne, profileTwo, profileThree;
    List<Profile> profiles;
    ProfileContainer profileContainer;

    // project typologies
    ProjectTypologyContainer projectTypologyContainer;

    // projects
    ProjectContainer projectContainer;

    // accounts in project
    AccountInProjectContainer accountInProjectContainer;

    // customers
    CustomerContainer customerContainer;

    // business sectors
    BusinessSectorContainer businessSectorContainer;

    // company
    Company company;


    @BeforeEach
    void setUp() {
        // Accounts created.
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);

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
        projectTypologyContainer = null;
        projectContainer = null;
        customerContainer = null;
        company = null;
    }


    /**
     * getDTOFromAccount(Account account)
     */
    @Test
    void getDTOFromAccountSuccessfully() {
        // ARRANGE
        AccountMapper mapper = new AccountMapper();
        AccountDTO expected = new AccountDTO();
        expected.name = "Mike";
        expected.email = "mike@isep.ipp.pt";
        expected.phoneNumber = 932755689;
        expected.photo = null;
        expected.profile = profileTwo;
        expected.status = true;

        // ACT
        AccountDTO result = mapper.accountToDTO(accountOne);

        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * getAccountFromDTO(AccountDTO accountDTO)
     */
    @Test
    void getAccountFromDTOSuccessfullyByComparingTwoAccounts() {
        // ARRANGE
        AccountMapper mapper = new AccountMapper();
        AccountDTO accountOneDTO = new AccountDTO();
        accountOneDTO.name = "Mike";
        accountOneDTO.email = "mike@isep.ipp.pt";
        accountOneDTO.phoneNumber = 932755689;
        accountOneDTO.photo = null;
        accountOneDTO.profile = profileThree;
        accountOneDTO.status = false;

        accountOne.setProfile(profileThree);
        accountOne.setStatus(false);

        boolean expected = true;

        // ACT
        Account accountOneAfterMapping = mapper.getAccountFromDTO(accountOneDTO);
        boolean result = accountOne.equals(accountOneAfterMapping);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void getAccountFromDTOUnSuccessfullyByComparingTwoAccounts() {
        // ARRANGE
        AccountMapper mapper = new AccountMapper();
        AccountDTO accountOneDTO = new AccountDTO();
        accountOneDTO.name = "Mike";
        accountOneDTO.email = "mike@isep.ipp.pt";
        accountOneDTO.phoneNumber = 932755689;
        accountOneDTO.photo = null;
        accountOneDTO.profile = profileThree;
        accountOneDTO.status = false;

        accountOne.setProfile(profileTwo);
        accountOne.setStatus(true);

        boolean expected = false;

        // ACT
        Account accountOneAfterMapping = mapper.getAccountFromDTO(accountOneDTO);
        boolean result = accountOne.equals(accountOneAfterMapping);

        // ASSERT
        assertEquals(expected, result);
    }

    @Test
    void getAccountFromDTOSuccessfully() {
        // ARRANGE
        AccountMapper mapper = new AccountMapper();
        AccountDTO accountOneDTO = new AccountDTO();
        accountOneDTO.name = "Mike";
        accountOneDTO.email = "mike@isep.ipp.pt";
        accountOneDTO.phoneNumber = 932755689;
        accountOneDTO.photo = null;
        accountOneDTO.profile = profileTwo;
        accountOneDTO.status = true;
        Account expected = accountOne;
        // ACT
        Account result = mapper.getAccountFromDTO(accountOneDTO);
        // ASSERT
        assertEquals(expected, result);
    }


    /**
     * getListDTOFromAccounts(List<Account> accounts)
     */
    @Test
    void getListDTOFromAccountsSuccessfully() {
        // ARRANGE
        AccountMapper mapper = new AccountMapper();

        AccountDTO accountOneDTO = new AccountDTO();
        accountOneDTO.name = "Mike";
        accountOneDTO.email = "mike@isep.ipp.pt";
        accountOneDTO.phoneNumber = 932755689;
        accountOneDTO.photo = null;
        accountOneDTO.profile = profileTwo;
        accountOneDTO.status = true;

        AccountDTO accountTwoDTO = new AccountDTO();
        accountTwoDTO.name = "Emma";
        accountTwoDTO.email = "emma@isep.ipp.pt";
        accountTwoDTO.phoneNumber = 932755688;
        accountTwoDTO.photo = null;
        accountTwoDTO.profile = profileTwo;
        accountTwoDTO.status = true;

        AccountDTO accountThreeDTO = new AccountDTO();
        accountThreeDTO.name = "Jane";
        accountThreeDTO.email = "jane@isep.ipp.pt";
        accountThreeDTO.phoneNumber = 932755687;
        accountThreeDTO.photo = null;
        accountThreeDTO.profile = profileTwo;
        accountThreeDTO.status = true;

        List<AccountDTO> expected = new ArrayList<>();
        expected.add(accountOneDTO);
        expected.add(accountTwoDTO);
        expected.add(accountThreeDTO);

        // ACT
        List<AccountDTO> result = mapper.getListDTOFromAccounts(accounts);

        // ASSERT
        assertEquals(expected, result);
    }
}