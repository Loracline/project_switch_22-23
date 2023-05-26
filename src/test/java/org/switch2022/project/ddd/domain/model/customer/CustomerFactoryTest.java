package org.switch2022.project.ddd.domain.model.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class CustomerFactoryTest {

    private ICustomerFactory factory;

    @Mock
    private TaxId taxIdDouble;

    @Mock
    private Name nameDouble;

    @BeforeEach
    public void setup() {
        factory = new CustomerFactory();
    }

    @DisplayName("Customer created")
    @Test
    void ensureCustomerIsCreatedSuccessfully() {
        // Arrange
        Customer expected = new Customer(taxIdDouble, nameDouble);

        // Act
        Customer result = factory.createCustomer(taxIdDouble, nameDouble);

        // Assert
        assertEquals(expected.getTaxId(), result.getTaxId());
        assertEquals(expected.getName(), result.getName());
    }
}