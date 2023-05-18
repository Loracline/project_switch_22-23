package org.switch2022.project.ddd.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.domain.model.account.IAccountRepository;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ResourceAllocationService.class
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
}