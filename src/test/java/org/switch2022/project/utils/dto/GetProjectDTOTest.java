package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GetProjectDTOTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    GetProjectDTO projectDTO1;
    GetProjectDTO projectDTO2;
    GetProjectDTO projectDTO3;

    @BeforeEach
    void setUp() {
        projectDTO1 = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTO2 = new GetProjectDTO("AA002", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        projectDTO3 = new GetProjectDTO("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");

    }

    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDTO1.equals(projectDTO1));
    }

    @Test
    void ensureTwoProjectsAreNotEqual() {
        assertFalse(projectDTO1.equals(null));
    }

    @Test
    void ensureTwoProjectsAreNotEqual2() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDTO1.equals(notSameObjectType));
    }
    @Test
    public void testHashCodeGetProjectDTO() {
        GetProjectDTO obj1 = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        GetProjectDTO obj2 = new GetProjectDTO("AA001", "software development management",
                "isep", "planned", "fixed cost", "fishing");
        GetProjectDTO obj3 = new GetProjectDTO("AA003", "software development management",
                "isep", "planned", "fixed cost", "fishing");

        // Check that equal objects have the same hash code
        assertEquals(obj1.hashCode(), obj2.hashCode());

        // Check that unequal objects have different hash codes
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }
}
