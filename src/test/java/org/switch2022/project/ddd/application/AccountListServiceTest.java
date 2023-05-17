package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountListService.class
)
class AccountListServiceTest {
    @InjectMocks
    AccountListService service;
    @MockBean
    IAccountRepository accountRepository;

    /**
     * Method listAllAccounts()
     * Scenario 1: returns a lst of Accounts
     */
    @Test
    void ensureThatAListWithAccountsIsReturned() {
        // Arrange
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        List<Account> expected = Arrays.asList(accountOne, accountTwo, accountThree);
        when(accountRepository.getAccounts()).thenReturn(expected);

        // Act
        List<Account> result = service.listAllAccounts();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method listAllAccounts()
     * Scenario 2: returns an empty lst
     */
    @Test
    void ensureThatAnEmptyListsIsReturned() {
        // Arrange
        List<Account> expected = new ArrayList<>();
        when(accountRepository.getAccounts()).thenReturn(expected);

        // Act
        List<Account> result = service.listAllAccounts();

        // Assert
        assertEquals(expected, result);
    }

}