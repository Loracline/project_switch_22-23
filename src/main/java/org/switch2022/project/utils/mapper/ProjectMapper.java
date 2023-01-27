package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.model.container.BusinessSectorContainer;
import org.switch2022.project.model.container.CustomerContainer;
import org.switch2022.project.model.container.ProjectTypologyContainer;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.model.Project;

public class ProjectMapper {

  private ProjectMapper(){
  }
  public static Project getProjectFromDTO(ProjectDTO projectDTO, ProjectTypologyContainer projectTypologyContainer,
                                          CustomerContainer customerContainer, BusinessSectorContainer businessSectorContainer) {

    Customer customer = customerContainer.getCustomer(projectDTO.customerName,projectDTO.customerNif);
    ProjectTypology projectTypology = projectTypologyContainer.getProjectTypology(projectDTO.projectTypology);
    BusinessSector businessSector = businessSectorContainer.getBusinessSector(projectDTO.businessSector);
    return new Project(projectDTO.code, projectDTO.name, customer,
            projectTypology, businessSector);
  }
}

