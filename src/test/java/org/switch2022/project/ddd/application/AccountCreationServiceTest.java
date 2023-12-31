package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountFactory;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.dto.AccountCreationDto;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountCreationService.class
)
class AccountCreationServiceTest {
    @InjectMocks
    AccountCreationService service;
    @MockBean
    IAccountFactory accountFactory;
    @Qualifier("account_jpa")
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
        // Arrange
        String name = "Ana";
        String email = "ana@isep.pt";
        int phoneNumber = 923456789;
        BufferedImage photo = mock(BufferedImage.class);
        Account account = mock(Account.class);
        AccountCreationDto accountCreationDto = new AccountCreationDto(name, email, phoneNumber,
                photo);

        when(accountFactory.create(any(), any(), any(), any())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(true);

        // Act
        boolean result = service.registerAccount(accountCreationDto);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: The account is not created because it already exists in the
     * repository.
     * Expected return: False.
     */

    @Test
    void ensureThatAccountIsNotCreatedBecauseItAlreadyExist() {
        // Arrange
        String name = "Ana";
        String email = "ana@isep.pt";
        int phoneNumber = 923456789;
        BufferedImage photo = mock(BufferedImage.class);
        Account account = mock(Account.class);
        AccountCreationDto accountCreationDto = new AccountCreationDto(name, email, phoneNumber,
                photo);

        when(accountFactory.create(any(), any(), any(), any())).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(false);

        // Act
        boolean result = service.registerAccount(accountCreationDto);

        // Assert
        assertFalse(result);
    }
}