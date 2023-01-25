package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.Project;

public class ProjectMapper {

  public ProjectDTO getDTOFromProject(Project project) {
    ProjectDTO projectDTO = new ProjectDTO(project.getProjectCode(), project.getProjectName(),
            project.getCustomer().getCustomerName(),project.getProjectTypology().getProjectTypologyName(),
            project.getBusinessSector().getBusinessSectorName());
    return projectDTO;
  }

  public Project getProjectFromDTO(ProjectDTO projectDTO) {
    Customer customer= new Customer(projectDTO.customer,null);
    ProjectTypology projectTypology= new ProjectTypology(projectDTO.projectTypology);
    BusinessSector businessSector= new BusinessSector(projectDTO.customer);
    Project project = new Project(projectDTO.code, projectDTO.name, customer,
            projectTypology, businessSector);
    return project;
  }

}

