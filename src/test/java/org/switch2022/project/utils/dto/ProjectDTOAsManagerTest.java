package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectDTOAsManagerTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the tests below.
   */

  ProjectDTOAsManager projectDTOAsManagerOne, projectDTOAsManagerTwo, projectDTOAsManagerThree;

  @BeforeEach
  void setUp() {
    projectDTOAsManagerOne = new ProjectDTOAsManager("AA001", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");
    projectDTOAsManagerTwo = new ProjectDTOAsManager("AA002", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");
    projectDTOAsManagerThree = new ProjectDTOAsManager("AA002", "Aptoide", "ISEP","228674498",
            "Fixed cost",
            "fishing");

  }

  @AfterEach
  void tearDown() {
    projectDTOAsManagerOne = null;
    projectDTOAsManagerTwo = null;
    projectDTOAsManagerThree = null;
  }

  @Test
  void ensureSameProjectDTOEqualsItself() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");

    ProjectDTOAsManager other = reference;

    boolean expected = true;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsAreNotEqual() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");


    ProjectDTOAsManager other = new ProjectDTOAsManager("AA002", "john@isep.ipp.pt", "John","111222333","fixed cost","medicine");

    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentCustomerNif() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");


    ProjectDTOAsManager other = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222433","fixed cost","sports");

    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentProjectTypology() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");


    ProjectDTOAsManager other = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed materials","sports");

    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentBusinessSector() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");


    ProjectDTOAsManager other = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","medicine");

    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }


  @Test
  void ensureProjectDTONotEqualsOtherTypeObject() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");

    Object other = new Object();

    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  /**
   * hashCode()
   */
  @Test
  void ensureProjectDTOsHaveSameHashCode() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");

    ProjectDTOAsManager other = new ProjectDTOAsManager("AA001", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");

    // ACT
    int hashCodeReference = reference.hashCode();
    int hashCodeOther = other.hashCode();

    // ASSERT
    assertEquals(hashCodeOther, hashCodeReference);
  }

  @Test
  void ensureProjectDTOsHaveDifferentHashCode() {
    // ARRANGE
    ProjectDTOAsManager reference = new ProjectDTOAsManager("AA002", "john@isep.ipp.pt", "John","111222333","fixed cost","sports");


    ProjectDTOAsManager other = new ProjectDTOAsManager("AA003", "mary@isep.ipp.pt", "Mary","211222333","fixed costs","sports");

    // ACT
    int hashCodeReference = reference.hashCode();
    int hashCodeOther = other.hashCode();

    // ASSERT
    assertNotEquals(hashCodeOther, hashCodeReference);
  }
}
