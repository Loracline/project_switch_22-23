package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.value_object.TaxId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    void ensureTwoEqualRepositoryInstancesHaveDifferentHashcode() {
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

    /**
     * METHOD getCustomerByTaxId()
     */
    @DisplayName("Customer retrieved successfully")
    @Test
    void ensureCustomerIsRetrievedSuccessfully() {
        // ARRANGE
        // Double of Customer One.
        TaxId customerTaxIdOne = mock(TaxId.class);
        String customerNameOne = "Partilha Cortesia Lda.";
        Customer customerOne = mock(Customer.class);

        // Double of Customer Two.
        TaxId customerTaxIdTwo = mock(TaxId.class);
        Customer customerTwo = mock(Customer.class);

        // Double of Customer Three.
        TaxId customerTaxIdThree = mock(TaxId.class);
        Customer customerThree = mock(Customer.class);

        // Setting up mock behaviour.
        when(customerOne.hasTaxId(customerTaxIdOne)).thenReturn(true);
        when(customerTwo.hasTaxId(customerTaxIdTwo)).thenReturn(false);
        when(customerThree.hasTaxId(customerTaxIdThree)).thenReturn(false);

        when(customerOne.getCustomerName()).thenReturn(customerNameOne);

        // Adding customers to the Repository.
        CustomerRepository repository = new CustomerRepository();
        repository.addCustomerToRepository(customerOne);
        repository.addCustomerToRepository(customerTwo);
        repository.addCustomerToRepository(customerThree);

        // The customer one wishes to retrieve - Customer One.
        Optional<String> expected = Optional.of(customerNameOne);

        // ACT
        Optional<String> result = repository.getCustomerNameByTaxId(customerTaxIdOne);

        // ASSERT
        assertEquals(expected, result);
    }

    @DisplayName("Customer does not exist in repository")
    @Test
    void ensureAnEmptyOptionalIsRetrievedWhenCustomerDoesNotExist() {
        // ARRANGE
        // Double of Customer One.
        TaxId customerTaxIdOne = mock(TaxId.class);
        Customer customerOne = mock(Customer.class);

        // Double of Customer Two.
        TaxId customerTaxIdTwo = mock(TaxId.class);
        Customer customerTwo = mock(Customer.class);

        // Double of Customer Three.
        TaxId customerTaxIdThree = mock(TaxId.class);
        Customer customerThree = mock(Customer.class);

        // Setting up mock behaviour.
        when(customerOne.hasTaxId(customerTaxIdOne)).thenReturn(true);
        when(customerTwo.hasTaxId(customerTaxIdTwo)).thenReturn(false);
        when(customerThree.hasTaxId(customerTaxIdThree)).thenReturn(false);

        // Adding customers to the Repository.
        CustomerRepository repository = new CustomerRepository();
        repository.addCustomerToRepository(customerOne);
        repository.addCustomerToRepository(customerTwo);

        // An empty optional is expected because Customer Three is not in Repository.
        Optional<String> expected = Optional.empty();

        // ACT
        Optional<String> result = repository.getCustomerNameByTaxId(customerTaxIdThree);

        // ASSERT
        assertEquals(expected, result);
    }
}