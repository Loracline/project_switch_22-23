package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerServiceTest.class
)
class CustomerServiceTest {

    @InjectMocks
    CustomerService service;

    @MockBean
    ICustomerFactory factory;

    @MockBean
    @Qualifier("customer_jpa")
    ICustomerRepository repository;

    @DisplayName("Customer is created successfully")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);
        Customer customerDouble = mock(Customer.class);

        when(dtoDouble.getCustomerName()).thenReturn("Partilha Cortesia Lda.");
        when(dtoDouble.getCustomerTaxId()).thenReturn("514024054");
        when(factory.createCustomer(any(), any())).thenReturn(customerDouble);
        when(repository.save(any())).thenReturn(true);

        boolean expected = true;

        // Act
        boolean result = service.addCustomer(dtoDouble);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Customer is not created - already exists")
    @Test
    void ensureCustomerIsNotCreatedBecauseAlreadyExists() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);
        Customer customerDouble = mock(Customer.class);

        String expected = "Customer's tax ID already exists!";

        when(dtoDouble.getCustomerName()).thenReturn("Partilha Cortesia Lda.");
        when(dtoDouble.getCustomerTaxId()).thenReturn("514024054");
        when(factory.createCustomer(any(), any())).thenReturn(customerDouble);
        when(repository.save(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        // Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.addCustomer(dtoDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Customer is not created - invalid tax ID")
    @Test
    void ensureCustomerIsNotCreatedBecauseTaxIdIsInvalid() {
        // Arrange
        CustomerCreationDto dtoDouble = mock(CustomerCreationDto.class);

        String expected = "Invalid or unsupported country for tax ID validation.";

        when(dtoDouble.getCustomerName()).thenReturn("Partilha Cortesia Lda.");
        when(dtoDouble.getCustomerTaxId()).thenReturn("51402X054");

        // Act
        InvalidInputException result =
                assertThrows(InvalidInputException.class, () -> service.addCustomer(dtoDouble));


        // Assert
        assertEquals(expected, result.getMessage());
    }
}