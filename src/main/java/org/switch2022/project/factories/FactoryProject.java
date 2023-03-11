package org.switch2022.project.factories;

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
     * @param projectCode
     * @param name
     * @param customer
     * @param projectTypology
    * @param businessSector
     * @return
     */

    public Project createProject (String projectCode, String name, Customer customer,
                           ProjectTypology projectTypology, BusinessSector businessSector) {
        return new Project(projectCode, name, customer, projectTypology, businessSector);
    }

}



