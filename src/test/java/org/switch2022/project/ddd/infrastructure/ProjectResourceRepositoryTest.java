package org.switch2022.project.ddd.infrastructure;

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

    @Test
    void ensureThatTheRoleOfResoureIsProjectManager() {
        //Arrange
        Role projectManager = Role.PROJECT_MANAGER;
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();

        //Act
        boolean result = projectResourceRepository.isNotProjectManager(projectManager);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTheRoleOfResoureIsNotProjectManager() {
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
        Role productOwner = Role.PRODUCT_OWNER;
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        ProjectResourceFactory projectResourceFactory = mock(ProjectResourceFactory.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        projectResourceRepository.add(projectResource);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(productOwner, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnTrueWhenRepositoryAlreadyHasScrumMaster() {
        //Arrange
        ProjectResourceRepository projectResourceRepository = new ProjectResourceRepository();
        Role scrumMaster = Role.SCRUM_MASTER;
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        ProjectResourceFactory projectResourceFactory = mock(ProjectResourceFactory.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        projectResourceRepository.add(projectResource);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                projectResourceRepository.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(scrumMaster, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
    }
}
