package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.GetProjectDTO;

import java.util.ArrayList;
import java.util.List;

public class ListOfProjectsMapper {

    public List<GetProjectDTO> toDTO(List<Project> projects) {
        List<GetProjectDTO> listOfProjectsDto = new ArrayList<>();
        for (Project project : projects) {
            GetProjectDTO getProjectDTO = new GetProjectDTO(project.getProjectCode(),
                    project.getProjectName(), project.getCustomer().getCustomerName(),
                    project.getProjectStatus(),
                    project.getProjectTypology().getProjectTypologyName(),
                    project.getBusinessSector().getBusinessSectorName());

            listOfProjectsDto.add(getProjectDTO);
        }
        return listOfProjectsDto;
    }
}