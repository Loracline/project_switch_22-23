package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountContainerTest {

    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    Account accountOne, accountTwo, accountThree, accountFour;
    Profile profileOne, profileTwo, profileThree;
    List<Account> accounts;
    List<Profile> profiles;
    AccountContainer accountContainer;
    ProfileContainer profileContainer;
    BusinessSector businessSector;
    List<BusinessSector> businessSectors;
    BusinessSectorContainer businessSectorContainer;
    Company company;

    @BeforeEach
    void setUp() {
        accountOne = new Account("Claire", "claire@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accountFour = new Account("Poppy", "poppy@isep.ipp.pt", 932755686, null);
        accounts = new ArrayList<>();
        accountContainer = new AccountContainer(accounts);
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        profileOne = new Profile("Administrator");
        profileTwo = new Profile("Manager");
        profileThree = new Profile("Administrator");


        profiles = new ArrayList<>();
        profileContainer = new ProfileContainer(profiles);
        profiles.add(profileOne);
        profiles.add(profileTwo);
        profiles.add(profileThree);

        businessSector = new BusinessSector("fishing");

        businessSectors = new ArrayList<>();
        businessSectorContainer = new BusinessSectorContainer(businessSectors);
        businessSectors.add(businessSector);

        company = new Company(accountContainer, profileContainer, businessSectorContainer);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accountFour = null;
        profileOne = null;
        profileTwo = null;
        accounts.clear();
        profiles.clear();
        accountContainer = null;
        profileContainer = null;
        businessSector = null;
        businessSectors.clear();
        businessSectorContainer = null;
        company = null;
    }

    @Test
    void ensureThatAccountListIsRetrieved() {
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        List<Account> result = accountContainer.getAccounts();

        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountIsAddedIfEmailIsUnique() {
        boolean expected = true;

        boolean result = accountContainer.addAccount("Poppy", "poppy@isep.ipp.pt", 932755686, null);

        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountIsNotAddedIfEmailIsDuplicated() {
        boolean expected = false;

        boolean result = accountContainer.addAccount("Jane", "jane@isep.ipp.pt", 932755687, null);

        assertEquals(expected, result);
    }

    @Test
    void ensureThatCopyOfAccountListIsSuccessfullyGenerated() {
        AccountContainer listOfAccounts = new AccountContainer(accounts);

        List<Account> copy = new ArrayList<>();
        copy.add(accountOne);
        copy.add(accountTwo);
        copy.add(accountThree);

        List<Account> result = listOfAccounts.getAccounts();

        assertEquals(copy, result);
    }

    @Test
    void ensureThatModifiedCopyDoesNotEqualOriginal() {
        AccountContainer newAccountContainer = new AccountContainer(accounts);

        List<Account> copy = new ArrayList<>();
        copy.add(accountOne);

        List<Account> result = newAccountContainer.getAccounts();

        assertNotEquals(copy, result);
    }


    @Test
    void ensureAccountIsRetrievedSuccessfully() {
        Account result = accountContainer.getAccountByEmail("claire@isep.ipp.pt");

        assertEquals(accountOne, result);
    }

    @Test
    void ensureThatGetAccountReturnNull() {
        Account result = accountContainer.getAccountByEmail("cclaire@isep.ipp.pt");

        assertNull(result);
    }

    @Test
    void ensureThatAccountHasProfileManagerSuccessfully() {
        //Arrange
        accountOne.setProfile(profileTwo);
        boolean expected = true;
        //Act
        boolean result = accountContainer.validateManager("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileManagerUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = accountContainer.validateManager("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void ensureThatAccountHasProfileAdministratorSuccessfully() {
        //Arrange
        accountOne.setProfile(profileThree);
        boolean expected = true;
        //Act
        boolean result = accountContainer.validateAdministrator("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void ensureThatAccountHasProfileAdministratorUnsuccessfully() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = accountContainer.validateAdministrator("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileUserUnsuccessfully() {
        //Arrange
        accountOne.setProfile(profileTwo);
        boolean expected = false;
        //Act
        boolean result = accountContainer.validateUser("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void ensureThatAccountHasProfileUserSuccessfully() {
        //Arrange
        boolean expected = true;
        //Act
        boolean result = accountContainer.validateUser("claire@isep.ipp.pt");
        //Assert
        assertEquals(expected, result);
    }
}