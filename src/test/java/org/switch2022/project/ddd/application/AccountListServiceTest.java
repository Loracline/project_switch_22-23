package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    @Qualifier("account_jpa")
    @MockBean
    IAccountRepository accountRepository;
    @MockBean
    AccountMapper accountMapper;

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
        List<Account> accounts = Arrays.asList(accountOne, accountTwo, accountThree);
        when(accountRepository.findAll()).thenReturn(accounts);
        AccountDto accountDtoOne = mock(AccountDto.class);
        AccountDto accountDtoTwo = mock(AccountDto.class);
        AccountDto accountDtoThree = mock(AccountDto.class);
        List<AccountDto> expected = Arrays.asList(accountDtoOne, accountDtoTwo,
                accountDtoThree);
        when(accountMapper.listAccountsToDto(any())).thenReturn(expected);

        // Act
        List<AccountDto> result = service.listAllAccounts();

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
        List<Account> accounts = new ArrayList<>();
        List<AccountDto> expected = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountMapper.listAccountsToDto(any())).thenReturn(expected);

        // Act
        List<AccountDto> result = service.listAllAccounts();

        // Assert
        assertEquals(expected, result);
    }
}