package org.switch2022.project.model.container;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.utils.dto.ProjectDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectContainerTest {
    /**
     * BeforeEach and AfterEach executes common code before/after running the
     * tests below.
     */
    Project projectOne, projectTwo;
    List<Project> projects;
    ProjectContainer projectContainer;
    ProjectDTO projectOneDTO, projectTwoDTO;

    @BeforeEach
    void setUp() {
        /*
          Projects created.
         */
        projectOne = new Project("AA001", "Aptoide", new Customer("John"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
        projectTwo = new Project("AA002", "Aptoide", new Customer("John"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));

        /*
          Container of projects created.
         */
        projects = new ArrayList<>();
        projectContainer = new ProjectContainer(projects);

        /*
          Project is added to the container.
         */
        projects.add(projectOne);
        projects.add(projectTwo);

        /*
          Project DTOs created.
         */
        projectOneDTO = new ProjectDTO("AA001", "Aptoide", new Customer("John"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
        projectTwoDTO = new ProjectDTO("AA003", "Aptoide", new Customer("John"),
                new ProjectTypology("Fixed cost"), new BusinessSector("Hunting"));
    }

    @AfterEach
    void tearDown() {
        projectOne = null;
        projectTwo = null;
        projectOneDTO = null;
        projectTwoDTO = null;
    }

    /**
     * Testing if one is able to register a new project and add it to the
     * container.
     */
    @Test
    void verifyProjectIsNotRegistered() {
        boolean expected = false;
        boolean result = projectContainer.registerProject(projectOneDTO);
        assertEquals(expected, result);
    }

    @Test
    void ensureProjectIsRegistered() {
        boolean expected = true;
        boolean result = projectContainer.registerProject(projectTwoDTO);
        assertEquals(expected, result);
    }
}
