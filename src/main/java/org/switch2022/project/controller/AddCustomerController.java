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

    public AddCustomerController(Company company) {
        this.company = company;
    }

    /**
     * Method addCustomer
     *
     * @return true if customer is added
     *
     */

    public boolean addCustomer(String customerName, String email) {
        boolean addCustomer = false;
        if (company.validateAdministrator(email)) {
            addCustomer = company.addCustomer(customerName);
        }
        return addCustomer;
    }
}


