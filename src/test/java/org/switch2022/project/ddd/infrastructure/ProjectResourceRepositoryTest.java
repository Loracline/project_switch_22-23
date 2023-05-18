package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    void ensureTwoObjectsWithDifferentHashcodeAreNotEqual() {
        //Arrange
        ProjectResourceRepository repositoryOne = new ProjectResourceRepository();
        ProjectResource resourceDouble = mock(ProjectResource.class);
        repositoryOne.add(resourceDouble);

        ProjectResourceRepository repositoryTwo = new ProjectResourceRepository();

        //Act and Assert
        assertNotEquals(repositoryOne.hashCode(), repositoryTwo.hashCode());
    }

    /**
     * Method: add(ProjectResource).
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
     * Method: add(ProjectResource).
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
     * Method: getAccountsAllocatedToProject(Code projectCode).
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
        List<String> expected = new ArrayList<>();
        //Act
        List<String> result = repository.getAccountsAllocatedToProject(projectCode);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Method: getAccountsAllocatedToProject(Code projectCode).
     * Returns a list of project resources with a given project code.
     * <br>
     * Scenario 02: Check that a list is of project resources is returned if the list of project resources has entries.
     * The two lists should assert equal.
     */
    @Test
    void ensureThatAListOfProjectResourcesIsSuccessfullyReturned() {
        // Arrange
        List<String> expected = new ArrayList<>();
        String emailOne = "person@isep.ipp.pt";
        String emailTwo = "other_person@isep.ipp.pt";
        expected.add(emailOne);
        expected.add(emailTwo);

        ProjectResourceRepository repository = new ProjectResourceRepository();
        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        repository.add(resourceOne);
        repository.add(resourceTwo);
        repository.add(resourceThree);

        Code projectCode = mock(Code.class);

        when(resourceOne.hasProjectCode(projectCode)).thenReturn(true);
        when(resourceTwo.hasProjectCode(projectCode)).thenReturn(true);
        when(resourceOne.getEmail()).thenReturn(emailOne);
        when(resourceTwo.getEmail()).thenReturn(emailTwo);

        // Act
        List<String> result = repository.getAccountsAllocatedToProject(projectCode);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * METHOD validatePercentageOfAllocation()
     */
    @DisplayName("Sum < 100%")
    @Test
    void ensureReturnsTrueWhenPercentageOfAllocationIsValid() {
        // Arrange
        boolean expected = true;

        ProjectResourceRepository repository = new ProjectResourceRepository();

        // Searching for John's allocations in repository...
        ProjectResource johnInProjectOne = mock(ProjectResource.class);
        ProjectResource johnInProjectTwo = mock(ProjectResource.class);
        ProjectResource johnInProjectThree = mock(ProjectResource.class);

        ProjectResource maryInProjectOne = mock(ProjectResource.class);
        ProjectResource maryInProjectTwo = mock(ProjectResource.class);

        repository.add(johnInProjectOne);
        repository.add(johnInProjectTwo);
        repository.add(johnInProjectThree);

        repository.add(maryInProjectTwo);
        repository.add(maryInProjectOne);

        Email accountEmail = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation toAdd = mock(PercentageOfAllocation.class);

        when(johnInProjectOne.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectTwo.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectThree.hasAccount(accountEmail)).thenReturn(true);

        when(johnInProjectOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectTwo.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectThree.allocationPeriodIncludesDate(date)).thenReturn(false);

        when(johnInProjectOne.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectTwo.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectThree.getPercentageOfAllocation()).thenReturn(50.0F);

        when(toAdd.getValue()).thenReturn(25.0F);

        // Act
        boolean result = repository.validatePercentageOfAllocation(accountEmail, date, toAdd);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Sum > 100%")
    @Test
    void ensureReturnsFalseWhenSumIsAboveMaximumAllowed() {
        // Arrange
        boolean expected = false;

        ProjectResourceRepository repository = new ProjectResourceRepository();

        // Searching for John's allocations in repository...
        ProjectResource johnInProjectOne = mock(ProjectResource.class);
        ProjectResource johnInProjectTwo = mock(ProjectResource.class);
        ProjectResource johnInProjectThree = mock(ProjectResource.class);

        ProjectResource maryInProjectOne = mock(ProjectResource.class);
        ProjectResource maryInProjectTwo = mock(ProjectResource.class);

        repository.add(johnInProjectOne);
        repository.add(johnInProjectTwo);
        repository.add(johnInProjectThree);

        repository.add(maryInProjectTwo);
        repository.add(maryInProjectOne);

        Email accountEmail = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation toAdd = mock(PercentageOfAllocation.class);

        when(johnInProjectOne.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectTwo.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectThree.hasAccount(accountEmail)).thenReturn(true);

        when(johnInProjectOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectTwo.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectThree.allocationPeriodIncludesDate(date)).thenReturn(false);

        when(johnInProjectOne.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectTwo.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectThree.getPercentageOfAllocation()).thenReturn(50.0F);

        when(toAdd.getValue()).thenReturn(100.0F);

        // Act
        boolean result = repository.validatePercentageOfAllocation(accountEmail, date, toAdd);

        // Assert
        assertEquals(expected, result);
    }

    @DisplayName("Sum = 100%")
    @Test
    void ensureReturnsTrueWhenSumIsEqualToMaximumAllowed() {
        // Arrange
        boolean expected = true;

        ProjectResourceRepository repository = new ProjectResourceRepository();

        // Searching for John's allocations in repository...
        ProjectResource johnInProjectOne = mock(ProjectResource.class);
        ProjectResource johnInProjectTwo = mock(ProjectResource.class);
        ProjectResource johnInProjectThree = mock(ProjectResource.class);

        ProjectResource maryInProjectOne = mock(ProjectResource.class);
        ProjectResource maryInProjectTwo = mock(ProjectResource.class);

        repository.add(johnInProjectOne);
        repository.add(johnInProjectTwo);
        repository.add(johnInProjectThree);

        repository.add(maryInProjectTwo);
        repository.add(maryInProjectOne);

        Email accountEmail = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation toAdd = mock(PercentageOfAllocation.class);

        when(johnInProjectOne.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectTwo.hasAccount(accountEmail)).thenReturn(true);
        when(johnInProjectThree.hasAccount(accountEmail)).thenReturn(true);

        when(johnInProjectOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectTwo.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(johnInProjectThree.allocationPeriodIncludesDate(date)).thenReturn(false);

        when(johnInProjectOne.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectTwo.getPercentageOfAllocation()).thenReturn(25.0F);
        when(johnInProjectThree.getPercentageOfAllocation()).thenReturn(50.0F);

        when(toAdd.getValue()).thenReturn(50.0F);

        // Act
        boolean result = repository.validatePercentageOfAllocation(accountEmail, date, toAdd);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Method isResourceOverlapping()
     * Scenario 01: Make sure resource is overlapping, projectCode, accountEmail are same and period is overlapping.
     * Expected return: True.
     */
    @Test
    void ensureResourceIsOverlapping() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        projectResourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = projectResourceRepository.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: Check resource is not overlapping, projectCode is not equal, accountEmail is same,
     * and the period is overlapped.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingBecauseTheProjectCodeIsNotTheSame() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        projectResourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(false);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = projectResourceRepository.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }


    /**
     * Scenario 03: Check resource is not overlapped, projectCode is same but accountEmail is not same
     * and the period is overlapped.
     * Expected return: false.
     */
    @Test
    void assureResourceIsNotOverlappingBecauseTheAccountEmailIsNotTheSame() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        projectResourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(false);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = projectResourceRepository.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 04: Check resource is not overlapped, projectCode, accountEmail are same and period is not
     * overlay.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingbecauseThePeriodIsNotOverllapping() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        projectResourceRepository.add(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(false);
        // Act
        boolean result = projectResourceRepository.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);

    }

    /**
     * Method findProjectsByAccountEmail(Email email) checks if there is an instance of ProjectResource in the
     * ProjectResourceRepository that contains an account with the email passed as argument and retrieves the project
     * of the corresponding allocation. It returns a list of string representations of the project codes, or an empty
     * list if no ProjectResource instance with the given account email was found.
     *
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
     * Method findProjectsByAccountEmail(Email email) checks if there is an instance of ProjectResource inf the
     * ProjectResourceRepository that contains an account with the email passed as argument and retrieves the project
     * of the corresponding allocation. It returns a list of string representations of the project codes, or an empty
     * list if no ProjectResource instance with the given account email was found.
     *
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

        repository.add(resourceOne);
        repository.add(resourceTwo);
        repository.add(resourceThree);

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
}
