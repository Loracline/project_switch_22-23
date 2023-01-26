package org.switch2022.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {
  /**
   * BeforeEach and AfterEach execute common code before/after running the
   * tests below.
   */
  Project projectOne, projectTwo, projectThree;

  @BeforeEach
  void setUp() {
    projectOne = new Project("AA001", "Aptoide", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectTwo = new Project("AA002", "Aptoide", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    projectThree = new Project("AA001", "Aptoide", new Customer("John","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
    projectThree = null;
  }

  /**
   * Test to ensure the object equals itself
   */
  @Test
  void ensureSameObjectEqualsItself() {
    Project projectReference = projectOne;
    boolean expected = true;
    boolean result = projectOne.equals(projectReference);
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that two objects are from different classes
   */
  @Test
  void ensureObjectsAreFromDifferentClasses() {
    Object projectObject = new Object();
    boolean expected = false;
    boolean result = projectOne.equals(projectObject);
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that two objects from the same class are different
   */
  @Test
  void ensureTwoProjectsAreNotEqual() {
    boolean expected = false;
    boolean result = projectOne.equals(projectTwo);
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that two objects from the same class are equal
   */
  @Test
  void ensureTwoProjectsAreEqual() {
    Project project = new Project("AA001", "Aptoide", new Customer("john","228674498"),
            new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    assertEquals(project, projectOne);
  }

  /**
   * Test to ensure that project code requested from a given project is retrieved
   */
  @Test
  void ensureProjectCodeIsEqual() {
    String expected = "AA001";
    String result = projectOne.getProjectCode();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that project code requested from a given project is not retrieved
   */
  @Test
  void ensureProjectCodeIsNotEqual() {
    String expected = "AA002";
    String result = projectOne.getProjectCode();
    assertNotEquals(expected, result);
  }

  /**
   * Test to ensure that project name requested from a given project is retrieved
   */
  @Test
  void ensureProjectNameIsEqual() {
    String expected = "Aptoide";
    String result = projectOne.getProjectName();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that project name requested from a given project is not retrieved
   */
  @Test
  void ensureProjectNameIsNotEqual() {
    String expected = "Aptoido";
    String result = projectOne.getProjectName();
    assertNotEquals(expected, result);
  }

  /**
   * Test to ensure that customer requested from a given project is retrieved
   */
  @Test
  void ensureCustomerIsEqual() {
    Customer expected = new Customer("john","228674498");
    Customer result = projectOne.getCustomer();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that customer requested from a given project is not retrieved
   */
  @Test
  void ensureCustomerIsNotEqual() {
    Customer expected = new Customer("Johnny", null);
    Customer result = projectOne.getCustomer();
    assertNotEquals(expected, result);
  }

  /**
   * Test to ensure that status requested from a given project is retrieved
   */
  @Test
  void ensureStatusIsEqual() {
    String expected = "planned";
    String result = projectOne.getProjectStatus();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that status requested from a given project is not retrieved
   */
  @Test
  void ensureStatusIsNotEqual() {
    String expected = "finished";
    String result = projectOne.getProjectStatus();
    assertNotEquals(expected, result);
  }

  /**
   * Test to ensure that project typology requested from a given project is retrieved
   */
  @Test
  void ensureProjectTypologyIsEqual() {
    ProjectTypology expected = new ProjectTypology("Fixed cost");
    ProjectTypology result = projectOne.getProjectTypology();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that project typology requested from a given project is not retrieved
   */
  @Test
  void ensureProjectTypologyIsNotEqual() {
    ProjectTypology expected = new ProjectTypology("Variable cost");
    ProjectTypology result = projectOne.getProjectTypology();
    assertNotEquals(expected, result);
  }

  /**
   * Test to ensure that business sector requested from a given project is retrieved
   */
  @Test
  void ensureBusinessSectorIsEqual() {
    BusinessSector expected = new BusinessSector("Hunting");
    BusinessSector result = projectOne.getBusinessSector();
    assertEquals(expected, result);
  }
  /**
   * Test to ensure that business sector requested from a given project is not retrieved
   */
  @Test
  void ensureBusinessSectorIsNotEqual() {
    BusinessSector expected = new BusinessSector("Fishing");
    BusinessSector result = projectOne.getBusinessSector();
    assertNotEquals(expected, result);
  }
  /**
   * Test to check the hashcode when objects are equal and unequal
   */
  @Test
  void testHashCode() {
    Project objectOne = projectOne;
    Project objectTwo = projectTwo;
    Project objectThree = projectThree;

    // Check that equal objects have the same hash code
    assertEquals(objectOne.hashCode(), objectThree.hashCode());

    // Check that unequal objects have different hash codes
    assertNotEquals(objectOne.hashCode(), objectTwo.hashCode());
  }
}