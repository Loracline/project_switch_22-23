package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.UserListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

public class UserListProjectsMapper {

    private UserListProjectsMapper() {}

    /**
     * This method receives a project and maps/transforms the project
     * into DTO
     */
    public static UserListProjectsDTO getDTOFromProject(Project project) {
        return new UserListProjectsDTO(project.getProjectCode(),
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
    public static List<UserListProjectsDTO> getListOfProjectsDTO(List<Project> projects) {
        List<UserListProjectsDTO> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            UserListProjectsDTO userListProjectsDTO = getDTOFromProject(project);

            listOfProjectsDto.add(userListProjectsDTO);
        }
        return listOfProjectsDto;
    }
}

