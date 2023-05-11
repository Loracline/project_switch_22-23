package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectResourceRepositoryTest {
    /**
     * Method: equals()
     * Scenario 01: Test to ensure the object equals itself.
     */
    @Test
    void ensureSameObjectEqualsItself() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        ProjectResourceRepository sameRepository = repository;
        boolean expected = true;

        //Act
        boolean result = repository.equals(sameRepository);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: equals()
     * Scenario 02: Test to ensure that two objects from different classes are different.
     */
    @Test
    void ensureObjectsAreFromDifferentClasses() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();

        Object otherObject = new Object();

        //Act
        boolean result = repository.equals(otherObject);

        //Assert
        assertFalse(result);
    }

    /**
     * Method: equals()
     * Scenario 03: Test to ensure that two different objects from the same class are different.
     */
    @Test
    void ensureTwoDifferentObjectsOfSameTypeAreNotEqual() {
        // Arrange
        ProjectResourceRepository repositoryOne = new ProjectResourceRepository();
        ProjectResource resource = mock(ProjectResource.class);
        repositoryOne.add(resource);

        ProjectResourceRepository repositoryTwo = new ProjectResourceRepository();

        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertFalse(result);
    }

    /**
     * Method: equals()
     * Scenario 04: Test to ensure that two equal objects from the same class are equal.
     */
    @Test
    void ensureTwoObjectsAreEqual() {
        // Arrange
        ProjectResourceRepository repositoryOne = new ProjectResourceRepository();
        ProjectResourceRepository repositoryTwo = new ProjectResourceRepository();

        //Act
        boolean result = repositoryOne.equals(repositoryTwo);
        //Assert
        assertTrue(result);
    }

    /**
     * Method: equals()
     * Scenario 05: Test to ensure that one object doesn't equal null.
     */
    @Test
    void ensureThatObjectDoesNotEqualNull() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();

        //Act
        boolean result = repository.equals(null);

        //Assert
        assertFalse(result);
    }


    /**
     * Method:hashCode()
     * Scenario 01:Two objects with the same hashcode are equal.
     */
    @Test
    void ensureTwoObjectsAreTheSameBecauseTheirHashcodeIsTheSame() {
        //Arrange
        ProjectResourceRepository repositoryOne = new ProjectResourceRepository();
        ProjectResourceRepository repositoryTwo = new ProjectResourceRepository();

        //Act and Assert
        assertEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }


    /**
     * Method:hashCode()
     * Scenario 02:Two objects with the different code and different hash codes are two different objects
     */
    @Test
    void ensureTwoObjectsWithDifferentHashcodesAreNotEqual() {
        //Arrange
        ProjectResourceRepository repositoryOne = new ProjectResourceRepository();
        ProjectResource resourceDouble = mock(ProjectResource.class);
        repositoryOne.add(resourceDouble);

        ProjectResourceRepository repositoryTwo = new ProjectResourceRepository();

        //Act and Assert
        assertNotEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }


    /**
     * Method: add(ProjectResource projectResource).
     * Adds a new ProjectResource instance to the repository of project resources.
     * <br>
     * Scenario 01: Check if an instance of ProjectResource is added to the list of project resources.
     * It should assert TRUE.
     */
    @Test
    void ensureThatAProjectResourceIsSuccessfullyAddedToTheProjectResourceRepository() {
        //Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        ProjectResource resource = mock(ProjectResource.class);

        //Act
        boolean result = repository.add(resource);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: add(ProjectResource projectResource).
     * Adds a new ProjectResource instance to the repository of project resources.
     * <p>
     * Scenario 02: Check that an instance of ProjectResource is not added to the repository if it already exists one
     * equal in the repository.
     * It should throw an AlreadyExistsInRepoException.
     */
    @Test
    void ensureThatAnExceptionIsThrownIfAnEqualInstanceOfProjectResourceAlreadyExistsInRepo() {
        //Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        ProjectResource resourceDouble = mock(ProjectResource.class);
        when(resourceDouble.hasSameAllocationInfo(resourceDouble)).thenReturn(true);
        repository.add(resourceDouble);

        String expected = "The project resource already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.add(resourceDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Method: getResourcesByProjectCode(Code projectCode).
     * Returns a list of project resources with a given project code.
     * <br>
     * Scenario 01: Check if an empty list is returned if the list of project resources is empty.
     * The two (empty) lists should assert equal.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedIfThereAreNoProjectResourcesWithGivenProjectCode() {
        //Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Code projectCode = mock(Code.class);
        List<ProjectResource> expected = new ArrayList<>();
        //Act
        List<ProjectResource> result = repository.getResourcesByProjectCode(projectCode);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getResourcesByProjectCode(Code projectCode).
     * Returns a list of project resources with a given project code.
     * <br>
     * Scenario 02: Check that a list is of project resources is returned if the list of project resources has entries.
     * The two lists should assert equal.
     */
    @Test
    void ensureThatAListOfProjectResourcesIsSuccessfullyReturned() {
        //Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);

        Code projectCode = mock(Code.class);

        when(resourceOne.hasProjectCode(projectCode)).thenReturn(true);
        when(resourceTwo.hasProjectCode(projectCode)).thenReturn(true);

        repository.add(resourceOne);
        repository.add(resourceTwo);
        repository.add(resourceThree);


        List<ProjectResource> expected = new ArrayList<>();
        expected.add(resourceOne);
        expected.add(resourceTwo);

        //Act
        List<ProjectResource> result = repository.getResourcesByProjectCode(projectCode);

        //Assert
        assertEquals(expected, result);
    }

}