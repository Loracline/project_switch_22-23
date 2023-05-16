package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.AccountService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ChangeAccountStatusController.class
)
class ChangeAccountStatusControllerTest {
    @InjectMocks
    ChangeAccountStatusController controller;
    @MockBean
    AccountService accountService;

    /**
     * Method changeStatus
     * Scenario 1: status is not changed because email is null.
     */
    @Test
    void ensureThatAccountStatusISNotChangedBecauseEmailIsNull() {
        // Arrange
        String email = null;
        String expectedMessage = "The email must not be null";

        // Act
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> controller.changeStatus(email, "active"));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Scenario 2: status is not changed because email is empty
     */
    @Test
    void ensureThatAccountStatusISNotChangedBecauseEmailIsBlank() {
        // Arrange
        String email = "";
        String expectedMessage = "The email must not be empty";

        // Act
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> controller.changeStatus(email, "active"));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Scenario 3: Status is updated, should return TRUE.
     */
    @Test
    void ensureThatAccountStatusIsChanged(){
        String email = "ana@isep.pt";
        when(accountService.changeStatus(email, "inactive")).thenReturn(true);

        boolean result = controller.changeStatus(email, "inactive");

        assertTrue(result);
    }
}