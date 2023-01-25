package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests
 * below.
 */

public class ProjectContainerTest {

  Project projectOne, projectTwo;
  List<Project> projects;
  ProjectContainer projectContainer;
  ProjectDTO projectOneDTO, projectTwoDTO;


  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );
    projectTwo = new Project("AA001", "Aptoide",new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"),new BusinessSector("Hunting") );
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);
    projectOneDTO = new ProjectDTO("AA001", "Aptoide", "John",
            "Fixed cost", "Hunting");
    projectTwoDTO = new ProjectDTO("AA003", "Aptoide", "John",
            "Fixed cost", "Hunting");

  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
    projectOneDTO = null;
    projectTwoDTO = null;
  }

  /**
   * Testing if one is able to register a new project and add it to the
   * container.
   */
  @Test
  void verifyProjectIsNotRegistered() {
    boolean expected = false;
    boolean result = projectContainer.registerProject(projectOneDTO);
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectIsRegistered() {
    boolean expected = true;
    boolean result = projectContainer.registerProject(projectTwoDTO);
    assertEquals(expected, result);
  }

  @Test
  void getProjectByCode() {
    Project result = projectContainer.getProjectByCode("AA001");

    assertEquals(projectOne, result);
  }

  @Test
  void ensureThatGetAccountReturnNull() {
      Project result = projectContainer.getProjectByCode("AA005");

    assertNull(result);
  }
}

