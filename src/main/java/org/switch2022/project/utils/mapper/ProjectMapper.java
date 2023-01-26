package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.Project;

public class ProjectMapper {

  public static ProjectDTO getDTOFromProject(Project project) {
    return new ProjectDTO(project.getProjectCode(), project.getProjectName(),
            project.getCustomer().getCustomerName(),project.getProjectTypology().getProjectTypologyName(),
            project.getBusinessSector().getBusinessSectorName());
  }

  public static Project getProjectFromDTO(ProjectDTO projectDTO) {
    Customer customer= new Customer(projectDTO.customer,null);
    ProjectTypology projectTypology= new ProjectTypology(projectDTO.projectTypology);
    BusinessSector businessSector= new BusinessSector(projectDTO.customer);
    return new Project(projectDTO.code, projectDTO.name, customer,
            projectTypology, businessSector);
  }
}

