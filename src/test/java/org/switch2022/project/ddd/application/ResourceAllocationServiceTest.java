package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.ddd.domain.value_object.Role.PRODUCT_OWNER;
import static org.switch2022.project.ddd.domain.value_object.Role.SCRUM_MASTER;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ResourceAllocationServiceTest.class
)
class ResourceAllocationServiceTest {

    @InjectMocks
    ResourceAllocationService service;

    @MockBean
    IProjectRepository projectRepository;

    @MockBean
    IAccountRepository accountRepository;

    @MockBean
    IProjectResourceRepository resourceRepository;

    @MockBean
    IProjectResourceFactory resourceFactory;

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

        // Act
        boolean result = service.isProjectValidForAllocation(projectCode, allocationPeriod);

        // Assert
        assertFalse(result);
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

        // Act
        boolean result = service.isProjectValidForAllocation(projectCode, allocationPeriod);

        // Assert
        assertFalse(result);
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

        // Act
        boolean result = service.isProjectValidForAllocation(projectCode, allocationPeriod);

        // Assert
        assertFalse(result);
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

        // Act
        boolean result = service.isProjectValidForAllocation(projectCode, allocationPeriod);

        // Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTheRoleOfResourceIsProjectManager() {
        //Arrange
        Role projectManager = Role.PROJECT_MANAGER;

        //Act
        boolean result = service.isNotProjectManager(projectManager);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTheRoleOfResourceIsNotProjectManager() {
        //Arrange
        Role teamMember = Role.TEAM_MEMBER;


        //Act
        boolean result = service.isNotProjectManager(teamMember);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnsFalseWhenRepositoryIsEmpty() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);

        //Act
        boolean result = service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble
                , codeDouble, periodDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatReturnTrueWhenRepositoryAlreadyHasProductOwner() {
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
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnTrueWhenRepositoryAlreadyHasScrumMaster() {
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
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void ensureThatReturnFalseWhenRoleToCheckIsNotProductOwnerOrScrumMaster() {
        //Arrange
        Role roleDouble = mock(Role.class);
        Code codeDouble = mock(Code.class);
        Period periodDouble = mock(Period.class);
        ProjectResource projectResource = mock(ProjectResource.class);
        when(resourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(any())).thenReturn(false);

        //Act
        boolean result =
                service.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

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

        when(resourceRepository.findResourcesByEmail(email)).thenReturn(resources);
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

        when(resourceRepository.findResourcesByEmail(email)).thenReturn(resources);
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

        when(resourceRepository.findResourcesByEmail(email)).thenReturn(resources);
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

        when(resourceRepository.findResourcesByEmail(email)).thenReturn(resources);
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

        when(resourceRepository.findResourcesByEmail(email)).thenReturn(resources);
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
        AccountStatus accountStatus = mock(AccountStatus.class);
        Account accountDouble = mock(Account.class);
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountDouble);

        when(accountDouble.hasEmail(accountEmailDouble.getEmail())).thenReturn(true);
        when(accountDouble.isAccountActive(accountStatus.getAccountStatus())).thenReturn(true);

        when(accountRepository.findAll()).thenReturn(accounts);

        // Act
        boolean result = service.isAValidAccount(accountEmailDouble, accountStatus);

        // Assert
        assertTrue(result);
    }

    /**
     * Scerario 02: the account exists, but is inactive.
     * Expected return: false.
     */
    @Test
    void ensureTheAccointExistisButIsInactivate() {
        //Arrange
        Email accountEmailDouble = mock(Email.class);
        AccountStatus accountStatus = mock(AccountStatus.class);
        boolean result = service.isAValidAccount(accountEmailDouble, accountStatus);
        //Assert
        assertFalse(result);
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
}
