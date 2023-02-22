package org.switch2022.project.utils.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountMapperTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Account accountOne, accountTwo, accountThree;
    AccountDTO accountDTOOne, accountDTOTwo, accountDTOThree;
    List<AccountDTO> accountsDTOOne;
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
        accountDTOOne = AccountMapper.accountToDTO(accountOne);
        accountDTOTwo = AccountMapper.accountToDTO(accountTwo);
        accountDTOThree = AccountMapper.accountToDTO(accountThree);
        accountsDTOOne = new ArrayList<>();
        accountsDTOOne.add(accountDTOOne);
        accountsDTOOne.add(accountDTOTwo);
        accountsDTOOne.add(accountDTOThree);
    }

    @AfterEach
    void tearDown() {
        accountOne = null;
        accountTwo = null;
        accountThree = null;
        accountDTOOne = null;
        accountDTOTwo = null;
        accountDTOThree = null;
        accountsDTOOne.clear();
        accounts.clear();
    }

    /**
     * getDTOFromAccount(Account account)
     */
    @Test
    void ensureThatAccountIsConvertedIntoAccountDTO() {
        // ARRANGE
        AccountDTO expected = new AccountDTO("Mike","mike@isep.ipp.pt", true);

        // ACT
        AccountDTO result = AccountMapper.accountToDTO(accountOne);

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * getListDTOFromAccounts(List<Account> accounts)
     */
    @Test
    void ensureThatAccountListIsConvertedIntoAccountListDTO() {
        // ARRANGE
        List<AccountDTO> expected = accountsDTOOne;

        //ACT
        List<AccountDTO> result = AccountMapper.listAccountsToDTO(accounts);

        // ASSERT
        assertEquals(expected, result);
    }
}