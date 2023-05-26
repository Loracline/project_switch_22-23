package org.switch2022.project.ddd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.ddd.application.CustomerService;
import org.switch2022.project.ddd.dto.CustomerCreationDto;

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
     * Adds a new customer based on the provided CustomerCreationDto by delegating the operation to the CustomerService.
     *
     * @param dto The CustomerCreationDto object containing customer information.
     * @return true if the customer was successfully added, false otherwise.
     */
    public boolean addCustomer(CustomerCreationDto dto) {
        return service.addCustomer(dto);
    }
}
