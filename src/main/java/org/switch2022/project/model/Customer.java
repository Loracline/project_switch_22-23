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
    private String customerName;
    private final String customerNIF;

    /**
     * Constructor
     */
    public Customer(String customerName, String customerNIF) {
        this.customerName = customerName.toLowerCase();
        this.customerNIF = customerNIF;
    }

    /**
     * Private constructor od the class sprint
     * @param customerNIF is the object identifier
     */
    private Customer(String customerNIF) {
        this.customerNIF = customerNIF;
    }

    /**
     * Static method to create a new Customer
     * @param customerName is the Customer Name
     * @param customerNIF is the Customer NIF
     * @return a new Customer
     */

    public static Customer createCustomer(String customerName, String customerNIF) {
        Customer customer = new Customer(customerNIF);
        customer.setCustomerName(customerName);
        return customer;
    }

    /**
     * Private set method to set Customer name when method createCustomer is executed.
     * @param customerName is the Customer Name.
     */

    private void setCustomerName(String customerName) {
        this.customerName = customerName.toLowerCase();
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
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null){
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Customer customer = (Customer) toCompare;
        return Objects.equals(customerName, customer.customerName.toLowerCase())
                && Objects.equals(customerNIF, customer.customerNIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, customerNIF);
    }

    public String getCustomerNif() {
        return customerNIF;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean hasCustomerNif(String nif) { return this.customerNIF.equals(nif); }
}