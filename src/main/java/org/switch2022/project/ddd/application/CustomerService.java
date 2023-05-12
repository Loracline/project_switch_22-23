package org.switch2022.project.ddd.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.domain.model.customer.Customer;
import org.switch2022.project.ddd.domain.model.customer.ICustomerFactory;
import org.switch2022.project.ddd.domain.model.customer.ICustomerRepository;
import org.switch2022.project.ddd.domain.value_object.Name;
import org.switch2022.project.ddd.domain.value_object.TaxId;
import org.switch2022.project.ddd.exceptions.InvalidTaxIdException;

/**
 * Service class responsible for creating and managing customers.
 */
@Service
public class CustomerService {

    /**
     * The customer repository used to store and retrieve customers.
     */
    @SuppressWarnings("all")
    @Autowired
    private ICustomerRepository repository;

    /**
     * The customer factory used to create new customers.
     */
    @SuppressWarnings("all")
    @Autowired
    private ICustomerFactory factory;

    /**
     * Creates a new customer with the given tax ID number and name, and adds them to the customer repository.
     *
     * @param taxIdNumber the tax ID number of the customer, which must be valid according to the TaxId validation rules.
     * @param name        the name of the customer.
     * @return TRUE if the customer was successfully added to the repository, or FALSE otherwise.
     * @throws InvalidTaxIdException if the tax ID number is invalid or unsupported according to the TaxId validation
     *                               rules.
     */
    public boolean addCustomer(String taxIdNumber, String name) {
        Name customerName = new Name(name);
        TaxId customerTaxId = new TaxId(taxIdNumber);

        if (customerTaxId.isValid()) {
            Customer customer = factory.createCustomer(customerTaxId, customerName);
            return repository.add(customer);
        } else throw new InvalidTaxIdException("Invalid or unsupported country for tax ID validation.");
    }
}
