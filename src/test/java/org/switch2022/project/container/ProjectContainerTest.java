package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests below.
 */

public class ProjectContainerTest {

  Project projectOne, projectTwo;
  List<Project> projects;
  ProjectContainer projectContainer;

  ProjectDTO projectOneDTO, projectTwoDTO;



  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwo = new Project("AA002", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);
    projectOneDTO = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwoDTO = new ProjectDTO("AA003", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
    projectOneDTO = null;
    projectTwoDTO = null;
  }

  @Test
  void projectNotRegistered() {
    boolean expected = false;
    boolean result = projectContainer.registerProject(projectOneDTO);
    assertEquals(expected, result);
  }

  @Test
  void projectRegistered() {
    boolean expected = true;
    boolean result = projectContainer.registerProject(projectTwoDTO);
    assertEquals(expected, result);
  }
}
