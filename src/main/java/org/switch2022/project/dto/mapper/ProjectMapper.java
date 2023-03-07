package org.switch2022.project.dto.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {
    /**
     * Constructor of the class ListOfProjectsMapper.
     */
    private ProjectMapper() {}

    /**
     * This method receives a project and maps/transforms the project
     * into Dto
     */
    public static ProjectDto getDtoFromProject(Project project) {
        return new ProjectDto(project.getProjectCode(),
                project.getProjectName(), project.getCustomer().getCustomerName(),
                project.getProjectStatus(),
                project.getProjectTypology().getProjectTypologyName(),
                project.getBusinessSector().getBusinessSectorName());
    }

    /**
     * This method receives a list of projects, creates an empty list, maps/transforms the
     * projects
     * into Dto's and then stores them in a list to be returned by the method developed in
     * GetListOfProjectsController
     */
    public static List<ProjectDto> getListOfProjectsDto(List<Project> projects) {
        List<ProjectDto> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ProjectDto projectDto = getDtoFromProject(project);

            listOfProjectsDto.add(projectDto);
        }
        return listOfProjectsDto;
    }
}