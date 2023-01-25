package org.switch2022.project.utils.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetProjectDTOTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the tests below.
     */

    GetProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        projectDTO = new GetProjectDTO("AA001", "software development management",
                "isep",  "planned" ,"fixed cost", "fishing");
    }

    @Test
    void ensureSameObjectEqualsItself() {
        assertTrue(projectDTO.equals(projectDTO));
    }

    @Test
    void ensureTwoProjectsAreNotEqual() {
        assertFalse(projectDTO.equals(null));
    }

    @Test
    void ensureTwoProjectsAreNotEqual2() {
        String notSameObjectType = "notSameObjectType";
        assertFalse(projectDTO.equals(notSameObjectType));
    }
}
