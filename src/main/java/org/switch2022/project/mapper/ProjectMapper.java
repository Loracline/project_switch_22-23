package org.switch2022.project.mapper;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.controller.AccountDTO;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
import java.util.List;

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

  public List<ProjectDTO> projectsToDTO(List<Project> projects) {
    List<ProjectDTO> projectsDTO = new ArrayList<>();
    for (int i = 0; i < projects.size(); i++) {
      Project project = projects.get(i);
      ProjectDTO projectDTO = toDTO(project);
      projectsDTO.add(projectDTO);
    }
    return projectsDTO;
  }






}

