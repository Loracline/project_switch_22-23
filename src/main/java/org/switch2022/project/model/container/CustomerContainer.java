package org.switch2022.project.model.container;

import org.switch2022.project.model.Customer;

import java.util.List;

import static org.switch2022.project.utils.Helper.isLower;

/**
 * Class CustomerContainer is built to access and manipulate set of customers
 * of the projects of this company.
 */
public class CustomerContainer {
    /**
     * Attributes
     */
    private final List<Customer> customers;

    /**
     * Constructor
     */
    public CustomerContainer(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Getter method for the attribute: customers.
     *
     * @return list of all the customers in the container.
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * This method validates if customer already exists by checking the name.
     *
     * @param customerName of the intended costumer.
     * @return TRUE if exists and FALSE otherwise.
     */
    private boolean doesCustomerExist(Customer customerName) {
        return this.customers.contains(customerName);
    }

    /**
     * This method creates a new costumer and adds to the container if one
     * doesn't already exist.
     *
     * @param customerName of the costumer to add.
     * @return TRUE if costumer is added and FALSE otherwise.
     */
    public boolean addCustomer(String customerName) {
        Customer newCustomer = new Customer(customerName);
        boolean isAddedToList = false;
        if (!customerName.isEmpty() && !doesCustomerExist(newCustomer)) {
            customers.add(new Customer(customerName));
            isAddedToList = true;
        }
        return isAddedToList;
    }

    /**
     * This method searches for a customer with given name.
     *
     * @param customerName of the intended customer.
     * @return costumer with given name.
     */
    public Customer getCustomerByName(String customerName) {
        Customer customer = new Customer(customerName);
        Customer requestedCustomer = null;
        int i = 0;
        while (isLower(i, this.customers.size())) {
            if (this.customers.contains(customer)) {
                requestedCustomer = customers.get(i);
                break;
            }
            i++;
        }
        return requestedCustomer;
    }



    public Customer getCustomerSector(String customer) {
        Customer requestedCustomer = null;
        int i = 0;
        while (i < customers.size()) {
            if (customers.get(i).equals(customer)) {
                requestedCustomer = customers.get(i);
                break;
            }
            i++;
        }
        return requestedCustomer;
    }
}
