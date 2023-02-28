package org.switch2022.project.dto.mapper;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

import org.switch2022.project.dto.ProjectDtoAsManager;

public final class ProjectMapper {

    private ProjectMapper() {}

    public static Project getProjectFromDto(ProjectDtoAsManager projectDtoAsManager) {

        Customer customer = new Customer(projectDtoAsManager.customerName, projectDtoAsManager.customerNif);
        ProjectTypology projectTypology = new ProjectTypology(projectDtoAsManager.projectTypology);
        BusinessSector businessSector = new BusinessSector(projectDtoAsManager.businessSector);
        return new Project(projectDtoAsManager.code, projectDtoAsManager.name,
                customer, projectTypology, businessSector);
    }
}

