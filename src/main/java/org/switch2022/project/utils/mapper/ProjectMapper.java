package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.model.container.BusinessSectorContainer;
import org.switch2022.project.model.container.CustomerContainer;
import org.switch2022.project.model.container.ProjectTypologyContainer;
import org.switch2022.project.utils.dto.ProjectDTOAsManager;
import org.switch2022.project.model.Project;

public class ProjectMapper {

  private ProjectMapper(){
  }
  public static Project getProjectFromDTO(ProjectDTOAsManager projectDTOAsManager, ProjectTypologyContainer projectTypologyContainer,
                                          CustomerContainer customerContainer, BusinessSectorContainer businessSectorContainer) {

    Customer customer = customerContainer.getCustomer(projectDTOAsManager.customerName, projectDTOAsManager.customerNif);
    ProjectTypology projectTypology = projectTypologyContainer.getProjectTypology(projectDTOAsManager.projectTypology);
    BusinessSector businessSector = businessSectorContainer.getBusinessSector(projectDTOAsManager.businessSector);
    return new Project(projectDTOAsManager.code, projectDTOAsManager.name, customer,
            projectTypology, businessSector);
  }
}

