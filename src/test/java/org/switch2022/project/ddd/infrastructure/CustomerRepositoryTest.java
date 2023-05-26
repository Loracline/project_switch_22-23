package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRepositoryTest {

    /**
     * METHOD save()
     */
    @DisplayName("Customer is added successfully")
    @Test
    void ensureCustomerIsAddedToRepositorySuccessfully() {
        // ARRANGE
        CustomerRepository repository = new CustomerRepository();
        Customer customer = mock(Customer.class);

        boolean expected = true;

        // ACT
        boolean result = repository.save(customer);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Customer already exists")
    @Test
    void ensureCustomerIsNotAddedWhenAlreadyExists() {
        CustomerRepository repository = new CustomerRepository();
        Customer customer = mock(Customer.class);
        repository.save(customer);

        String expected = "Customer's tax ID already exists!";

        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class,
                () -> repository.save(customer));

        // ACT
        String result = exception.getMessage();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD findCustomerNameByTaxId()
     */
    @SuppressWarnings("all")
    @DisplayName("Customer retrieved successfully")
    @Test
    void ensureCustomerIsRetrievedSuccessfully() {
        // ARRANGE
        // Double of Customer One.
        TaxId customerTaxIdOne = mock(TaxId.class);
        String customerNameOne = "Partilha Cortesia Lda.";
        Customer customerOne = mock(Customer.class);

        // Double of Customer Two.
        Customer customerTwo = mock(Customer.class);

        // Double of Customer Three.
        Customer customerThree = mock(Customer.class);

        // Setting up mock behaviour.
        when(customerOne.hasTaxId(customerTaxIdOne)).thenReturn(true);
        when(customerOne.getName()).thenReturn(customerNameOne);

        // Adding customers to the Repository.
        CustomerRepository repository = new CustomerRepository();
        repository.save(customerOne);
        repository.save(customerTwo);
        repository.save(customerThree);

        // The customer's name one wishes to retrieve - Customer One.
        String expected = customerNameOne;

        // ACT
        String result = repository.findCustomerNameByTaxId(customerTaxIdOne);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Customer does not exist in repository")
    @Test
    void ensureAnExceptionIsThrownWhenCustomerDoesNotExist() {
        // ARRANGE
        // Double of Customers to add in Repository.
        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);

        // Double of Tax ID of the Customer Three that don't exist in Repository.
        TaxId customerTaxIdThree = mock(TaxId.class);

        // Adding customers One and Two to the Repository.
        CustomerRepository repository = new CustomerRepository();
        repository.save(customerOne);
        repository.save(customerTwo);

        // Exception thrown when searching for the Customer Three in Repository.
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> repository.findCustomerNameByTaxId(customerTaxIdThree));

        // An exception message is expected because Customer Three is not in Repository.
        String expected = "Customer with this tax ID does not exist in Repository.";

        // ACT
        String result = exception.getMessage();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD findCustomerTaxIdByName()
     * <p>
     * Scenario 1: customer ID is retrieved successfully.
     */
    @Test
    void ensureCustomerIdIsRetrievedSuccessfully() {
        // Arrange
        String customerNameOne = "Philips";
        String customerTaxId = "c001";
        Customer customerOne = mock(Customer.class);

        CustomerRepository repository = new CustomerRepository();
        repository.save(customerOne);

        when(customerOne.getName()).thenReturn(customerNameOne);
        when(customerOne.getTaxId()).thenReturn(customerTaxId);

        // Act
        String result = repository.findCustomerTaxIdByName(customerNameOne);

        // Assert
        assertEquals(customerTaxId, result);
    }

    /**
     * Scenario 2: customer ID is not retrieved successfully.
     */
    @Test
    void ensureCustomerIdIsNotRetrievedSuccessfully() {
        // Arrange
        String customerNameOne = "Philips";
        String customerNameTwo = "Sony";
        String customerTaxId = "c001";
        Customer customerOne = mock(Customer.class);

        CustomerRepository repository = new CustomerRepository();
        repository.save(customerOne);

        when(customerOne.getName()).thenReturn(customerNameOne);
        when(customerOne.getTaxId()).thenReturn(customerTaxId);

        // Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> repository.findCustomerTaxIdByName(customerNameTwo));

        String expected = "Customer with this name does not exist in the Repository.";

        // Assert
        assertEquals(expected, exception.getMessage());
    }
}