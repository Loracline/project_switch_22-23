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
    projectOne = new Project("AA001", "Aptoide", "John",
            "Fixed cost", "Hunting");
    projectTwo = new Project("AA002", "Aptoide", "John",
            "Fixed cost", "Hunting");
    projectThree = new Project("AA001", "Aptoide", "John",
            "Fixed cost", "Hunting");
  }

  @AfterEach
  void tearDown() {
    projectOne = null;
    projectTwo = null;
  }

  /**
   * Testing the equals() method.
   */
  @Test
  void ensureSameObjectEqualsItself() {
    Project projectReference = projectOne;
    boolean expected = true;
    boolean result = projectOne.equals(projectReference);
    assertEquals(expected, result);
  }

  @Test
  void ensureObjectsAreFromDifferentClasses() {
    Object projectObject = new Object();
    boolean expected = false;
    boolean result = projectOne.equals(projectObject);
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectsAreNotEqual() {
    boolean expected = false;
    boolean result = projectOne.equals(projectTwo);
    assertEquals(expected, result);
  }

  /**
   * Testing if one can get code from given project.
   */
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

  /**
   * Testing if one can get name from given project.
   */
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

  /**
   * Testing if one can get customer from given project.
   */
  @Test
  void ensureCustomerIsEqual() {
    String expected = "John";
    String result = projectOne.getCustomer();
    assertEquals(expected, result);
  }

  @Test
  void ensureCustomerIsNotEqual() {
    String expected = "Johnny";
    String result = projectOne.getCustomer();
    assertNotEquals(expected, result);
  }

  /**
   * Testing if one can get status from given project.
   */
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

  /**
   * Testing if one can get typology from given project.
   */
  @Test
  void ensureProjectTypologyIsEqual() {
    String expected = "Fixed cost";
    String result = projectOne.getProjectTypology();
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectTypologyIsNotEqual() {
    String expected = "Variable cost";
    String result = projectOne.getProjectTypology();
    assertNotEquals(expected, result);
  }

  /**
   * Testing if one can get business sector from given project.
   */
  @Test
  void ensureBusinessSectorIsEqual() {
    String expected = "Hunting";
    String result = projectOne.getBusinessSector();
    assertEquals(expected, result);
  }

  @Test
  void ensureBusinessSectorIsNotEqual() {
    String expected = "Fishing";
    String result = projectOne.getBusinessSector();
    assertNotEquals(expected, result);
  }

  @Test
  void testHashCode() {
    Project obj1 = projectOne;
    Project obj2 = projectTwo;
    Project obj3 = projectThree;

    // Check that equal objects have the same hash code
    assertEquals(obj1.hashCode(), obj3.hashCode());

    // Check that unequal objects have different hash codes
    assertNotEquals(obj1.hashCode(), obj2.hashCode());
  }
}