package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.AccountChangeStatusService;
import org.switch2022.project.ddd.application.AccountCreationService;
import org.switch2022.project.ddd.application.AccountListService;
import org.switch2022.project.ddd.dto.AccountCreationDto;
import org.switch2022.project.ddd.dto.AccountDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AccountWebController.class)

public class AccountWebControllerTest {
    @InjectMocks
    AccountWebController controller;

    @MockBean
    AccountCreationService accountCreationService;

    @MockBean
    AccountListService accountListService;

    @MockBean
    AccountChangeStatusService accountChangeStatusService;

    @Test
    void ensureThatAccountIsCreatedSuccessfully() {
        //ARRANGE
        AccountCreationDto accountDtoDouble = mock(AccountCreationDto.class);

        when(accountCreationService.registerAccount(accountDtoDouble)).thenReturn(true);
        //ACT
        ResponseEntity<Object> responseEntity = controller.registerAccount(accountDtoDouble);
        //ASSERT
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void ensureThatAccountIsNotCreated() {
        //ARRANGE
        AccountCreationDto accountDtoDouble = mock(AccountCreationDto.class);
        when(accountCreationService.registerAccount(accountDtoDouble)).thenReturn(false);

        //ACT
        ResponseEntity<Object> responseEntity = controller.registerAccount(accountDtoDouble);

        //ASSERT
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void ensureThatAListOfAccountDtoIsReturned() {
        //ARRANGE
        List<AccountDto> expected = new ArrayList<>();
        AccountDto accountDtoDouble = mock(AccountDto.class);
        expected.add(accountDtoDouble);
        when(accountListService.listAllAccounts()).thenReturn(expected);

        //ACT
        ResponseEntity<List<AccountDto>> responseEntity =
                controller.listAllAccounts();

        //ASSERT
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<AccountDto> actual = responseEntity.getBody();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void ensureThatTheAccountStatusIsChangedSuccessfully() {
        //ARRANGE
        String email = "ana@isep.com";
        String status = "Active";

        when(accountChangeStatusService.changeStatus(email, status)).thenReturn(true);

        //ACT
        ResponseEntity<Object> response = controller.changeStatus(email, status);

        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void ensureThatTheAccountStatusIsNotChanged() {
        //ARRANGE
        String email = "ana@isep.com";
        String status = "Inactive";

        when(accountChangeStatusService.changeStatus(email, status)).thenReturn(false);

        //ACT
        ResponseEntity<Object> response = controller.changeStatus(email, status);

        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * METHOD getByEmail()
     */
    @DisplayName("Account DTO")
    @Test
    void ensureAccountDtoIsRetrievedWhenAccountExistsInDatabase() {
        // Arrange
        String email = "joao@email.pt";

        AccountDto expected = new AccountDto("joao", "joao@email.pt","active");

        when(accountListService.getAccountByEmail(any())).thenReturn(Optional.of(expected));

        // Act
        ResponseEntity<AccountDto> response = controller.getByEmail(email);

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Not found")
    @Test
    void ensureCustomerNotFoundWhenDoesntExistInDatabase() {
        // Arrange
        String email = "joao@email.com";

        when(accountListService.getAccountByEmail(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<AccountDto> response = controller.getByEmail(email);

        // Assert
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
