package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.datamodel_jpa.CustomerJpa;
import org.switch2022.project.ddd.datamodel_jpa.assemblers.CustomerDomainDataAssembler;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;
import org.switch2022.project.ddd.infrastructure.jpa.ICustomerJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CustomerJpaRepositoryTest.class
)
class CustomerJpaRepositoryTest {

    @InjectMocks
    CustomerJpaRepository repository;

    @MockBean
    ICustomerJpaRepository crudRepository;

    @MockBean
    CustomerDomainDataAssembler assembler;

    /**
     * METHOD save()
     */
    @DisplayName("Customer is saved")
    @Test
    void ensureCustomerIsSavedSuccessfully() {
        // Arrange
        Customer customer = mock(Customer.class);

        // Act
        boolean result = repository.save(customer);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Customer already exists")
    @Test
    void ensureExceptionIsThrownWhenCustomerExists() {
        // Arrange
        String expected = "Customer's tax ID already exists!";
        String taxId = "514 024 054";

        Customer customer = mock(Customer.class);
        CustomerJpa customerJpa = mock(CustomerJpa.class);

        when(assembler.toData(customer)).thenReturn(customerJpa);
        when(customer.getTaxId()).thenReturn(taxId);
        when(crudRepository.existsByCustomerTaxId(anyString())).thenReturn(true);

        // Act
        AlreadyExistsInRepoException result = assertThrows(
                AlreadyExistsInRepoException.class,
                () -> repository.save(customer));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD findCustomerNameByTaxId()
     */
    @DisplayName("Customer name is retrieved")
    @Test
    void ensureCustomerNameIsRetrievedSuccessfully() {
        // Arrange
        String expected = "Partilha Cortesia, Lda.";
        String taxId = "514 024 054";

        TaxId taxIdDouble = mock(TaxId.class);
        CustomerJpa customerJpaDouble = mock(CustomerJpa.class);
        Optional<CustomerJpa> optionalDouble = Optional.of(customerJpaDouble);

        when(taxIdDouble.getNumber()).thenReturn(taxId);
        when(crudRepository.findByCustomerTaxId(taxId)).thenReturn(optionalDouble);
        when(optionalDouble.get().getCustomerName()).thenReturn(expected);

        // Act
        String result = repository.findCustomerNameByTaxId(taxIdDouble);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Customer name not found")
    @Test
    void ensureExceptionIsThrownWhenCustomerDoesNotExistSearchingByTaxId() {
        // Arrange
        String expected = "Customer with this tax ID does not exist in the repository.";
        String taxId = "514 024 054";

        TaxId taxIdDouble = mock(TaxId.class);
        Optional<CustomerJpa> optionalDouble = Optional.empty();

        when(taxIdDouble.getNumber()).thenReturn(taxId);
        when(crudRepository.findByCustomerTaxId(taxId)).thenReturn(optionalDouble);

        // Act
        NotFoundInRepoException result = assertThrows(
                NotFoundInRepoException.class,
                () -> repository.findCustomerNameByTaxId(taxIdDouble));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD findCustomerTaxIdByName()
     */
    @DisplayName("Customer tax ID is retrieved")
    @Test
    void ensureCustomerTaxIdIsRetrievedSuccessfully() {
        // Arrange
        String expected = "514 024 054";
        String customerName = "Partilha Cortesia, Lda.";

        CustomerJpa customerJpaDouble = mock(CustomerJpa.class);
        Optional<CustomerJpa> optionalDouble = Optional.of(customerJpaDouble);

        when(crudRepository.findByCustomerName(customerName)).thenReturn(optionalDouble);
        when(optionalDouble.get().getCustomerTaxId()).thenReturn(expected);

        // Act
        String result = repository.findCustomerTaxIdByName(customerName);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Customer tax ID not found")
    @Test
    void ensureExceptionIsThrownWhenCustomerDoesNotExistSearchingByName() {
        // Arrange
        String expected = "Customer with this name does not exist in the repository.";
        String customerName = "Partilha Cortesia, Lda.";

        Optional<CustomerJpa> optionalDouble = Optional.empty();

        when(crudRepository.findByCustomerName(customerName)).thenReturn(optionalDouble);

        // Act
        NotFoundInRepoException result = assertThrows(
                NotFoundInRepoException.class,
                () -> repository.findCustomerTaxIdByName(customerName));

        // Assert
        assertEquals(expected, result.getMessage());
    }

    /**
     * METHOD findAll()
     */
    @DisplayName("Empty repository")
    @Test
    void ensureReturnsEmptyListWhenEmptyRepository() {
        // Arrange
        List<Customer> expected = new ArrayList<>();

        List<CustomerJpa> customersJpa = new ArrayList<>();

        when(crudRepository.findAll()).thenReturn(customersJpa);

        // Act
        List<Customer> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("List of all customers in repository")
    @Test
    void ensureAllCustomersInRepositoryAreReturnedInList() {
        // Arrange
        List<Customer> expected = new ArrayList<>();

        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);
        Customer customerThree = mock(Customer.class);

        expected.add(customerOne);
        expected.add(customerTwo);
        expected.add(customerThree);

        List<CustomerJpa> customersJpa = new ArrayList<>();

        CustomerJpa customerJpaOne = mock(CustomerJpa.class);
        CustomerJpa customerJpaTwo = mock(CustomerJpa.class);
        CustomerJpa customerJpaThree = mock(CustomerJpa.class);

        customersJpa.add(customerJpaOne);
        customersJpa.add(customerJpaTwo);
        customersJpa.add(customerJpaThree);

        when(crudRepository.findAll()).thenReturn(customersJpa);
        when(assembler.toDomain(customerJpaOne)).thenReturn(customerOne);
        when(assembler.toDomain(customerJpaTwo)).thenReturn(customerTwo);
        when(assembler.toDomain(customerJpaThree)).thenReturn(customerThree);

        // Act
        List<Customer> result = repository.findAll();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD findCustomerByTaxId()
     */
    @DisplayName("Optional of customer")
    @Test
    void ensureCustomerIsRetrievedSuccessfully() {
        // Arrange
        String taxId = "514 024 054";

        TaxId taxIdDouble = mock(TaxId.class);
        CustomerJpa customerJpaDouble = mock(CustomerJpa.class);
        Customer customerDouble = mock(Customer.class);
        Optional<CustomerJpa> optionalDouble = Optional.of(customerJpaDouble);

        Optional<Customer> expected = Optional.of(customerDouble);

        when(taxIdDouble.getNumber()).thenReturn(taxId);
        when(crudRepository.findByCustomerTaxId(taxId)).thenReturn(optionalDouble);
        when(assembler.toDomain(customerJpaDouble)).thenReturn(customerDouble);

        // Act
        Optional<Customer> result = repository.findCustomerByTaxId(taxIdDouble);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Empty optional")
    @Test
    void ensureCustomerIsNotRetrievedBecauseDoesntExist() {
        // Arrange
        TaxId taxIdDouble = mock(TaxId.class);
        Customer customerDouble = mock(Customer.class);

        Optional<CustomerJpa> empty = Optional.empty();
        Optional<Customer> expected = Optional.empty();

        when(crudRepository.findByCustomerTaxId(anyString())).thenReturn(empty);
        when(assembler.toDomain(any())).thenReturn(customerDouble);

        // Act
        Optional<Customer> result = repository.findCustomerByTaxId(taxIdDouble);

        // Assert
        assertEquals(expected, result);
    }
}