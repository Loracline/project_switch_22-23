package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Customer is built to create and manage new customers.
 * A customer is defined by name and NIF.
 */
public class Customer {
    /**
     * Attributes
     */
    private final String customerName;
    private final String customerNIF;

    /**
     * Constructor
     */
    public Customer(String customerName, String customerNIF) {
        this.customerName = customerName.toLowerCase();
        this.customerNIF = customerNIF;
    }



    /**
     * This method checks if two instances of Customer are equal by comparing
     * its names and NIF.
     *
     * @param toCompare Customer instance to compare with.
     * @return TRUE if the two have the same customer NIF and name, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) return true;
        if (!(toCompare instanceof Customer)) return false;
        Customer customer = (Customer) toCompare;
        return Objects.equals(customerName, customer.customerName.toLowerCase()) && Objects.equals(customerNIF, customer.customerNIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, customerNIF);
    }

    public String getCustomerName() {
        return  customerName;
    }
}