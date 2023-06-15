package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.dto.CustomerDto;
import org.switch2022.project.ddd.dto.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for retrieving a list of all customers.
 * <p>
 * This class provides methods to retrieve CustomerDto objects representing the customers.
 */
@Service
public class CustomerListService {

    @SuppressWarnings("all")
    @Autowired
    @Qualifier("customer_jpa")
    private ICustomerRepository repository;

    @SuppressWarnings("all")
    @Autowired
    private CustomerMapper mapper;

    /**
     * Retrieves a list of all customers.
     *
     * @return A list of CustomerDto objects representing the customers.
     */
    public List<CustomerDto> listAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<Customer> customers = repository.findAll();
        for (int i = 0; i < customers.size(); i++) {
            customerDtos.add(mapper.customerToDto(customers.get(i)));
        }
        return customerDtos;
    }

    /**
     * Retrieves an optional customer DTO (Data Transfer Object) based on the provided tax ID.
     *
     * @param taxId The tax ID of the customer to be retrieved.
     * @return An optional containing the customer DTO corresponding to the provided tax ID, or an empty optional if the
     * customer is not found.
     */
    public Optional<CustomerDto> getCustomerByTaxId(TaxId taxId) {
        Optional<Customer> opCustomer = repository.findCustomerByTaxId(taxId);
        Optional<CustomerDto> opCustomerDto = Optional.empty();
        if (opCustomer.isPresent()) {
            opCustomerDto = Optional.of(mapper.customerToDto(opCustomer.get()));
        }
        return opCustomerDto;
    }
}
