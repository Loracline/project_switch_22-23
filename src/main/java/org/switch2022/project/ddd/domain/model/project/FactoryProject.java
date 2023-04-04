package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.dto.ProjectCreationDto;

public class FactoryProject implements IFactoryProject {
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
    @Override
    public Project createProject(Code projectCode, ProjectCreationDto projectCreationDto,
                                 BusinessSectorId businessSectorId, CustomerId customerId,
                                 ProjectTypologyId projectTypologyId, IFactoryProductBacklog factoryProductBacklog) {

        Project project = new Project(projectCode);

        project.setName(projectCreationDto.projectName);
        project.setDescription(projectCreationDto.projectDescription);
        project.setSprintDuration(projectCreationDto.sprintDuration);

        /*
          The methods for mapping string names to their corresponding IDs would typically be called in the controller
          or service layer of the application, where is handled the creation of the project.
         */
        project.setBusinessSector(businessSectorId);
        project.setCustomer(customerId);
        project.setTypology(projectTypologyId);

        project.setProductBacklog(factoryProductBacklog);

        return project;
    }
}
