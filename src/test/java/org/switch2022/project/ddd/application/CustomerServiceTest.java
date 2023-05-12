package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.InvalidTaxIdException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerService.class
)

class CustomerServiceTest {

    @InjectMocks
    CustomerService service;

    @MockBean
    ICustomerFactory factory;

    @MockBean
    ICustomerRepository repository;

    @DisplayName("Customer is created successfully")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        String customerName = "Partilha Cortesia, Lda.";
        String customerTaxId = "514024054";

        when(repository.add(any())).thenReturn(true);

        boolean expected = true;

        // Act
        boolean result = service.addCustomer(customerTaxId, customerName);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Customer is not created - already exists")
    @Test
    void ensureCustomerIsNotCreatedBecauseAlreadyExists() {
        // Arrange
        String customerName = "Partilha Cortesia, Lda.";
        String customerTaxId = "514 024 054";

        String expected = "Customer's tax ID already exists!";
        when(repository.add(any())).thenThrow(new AlreadyExistsInRepoException(expected));

        // Act
        AlreadyExistsInRepoException result =
                assertThrows(AlreadyExistsInRepoException.class, () -> service.addCustomer(customerTaxId, customerName));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    @DisplayName("Customer is not created - invalid tax ID")
    @Test
    void ensureCustomerIsNotCreatedBecauseTaxIdIsInvalid() {
        // Arrange
        String customerName = "Partilha Cortesia, Lda.";
        String customerTaxId = "514 024 0O4";

        String expected = "Invalid or unsupported country for tax ID validation.";
        when(factory.createCustomer(any(), any())).thenThrow(new InvalidTaxIdException(expected));

        // Act
        InvalidTaxIdException result =
                assertThrows(InvalidTaxIdException.class, () -> service.addCustomer(customerTaxId, customerName));


        // Assert
        assertEquals(expected, result.getMessage());
    }
}