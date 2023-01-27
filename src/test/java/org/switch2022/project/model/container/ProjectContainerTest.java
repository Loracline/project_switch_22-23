package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BeforeEach and AfterEach executes common code before/after running the tests
 * below.
 */

public class ProjectContainerTest {


  Project projectOne;
  ProjectDTO projectDTO;
  ProjectTypologyContainer projectTypologyContainer;
  ProjectTypology typology;
  List<Project> projects;
  BusinessSectorContainer businessSectorContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  BusinessSector businessSector;
  Customer customer;
  Company company;


  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide", new Customer("ISEP","228674498"),
            new ProjectTypology("Fixed Cost"), new BusinessSector("fishing"));
    projects = new ArrayList<>();
    projects.add(projectOne);
    projectContainer = new ProjectContainer();

    projectTypologyContainer = new ProjectTypologyContainer();
    typology = new ProjectTypology("Fixed cost");

    customerContainer = new CustomerContainer();
    customer = new Customer("ISEP","228674498");

    businessSectorContainer = new BusinessSectorContainer();
    businessSector = new BusinessSector("fishing");

    projectDTO = new ProjectDTO("AA001", "Aptoide", "ISEP","228674498", "Fixed cost",
            "fishing");

  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    businessSectorContainer = null;
    businessSector = null;
    projectTypologyContainer = null;
    typology = null;
    projects.clear();
    projectContainer = null;
    projectDTO = null;
    customerContainer = null;
    customer = null;
    company = null;
  }

  /**
   * Test to verify that a project was not registered
   */
  @Test
  void verifyProjectIsNotRegistered() {
    boolean expected = false;
    boolean result = projectContainer.registerProject(projectDTO,projectTypologyContainer,customerContainer,businessSectorContainer);
    assertNotEquals(expected, result);
  }
  /**
   * Test to verify that a project was registered
   */
  @Test
  void ensureProjectIsRegistered() {
    boolean expected = true;
    boolean result = projectContainer.registerProject(projectDTO,projectTypologyContainer,customerContainer,businessSectorContainer);
    assertEquals(expected, result);
  }
  /**
   * Test to verify that a project is retrieved given a project code
   */
  @Test
  void ensureProjectIsRetrievedGivenProjectCode() {
    Project result = projectContainer.getProjectByCode("AA001");

    assertNotEquals(projectOne, result);
  }
  /**
   * Test to verify that a project is not retrieved given a project code
   */
  @Test
  void ensureProjectIsNotRetrievedGivenProjectCode() {
      Project result = projectContainer.getProjectByCode("AA005");

    assertNull(result);
  }
}

