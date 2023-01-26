package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

public class ListOfProjectsMapper {
    /**
     * Constructor of the class ListOfProjectsMapper.
     */
    private ListOfProjectsMapper() {
    }

    /**
     * This method receives a project and maps/transforms the project
     * into DTO
     */
    private static ListProjectsDTO getDTOFromProject(Project project) {
        return new ListProjectsDTO(project.getProjectCode(),
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
    public static List<ListProjectsDTO> getListOfProjectsDTO(List<Project> projects) {
        List<ListProjectsDTO> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ListProjectsDTO listProjectsDTO = getDTOFromProject(project);

            listOfProjectsDto.add(listProjectsDTO);
        }
        return listOfProjectsDto;
    }
}