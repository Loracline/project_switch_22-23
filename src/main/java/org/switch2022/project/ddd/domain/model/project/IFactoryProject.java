package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.BusinessSectorId;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.CustomerId;
import org.switch2022.project.ddd.domain.value_object.ProjectTypologyId;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

public interface IFactoryProject {
    /**
     * This method creates a new Project based on the provided ProjectCreationDto.
     *
     * @param projectCode           the unique code created by the system.
     * @param projectCreationDto    the DTO object containing the necessary information to create a Project.
     * @param businessSectorId      the ID object of the business sector.
     * @param customerId            the ID object of the customer.
     * @param projectTypologyId     the ID object of the project typology.
     * @param factoryProductBacklog the factory product backlog object used to create the product backlog for the
     *                              project.
     * @return the newly created Project object.
     */
    Project createProject(Code projectCode, ProjectCreationDto projectCreationDto, BusinessSectorId businessSectorId,
                          CustomerId customerId, ProjectTypologyId projectTypologyId,
                          IFactoryProductBacklog factoryProductBacklog);
}

