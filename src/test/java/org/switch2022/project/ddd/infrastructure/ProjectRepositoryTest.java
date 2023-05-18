package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectRepositoryTest {

    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        ProjectRepository projectRepositoryReference = projectRepositoryOne;
        boolean expected = true;

        //Act
        boolean result = projectRepositoryOne.equals(projectRepositoryReference);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 02:Test to ensure that two objects are from different classes
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();

        Object projectRepositoryObject = new Object();
        boolean expected = false;

        //Act
        boolean result = projectRepositoryOne.equals(projectRepositoryObject);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two objects from the same class are different
     */
    @Test
    void ensureTwoProjectsAreNotEqual() {
        // Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        Project projectOne = mock(Project.class);
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        ProjectRepository projectRepositoryTwo = new ProjectRepository();

        boolean expected = false;
        //Act
        boolean result = projectRepositoryOne.equals(projectRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that two objects from the same class are equals.
     */
    @Test
    void ensureTwoProjectsAreEqual() {
        // Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        Project projectOne = mock(Project.class);
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        ProjectRepository projectRepositoryTwo = new ProjectRepository();
        projectRepositoryTwo.addProjectToProjectRepository(projectOne);

        boolean expected = true;
        //Act
        boolean result = projectRepositoryOne.equals(projectRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 06: Test to ensure that Repository doesn't equals a null object
     */
    @Test
    void ensureThatProjectDoesNotEqualsNull() {
        // Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        ProjectRepository projectRepositoryTwo = null;

        boolean expected = false;
        //Act
        boolean result = projectRepositoryOne.equals(projectRepositoryTwo);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method:hashCode()
     * Scenario 01:Two equal ProjectsRepository objects are the same.
     */
    @Test
    void ensureTwoUsIdInstancesHashcodeAreTheSame() {
        Project projectOne = mock(Project.class);
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        ProjectRepository projectRepositoryTwo = new ProjectRepository();
        projectRepositoryTwo.addProjectToProjectRepository(projectOne);

        //Assert
        assertEquals(projectRepositoryOne.hashCode(), projectRepositoryTwo.hashCode());
    }

    /**
     * Method:hashCode()
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureNoTwoProjectsHaveTheSameHashCode() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        ProjectRepository projectRepositoryTwo = new ProjectRepository();
        projectRepositoryTwo.addProjectToProjectRepository(projectTwo);

        //Assert
        assertNotEquals(projectRepositoryOne.hashCode(), projectRepositoryTwo.hashCode());
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
        Optional<Project> optionalResult = projectRepositoryOne.findByCode(codeOne);

        //Assert
        assertEquals(Optional.of(projectOne), optionalResult);
    }

    /**
     * Method: getProjectByCode()
     * <br>
     * Scenario 02: test "Get Project By Code" When List Is Empty.
     * Checks whether an Optional contains a non-null value. <br>
     * It returns true if the Optional contains a value and false if it is empty.
     */
    @Test
    void whenProjectsListIsEmpty() {
        //Arrange
        Project projectOne = mock(Project.class);
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        Code codeOne = mock(Code.class);
        when(projectOne.hasProjectCode(any())).thenReturn(true);

        //Act
        Optional<Project> optionalResult = projectRepositoryOne.findByCode(codeOne);

        //Assert
        assertFalse(optionalResult.isPresent());
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

    /**
     * Method: findAll()
     * <p>
     * scenario 1: Verify that an empty list is returned when the project list is empty
     */
    @Test
    void ensureProjectReturnsAnEmptyListWhenThereAreNoProjects() {
        //Arrange
        ProjectRepository projectRepositoryOne = new ProjectRepository();
        List<Project> expected = new ArrayList<>();
        //Act
        List<Project> result = projectRepositoryOne.findAll();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * scenario 2: Verify that a list pf projects is returned when the project list has projects
     */
    @Test
    void ensureProjectReturnsAListOfProjects() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);

        ProjectRepository projectRepositoryOne = new ProjectRepository();
        projectRepositoryOne.addProjectToProjectRepository(projectOne);
        projectRepositoryOne.addProjectToProjectRepository(projectTwo);

        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        expected.add(projectTwo);


        //Act
        List<Project> result = projectRepositoryOne.findAll();

        //Assert
        assertEquals(expected, result);
    }
}