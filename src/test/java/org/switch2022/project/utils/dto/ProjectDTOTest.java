package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectDTOTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  ProjectDTO projectDTOOne, projectDTOTwo, projectDTOThree;

  @BeforeEach
  void setUp() {
    projectDTOOne = new ProjectDTO("AA001", "Aptoide", "John",
            "Fixed cost", "Hunting");
    projectDTOTwo = new ProjectDTO("AA002", "Aptoide", "John",
            "Fixed cost", "Hunting");
    projectDTOThree = new ProjectDTO("AA001", "Aptoide", "John",
            "Fixed cost", "Hunting");
  }

  @AfterEach
  void tearDown() {
    projectDTOOne = null;
    projectDTOTwo = null;
  }

  @Test
  void ensureSameObjectEqualsItself() {
    ProjectDTO projectDTOTwo = projectDTOOne;
    boolean expected = true;
    boolean result = projectDTOOne.equals(projectDTOTwo);
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectsAreNotEqual() {
    ProjectDTO projectDTOTwo = projectDTOOne;
    boolean expected = false;
    boolean result = projectDTOOne.equals(projectDTOTwo);
    assertNotEquals(expected, result);
  }

  @Test
  void ensureObjectsAreFromDifferentClasses() {
    Object projectObject = new Object();
    boolean expected = false;
    boolean result = projectDTOOne.equals(projectObject);
    assertEquals(expected, result);
  }

  @Test
  void testHashCode() {
    ProjectDTO objectOne = projectDTOOne;
    ProjectDTO objectTwo = projectDTOTwo;
    ProjectDTO objectThree = projectDTOThree;

    // Check that equal objects have the same hash code
    assertEquals(objectOne.hashCode(), objectThree.hashCode());

    // Check that unequal objects have different hash codes
    assertNotEquals(objectOne.hashCode(), objectTwo.hashCode());
  }
}
