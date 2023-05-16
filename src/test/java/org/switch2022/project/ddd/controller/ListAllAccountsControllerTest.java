package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.AccountService;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ListAllAccountsController.class
)
class ListAllAccountsControllerTest {
    @InjectMocks
    ListAllAccountsController controller;
    @MockBean
    AccountService accountService;
    @MockBean
    AccountMapper accountMapper;

    /**
     * Method listAllAccounts()
     *
     * Scenario 1: returns a list of AccountsDto
     */
    @Test
    void ensureThatAListOfAccountDtoIsReturned() {
        // Arrange
        Account account = mock(Account.class);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        AccountDto accountDto = mock(AccountDto.class);
        List<AccountDto> expectedAccountDtoList =
                Arrays.asList(accountDto);

        when(accountService.listAllAccounts()).thenReturn(accounts);
        when(accountMapper.listAccountsToDto(any())).thenReturn(expectedAccountDtoList);

        // Act
        List<AccountDto> actualAccountDtoList =
                controller.listAllAccounts();

        // Assert
        assertEquals(expectedAccountDtoList, actualAccountDtoList);
    }

    /**
     * Method listAllAccounts()
     *
     * Scenario 2: returns an empty list of AccountDto
     */
    @Test
    void ensureThatAnEmptyListOfAccountDtoIsReturned() {
        // Arrange
        List<Account> emptyAccountList = new ArrayList<>();
        when(accountService.listAllAccounts()).thenReturn(emptyAccountList);

        // Act
        List<AccountDto> result = controller.listAllAccounts();

        // Assert
        assertTrue(result.isEmpty());
    }
}