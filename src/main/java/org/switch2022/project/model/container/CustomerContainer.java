package org.switch2022.project.model.container;

import org.switch2022.project.model.Customer;

import java.util.List;

/**
 * Class CustomerContainer is built to allow access to class Customer.
 */

public class CustomerContainer {

  /**
   * CustomerContainer contains customers
   */

  private List<Customer> customers;

  public CustomerContainer(List<Customer> customers) {
    this.customers = customers;
  }

  /**
   * This method returns a list of customers
   *
   * @return list
   */
  public List<Customer> getCustomers() {
    return customers;
  }

  /**
   * This method returns a customer from the list of customers
   *
   * @param customer
   * @return customer
   */

  public Customer getCustomerSector(String customer) {
    Customer requestedCustomer = null;
    for (int i = 0; i < customers.size(); i++) {
      if (customers.get(i).equals(customer)) {
        requestedCustomer = customers.get(i);
        break;
      }
    }
    return requestedCustomer;
  }

  /**
   * Check if first is lower than second
   *
   * @param first  integer
   * @param second integer
   * @return True if first is lower than second, and False otherwise
   */
  public static boolean isLower(int first, int second) {
    return first < second;
  }

  /**
   * This method validates if customer exits
   *
   * @param customerName one must check
   * @return true if customer exists in customers
   */
  private boolean doesCustomerExist(Customer customerName) {
    return this.customers.contains(customerName);
  }

  /**
   * This method creates customer and adds it to customers
   *
   * @param customerName one must add
   */

  public boolean addCustomer(String customerName) {
    Customer newCustomer = new Customer(customerName);
    boolean isAddedToList = false;
    if (customerName != null && !customerName.isEmpty() && !doesCustomerExist(newCustomer)) {
      customers.add(new Customer(customerName));
      isAddedToList = true;
    }
    return isAddedToList;
  }

  /**
   * This method identifies the requested customer by indication of customerName
   *
   * @return an object Customer
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

}
