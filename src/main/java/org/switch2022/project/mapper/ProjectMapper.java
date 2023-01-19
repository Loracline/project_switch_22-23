package org.switch2022.project.mapper;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

public class ProjectMapper {
  public ProjectDTO toDTO(Project project) {
    ProjectDTO projectDTO = new ProjectDTO();

    //projectDTO.setCode(project.code);
    projectDTO.setName(project.name);
    //.setCustomer(project.customer.getName());
    //projectDTO.setStatus(project.status);
    //projectDTO.setProjectTypology(project.projectTypology.getName());
    //projectDTO.setBusinessSector(project.businessSector.getName());
    return projectDTO;
  }

  /*public Project fromDTO(ProjectDTO projectDTO, Customer customer, ProjectTypology projectTypology, BusinessSector businessSector) {
    Project project = new Project(projectDTO.getCode(), projectDTO.getName(), customer, projectTypology, businessSector);
    return project;
  }*/ //todo


}

