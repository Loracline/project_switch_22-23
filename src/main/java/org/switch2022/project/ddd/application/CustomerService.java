package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.dto.CustomerCreationDto;
import org.switch2022.project.ddd.exceptions.InvalidInputException;

/**
 * Service class responsible for creating and managing customers.
 */
@Service
public class CustomerService {

    /**
     * The customer repository used to store and retrieve customers.
     */
    @SuppressWarnings("all")
    @Qualifier("customer_jpa")
    @Autowired
    private ICustomerRepository repository;

    /**
     * The customer factory used to create new customers.
     */
    @SuppressWarnings("all")
    @Autowired
    private ICustomerFactory factory;

    /**
     * Adds a new customer based on the provided CustomerCreationDto.
     *
     * @param dto The CustomerCreationDto object containing customer information.
     * @return true if the customer was successfully added, false otherwise.
     * @throws InvalidInputException if the tax ID is invalid or the country is unsupported for tax ID validation.
     */
    public boolean addCustomer(CustomerCreationDto dto) {
        Name customerName = new Name(dto.customerName);
        TaxId customerTaxId = new TaxId(dto.customerTaxId);

        if (customerTaxId.isValid()) {
            Customer customer = factory.createCustomer(customerTaxId, customerName);
            return repository.save(customer);
        } else {
            throw new InvalidInputException("Invalid or unsupported country for tax ID validation.");
        }
    }
}
