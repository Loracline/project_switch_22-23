package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.utils.dto.ProjectDTO;

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
  ProjectTypology projectTypology;
  Customer customer;
  BusinessSector businessSector;
  ProjectDTO projectOneDTO, projectTwoDTO;


  @BeforeEach
  void setUp() {
    //typologies = new ArrayList<>();
    projectTypology = new ProjectTypology("Fixed cost");

    customer = new Customer("John");

    businessSector = new BusinessSector("Hunting");

    projectOne = new Project("AA001", "Aptoide", customer,
            projectTypology, businessSector);
    projectTwo = new Project("AA002", "Aptoide", customer, projectTypology,
            businessSector);
    projects = new ArrayList<>();
    projects.add(projectOne);
    projects.add(projectTwo);
    projectContainer = new ProjectContainer(projects);
    projectOneDTO = new ProjectDTO("AA001", "Aptoide", customer,
            projectTypology, businessSector);
    projectTwoDTO = new ProjectDTO("AA003", "Aptoide", customer,
            projectTypology, businessSector);

  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
    projectOneDTO = null;
    projectTwoDTO = null;
    projectTypology = null;
    customer = null;
    businessSector = null;
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
