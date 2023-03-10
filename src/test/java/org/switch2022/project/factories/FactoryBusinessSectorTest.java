package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;

import static org.junit.jupiter.api.Assertions.*;

class FactoryBusinessSectorTest {

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
    IFactoryBusinessSector IFactoryBusinessSector = new FactoryBusinessSector();
    // Act
    BusinessSector businessSector = IFactoryBusinessSector.createBusinessSector(businessSectorName);
    BusinessSector businessSectorToCompare = new BusinessSector("Sports");
    // Assert
    assertEquals(businessSector, businessSectorToCompare);
  }
}