package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

public class AddCustomerController {

    /**
     * Attributes of the class addCustomerController, according to the Class Diagram.
     */
    private Company company;

    /**
     * Create addCustomerController constructor
     */

    public AddCustomerController(Company company) { this.company = company;}

    /**
     * Method addCustomer
     *
     * @return true if customer is added
     * @return false if customer isn't added successfully
     */

    public boolean addCustomer(String customerName) {
        return (company.addCustomer(customerName));
    }

}

