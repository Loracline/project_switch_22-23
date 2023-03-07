package org.switch2022.project.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectCreationDtoTest {

  /**
   * BeforeEach and AfterEach executes common code before/after running the
   * tests below.
   */

  ProjectCreationDto projectCreationDtoOne, projectCreationDtoTwo,
          projectCreationDtoThree;

  @BeforeEach
  void setUp() {
    projectCreationDtoOne = new ProjectCreationDto("AA001",
            "Aptoide", "ISEP", "228674498",
            "Fixed cost", "fishing");
    projectCreationDtoTwo = new ProjectCreationDto("AA002",
            "Aptoide", "ISEP", "228674498",
            "Fixed cost", "fishing");
    projectCreationDtoThree = new ProjectCreationDto("AA002",
            "Aptoide", "ISEP", "228674498",
            "Fixed cost", "fishing");
  }

  @AfterEach
  void tearDown() {
    projectCreationDtoOne = null;
    projectCreationDtoTwo = null;
    projectCreationDtoThree = null;
  }

  @Test
  void ensureSameProjectDTOEqualsItself() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = reference;
    boolean expected = true;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsAreNotEqual() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA002", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "medicine");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  /**
   * Test to ensure that the object to compare is equal to null
   */
  @Test
  void ensureObjectToCompareIsNull() {
    //Arrange
    Project other = null;
    boolean expected = false;

    //Act
    boolean result = projectCreationDtoOne.equals(other);

    //Assert
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsAreNotTheSameTypeOfObjects() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    Object other = new Object();
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentCustomerNif() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222433", "fixed cost", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentCode() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA002", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentCustomerName() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "Johnny", "111222333", "fixed cost", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentProjectTypology() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed materials", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveOnlyEqualNameAndCustomerName() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA002", "john@isep.ipp.pt",
            "John", "111222323", "fixed materials", "health");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveOnlyEqualProjectTypology() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA002", "johnny@isep.ipp.pt",
            "Johnny", "111222323", "fixed cost", "health");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentBusinessSector() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "medicine");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveTheSameBusinessSectorButDifferentAttributes() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA002", "johnny@isep.ipp.pt",
            "Johnny", "111222334", "fixed materials", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureTwoProjectDTOsHaveDifferentName() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "johnny@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    boolean expected = false;

    // ACT
    boolean result = reference.equals(other);

    // ASSERT
    assertEquals(expected, result);
  }

  @Test
  void ensureProjectDTONotEqualsOtherTypeObject() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
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
    ProjectCreationDto reference = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");
    ProjectCreationDto other = new ProjectCreationDto("AA001", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");

    // ACT
    int hashCodeReference = reference.hashCode();
    int hashCodeOther = other.hashCode();

    // ASSERT
    assertEquals(hashCodeOther, hashCodeReference);
  }

  @Test
  void ensureProjectDTOsHaveDifferentHashCode() {
    // ARRANGE
    ProjectCreationDto reference = new ProjectCreationDto("AA002", "john@isep.ipp.pt",
            "John", "111222333", "fixed cost", "sports");


    ProjectCreationDto other = new ProjectCreationDto("AA003", "mary@isep.ipp.pt",
            "Mary", "211222333", "fixed costs", "sports");

    // ACT
    int hashCodeReference = reference.hashCode();
    int hashCodeOther = other.hashCode();

    // ASSERT
    assertNotEquals(hashCodeOther, hashCodeReference);
  }
}
