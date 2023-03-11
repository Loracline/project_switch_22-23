package org.switch2022.project.factories;

import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectTypology;

/**
 * Implementation of the FactoryProject interface that creates instances of the Project class
 */

public class FactoryProject implements IFactoryProject {
    /**
     * This method creates a new Project object with the specified code, name, customer, project typology and
     * business sector
     *
     * @param projectCreationDto     with all the attributes needed for the construction of a project
     * @param customer
     * @param projectTypology
     * @param businessSector
     * @param factoryProductBacklog
     * @param factoryUserStory
     * @return
     */

    public Project createProject(ProjectCreationDto projectCreationDto, Customer customer,
                                 ProjectTypology projectTypology, BusinessSector businessSector, IFactoryProductBacklog factoryProductBacklog,
                                 IFactoryUserStory factoryUserStory) {
        return new Project(projectCreationDto.code, projectCreationDto.name, customer, projectTypology, businessSector,factoryProductBacklog,
                factoryUserStory);
    }

}



