package org.switch2022.project.ddd.dto.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.dto.ProjectDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectMapperTest {


    /**
     * Method projectToDto(project).
     * Scenario 1: it should return a dto from the UserStory given
     */

    @Test
    void ensureThatItReturnsAProjectDto() {
        //Arrange
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = mock(Project.class);
        String customerName = "ISEP";
        String projectCode = "P001";
        String projectName = "Alpha";
        String projectStatus = "planned";
        String startDate = "2023.01.01";
        String endDate = "2024.01.03";
        when(project.getProjectCode()).thenReturn(projectCode);
        when(project.getProjectName()).thenReturn(projectName);
        when(project.getProjectStatus()).thenReturn(projectStatus);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);
        ProjectDto expected = new ProjectDto(projectCode,projectName,customerName,projectStatus,startDate,endDate);
        //Act
        ProjectDto  result = projectMapper.projectToDto(project,customerName);
        //Assert
        assertEquals(expected, result);
    }

}