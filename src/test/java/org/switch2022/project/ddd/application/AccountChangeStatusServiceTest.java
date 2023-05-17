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
import org.switch2022.project.ddd.domain.value_object.AccountStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountChangeStatusService.class
)
class AccountChangeStatusServiceTest {
    @InjectMocks
    AccountChangeStatusService service;
    @MockBean
    IAccountRepository accountRepository;


    /**
     * Method changeStatus()
     * Scenario 1: returns true if status changed.
     */

    @Test
    void ensureThatStatusIsChanged() {
        //Arrange
        String email = "ana@isep.pt";
        Account expected = mock(Account.class);

        when(accountRepository.getAccountByEmail(email)).thenReturn(expected);
        when(expected.changeStatus(AccountStatus.ACTIVE)).thenReturn(true);

        //Act
        boolean result = service.changeStatus(email,AccountStatus.ACTIVE.getAccountStatus());

        //Assert
        assertTrue(result);
    }

    /**
     * Method changeStatus()
     * Scenario 2: returns false if status has not changed.
     */

    @Test
    void ensureThatStatusIsNotChanged() {
        //Arrange
        String email = "ana@isep.pt";
        Account expected = mock(Account.class);

        when(accountRepository.getAccountByEmail(email)).thenReturn(expected);
        when(expected.changeStatus(any())).thenReturn(false);

        //Act
        boolean result = service.changeStatus(email,AccountStatus.ACTIVE.getAccountStatus());

        //Assert
        assertFalse(result);
    }
}