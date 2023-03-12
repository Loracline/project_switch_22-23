package org.switch2022.project.dto.mapper;

import org.switch2022.project.container.BusinessSectorContainer;
import org.switch2022.project.container.CustomerContainer;
import org.switch2022.project.container.ProjectTypologyContainer;
import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.factories.IFactoryProductBacklog;
import org.switch2022.project.factories.IFactoryProject;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

public final class ProjectCreationMapper {

  private ProjectCreationMapper() {
  }

  public static Project getProjectFromDto(ProjectCreationDto projectCreationDto, ProjectTypologyContainer
          projectTypologyContainer, CustomerContainer customerContainer, BusinessSectorContainer
                                                  businessSectorContainer, IFactoryProductBacklog factoryProductBacklog,
                                          IFactoryUserStory factoryUserStory, IFactoryProject factoryProject) {

    Customer customer = customerContainer.getCustomer(projectCreationDto.customerName,
            projectCreationDto.customerNif);

    ProjectTypology projectTypology =
            projectTypologyContainer.getProjectTypology(projectCreationDto.projectTypology);

    BusinessSector businessSector =
            businessSectorContainer.getBusinessSector(projectCreationDto.businessSector);

    return factoryProject.createProject(projectCreationDto,customer,projectTypology,businessSector,
            factoryProductBacklog,factoryUserStory);
  }
}

