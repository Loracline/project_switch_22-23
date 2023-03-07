package org.switch2022.project.dto.mapper;

import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.CustomerContainer;
import org.switch2022.project.container.ProjectTypologyContainer;
import org.switch2022.project.dto.ProjectDtoAsManager;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

public final class ProjectMapper {

  private ProjectMapper() {
  }

  public static Project getProjectFromDto(ProjectDtoAsManager projectDtoAsManager, ProjectTypologyContainer
          projectTypologyContainer, CustomerContainer customerContainer, BusinessSectorContainer
                                                  businessSectorContainer) {

    Customer customer = customerContainer.getCustomer(projectDtoAsManager.customerName,
            projectDtoAsManager.customerNif);

    ProjectTypology projectTypology =
            projectTypologyContainer.getProjectTypology(projectDtoAsManager.projectTypology);

    BusinessSector businessSector =
            businessSectorContainer.getBusinessSector(projectDtoAsManager.businessSector);

    return new Project(projectDtoAsManager.code, projectDtoAsManager.name,
            customer, projectTypology, businessSector);
  }
}

