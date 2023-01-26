package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ListProjectsDTO;

import java.util.ArrayList;
import java.util.List;

public class ListOfProjectsMapper {

    public List<ListProjectsDTO> toDTO(List<Project> projects) {
        List<ListProjectsDTO> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            ListProjectsDTO listProjectsDTO = new ListProjectsDTO(project.getProjectCode(),
                    project.getProjectName(), project.getCustomer().getCustomerName(),
                    project.getProjectStatus(),
                    project.getProjectTypology().getProjectTypologyName(),
                    project.getBusinessSector().getBusinessSectorName());

            listOfProjectsDto.add(listProjectsDTO);
        }
        return listOfProjectsDto;
    }
}