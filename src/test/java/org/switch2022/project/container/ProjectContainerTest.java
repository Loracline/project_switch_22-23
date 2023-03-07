package org.switch2022.project.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.dto.ProjectDtoAsManager;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectContainerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the
   * tests below.
   */

  Project projectOne;
  ProjectDtoAsManager projectOneDTO, projectTwoDTO;
  ProjectTypology typology;
  Customer customer;
  BusinessSector businessSector;
  ProjectTypologyContainer projectTypologyContainer;
  BusinessSectorContainer businessSectorContainer;
  ProjectContainer projectContainer;
  CustomerContainer customerContainer;
  Company company;

  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide",
            new Customer("ISEP", "228674498"),
            new ProjectTypology("Fixed cost"),
            new BusinessSector("fishing"));

    projectTypologyContainer = new ProjectTypologyContainer();
    customerContainer = new CustomerContainer();
    businessSectorContainer = new BusinessSectorContainer();

    projectOneDTO = new ProjectDtoAsManager("AA001", "Aptoide",
            "ISEP", "228674498", "Fixed cost",
            "fishing");
    projectTwoDTO = new ProjectDtoAsManager("AA002", "Aptoide",
            "ISEP", "228674498", "Fixed cost",
            "fishing");

    projectContainer = new ProjectContainer();
    projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer);
  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    businessSectorContainer = null;
    businessSector = null;
    projectTypologyContainer = null;
    typology = null;
    projectContainer = null;
    projectOneDTO = null;
    customerContainer = null;
    customer = null;
    company = null;
  }

  /**
   * This test verifies that a new project is registered and added to the
   * container successfully.
   */
  @Test
  void ensureProjectIsRegisteredSuccessfully() {
    // Arrange
    boolean expected = true;

    // Act
    boolean result = projectContainer.registerProject(projectTwoDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * This test verifies that a project is not registered because it already
   * exists in the container.
   */
  @Test
  void ensureProjectIsNotRegisteredBecauseItAlreadyExists() {
    // Arrange
    boolean expected = false;

    // Act
    boolean result = projectContainer.registerProject(projectOneDTO, projectTypologyContainer, customerContainer,
            businessSectorContainer);

    // Assert
    assertEquals(expected, result);
  }
}

