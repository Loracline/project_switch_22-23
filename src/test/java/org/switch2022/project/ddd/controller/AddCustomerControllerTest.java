package org.switch2022.project.ddd.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // JUnit 5 extension that provides support for Mockito's mocking framework.
@AutoConfigureMockMvc // Configures an instance that can be used to send HTTP requests to the app and verify the responses.
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK, // App should be started with a mock web environment.
        classes = AddCustomerController.class // Class that should be loaded by the Spring context.
)

class AddCustomerControllerTest {

    @InjectMocks
    AddCustomerController controller;

    @MockBean
    CustomerService service;

    @DisplayName("Customer created successfully")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514 024 054";

        when(service.addCustomer(any(), any())).thenReturn(true);

        boolean expected = true;

        // Act
        boolean result = controller.addCustomer(taxIdNumber, name);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Customer is not created - already exists")
    @Test
    void ensureCustomerIsNotCreatedBecauseAlreadyExists() {
        //Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514 024 054";

        String expected = "Customer's tax ID already exists!";
        when(service.addCustomer(any(), any())).thenThrow(new AlreadyExistsInRepoException(expected));

        //Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> controller.addCustomer(taxIdNumber, name));

        //Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Customer is not created - invalid tax ID")
    @Test
    void ensureCustomerIsNotCreatedBecauseTaxIdIsInvalid() {
        //Arrange
        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514 024 0X4";

        String expected = "Invalid or unsupported country for tax ID validation.";
        when(service.addCustomer(any(), any())).thenThrow(new InvalidInputException(expected));

        //Act
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> controller.addCustomer(taxIdNumber, name));

        //Assert
        assertEquals(expected, result.getMessage());
    }
}