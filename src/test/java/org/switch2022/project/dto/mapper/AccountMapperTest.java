package org.switch2022.project.dto.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.dto.AccountDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountMapperTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Account accountOne, accountTwo, accountThree;
    AccountDto accountDtoOne, accountDtoTwo, accountDtoThree;
    List<AccountDto> accountsDTOOne;
    List<Account> accounts;

    @BeforeEach
    void setUp() {
        //accounts
        accountOne = new Account("Mike", "mike@isep.ipp.pt", 932755689, null);
        accountTwo = new Account("Emma", "emma@isep.ipp.pt", 932755688, null);
        accountThree = new Account("Jane", "jane@isep.ipp.pt", 932755687, null);
        accounts = new ArrayList<>();
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);

        //accountDTO
        accountDtoOne = AccountMapper.accountToDTO(accountOne);
        accountDtoTwo = AccountMapper.accountToDTO(accountTwo);
        accountDtoThree = AccountMapper.accountToDTO(accountThree);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDtoOne);
        accountsDTOOne.add(accountDtoTwo);
        accountsDTOOne.add(accountDtoThree);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accountDtoOne = null;
        accountDtoTwo = null;
        accountDtoThree = null;
        accountsDTOOne.clear();
        accounts.clear();
    }

    /**
     * getDTOFromAccount(Account account)
     */
    @Test
    void ensureThatAccountIsConvertedIntoAccountDTO() {
        // ARRANGE
        AccountDto expected = new AccountDto("Mike","mike@isep.ipp.pt", true);

        // ACT
        AccountDto result = AccountMapper.accountToDTO(accountOne);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * getListDTOFromAccounts(List<Account> accounts)
     */
    @Test
    void ensureThatAccountListIsConvertedIntoAccountListDTO() {
        // ARRANGE
        List<AccountDto> expected = accountsDTOOne;

        //ACT
        List<AccountDto> result = AccountMapper.listAccountsToDTO(accounts);

        // ASSERT
        assertEquals(expected, result);
    }
}