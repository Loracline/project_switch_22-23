package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Customer is built to create and manage new customers.
 * A customer is defined by name.
 */
public class Customer {
    /**
     * Attributes
     */
    private final String customerName;

    /**
     * Constructor
     */
    public Customer(String customerName) {
        this.customerName = customerName.toLowerCase();
    }

    /**
     * This method checks if two instances of Customer are equal by comparing
     * its names.
     *
     * @param toCompare Customer instance to compare with.
     * @return TRUE if the two have the same customer name, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (!(toCompare instanceof Customer)) {
            return false;
        }
        Customer customer1 = (Customer) toCompare;
        return Objects.equals(customerName, customer1.customerName);
    }
}
