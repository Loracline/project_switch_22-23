package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProjectTypology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectTypologyContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    ProjectTypologyContainer projectTypologyContainer;

    @BeforeEach
    void setUp() {
        /*
          Project typology Container
         */
        projectTypologyContainer = new ProjectTypologyContainer();
        projectTypologyContainer.createProjectTypology("Fixed Cost");
        projectTypologyContainer.createProjectTypology("Fixed time and materials");
    }

    @AfterEach
    void tearDown() {
        projectTypologyContainer = null;
    }

    /**
     * Testing if one can create new typology of project and add it to the
     * container.
     */
    @Test
    void ensureProjectTypologyCreatedSuccessfully() {
        boolean expected = true;
        boolean result = projectTypologyContainer.createProjectTypology("Fixed new typology");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedUnsuccessfully() {
        boolean expected = false;
        boolean result = projectTypologyContainer.createProjectTypology("Fixed Cost");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedUnsuccessfully_CaseInsensitive() {
        boolean expected = false;
        boolean result = projectTypologyContainer.createProjectTypology("fixed cost");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedSuccessfully_CaseInsensitive() {
        boolean expected = true;
        boolean result = projectTypologyContainer.createProjectTypology("fIxEd new typology");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyIsRetrieved() {
        ProjectTypology expected = new ProjectTypology("fixed cost");
        ProjectTypology result = projectTypologyContainer.getProjectTypology("fixed cost");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyIsNotRetrieved() {
        ProjectTypology expected = new ProjectTypology("fixed costs");
        ProjectTypology result = projectTypologyContainer.getProjectTypology("fixed cost");
        assertNotEquals(expected, result);
    }

    @Test
    void ensureAddProjectTypologyToTypologiesListUnsuccessfullyProjectTypologyNameEmpty() {
        //Arrange
        boolean expected = false;
        //Act
        boolean result = projectTypologyContainer.createProjectTypology("");
        //Assert
        assertEquals(expected, result);
    }
}