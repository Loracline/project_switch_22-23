package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDTOTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  ProjectDTO projectDTOOne, projectDTOTwo;

  @BeforeEach
  void setUp() {
    projectDTOOne = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectDTOTwo = new ProjectDTO("AA002", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
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
}