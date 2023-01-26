package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ManagerListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

public class ManagerListProjectsMapper {
    /**
     * Constructor of the class ListOfProjectsMapper.
     */
    private ManagerListProjectsMapper() {
    }

    /**
     * This method receives a project and maps/transforms the project
     * into DTO
     */
    private static ManagerListProjectsDTO getDTOFromProject(Project project) {
        return new ManagerListProjectsDTO(project.getProjectCode(),
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
    public static List<ManagerListProjectsDTO> getListOfProjectsDTO(List<Project> projects) {
        List<ManagerListProjectsDTO> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ManagerListProjectsDTO managerListProjectsDTO = getDTOFromProject(project);

            listOfProjectsDto.add(managerListProjectsDTO);
        }
        return listOfProjectsDto;
    }
}