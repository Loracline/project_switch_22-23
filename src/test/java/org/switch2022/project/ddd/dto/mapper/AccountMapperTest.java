package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.dto.AccountDto;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountMapperTest {


    /**
     * Method accountToDto(account).
     * Scenario 1: it should return a dto from the Account given
     */

    @Test
    void ensureThatItReturnsAAccountDto() {
        //Arrange
        AccountMapper accountMapper = new AccountMapper();
        Account account = mock(Account.class);
        String accountName = "Jose";
        String email = "jose@isep.pt";
        boolean status = true;
        when(account.getAccountName()).thenReturn(accountName);
        when(account.getAccountEmail()).thenReturn(email);
        when(account.getAccountStatus()).thenReturn(true);
        AccountDto expected = new AccountDto(accountName,email,status);
        //Act
        AccountDto result = accountMapper.accountToDto(account);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method listAccountsToDto(account).
     * Scenario 1: it should return a dto from the Account given
     */
    @Test
    void ensureThatListAccountsToDtoReturnsListOfAccountDtos() {
        // Arrange
        AccountMapper accountMapper = new AccountMapper();
        List<Account> accounts = new ArrayList<>();
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        String accountName1 = "John";
        String email1 = "john@example.com";
        boolean status1 = true;
        String accountName2 = "Jane";
        String email2 = "jane@example.com";
        boolean status2 = false;
        when(account1.getAccountName()).thenReturn(accountName1);
        when(account1.getAccountEmail()).thenReturn(email1);
        when(account1.getAccountStatus()).thenReturn(status1);
        when(account2.getAccountName()).thenReturn(accountName2);
        when(account2.getAccountEmail()).thenReturn(email2);
        when(account2.getAccountStatus()).thenReturn(status2);
        accounts.add(account1);
        accounts.add(account2);

        List<AccountDto> expected = new ArrayList<>();
        expected.add(new AccountDto(accountName1, email1, status1));
        expected.add(new AccountDto(accountName2, email2, status2));

        // Act
        List<AccountDto> result = accountMapper.accountsToDtoList(accounts);

        // Assert
        assertEquals(expected, result);
    }


}