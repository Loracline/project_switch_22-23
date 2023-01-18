package org.switch2022.project.model;

import java.util.Objects;
/**
 * Class Customer is built to create and manage new customers.
 * A customer is defined by a name.
 */
public class Customer {

  private String customer;

  public Customer(String customer){
    this.customer = customer.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer1 = (Customer) o;
    return Objects.equals(customer, customer1.customer);
  }
}
