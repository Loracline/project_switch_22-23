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
                    project.getProjectName(), project.getCustomer(),
                    project.getProjectStatus(),
                    project.getProjectTypology(),
                    project.getBusinessSector());

            listOfProjectsDto.add(getProjectDTO);
        }
        return listOfProjectsDto;
    }
}