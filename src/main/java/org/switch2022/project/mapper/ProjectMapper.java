package org.switch2022.project.mapper;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.model.Project;

public class ProjectMapper {
  public ProjectDTO toDTO(Project project) {
    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.code = project.getCode();
    projectDTO.name = project.getName();
    projectDTO.customer = project.getCustomer();
    projectDTO.status = project.getStatus();
    projectDTO.projectTypology = project.getProjectTypology();
    projectDTO.businessSector = project.getBusinessSector();
    return projectDTO;
  }

  public Project fromDTO(ProjectDTO projectDTO) {
    Project project = new Project(projectDTO.code, projectDTO.name, projectDTO.customer,
            projectDTO.projectTypology, projectDTO.businessSector);
    return project;
  }
}

