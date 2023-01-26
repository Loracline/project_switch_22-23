package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ProjectDTO;
import java.util.ArrayList;
import java.util.List;

    public class ProjectListMapper {
        /**
         * Receives a ProjectList and transforms into a ProjectListDTO
         *
         * @param projects
         * @return projectsDTO
         */
    public static List<ProjectDTO> projectsToDTO(List<Project> projects) {
        List<ProjectDTO> projectsDTO = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            ProjectDTO projectDTO = ProjectMapper.getDTOFromProject(projects.get(i));
            projectsDTO.add(projectDTO);
        }
        return projectsDTO;
    }



}
