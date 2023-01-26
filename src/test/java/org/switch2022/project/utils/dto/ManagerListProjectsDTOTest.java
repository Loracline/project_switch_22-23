package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerListProjectsDTOTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    ManagerListProjectsDTO projectDTOOne;
    ManagerListProjectsDTO projectDTOTwo;
    ManagerListProjectsDTO projectDTOThree;

    @BeforeEach
    void setUp() {
        projectDTOOne = new ManagerListProjectsDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOTwo = new ManagerListProjectsDTO("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOThree = new ManagerListProjectsDTO("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }
    /**
     * Test to ensure the object are equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDTOOne.equals(projectDTOOne));
    }
    /**
     * Test to ensure the object are not equals itself
     */
    @Test
    void ensureObjectAreNotEqualsItself() {
        assertFalse(projectDTOOne.equals(projectDTOTwo));
    }
    /**
     * Test to ensure that two projects are equals
     */
    @Test
    void ensureTwoProjectsAreEqualsItself() {
        assertTrue(projectDTOTwo.equals(projectDTOThree));
    }
    /**
     * Test to ensure that two projects does not equal null
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsNull() {
        assertFalse(projectDTOOne.equals(null));
    }
    /**
     * Test to ensure that two projects does not equal other type of object(
     */
    @Test
    void ensureTwoProjectsDoesNotEqualsOtherTypeOfObject() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDTOOne.equals(notSameObjectType));
    }
    /**
     * Test to check the hashcode when objects are equal and unequal
     */
    @Test
    public void testHashCodeGetProjectDTO() {
        ManagerListProjectsDTO objectOne = new ManagerListProjectsDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDTO objectTwo = new ManagerListProjectsDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        ManagerListProjectsDTO objectThree = new ManagerListProjectsDTO("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }
}
