package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ListProjectsDTO;
import org.switch2022.project.utils.dto.ProjectDTOus016;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapperUS016 {

    private ProjectMapperUS016() {
    }


    /**
     * This method receives a project and maps/transforms the project
     * into DTO
     */
    public static ProjectDTOus016 getDTOFromProject(Project project) {
        return new ProjectDTOus016(project.getProjectCode(),
                project.getProjectName(), project.getCustomer().getCustomerName(),
                project.getProjectStatus(),
                project.getProjectTypology().getProjectTypologyName(),
                project.getBusinessSector().getBusinessSectorName());
    }

    /**
     * This method receives a list of projects, creates an empty list, maps/transforms the
     * projects
     * into DTOs and then stores them in a list to be returned by the method developed in
     * GetListOfProjectsController
     */
    public static List<ProjectDTOus016> getListOfProjectsDTO(List<Project> projects) {
        List<ProjectDTOus016> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ProjectDTOus016 projectDTOus016 = getDTOFromProject(project);

            listOfProjectsDto.add(projectDTOus016);
        }
        return listOfProjectsDto;
    }
}

