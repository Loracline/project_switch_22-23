package org.switch2022.project.container;

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
}
