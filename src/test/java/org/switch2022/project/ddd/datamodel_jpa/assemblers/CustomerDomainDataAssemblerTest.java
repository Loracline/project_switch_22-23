package org.switch2022.project.ddd.datamodel_jpa.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CustomerDomainDataAssemblerTest.class)
class CustomerDomainDataAssemblerTest {

    @InjectMocks
    CustomerDomainDataAssembler assembler;

    @MockBean
    ICustomerFactory factory;

    @DisplayName("From Domain Model to Data Schema")
    @Test
    void ensureConversionToDataSchemaIsSuccessful() {
        // Arrange
        Customer customerDouble = mock(Customer.class);
        when(factory.createCustomer(any(), any())).thenReturn(customerDouble);

        // Act
        CustomerJpa result = assembler.toData(customerDouble);

        // Assert
        assertEquals(customerDouble.getName(), result.getCustomerName());
        assertEquals(customerDouble.getTaxId(), result.getCustomerTaxId());
    }

    @DisplayName("From Data Schema to Domain Model")
    @Test
    void ensureConversionToDomainModelIsSuccessful() {
        // Arrange
        CustomerJpa customerJpaDouble = mock(CustomerJpa.class);
        Customer customerDouble = mock(Customer.class);
        when(customerJpaDouble.getCustomerTaxId()).thenReturn("514024054");
        when(customerJpaDouble.getCustomerName()).thenReturn("Partilha Cortesia, Lda.");
        when(factory.createCustomer(any(), any())).thenReturn(customerDouble);

        // Act
        Customer result = assembler.toDomain(customerJpaDouble);

        // Assert
        assertEquals(customerDouble.getTaxId(), result.getTaxId());
        assertEquals(customerDouble.getName(), result.getName());
    }
}