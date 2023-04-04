package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectRepositoryTest {

    /**
     * Method: getProject().
     * <br>
     * Scenario 01: Lists of projects with isolation are equal.
     * Expected result: true.
     */
    @Test
    void ensureAllProjectsAreListedSuccessfullyListsAreEqual() {
        // Arrange
        Project projectDouble = mock(Project.class);
        ProjectRepository projectRepositoryDouble = new ProjectRepository();
        ProjectRepository projectRepositoryDoubleTwo = new ProjectRepository();
        projectRepositoryDouble.addProjectToProjectRepository(projectDouble);
        projectRepositoryDoubleTwo.addProjectToProjectRepository(projectDouble);

        // Act
        List<Project> expectedList = projectRepositoryDoubleTwo.getProjects();
        List<Project> resultList = projectRepositoryDouble.getProjects();
        boolean isEquals = expectedList.equals(resultList);

        // Assert
        assertTrue(isEquals);
    }

    /**
     * Method: getProjects()
     * <br>
     * Scenario 02: Lists of projects with isolation are different.
     * Expected result: false.
     */
    @Test
    void ensureAllProjectsAreListedSuccessfullyListsAreNotEqual() {
        // Arrange
        Project projectDouble = mock(Project.class);
        ProjectRepository projectRepositoryDouble = new ProjectRepository();
        ProjectRepository projectRepositoryDoubleTwo = new ProjectRepository();
        projectRepositoryDouble.addProjectToProjectRepository(projectDouble);

        // Act
        List<Project> expectedList = projectRepositoryDoubleTwo.getProjects();
        List<Project> resultList = projectRepositoryDouble.getProjects();
        boolean isEquals = expectedList.equals(resultList);

        // Assert
        assertFalse(isEquals);
    }

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
}