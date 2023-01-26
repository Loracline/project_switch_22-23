package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetProjectDTOTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    GetProjectDTO projectDTOOne;
    GetProjectDTO projectDTOTwo;
    GetProjectDTO projectDTOThree;

    @BeforeEach
    void setUp() {
        projectDTOOne = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOTwo = new GetProjectDTO("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTOThree = new GetProjectDTO("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
    }

    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDTOOne.equals(projectDTOOne));
    }

    @Test
    void ensureTwoProjectsAreNotEqual() {
        assertFalse(projectDTOOne.equals(null));
    }

    @Test
    void ensureTwoProjectsAreNotEqual2() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDTOOne.equals(notSameObjectType));
    }

    @Test
    public void testHashCodeGetProjectDTO() {
        GetProjectDTO objectOne = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        GetProjectDTO objectTwo = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        GetProjectDTO objectThree = new GetProjectDTO("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        // Check that equal objects have the same hash code
        assertEquals(objectOne.hashCode(), objectTwo.hashCode());
        // Check that unequal objects have different hash codes
        assertNotEquals(objectOne.hashCode(), objectThree.hashCode());
    }

}
