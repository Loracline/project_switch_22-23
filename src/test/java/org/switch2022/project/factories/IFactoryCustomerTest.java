package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IFactoryCustomerTest {

  /**
   * Method createCustomer(String customerName, String customerNIF)
   */

  /**
   * Scenario 1: Verify if a Customer object is created through the iFactoryCustomer instance and equal to a
   * Customer object from the Customer instance.
   * Expected result: Both Customer objects are equal.
   */
  @Test
  void ensureThatTheUserStoryHasNotTheGivingUserStoryNumber() {
    // Arrange
    String customerName = "John Doe";
    String customerNIF = "123456789";
    FactoryCustomer factoryCustomer = new IFactoryCustomer();
    // Act
    Customer customer = factoryCustomer.createCustomer(customerName, customerNIF);
    Customer customerToCompare = new Customer("John Doe", "123456789");
    // Assert
    assertEquals(customer, customerToCompare);
  }
}