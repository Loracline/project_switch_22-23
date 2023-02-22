package org.switch2022.project.model.container;

import org.switch2022.project.model.Customer;

import java.util.ArrayList;
import java.util.List;


/**
 * Class CustomerContainer is built to access and manipulate set of customers
 * of the projects of this company.
 */
public class CustomerContainer {
    /**
     * Attributes
     */
    private final List<Customer> customers = new ArrayList<>();


    /**
     * This method validates if customer already exists by checking the NIF.
     *
     * @param customerNIF of the intended costumer.
     * @return TRUE if exists and FALSE otherwise.
     */
    private boolean doesCustomerNIFExist(Customer customerNIF) {
        return this.customers.contains(customerNIF);
    }

    /**
     * This method creates a new costumer and adds to the container if one
     * doesn't already exist.
     *
     * @param customerNIF of the costumer to add.
     * @return TRUE if costumer is added and FALSE otherwise.
     */
    public boolean addCustomer(String customerName, String customerNIF) {
        Customer newCustomer = new Customer(customerName, customerNIF);
        boolean isAddedToList = false;
        if (isValidNIF(customerNIF) && !customerName.isEmpty() && !doesCustomerNIFExist(newCustomer)) {
            customers.add(newCustomer);
            isAddedToList = true;
        }
        return isAddedToList;
    }

    /**
     * This method Checks if the NIF has the correct length (9 digits) and checks if
     * the NIF contains only digits.
     *
     * @param customerNIF of the costumer to add.
     * @return TRUE if costumerNIF has the correct length and contains only digits and FALSE otherwise.
     */
    private static boolean isValidNIF(String customerNIF) {
        boolean isValidNIF = false;
        final byte NIF_LENGTH = 9;
        if (customerNIF.length() == NIF_LENGTH) {
            isValidNIF = true;
            for (int i = 0; i < customerNIF.length(); i++) {
                if (!Character.isDigit(customerNIF.charAt(i))) {
                    isValidNIF = false;
                }
            }
        }
        return isValidNIF;
    }

    public Customer getCustomer(String customerName, String nif) {
        Customer requestedCustomer = new Customer(customerName, nif);
        int i = 0;
        while (i < customers.size()) {
            if (customers.get(i).getCustomerNif().equals(nif)) {
                requestedCustomer = customers.get(i);
            }
            i++;
        }
        return requestedCustomer;
    }
}
