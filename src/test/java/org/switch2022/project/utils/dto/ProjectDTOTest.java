package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectDTOTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  ProjectDTO projectDTOOne, projectDTOTwo,projectDTOThree;

  @BeforeEach
  void setUp() {
    projectDTOOne = new ProjectDTO("AA001", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");
    projectDTOTwo = new ProjectDTO("AA002", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");
    projectDTOThree = new ProjectDTO("AA002", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");

  }

  @AfterEach
  void tearDown() {
    projectDTOOne = null;
    projectDTOTwo = null;
    projectDTOThree = null;
  }

  /**
   * Test to ensure the object equals itself
   */
  @Test
  void ensureSameObjectEqualsItself() {
    // Arrange
    ProjectDTO projectDTOTwo = projectDTOOne;

    // Act
    boolean expected = true;
    boolean result = projectDTOOne.equals(projectDTOTwo);

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that two objects from the same class are different
   */
  @Test
  void ensureTwoProjectsAreNotEqual() {
    // Arrange
    ProjectDTO reference = projectDTOOne;

    // Act
    boolean expected = false;
    boolean result = projectDTOOne.equals(reference);

    // Assert
    assertNotEquals(expected, result);
  }
  /**
   * Test to ensure that two objects are from different classes
   */
  @Test
  void ensureObjectsAreFromDifferentClasses() {
    // Arrange
    Object projectObject = new Object();

    // Act
    boolean expected = false;
    boolean result = projectDTOOne.equals(projectObject);

    // Assert
    assertEquals(expected, result);
  }
  /**
   * Test to check the hashcode when objects are equal and unequal
   */
  @Test
  void testHashCode() {
    // Arrange
    ProjectDTO objectOne = projectDTOOne;
    ProjectDTO objectTwo = projectDTOTwo;
    ProjectDTO objectThree = projectDTOThree;

    // Assert
    // Check that equal objects have the same hash code
    assertEquals(objectTwo.hashCode(), objectThree.hashCode());

    // Check that unequal objects have different hash codes
    assertNotEquals(objectOne.hashCode(), objectTwo.hashCode());
  }
}
