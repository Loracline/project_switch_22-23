package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.CustomerService;

/**
 * Controller responsible for creating a new customer.
 */
@Controller
public class AddCustomerController {

    /**
     * The customer service used to create the customer.
     */
    @SuppressWarnings("all")
    @Autowired
    private CustomerService service;

    /**
     * Creates a new customer with the given tax ID number and name, using the customer service.
     *
     * @param taxIdNumber the tax ID number of the customer, which must be valid, according to the TaxId validation
     *                    rules.
     * @param name        the name of the customer.
     * @return TRUE if the customer was successfully created, or FALSE otherwise.
     */
    public boolean addCustomer(String taxIdNumber, String name) {
        return service.addCustomer(taxIdNumber, name);
    }
}
