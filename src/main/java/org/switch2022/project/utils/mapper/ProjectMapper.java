package org.switch2022.project.utils.mapper;

import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.Project;

public class ProjectMapper {

  public ProjectDTO getDTOFromProject(Project project) {
    ProjectDTO projectDTO = new ProjectDTO(project.getProjectCode(), project.getProjectName(),
            project.getCustomer(),project.getProjectTypology(),
            project.getBusinessSector());
    return projectDTO;
  }

  public Project getProjectFromDTO(ProjectDTO projectDTO) {
    Project project = new Project(projectDTO.code, projectDTO.name, projectDTO.customer,
            projectDTO.projectTypology, projectDTO.businessSector);
    return project;
  }

}

