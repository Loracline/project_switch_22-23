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
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.dto.CustomerDto;
import org.switch2022.project.ddd.dto.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        classes = CustomerListServiceTest.class
)
class CustomerListServiceTest {

    @InjectMocks
    CustomerListService service;

    @MockBean
    @Qualifier("customer_jpa")
    ICustomerRepository repository;

    @MockBean
    CustomerMapper mapper;

    @DisplayName("Empty repository")
    @Test
    void ensureEmptyListIsRetrievedWhenEmptyRepository() {
        // Arrange
        List<CustomerDto> expected = new ArrayList<>();

        List<Customer> customers = new ArrayList<>();

        when(repository.findAll()).thenReturn(customers);

        // Act
        List<CustomerDto> result = service.listAllCustomers();

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("List of DTOs of all customers in repository")
    @Test
    void ensureListOfDtosOfAllCustomersIsRetrieved() {
        // Arrange
        List<CustomerDto> expected = new ArrayList<>();

        CustomerDto customerDtoOne = mock(CustomerDto.class);
        CustomerDto customerDtoTwo = mock(CustomerDto.class);
        CustomerDto customerDtoThree = mock(CustomerDto.class);

        expected.add(customerDtoOne);
        expected.add(customerDtoTwo);
        expected.add(customerDtoThree);

        List<Customer> customers = new ArrayList<>();

        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);
        Customer customerThree = mock(Customer.class);

        customers.add(customerOne);
        customers.add(customerTwo);
        customers.add(customerThree);

        when(repository.findAll()).thenReturn(customers);
        when(mapper.customerToDto(customerOne)).thenReturn(customerDtoOne);
        when(mapper.customerToDto(customerTwo)).thenReturn(customerDtoTwo);
        when(mapper.customerToDto(customerThree)).thenReturn(customerDtoThree);

        // Act
        List<CustomerDto> result = service.listAllCustomers();

        // Assert
        assertEquals(expected, result);
    }
}