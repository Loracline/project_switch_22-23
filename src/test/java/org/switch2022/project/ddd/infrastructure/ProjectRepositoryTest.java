package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectRepositoryTest {

    /**
     * Method: addProjectToProjectRepository(project).
     * adds a Project to Project Repository
     * <br>
     * Scenario 01: verify if a Project is added to the list of Projects, if the list is not empty.
     * Expected result: true.
     */
    @Test
    void ensureThatProjectIsSuccessfullyAddedToProjectsList() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectRepository projectRepository = new ProjectRepository();

        Project projectDoubleTest = mock(Project.class);
        projectRepository.addProjectToProjectRepository(projectDoubleTest);

        //Act
        boolean result = projectRepository.addProjectToProjectRepository(projectDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: addProjectToProjectRepository(project).
     * <br>
     * Scenario 02: verify if a Project is added to the list of Projects, after project
     * list is empty.
     * Expected result: true.
     */
    @Test
    void ensureProjectIsSuccessfullyAddedToProjectRepositoryBecauseProjectListIsEmpty() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectRepository projectRepository = new ProjectRepository();

        //Act
        boolean result = projectRepository.addProjectToProjectRepository(projectDouble);

        //Assert
        assertTrue(result);

    }

    /**
     * Method: addProjectToProjectRepository(project)
     * <br>
     * Scenario 03: verify if a Project is not added to the list os Projects because
     * the project is already on the list.
     * Expected result: false.
     */
    @Test
    void ensureProjectIsNotAddedToProjectsListBecauseItAlreadyExists() {
        //Arrange
        Project projectDouble = mock(Project.class);
        ProjectRepository projectRepository = new ProjectRepository();

        projectRepository.addProjectToProjectRepository(projectDouble);

        //Act
        boolean result = projectRepository.addProjectToProjectRepository(projectDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: getProjectByCode()
     * <br>
     * Scenario 01: check if I can select the project by your code
     */
    @Test
    void ensureSelectTheProjectByCode() {
        //Arrange

        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);

        ProjectRepository projectRepositoryOne = new ProjectRepository();
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        projectRepositoryOne.addProjectToProjectRepository(projectTwo);

        Code codeOne = mock(Code.class);
        when(projectOne.hasProjectCode(any())).thenReturn(true);

        //Act
        Optional<Project> optionalResult = projectRepositoryOne.getProjectByCode(codeOne);

        //Assert
        assertEquals(Optional.of(projectOne), optionalResult);
    }

    /**
     * Method: getProjectNumber().
     * <br>
     * Scenario 01: return the number of projects from a list
     * Expected result: true.
     */
    @Test
    void theNumberOfProjectsOnTheList() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);

        ProjectRepository projectRepositoryOne = new ProjectRepository();
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        projectRepositoryOne.addProjectToProjectRepository(projectTwo);

        int expected = 2;
        int result = projectRepositoryOne.getProjectNumber();

        assertEquals(expected, result);

    }
}