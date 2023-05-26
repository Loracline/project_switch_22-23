package org.switch2022.project.ddd.webcontrollers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = CustomerWebControllerTest.class
)
class CustomerWebControllerTest {

    @InjectMocks
    CustomerWebController controller;

    @MockBean
    CustomerService service;

    @DisplayName("Customer created successfully")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);

        when(service.addCustomer(dtoDouble)).thenReturn(true);

        // Act
        ResponseEntity<Object> response = controller.addCustomer(dtoDouble);

        // Assert
        assertEquals(response.getStatusCodeValue(), 201);
    }

    @DisplayName("Customer is not created - already exists")
    @Test
    void ensureCustomerIsNotCreatedBecauseAlreadyExists() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);
        String expected = "Customer's tax ID already exists!";

        service.addCustomer(dtoDouble);

        when(service.addCustomer(dtoDouble)).thenThrow(new AlreadyExistsInRepoException(expected));

        // Act
        ResponseEntity<Object> response = controller.addCustomer(dtoDouble);

        // Assert
        assertEquals(expected, response.getBody());
        assertEquals(response.getStatusCodeValue(), 409);
    }

    @DisplayName("Customer is not created - invalid tax ID")
    @Test
    void ensureCustomerIsNotCreatedBecauseTaxIdIsInvalid() {
        //Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);
        String expected = "Invalid or unsupported country for tax ID validation.";

        when(service.addCustomer(dtoDouble)).thenThrow(new InvalidInputException(expected));

        //Act
        ResponseEntity<Object> response = controller.addCustomer(dtoDouble);

        //Assert
        assertEquals(expected, response.getBody());
        assertEquals(response.getStatusCodeValue(), 409);
    }
}