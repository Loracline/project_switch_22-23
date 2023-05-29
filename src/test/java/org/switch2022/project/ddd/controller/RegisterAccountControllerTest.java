package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.AccountCreationService;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RegisterAccountController.class
)
class RegisterAccountControllerTest {
    @InjectMocks
    RegisterAccountController controller;
    @MockBean
    AccountCreationService accountService;

    /**
     * Method: registerAccount()
     * Scenario 01: Account is created successfully.
     * Expected return: TRUE.
     */

    @DisplayName("account created successfully")
    @Test
    void ensureThatAccountIsCreatedSuccessfully() {
        // Arrange
        AccountCreationDto accountCreationDto = new AccountCreationDto("Ana", "ana@isep" +
                ".pt", 123456789, null);
        when(accountService.registerAccount(accountCreationDto)).thenReturn(true);

        // Act
        boolean result = controller.registerAccount(accountCreationDto);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 2: Account is not created because the AccountCreationDto is null.
     */

    @DisplayName("account not created")
    @Test
    void ensureThatAccountIsNotCreatedBecauseDtoIsNull() {
        // Arrange
        String expected = "The account Dto can't be null";
        AccountCreationDto accountCreationDto = null;

        // Act
        IllegalArgumentException result =
                Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.registerAccount(accountCreationDto));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * Scenario 3: Account is not created because it already exists.
     */
    @DisplayName("account not created")
    @Test
    void ensureThatAccountIsNotCreatedBecauseItAlreadyExists() {
        // Arrange
        String expected = "The account already exists in the repository.";
        AccountCreationDto accountCreationDto = new AccountCreationDto("Ana", "ana@isep" +
                ".pt", 123456789, null);
        when(accountService.registerAccount(accountCreationDto)).thenThrow(new AlreadyExistsInRepoException(expected));

        // Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class,
                () -> controller.registerAccount(accountCreationDto));

        //Assert
        assertEquals(expected, result.getMessage());
    }

}