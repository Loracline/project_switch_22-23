package org.switch2022.project.factories;

import org.switch2022.project.model.Customer;

/**
 * Interface for a Customer factory.
 */

public interface FactoryCustomer {

  /**
   * This method creates a new Customer object with the specified name and NIF with no return.
   *
   * @param customerName the name of the customer
   * @param customerNIF  the NIF of the customer
   */

  public Customer createCustomer(String customerName, String customerNIF);

}

