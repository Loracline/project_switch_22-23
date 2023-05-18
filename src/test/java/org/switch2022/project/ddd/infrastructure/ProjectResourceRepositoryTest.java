package org.switch2022.project.ddd.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResourceFactory;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.AlreadyExistsInRepoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void ensureThatTheRoleOfResourceIsProjectManager() {
        //Arrange
        Role projectManager = Role.PROJECT_MANAGER;
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();

        //Act
        boolean result = projectResourceRepository.isNotProjectManager(projectManager);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTheRoleOfResourceIsNotProjectManager() {
        //Arrange
        Role teamMember = Role.TEAM_MEMBER;
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();

        //Act
        boolean result = projectResourceRepository.isNotProjectManager(teamMember);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnsTrueWhenRepositoryAlreadyHasAScrumMasterInThatPeriod() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();

        Role scrumMaster = Role.SCRUM_MASTER;
        Code code = new Code(1);
        Period period = new Period(LocalDate.of(2023, 5, 10), 2);
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Email email = mock(Email.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResource = projectResourceFactory.createProjectResource(projectResourceIdDouble,
                code, email, scrumMaster, period, costDouble, percentageOfAllocationDouble);

        projectResourceRepository.add(projectResource);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(scrumMaster, code,
                        period);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnsTrueWhenRepositoryAlreadyHasAProductOwnerInThatPeriod() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();

        Role productOwner = Role.PRODUCT_OWNER;
        Code code = new Code(1);
        Period period = new Period(LocalDate.of(2023, 5, 10), 2);
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Email email = mock(Email.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResource = projectResourceFactory.createProjectResource(projectResourceIdDouble,
                code, email, productOwner, period, costDouble, percentageOfAllocationDouble);

        projectResourceRepository.add(projectResource);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(productOwner, code,
                        period);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnsFalseWhenRepositoryDoesNotHaveScrumMasterInThatPeriod() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();

        Role scrumMaster = Role.SCRUM_MASTER;
        Code code = new Code(1);
        Period period = new Period(LocalDate.of(2023, 5, 10), 2);
        Period diferentPeriod = new Period(LocalDate.of(2023, 5, 30), 2);
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Email email = mock(Email.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceOne = projectResourceFactory.createProjectResource(projectResourceIdDouble,
                code, email, scrumMaster, period, costDouble, percentageOfAllocationDouble);

        projectResourceRepository.add(projectResourceOne);

        //Act
        boolean otherResult = projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(scrumMaster, code,
                diferentPeriod);

        //Assert
        assertFalse(otherResult);
    }

    @Test
    void ensureThatReturnsFalseWhenRepositoryDoesNotHaveProductOwnerInThatPeriod() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        ProjectResourceFactory projectResourceFactory = new ProjectResourceFactory();

        Role productOwner = Role.PRODUCT_OWNER;
        Code code = new Code(1);
        Period period = new Period(LocalDate.of(2023, 5, 10), 2);
        Period diferentPeriod = new Period(LocalDate.of(2023, 5, 30), 2);
        ProjectResourceId projectResourceIdDouble = mock(ProjectResourceId.class);
        Email email = mock(Email.class);
        CostPerHour costDouble = mock(CostPerHour.class);
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);

        ProjectResource projectResourceTwo = projectResourceFactory.createProjectResource(projectResourceIdDouble,
                code, email, productOwner, period, costDouble, percentageOfAllocationDouble);

        projectResourceRepository.add(projectResourceTwo);

        //Act
        boolean otherResult = projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(productOwner, code,
                diferentPeriod);

        //Assert
        assertFalse(otherResult);
    }

    @Test
    void ensureThatReturnsFalseWhenRepositoryIsEmpty() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();

        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        //Act
        boolean result = projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble
                , codeDouble, periodDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatReturnFalseWhenRoleToCheckIsNotProductOwnerOrScrumMaster() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        Role teamMember = Role.TEAM_MEMBER;
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(teamMember, codeDouble,
                        periodDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatReturnTrueWhenRepositoryAlreadyHasProductOwner() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResourceFactory projectResourceFactory = mock(ProjectResourceFactory.class);
        ProjectResource projectResource = mock(ProjectResource.class);

        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        projectResourceRepository.add(projectResource);

        when(roleDouble.sameValueAs(Role.PRODUCT_OWNER)).thenReturn(true);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnTrueWhenRepositoryAlreadyHasScrumMaster() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResourceFactory projectResourceFactory = mock(ProjectResourceFactory.class);
        ProjectResource projectResource = mock(ProjectResource.class);

        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        projectResourceRepository.add(projectResource);

        when(roleDouble.sameValueAs(Role.SCRUM_MASTER)).thenReturn(true);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
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
}
