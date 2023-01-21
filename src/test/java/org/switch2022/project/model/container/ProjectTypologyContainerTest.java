package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProjectTypology;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectTypologyContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    ProjectTypology projectTypologyOne, projectTypologyTwo;
    List<ProjectTypology> typologies;
    ProjectTypologyContainer projectTypologyContainerReference;

    @BeforeEach
    void setUp() {
        /*
          Typologies created.
         */
        projectTypologyOne = new ProjectTypology("Fixed Cost");
        projectTypologyTwo = new ProjectTypology("Fixed time and materials");

        /*
          Container of typologies created.
         */
        typologies = new ArrayList<>();
        projectTypologyContainerReference = new ProjectTypologyContainer(typologies);

        /*
          Typologies added to the Container.
         */
        typologies.add(projectTypologyOne);
        typologies.add(projectTypologyTwo);
    }

    @AfterEach
    void tearDown() {
        projectTypologyOne = null;
        projectTypologyTwo = null;
        typologies.clear();
        projectTypologyContainerReference = null;
    }

    /**
     * Testing if one can create new typology of project and add it to the
     * container.
     */
    @Test
    void ensureProjectTypologyCreatedSuccessfully() {
        boolean expected = true;
        boolean result = projectTypologyContainerReference.createProjectTypology("Fixed new typology");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedUnsuccessfully() {
        boolean expected = false;
        boolean result = projectTypologyContainerReference.createProjectTypology("Fixed Cost");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedUnsuccessfully_CaseInsensitive() {
        boolean expected = false;
        boolean result = projectTypologyContainerReference.createProjectTypology("fixed cost");
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectTypologyCreatedSuccessfully_CaseInsensitive() {
        boolean expected = true;
        boolean result = projectTypologyContainerReference.createProjectTypology("fIxEd new typology");
        assertEquals(expected, result);
    }
}