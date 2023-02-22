package org.switch2022.project.utils.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;
import org.switch2022.project.model.container.BusinessSectorContainer;
import org.switch2022.project.model.container.CustomerContainer;
import org.switch2022.project.model.container.ProjectTypologyContainer;
import org.switch2022.project.utils.dto.ProjectDtoAsManager;

public final class ProjectMapper {

    private ProjectMapper() {}

    public static Project getProjectFromDTO(ProjectDtoAsManager projectDTOAsManager) {

        Customer customer = new Customer(projectDTOAsManager.customerName, projectDTOAsManager.customerNif);
        ProjectTypology projectTypology = new ProjectTypology(projectDTOAsManager.projectTypology);
        BusinessSector businessSector = new BusinessSector(projectDTOAsManager.businessSector);
        return new Project(projectDTOAsManager.code, projectDTOAsManager.name,
                customer, projectTypology, businessSector);
    }
}

