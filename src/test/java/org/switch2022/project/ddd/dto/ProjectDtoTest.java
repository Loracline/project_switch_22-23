package org.switch2022.project.ddd.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDtoTest {

    /**
     * These annotations are used to run methods before or after each test method in this test class.
     * The method annotated with @BeforeEach is run before each test method, and is used to set up
     * any common test fixtures needed by the tests. The method annotated with @AfterEach is run after
     * each test method, and is used to clean up any test fixtures created during the test.
     */

    ProjectDto projectDtoOne;
    ProjectDto projectDtoTwo;
    ProjectDto projectDtoThree;

    @BeforeEach
    void setUp() {
        projectDtoOne = new ProjectDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoTwo = new ProjectDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoThree = new ProjectDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }


    /**
     * Scenario 1: Verifies if the object is equal to itself, as it should be.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDtoOne.equals(projectDtoOne));
    }

    /**
     * Scenario 2: Test to ensure the object is not equal to itself.
     */
    @Test
    void ensureObjectAreNotEqualsItself() {
        assertFalse(projectDtoOne.equals(projectDtoTwo));
    }

    /**
     * Scenario 3: This test checks if two projects with the same properties are considered equal.
     */
    @Test
    void ensureTwoProjectsAreEqualsItself() {
        assertTrue(projectDtoTwo.equals(projectDtoThree));
    }

    /**
     * Scenario 4: Test to ensure that two projects does not equal null.
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsNull() {
        assertFalse(projectDtoOne.equals(null));
    }

    /**
     * Scenario 5: Test to ensure that two projects does not equal other type of object.
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsOtherTypeOfObject() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDtoOne.equals(notSameObjectType));
    }

    /**
     * Scenario 6: Test to check the hashcode when objects are equal and unequal.
     */
    @Test
    public void testHashCodeGetProjectDto() {
        ProjectDto objectOne = new ProjectDto
                ("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDto objectTwo = new ProjectDto
                ("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ProjectDto objectThree = new ProjectDto(
                "AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }

    /**
     * Method: getProjectCode
     * This method ensure that the project code is returned.
     */
    @Test
    void getProjectCode() {
        //arrange
        ProjectDto projectDtoFive = projectDtoOne;
        String expected = "AA001";

        //act
        String result = projectDtoFive.getProjectCode();

        //assert
        assertEquals(expected, result);
    }
}

