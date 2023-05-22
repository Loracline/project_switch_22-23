package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Email;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectResourceRepositoryTest {

    /**
     * Method: save(ProjectResource).
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
        boolean result = repository.save(resource);

        //Assert
        assertTrue(result);
    }

    /**
     * Method: save(ProjectResource).
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
        repository.save(resourceDouble);

        String expected = "The project resource already exists in the repository.";

        //Act
        AlreadyExistsInRepoException exception = assertThrows(AlreadyExistsInRepoException.class, () ->
                repository.save(resourceDouble));

        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * METHOD findAccountEmailsByProjectCode()
     */
    @DisplayName("No resources allocated to project")
    @Test
    void ensureReturnsEmptyListWhenNoResourceIsAllocatedToProject() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Code projectCode = mock(Code.class);
        List<Email> expected = new ArrayList<>();
        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        repository.save(resourceOne);
        repository.save(resourceTwo);

        // Act
        List<Email> result = repository.findAccountEmailsByProjectCode(projectCode);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("List of e-mails of the resources of a project")
    @Test
    void ensureThatAListOfProjectResourcesIsSuccessfullyReturned() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Code projectCode = mock(Code.class);

        List<Email> expected = new ArrayList<>();
        String stringOf_emailOne = "person@isep.ipp.pt";
        Email emailOne = new Email(stringOf_emailOne);
        expected.add(emailOne);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        repository.save(resourceOne);
        repository.save(resourceTwo);
        repository.save(resourceThree);

        when(resourceOne.hasProjectCode(projectCode)).thenReturn(true);
        when(resourceOne.getEmail()).thenReturn(stringOf_emailOne);

        // Act
        List<Email> result = repository.findAccountEmailsByProjectCode(projectCode);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method findProjectsByAccountEmail(Email) checks if there is an instance of ProjectResource in the
     * ProjectResourceRepository that contains an account with the email passed as argument and retrieves the project
     * of the corresponding allocation. It returns a list of string representations of the project codes, or an empty
     * list if no ProjectResource instance with the given account email was found.
     * <p>
     * Scenario 01: Check if an empty list is returned if no ProjectResource instance with the given account
     * email was found.
     * The two (empty) lists should assert equal.
     */
    @Test
    void ensureThatAnEmptyListIsReturnedIfThereAreNoResourcesWithGivenAccountEmail() {
        //Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Email emailDouble = mock(Email.class);
        List<Code> expected = new ArrayList<>();
        //Act
        List<Code> result = repository.findProjectCodesByAccountEmail(emailDouble);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method findProjectsByAccountEmail(Email) checks if there is an instance of ProjectResource inf the
     * ProjectResourceRepository that contains an account with the email passed as argument and retrieves the project
     * of the corresponding allocation. It returns a list of string representations of the project codes, or an empty
     * list if no ProjectResource instance with the given account email was found.
     * <p>
     * Scenario 02: Check that a list of project codes is returned.
     * The two lists should assert equal.
     */
    @Test
    void ensureThatAListOfProjectCodesIsSuccessfullyReturned() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Email emailDouble = mock(Email.class);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);

        repository.save(resourceOne);
        repository.save(resourceTwo);
        repository.save(resourceThree);

        when(resourceOne.hasAccount(emailDouble)).thenReturn(true);
        when(resourceThree.hasAccount(emailDouble)).thenReturn(true);

        when(resourceOne.getCode()).thenReturn("P01");
        when(resourceTwo.getCode()).thenReturn("P02");
        when(resourceThree.getCode()).thenReturn("P03");

        Code codeOne = new Code(1);
        Code codeTwo = new Code(3);
        List<Code> expected = Arrays.asList(codeOne,codeTwo);

        // Act
        List<Code> result = repository.findProjectCodesByAccountEmail(emailDouble);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD findResourcesByEmail()
     */
    @DisplayName("No resources with given e-mail = empty list")
    @Test
    void ensureEmptyListIsRetrievedWhenNoResourcesWithEmail() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Email email = mock(Email.class);
        List<ProjectResource> expected = new ArrayList<>();
        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        repository.save(resourceOne);
        repository.save(resourceTwo);
        repository.save(resourceThree);

        // Act
        List<ProjectResource> result = repository.findResourcesByAccountEmail(email);

        // Arrange
        assertEquals(expected, result);
    }

    @DisplayName("List with resources with given e-mail")
    @Test
    void ensureResourceListIsRetrievedSuccessfully() {
        // Arrange
        ProjectResourceRepository repository = new ProjectResourceRepository();
        Email email = mock(Email.class);
        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        repository.save(resourceOne);
        repository.save(resourceTwo);
        repository.save(resourceThree);

        when(resourceTwo.hasAccount(email)).thenReturn(true);

        List<ProjectResource> expected = new ArrayList<>();
        expected.add(resourceTwo);

        // Act
        List<ProjectResource> result = repository.findResourcesByAccountEmail(email);

        // Arrange
        assertEquals(expected, result);
    }
}
