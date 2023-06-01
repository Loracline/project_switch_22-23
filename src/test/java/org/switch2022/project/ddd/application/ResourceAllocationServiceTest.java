package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.Account;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.AllocationDto;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.ddd.domain.value_object.Role.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ResourceAllocationServiceTest.class)
class ResourceAllocationServiceTest {

    @InjectMocks
    ResourceAllocationService service;
    @MockBean
    IProjectRepository projectRepository;
    @MockBean
    IAccountRepository accountRepository;
    @MockBean
    @Qualifier("resource_memory")
    IProjectResourceRepository resourceRepository;
    @MockBean
    IProjectResourceFactory resourceFactory;

    /**
     * Method addUserToProject().
     * Scenario 01: the user has been successfully added to the allocation.
     * Expected return: true.
     */
    @Test
    void ensureAddUserToProjectSuccessfully() {
        //Arrange
        AllocationDto allocationDto = new AllocationDto("P02", "test@project.com", TEAM_MEMBER.toString(), 8f, 50f,
                LocalDate.now(), LocalDate.now().plusWeeks(2));

        Account accountDouble = mock(Account.class);
        Project projectDouble = mock(Project.class);

        Optional<Project> projectOptional = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);

        when(projectDouble.hasStatus(any())).thenReturn(false);
        when(projectDouble.contains(any())).thenReturn(true);

        List<Account> accounts = new ArrayList<>();
        accounts.add(accountDouble);
        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountDouble.hasEmail(anyString())).thenReturn(true);
        when(accountDouble.isAccountActive()).thenReturn(true);

        ProjectResource projectResourceDouble = mock(ProjectResource.class);
        List<ProjectResource> projectResources = new ArrayList<>();
        projectResources.add(projectResourceDouble);
        when(resourceRepository.findAll()).thenReturn(projectResources);
        when(resourceRepository.save(any())).thenReturn(true);

        //Act
        boolean result = service.addUserToProject(allocationDto);
        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: the user was not successfully added to the allocation due to the project status being
     * different from expected.
     * Expected: throws exception.
     */
    @Test
    void ensureAddUserToProjectInsuccessfully() {
        //Arrange
        AllocationDto allocationDto = new AllocationDto("P02", "test@project.com", TEAM_MEMBER.toString(), 8f, 50f,
                LocalDate.now(), LocalDate.now().plusWeeks(2));

        Project projectDouble = mock(Project.class);

        Optional<Project> projectOptional = Optional.ofNullable(projectDouble);
        when(projectRepository.findByCode(any())).thenReturn(projectOptional);

        when(projectDouble.hasStatus(any())).thenReturn(true);

        //Act, Assert
        assertThrows(RuntimeException.class, ()->service.addUserToProject(allocationDto));
    }


    /**
     * METHOD isProjectValidForAllocation()
     */
    @SuppressWarnings("all")
    @DisplayName("Project in INCEPTION phase valid for allocation.")
    @Test
    void ensureProjectIsValidForAllocationWhenStatusInception() {
        // Arrange
        // Creating doubles that will be used in test to simulate behaviour.
        Code projectCode = mock(Code.class);
        Period allocationPeriod = mock(Period.class);
        Optional<Project> projectOpt = mock(Optional.class);
        Project project = mock(Project.class);

        // Setting up the behaviour of methods called.
        when(projectRepository.findByCode(projectCode)).thenReturn(projectOpt);
        when(projectOpt.isPresent()).thenReturn(true);
        when(projectOpt.get()).thenReturn(project);

        when(project.hasStatus(ProjectStatus.PLANNED)).thenReturn(false);
        when(project.hasStatus(ProjectStatus.CLOSED)).thenReturn(false);

        when(project.contains(allocationPeriod)).thenReturn(true);

        // Act
        boolean result = service.isProjectValidForAllocation(projectCode, allocationPeriod);

        // Assert
        assertTrue(result);
    }

    @SuppressWarnings("all")
    @DisplayName("Project in PLANNED phase invalid for allocation.")
    @Test
    void ensureReturnsFalseWhenProjectInPlannedStatus() {
        // Arrange
        Code projectCode = mock(Code.class);
        Period allocationPeriod = mock(Period.class);
        Optional<Project> projectOpt = mock(Optional.class);
        Project project = mock(Project.class);

        when(projectRepository.findByCode(projectCode)).thenReturn(projectOpt);
        when(projectOpt.isPresent()).thenReturn(true);
        when(projectOpt.get()).thenReturn(project);

        when(project.hasStatus(ProjectStatus.PLANNED)).thenReturn(true);

        // Act, Assert
        assertThrows(RuntimeException.class, ()->service.isProjectValidForAllocation(projectCode, allocationPeriod));
    }

    @SuppressWarnings("all")
    @DisplayName("Project in CLOSED phase invalid for allocation.")
    @Test
    void ensureReturnsFalseWhenProjectInClosedStatus() {
        // Arrange
        Code projectCode = mock(Code.class);
        Period allocationPeriod = mock(Period.class);
        Optional<Project> projectOpt = mock(Optional.class);
        Project project = mock(Project.class);

        when(projectRepository.findByCode(projectCode)).thenReturn(projectOpt);
        when(projectOpt.isPresent()).thenReturn(true);
        when(projectOpt.get()).thenReturn(project);

        when(project.hasStatus(ProjectStatus.PLANNED)).thenReturn(false);
        when(project.hasStatus(ProjectStatus.CLOSED)).thenReturn(true);

        // Act, Assert
        assertThrows(RuntimeException.class, ()->service.isProjectValidForAllocation(projectCode, allocationPeriod));
    }

    @SuppressWarnings("all")
    @DisplayName("Project does not exist in repository.")
    @Test
    void ensureReturnsFalseWhenProjectDoesNotExist() {
        // Arrange
        Code projectCode = mock(Code.class);
        Period allocationPeriod = mock(Period.class);
        Optional<Project> projectOpt = mock(Optional.class);

        when(projectRepository.findByCode(projectCode)).thenReturn(projectOpt);
        when(projectOpt.isPresent()).thenReturn(false);

        // Act, Assert
        assertThrows(RuntimeException.class, ()->service.isProjectValidForAllocation(projectCode, allocationPeriod));
    }

    @SuppressWarnings("all")
    @DisplayName("Project does not contain allocation period.")
    @Test
    void ensureReturnsFalseWhenProjectDoesNotContainAllocationPeriod() {
        // Arrange
        Code projectCode = mock(Code.class);
        Period allocationPeriod = mock(Period.class);
        Optional<Project> projectOpt = mock(Optional.class);
        Project project = mock(Project.class);

        when(projectRepository.findByCode(projectCode)).thenReturn(projectOpt);
        when(projectOpt.isPresent()).thenReturn(true);
        when(projectOpt.get()).thenReturn(project);

        when(project.contains(allocationPeriod)).thenReturn(false);

        // Act, Assert
        assertThrows(RuntimeException.class, ()->service.isProjectValidForAllocation(projectCode, allocationPeriod));
    }

    /**
     * Method isNotProjectManager().
     * <p>
     * Scenario 01: Verify if the role is not Project Manager.
     * Expected return: false.
     */
    @SuppressWarnings("all")
    @Test
    void ensureThatTheRoleOfResourceIsProjectManager() {
        //Arrange
        Role projectManager = Role.PROJECT_MANAGER;

        //Act, Assert
        assertThrows(RuntimeException.class, ()->service.isNotProjectManager(projectManager));
    }

    /**
     * Scenario 02: Verify if the role is not Project Manager when is Team Member.
     */
    @SuppressWarnings("all")
    @Test
    void ensureThatTheRoleOfResourceIsNotProjectManager() {
        //Arrange
        Role teamMember = Role.TEAM_MEMBER;

        //Act
        boolean result = service.isNotProjectManager(teamMember);

        //Assert
        assertTrue(result);
    }

    /**
     * Method projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod().
     * <p>
     * Scenario 01: Verify if the database has a Resource with the role of SCRUM_MASTER or PRODUCT_OWNER during a given
     * period of time, when there is no Resource added to database.
     * Expected return: false.
     */
    @Test
    void ensureThatReturnsFalseWhenResourceRepositoryIsEmpty() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        //Act
        boolean result = service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble, periodDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Scenario 02: Verify if the database has a Resource with the role of SCRUM_MASTER or PRODUCT_OWNER during a given
     * period of time, when there is a Resource in database with the role of PRODUCT_OWNER during that period.
     * Expected return: true.
     */
    @Test
    void ensureThatReturnsTrueWhenRepositoryAlreadyHasProductOwner() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(resourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(SCRUM_MASTER)).thenReturn(false);
        when(roleDouble.sameValueAs(PRODUCT_OWNER)).thenReturn(true);

        List<ProjectResource> projectResourceList = new ArrayList<>();
        projectResourceList.add(projectResource);

        when(resourceRepository.findAll()).thenReturn(projectResourceList);
        when(projectResource.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResource.isPeriodOverlapping(periodDouble)).thenReturn(true);
        when(projectResource.hasRole(roleDouble)).thenReturn(true);

        //Act, Assert
        assertThrows(RuntimeException.class, ()->service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble, periodDouble));
    }

    /**
     * Scenario 03: Verify if the database has a Resource with the role of SCRUM_MASTER or PRODUCT_OWNER during a given
     * period of time, when there is a Resource in database with the role of SCRUM_MASTER during that period.
     * Expected return: true.
     */
    @Test
    void ensureThatReturnsTrueWhenRepositoryAlreadyHasScrumMaster() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(resourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(SCRUM_MASTER)).thenReturn(true);

        List<ProjectResource> projectResourceList = new ArrayList<>();
        projectResourceList.add(projectResource);

        when(resourceRepository.findAll()).thenReturn(projectResourceList);
        when(projectResource.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResource.isPeriodOverlapping(periodDouble)).thenReturn(true);
        when(projectResource.hasRole(roleDouble)).thenReturn(true);

        //Act, Assert
        assertThrows(RuntimeException.class, ()->service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble, periodDouble));
    }

    /**
     * Scenario 04: Verify if the validation that cheks if the database has a Resource with the role of SCRUM_MASTER
     * or PRODUCT_OWNER returns false when the resource to be created is neither Scrum Master nor Product Owner.
     * Expected return: false.
     */
    @Test
    void ensureThatReturnFalseWhenRoleToBeCheckedIsNeitherProductOwnerNOrScrumMaster() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(resourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(SCRUM_MASTER)).thenReturn(false);
        when(roleDouble.sameValueAs(PRODUCT_OWNER)).thenReturn(false);

        //Act
        boolean result = service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble, periodDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * METHOD validatePercentageOfAllocation()
     */
    @DisplayName("Sum < 100%")
    @Test
    void ensureReturnsTrueWhenPercentageOfAllocationIsValid() {
        // Arrange
        Email email = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(resourceOne);
        resources.add(resourceTwo);
        resources.add(resourceThree);

        when(resourceRepository.findResourcesByAccountEmail(email)).thenReturn(resources);
        when(resourceOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(resourceOne.getPercentageOfAllocation()).thenReturn(25.0F);
        when(percentageOfAllocation.getValue()).thenReturn(50.0F);

        // Act
        boolean result = service.validatePercentageOfAllocation(email, date, percentageOfAllocation);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Sum = 100%")
    @Test
    void ensureReturnsTrueWhenPercentageOfAllocationIsValidAtMaximumAllowed() {
        // Arrange
        Email email = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(resourceOne);
        resources.add(resourceTwo);
        resources.add(resourceThree);

        when(resourceRepository.findResourcesByAccountEmail(email)).thenReturn(resources);
        when(resourceOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(resourceOne.getPercentageOfAllocation()).thenReturn(50.0F);
        when(percentageOfAllocation.getValue()).thenReturn(50.0F);

        // Act
        boolean result = service.validatePercentageOfAllocation(email, date, percentageOfAllocation);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Sum > 100%")
    @Test
    void ensureReturnsFalseWhenPercentageOfAllocationIsInvalidAboveMaximum() {
        // Arrange
        Email email = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(resourceOne);
        resources.add(resourceTwo);
        resources.add(resourceThree);

        when(resourceRepository.findResourcesByAccountEmail(email)).thenReturn(resources);
        when(resourceOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(resourceOne.getPercentageOfAllocation()).thenReturn(50.1F);
        when(percentageOfAllocation.getValue()).thenReturn(50.0F);

        // Act
        boolean result = service.validatePercentageOfAllocation(email, date, percentageOfAllocation);

        // Assert
        assertFalse(result);
    }

    @DisplayName("Already allocated at 100%")
    @Test
    void ensureReturnsFalseWhenAlreadyAllocatedAtMaximum() {
        // Arrange
        Email email = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);

        ProjectResource resourceOne = mock(ProjectResource.class);
        ProjectResource resourceTwo = mock(ProjectResource.class);
        ProjectResource resourceThree = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(resourceOne);
        resources.add(resourceTwo);
        resources.add(resourceThree);

        when(resourceRepository.findResourcesByAccountEmail(email)).thenReturn(resources);
        when(resourceOne.allocationPeriodIncludesDate(date)).thenReturn(true);
        when(resourceOne.getPercentageOfAllocation()).thenReturn(100.0F);
        when(percentageOfAllocation.getValue()).thenReturn(0.1F);

        // Act
        boolean result = service.validatePercentageOfAllocation(email, date, percentageOfAllocation);

        // Assert
        assertFalse(result);
    }

    @DisplayName("Not allocated in any project yet")
    @Test
    void ensureReturnsTrueWhenIsFirstAllocation() {
        // Arrange
        Email email = mock(Email.class);
        LocalDate date = mock(LocalDate.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);

        List<ProjectResource> resources = new ArrayList<>();

        when(resourceRepository.findResourcesByAccountEmail(email)).thenReturn(resources);
        when(percentageOfAllocation.getValue()).thenReturn(50.0F);

        // Act
        boolean result = service.validatePercentageOfAllocation(email, date, percentageOfAllocation);

        // Assert
        assertTrue(result);
    }

    /**
     * Method IsAValidAccount().
     * Scenario 01: Make sure the account is valid and active.
     * Expected return: True.
     */
    @Test
    void ensureThatAccountIsValid() {
        // Arrange
        Email accountEmailDouble = mock(Email.class);
        Account accountDouble = mock(Account.class);
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountDouble);

        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.isAccountActive()).thenReturn(true);

        when(accountRepository.findAll()).thenReturn(accounts);

        // Act
        boolean result = service.isAccountValidForAllocation(accountEmailDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scerario 02: the account exists, but is inactive.
     * Expected: throws exception.
     */
    @Test
    void ensureTheAccointExistisButIsInactivate() {
        //Arrange
        Email accountEmailDouble = mock(Email.class);

        //Act, Assert
        assertThrows(RuntimeException.class,()->service.isAccountValidForAllocation(accountEmailDouble));
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
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.save(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(true);
        // Act
        boolean result = service.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: Check resource is not overlapping.
     * Expected return: false.
     */
    @Test
    void ensureResourceIsNotOverlappingBecauseTheProjectCodeIsNotTheSame() {
        // Arrange
        Code codeDouble = mock(Code.class);
        Email emailDouble = mock(Email.class);
        Period periodDouble = mock(Period.class);
        // Act
        boolean result = service.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Scenario 03: Check resource is not overlapped, projectCode, accountEmail are same and period is not
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
        List<ProjectResource> resources = Arrays.asList(projectResourceOneDouble);
        when(resourceRepository.findAll()).thenReturn(resources);

        resourceRepository.save(projectResourceOneDouble);
        when(projectResourceOneDouble.hasProjectCode(codeDouble)).thenReturn(true);
        when(projectResourceOneDouble.hasAccount(emailDouble)).thenReturn(true);
        when(projectResourceOneDouble.isPeriodOverlapping(periodDouble)).thenReturn(false);
        // Act
        boolean result = service.isResourceOverlapping(codeDouble, emailDouble, periodDouble);

        // Assert
        assertFalse(result);
    }

    /**
     * Method isPercentageOfAllocationValid().
     * Scenario 01: Checks whether it is possible to allocate a user to a project if the allocation percentage of that
     * user during a given period of time is not greater than 100%.
     * Expected return: true.
     */
    @Test
    void ensureThatPercentageOfAllocationIsValid() {
        //Arrange
        //1
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResource projectResourceTwoDouble = mock(ProjectResource.class);
        ProjectResource projectResourceThreeDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        resources.add(projectResourceTwoDouble);
        resources.add(projectResourceThreeDouble);
        when(resourceRepository.findResourcesByAccountEmail(emailDouble)).thenReturn(resources);
        //2
        Period periodDouble = mock(Period.class);
        when(resources.get(0).isPeriodNotOverlapping(periodDouble)).thenReturn(false);
        when(resources.get(1).isPeriodNotOverlapping(periodDouble)).thenReturn(false);
        when(resources.get(2).isPeriodNotOverlapping(periodDouble)).thenReturn(true);
        //3
        when(periodDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        //4
        when(periodDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 1).toString());
        //5
        when(projectResourceOneDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        when(projectResourceTwoDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        //6
        Period periodOneDouble = mock(Period.class);
        Period periodTwoDouble = mock(Period.class);
        when(projectResourceOneDouble.getPeriod()).thenReturn(periodOneDouble);
        when(projectResourceTwoDouble.getPeriod()).thenReturn(periodTwoDouble);
        when(periodOneDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 9).toString());
        when(periodTwoDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 10).toString());
        //7
        when(projectResourceOneDouble.getPercentageOfAllocation()).thenReturn(40f);
        when(projectResourceTwoDouble.getPercentageOfAllocation()).thenReturn(10f);
        //8
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);
        when(percentageOfAllocationDouble.getValue()).thenReturn(50f);

        //Act
        boolean result = service.isPercentageOfAllocationValid(periodDouble, emailDouble, percentageOfAllocationDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Scenario 02: Checks whether it is possible to allocate a user to a project if the allocation percentage of that
     * user during a given period of time is greater than 100%.
     * Expected return: false.
     */

    @Test
    void ensureThatPercentageOfAllocationIsInvalid_LastDayHas101percent() {
        //Arrange
        //1
        Email emailDouble = mock(Email.class);
        ProjectResource projectResourceOneDouble = mock(ProjectResource.class);
        ProjectResource projectResourceTwoDouble = mock(ProjectResource.class);
        ProjectResource projectResourceThreeDouble = mock(ProjectResource.class);
        List<ProjectResource> resources = new ArrayList<>();
        resources.add(projectResourceOneDouble);
        resources.add(projectResourceTwoDouble);
        resources.add(projectResourceThreeDouble);
        when(resourceRepository.findResourcesByAccountEmail(emailDouble)).thenReturn(resources);
        //2
        Period periodDouble = mock(Period.class);
        when(resources.get(0).isPeriodNotOverlapping(periodDouble)).thenReturn(false);
        when(resources.get(1).isPeriodNotOverlapping(periodDouble)).thenReturn(false);
        when(resources.get(2).isPeriodNotOverlapping(periodDouble)).thenReturn(true);
        //3
        when(periodDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        //4
        when(periodDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 1).toString());
        //5
        when(projectResourceOneDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        when(projectResourceTwoDouble.numberOfDaysContainedInPeriod()).thenReturn(10);
        //6
        Period periodOneDouble = mock(Period.class);
        Period periodTwoDouble = mock(Period.class);
        when(projectResourceOneDouble.getPeriod()).thenReturn(periodOneDouble);
        when(projectResourceTwoDouble.getPeriod()).thenReturn(periodTwoDouble);
        when(periodOneDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 9).toString());
        when(periodTwoDouble.getStartDate()).thenReturn(LocalDate.of(2023, 5, 10).toString());
        //7
        when(projectResourceOneDouble.getPercentageOfAllocation()).thenReturn(40f);
        when(projectResourceTwoDouble.getPercentageOfAllocation()).thenReturn(11f);
        //8
        PercentageOfAllocation percentageOfAllocationDouble = mock(PercentageOfAllocation.class);
        when(percentageOfAllocationDouble.getValue()).thenReturn(50f);

        //Act, Assert
        assertThrows(RuntimeException.class, ()->service.isPercentageOfAllocationValid(periodDouble, emailDouble, percentageOfAllocationDouble));
    }
}
