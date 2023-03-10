package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryCustomerTest {

  /**
   * Method createCustomer(String customerName, String customerNIF)
   */

  /**
   * Scenario 1: Verify if a Customer object is created through the IFactoryCustomer instance and equal to a
   * Customer object from a Customer instance.
   * <p>
   * Expected result: Both Customer objects are equal.
   */
  @Test
  void ensureThatTwoCustomerObjectsHaveTheSameParameters() {
    // Arrange
    String customerName = "John Doe";
    String customerNIF = "123456789";
    IFactoryCustomer IFactoryCustomer = new FactoryCustomer();
    // Act
    Customer customer = IFactoryCustomer.createCustomer(customerName, customerNIF);
    Customer customerToCompare = new Customer("John Doe", "123456789");
    // Assert
    assertEquals(customer, customerToCompare);
  }
}
