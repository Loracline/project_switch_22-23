package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  Project projectOne, projectTwo;

  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwo = new Project("AA002", "Aptoide", new Customer("John"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
  }

  @Test
  void ensureSameObjectEqualsItself() {
    Project projectTwo = projectOne;
    boolean expected = true;
    boolean result = projectOne.equals(projectTwo);
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectsAreNotEqual() {
    Project projectTwo = projectOne;
    boolean expected = false;
    boolean result = projectOne.equals(projectTwo);
    assertNotEquals(expected, result);
  }

  @Test
  void ensureProjectCodeIsEqual() {
    String expected = "AA001";
    String result = projectOne.getProjectCode();
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectCodeIsNotEqual() {
    String expected = "AA002";
    String result = projectOne.getProjectCode();
    assertNotEquals(expected, result);
  }

  @Test
  void ensureProjectNameIsEqual() {
    String expected = "Aptoide";
    String result = projectOne.getProjectName();
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectNameIsNotEqual() {
    String expected = "Aptoido";
    String result = projectOne.getProjectName();
    assertNotEquals(expected, result);
  }

  @Test
  void ensureCustomerIsEqual() {
    Customer expected = new Customer("John");
    Customer result = projectOne.getCustomer();
    assertEquals(expected, result);
  }

  @Test
  void ensureCustomerIsNotEqual() {
    Customer expected = new Customer("Johnny");
    Customer result = projectOne.getCustomer();
    assertNotEquals(expected, result);
  }

  @Test
  void ensureStatusIsEqual() {
    String expected = "planned";
    String result = projectOne.getProjectStatus();
    assertEquals(expected, result);
  }

  @Test
  void ensureStatusIsNotEqual() {
    String expected = "finished";
    String result = projectOne.getProjectStatus();
    assertNotEquals(expected, result);
  }

  @Test
  void ensureProjectTypologyIsEqual() {
    ProjectTypology expected = new ProjectTypology("Fixed cost");
    ProjectTypology result = projectOne.getProjectTypology();
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectTypologyIsNotEqual() {
    ProjectTypology expected = new ProjectTypology("Variable cost");
    ProjectTypology result = projectOne.getProjectTypology();
    assertNotEquals(expected, result);
  }

  @Test
  void ensureBusinessSectorIsEqual() {
    BusinessSector expected = new BusinessSector("Hunting");
    BusinessSector result = projectOne.getBusinessSector();
    assertEquals(expected, result);
  }

  @Test
  void ensureBusinessSectorIsNotEqual() {
    BusinessSector expected = new BusinessSector("Fishing");
    BusinessSector result = projectOne.getBusinessSector();
    assertNotEquals(expected, result);
  }
}
