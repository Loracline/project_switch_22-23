package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class IFactoryBusinessSectorTest {

  /**
   * Method createBusinessSector(String businessSectorName)
   */

  /**
   * Scenario 1: Verify if a BusinessSector object is created through the IFactoryBusinessSector instance
   * and equal to a BusinessSector object from a BusinessSector instance.
   * <p>
   * Expected result: Both BusinessSector objects are equal.
   */

  @Test
  void ensureThatTwoBusinessSectorObjectsHaveTheSameParameters() {
    // Arrange
    String businessSectorName = "Sports";
    FactoryBusinessSector factoryBusinessSector = new IFactoryBusinessSector();
    // Act
    BusinessSector businessSector = factoryBusinessSector.createBusinessSector(businessSectorName);
    BusinessSector businessSectorToCompare = new BusinessSector("Sports");
    // Assert
    assertEquals(businessSector, businessSectorToCompare);
  }
}