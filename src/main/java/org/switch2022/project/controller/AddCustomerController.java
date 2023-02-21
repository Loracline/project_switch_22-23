package org.switch2022.project.controller;

import org.switch2022.project.model.Company;
import org.switch2022.project.model.Profile;

public class AddCustomerController {

    /**
     * Attributes of the class addCustomerController, according to the Class Diagram.
     */
    private final Company company;

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

    public boolean addCustomer(String customerName, String customerNIF, String email) {

        return company.validateProfileRequired(email, Profile.ADMINISTRATOR)
                && company.addCustomer(customerName, customerNIF);
    }
}


