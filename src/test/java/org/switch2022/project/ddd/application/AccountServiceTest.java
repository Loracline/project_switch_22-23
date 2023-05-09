package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountService.class
)
class AccountServiceTest {
    @InjectMocks
    AccountService service;
    @MockBean
    IAccountFactory accountFactory;
    @MockBean
    IAccountRepository accountRepository;

    /**
     * Method: registerAccount().
     * Scenario 01: The account is created successfully.
     * Expected return: TRUE.
     */

    @DisplayName("account created successfully")
    @Test
    void ensureThatAccountIsCreatedSuccessfully() {
        String name = "Ana";
        String email = "ana@isep.pt";
        int phoneNumber = 923456789;
        BufferedImage photo = null;
        Account account = mock(Account.class);

        when(accountFactory.create(any(), any(), any(), any())).thenReturn(account);
        when(accountRepository.add(account)).thenReturn(true);

        boolean result = service.registerAccount(name, email, phoneNumber, photo);

        assertTrue(result);
    }

    /**
     * Method: registerAccount().
     * Scenario 01: The account is not created because it already exists in the
     * repository.
     * Expected return: False.
     */

    @Test
    void ensureThatAccountIsNotCreatedBecauseItAlreadyExist() {
        String name = "Ana";
        String email = "ana@isep.pt";
        int phoneNumber = 923456789;
        BufferedImage photo = null;
        Account account = mock(Account.class);

        when(accountFactory.create(any(), any(), any(), any())).thenReturn(account);
        when(accountRepository.add(account)).thenReturn(false);

        boolean result = service.registerAccount(name, email, phoneNumber, photo);

        assertFalse(result);
    }

    /**
     * Method listAllAccounts()
     * Scenario 1: returns a lst of Accounts
     */
    @Test
    void ensureThatAListWithAccountsIsReturned() {
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        List<Account> expected = Arrays.asList(accountOne, accountTwo, accountThree);
        when(accountRepository.getAccounts()).thenReturn(expected);

        List<Account> result = service.listAllAccounts();

        assertEquals(expected, result);
    }

    /**
     * Method listAllAccounts()
     * Scenario 1: returns an empty lst
     */
    @Test
    void ensureThatAnEmptyListsIsReturned() {
        List<Account> expected = new ArrayList<>();
        when(accountRepository.getAccounts()).thenReturn(expected);

        List<Account> result = service.listAllAccounts();

        assertEquals(expected, result);
    }

    /**
     * Method changeStatus()
     * Scenario 1: returns true if status changed.
     */

    @Test
    void ensureThatStatusIsChanged() {
        //Arrange
        String email = "ana@isep.pt";
        boolean newStatus = true;
        Account expected = mock(Account.class);

        when(accountRepository.getAccountByEmail(email)).thenReturn(expected);
        when(expected.changeStatus(newStatus)).thenReturn(true);

        //Act
        boolean result = service.changeStatus(email, newStatus);

        //Assert
        assertTrue(result);
    }


}