package org.switch2022.project.factories;

import org.switch2022.project.model.Customer;

/**
 * Implementation of the FactoryCustomer interface that creates instances of the Customer class
 */
public class IFactoryCustomer implements FactoryCustomer {

    /**
     * This method creates a new Customer object with the specified name and NIF.
     *
     * @param customerName the name of the customer
     * @param customerNIF  the NIF of the customer
     * @return a new Customer object with the specified name and NIF
     */
    public Customer createCustomer(String customerName, String customerNIF) {
        return new Customer(customerName, customerNIF);
    }
}

