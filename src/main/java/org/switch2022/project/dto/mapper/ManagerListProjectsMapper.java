package org.switch2022.project.dto.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.dto.ManagerListProjectsDto;

import java.util.ArrayList;
import java.util.List;

public class ManagerListProjectsMapper {
    /**
     * Constructor of the class ListOfProjectsMapper.
     */
    private ManagerListProjectsMapper() {}

    /**
     * This method receives a project and maps/transforms the project
     * into DTO
     */
    public static ManagerListProjectsDto getDTOFromProject(Project project) {
        return new ManagerListProjectsDto(project.getProjectCode(),
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
    public static List<ManagerListProjectsDto> getListOfProjectsDTO(List<Project> projects) {
        List<ManagerListProjectsDto> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ManagerListProjectsDto managerListProjectsDTO = getDTOFromProject(project);

            listOfProjectsDto.add(managerListProjectsDTO);
        }
        return listOfProjectsDto;
    }
}