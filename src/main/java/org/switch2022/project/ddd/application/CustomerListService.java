package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.dto.CustomerDto;
import org.switch2022.project.ddd.dto.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

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
}
