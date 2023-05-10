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
     * METHOD equals()
     */
    @SuppressWarnings("all")
    @DisplayName("Same object equals itself")
    @Test
    void ensureSameRepositoryEqualsItself() {
        // Arrange
        CustomerRepository reference = new CustomerRepository();
        CustomerRepository other = reference;
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two repositories are equal")
    @Test
    void ensureTwoInstancesAreEqual() {
        // Arrange
        CustomerRepository reference = new CustomerRepository();
        CustomerRepository other = new CustomerRepository();
        boolean expected = true;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two repositories are different")
    @Test
    void ensureTwoInstancesAreDifferent() {
        // Arrange
        Customer customer = mock(Customer.class);
        CustomerRepository reference = new CustomerRepository();
        reference.addCustomerToRepository(customer);

        CustomerRepository other = new CustomerRepository();
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("Repository does not equal null")
    @Test
    void ensureRepositoryDoesNotEqualNull() {
        // Arrange
        CustomerRepository reference = new CustomerRepository();
        CustomerRepository other = null;
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    @SuppressWarnings("all")
    @DisplayName("Repository does not equal other type of object")
    @Test
    void ensureRepositoryDoesNotEqualOtherTypeOfObject() {
        // Arrange
        CustomerRepository reference = new CustomerRepository();
        ProjectRepository other = mock(ProjectRepository.class);
        boolean expected = false;

        // Act
        boolean result = reference.equals(other);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD hashCode()
     */
    @DisplayName("Two repositories have the same hashcode")
    @Test
    void ensureTwoEqualRepositoryInstancesHaveTheSameHashcode() {
        // Arrange
        CustomerRepository reference = new CustomerRepository();
        CustomerRepository other = new CustomerRepository();
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Two repositories have different hashcode")
    @Test
    void ensureTwoRepositoryInstancesHaveDifferentHashcode() {
        // Arrange
        Customer customer = mock(Customer.class);
        CustomerRepository reference = new CustomerRepository();
        reference.addCustomerToRepository(customer);

        CustomerRepository other = new CustomerRepository();
        int expected = reference.hashCode();

        // Act
        int result = other.hashCode();

        // Assert
        assertNotEquals(expected, result);
    }

    /**
     * METHOD addCustomerToRepository()
     */
    @DisplayName("Customer is added successfully")
    @Test
    void ensureCustomerIsAddedToRepositorySuccessfully() {
        // ARRANGE
        CustomerRepository repository = new CustomerRepository();
        Customer customer = mock(Customer.class);

        boolean expected = true;

        // ACT
        boolean result = repository.addCustomerToRepository(customer);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Customer already exists")
    @Test
    void ensureCustomerIsNotAddedWhenAlreadyExists() {
        CustomerRepository repository = new CustomerRepository();
        Customer customer = mock(Customer.class);
        repository.addCustomerToRepository(customer);

        String expected = "Customer's tax ID already exists!";

        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class,
                () -> repository.addCustomerToRepository(customer));

        // ACT
        String result = exception.getMessage();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getCustomerByTaxId()
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
        repository.addCustomerToRepository(customerOne);
        repository.addCustomerToRepository(customerTwo);
        repository.addCustomerToRepository(customerThree);

        // The customer's name one wishes to retrieve - Customer One.
        String expected = customerNameOne;

        // ACT
        String result = repository.getCustomerNameByTaxId(customerTaxIdOne);

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
        repository.addCustomerToRepository(customerOne);
        repository.addCustomerToRepository(customerTwo);

        // Exception thrown when searching for the Customer Three in Repository.
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> repository.getCustomerNameByTaxId(customerTaxIdThree));

        // An exception message is expected because Customer Three is not in Repository.
        String expected = "Customer with this tax ID does not exist in Repository.";

        // ACT
        String result = exception.getMessage();

        // ASSERT
        assertEquals(expected, result);
    }

    /**
     * METHOD getCustomerTaxIdByName()
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
        repository.addCustomerToRepository(customerOne);

        when(customerOne.getName()).thenReturn(customerNameOne);
        when(customerOne.getTaxId()).thenReturn(customerTaxId);

        // Act
        String result = repository.getCustomerTaxIdByName(customerNameOne);

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
        repository.addCustomerToRepository(customerOne);

        when(customerOne.getName()).thenReturn(customerNameOne);
        when(customerOne.getTaxId()).thenReturn(customerTaxId);

        // Act
        NotFoundInRepoException exception = assertThrows(NotFoundInRepoException.class,
                () -> repository.getCustomerTaxIdByName(customerNameTwo));

        String expected = "Customer with this name does not exist in the Repository.";

        // Assert
        assertEquals(expected, exception.getMessage());
    }
}