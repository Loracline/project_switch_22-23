package org.switch2022.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerListProjectsDtoTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    ManagerListProjectsDto projectDtoOne;
    ManagerListProjectsDto projectDtoTwo;
    ManagerListProjectsDto projectDtoThree;

    @BeforeEach
    void setUp() {
        projectDtoOne = new ManagerListProjectsDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoTwo = new ManagerListProjectsDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDtoThree = new ManagerListProjectsDto("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }
    /**
     * Test to ensure the object are equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDtoOne.equals(projectDtoOne));
    }
    /**
     * Test to ensure the object are not equals itself
     */
    @Test
    void ensureObjectAreNotEqualsItself() {
        assertFalse(projectDtoOne.equals(projectDtoTwo));
    }
    /**
     * Test to ensure that two projects are equals
     */
    @Test
    void ensureTwoProjectsAreEqualsItself() {
        assertTrue(projectDtoTwo.equals(projectDtoThree));
    }
    /**
     * Test to ensure that two projects does not equal null
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsNull() {
        assertFalse(projectDtoOne.equals(null));
    }
    /**
     * Test to ensure that two projects does not equal other type of object(
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsOtherTypeOfObject() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDtoOne.equals(notSameObjectType));
    }
    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    public void testHashCodeGetProjectDto() {
        ManagerListProjectsDto objectOne = new ManagerListProjectsDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDto objectTwo = new ManagerListProjectsDto("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDto objectThree = new ManagerListProjectsDto("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }
}
