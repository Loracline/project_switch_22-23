package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.AccountListService;
import org.switch2022.project.ddd.dto.AccountDto;
import org.switch2022.project.ddd.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    AccountListService accountService;
    @MockBean
    AccountMapper accountMapper;

    /**
     * Method listAllAccounts()
     * <p>
     * Scenario 1: returns a list of AccountsDto
     */
    @Test
    void ensureThatAListOfAccountDtoIsReturned() {
        // Arrange
        AccountDto accountDto = mock(AccountDto.class);
        AccountDto accountDtoTwo = mock(AccountDto.class);
        List<AccountDto> expectedAccountDtoList =
                Arrays.asList(accountDto, accountDtoTwo);

        when(accountService.listAllAccounts()).thenReturn(expectedAccountDtoList);

        // Act
        List<AccountDto> actualAccountDtoList =
                controller.listAllAccounts();

        // Assert
        assertEquals(expectedAccountDtoList, actualAccountDtoList);
    }

    /**
     * Method listAllAccounts()
     * <p>
     * Scenario 2: returns an empty list of AccountDto
     */
    @Test
    void ensureThatAnEmptyListOfAccountDtoIsReturned() {
        // Arrange
        List<AccountDto> emptyAccountList = new ArrayList<>();
        when(accountService.listAllAccounts()).thenReturn(emptyAccountList);

        // Act
        List<AccountDto> result = controller.listAllAccounts();

        // Assert
        assertTrue(result.isEmpty());
    }
}