package org.switch2022.project.ddd.application;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceFactory;
import org.switch2022.project.ddd.domain.model.project_resource.IProjectResourceRepository;
import org.switch2022.project.ddd.domain.model.project_resource.ProjectResource;

import org.switch2022.project.ddd.domain.value_object.*;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.ddd.domain.value_object.Role.PRODUCT_OWNER;
import static org.switch2022.project.ddd.domain.value_object.Role.SCRUM_MASTER;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ResourceAllocationServiceTest.class)
class ResourceAllocationServiceTest {
    @InjectMocks
    ResourceAllocationService resourceAllocationService;
    @MockBean
    IProjectResourceFactory projectResourceFactory;
    @MockBean
    IProjectResourceRepository projectResourceRepository;

    @Test
    void ensureThatTheRoleOfResourceIsProjectManager() {
        //Arrange
        Role projectManager = Role.PROJECT_MANAGER;

        //Act
        boolean result = resourceAllocationService.isNotProjectManager(projectManager);

        //Assert
        assertFalse(result);
    }

    @Test
    void ensureThatTheRoleOfResourceIsNotProjectManager() {
        //Arrange
        Role teamMember = Role.TEAM_MEMBER;


        //Act
        boolean result = resourceAllocationService.isNotProjectManager(teamMember);

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
        boolean result = resourceAllocationService.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble
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
        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(SCRUM_MASTER)).thenReturn(false);
        when(roleDouble.sameValueAs(PRODUCT_OWNER)).thenReturn(true);

        List<ProjectResource> projectResourceList = new ArrayList<>();
        projectResourceList.add(projectResource);

        when(projectResourceRepository.findAll()).thenReturn(projectResourceList);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                resourceAllocationService.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
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
        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(SCRUM_MASTER)).thenReturn(true);
        List<ProjectResource> projectResourceList = new ArrayList<>();
        projectResourceList.add(projectResource);
        when(projectResourceRepository.findAll()).thenReturn(projectResourceList);
        when(projectResource.hasProjectCode(any())).thenReturn(true);
        when(projectResource.isPeriodOverlapping(any())).thenReturn(true);
        when(projectResource.hasRole(any())).thenReturn(true);

        //Act
        boolean result =
                resourceAllocationService.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
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
        when(projectResourceFactory.createProjectResource(any(), any(), any(), any(), any(), any(), any())).thenReturn(projectResource);
        when(roleDouble.sameValueAs(any())).thenReturn(false);

        //Act
        boolean result =
                resourceAllocationService.projectAlreadyHasScrumMasterOrProductOwnerInThatPeriod(roleDouble, codeDouble,
                        periodDouble);

        //Assert
        assertFalse(result);
    }

}