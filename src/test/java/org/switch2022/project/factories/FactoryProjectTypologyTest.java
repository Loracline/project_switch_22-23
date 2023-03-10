package org.switch2022.project.factories;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryProjectTypologyTest {

  /**
   * Method createProjectTypology(String projectTypologyName)
   */

  /**
   * Scenario 1: Verify if a ProjectTypology object is created through the IFactoryProjectTypology instance
   * and equal to a ProjectTypology object from a ProjectTypology instance.
   * <p>
   * Expected result: Both ProjectTypology objects are equal.
   */

  @Test
  void createProjectTypology() {
    // Arrange
    String projectTypologyName = "Fixed Cost";
    IFactoryProjectTypology factoryprojectTypologyI = new FactoryProjectTypology();
    // Act
    ProjectTypology projectTypology = factoryprojectTypologyI.createProjectTypology(projectTypologyName);
    ProjectTypology projectTypologyToCompare = new ProjectTypology("Fixed Cost");
    // Assert
    assertEquals(projectTypology, projectTypologyToCompare);
  }
}