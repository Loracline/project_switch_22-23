package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.dto.CustomerDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerMapperTest {

    @DisplayName("Customer -> DTO of Customer")
    @Test
    void ensureCustomerIsMappedToDtoSuccessfully() {
        // Arrange
        CustomerMapper mapper = new CustomerMapper();

        Customer customer = mock(Customer.class);

        String name = "Partilha Cortesia, Lda.";
        String taxIdNumber = "514024054";

        when(customer.getName()).thenReturn(name);
        when(customer.getTaxId()).thenReturn(taxIdNumber);

        CustomerDto expected = new CustomerDto(name, taxIdNumber);

        // Act
        CustomerDto result = mapper.customerToDto(customer);

        // Assert
        assertEquals(expected, result);
    }
}