package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.value_object.*;

public class FactoryProject implements IFactoryProject {
    /**
     * This method creates a new Project.
     *
     * @param projectNumber     the unique number to create projectCode.
     * @param projectName       the name of the project.
     * @param description       the description of the project.
     * @param customerTaxId     the identifier of the customer.
     * @param businessSectorId  the identifier of the businessSector.
     * @param projectTypologyId the identifier of the projectTypology.
     * @param productBacklog    the Product Backlog of the project.
     * @return the newly created Project object.
     */
    @Override
    public Project createProject(final Number projectNumber, Name projectName, Description description,
                                 BusinessSectorId businessSectorId, TaxId customerTaxId,
                                 ProjectTypologyId projectTypologyId, ProductBacklog productBacklog) {
        return new Project(projectNumber, projectName, description, businessSectorId, customerTaxId,
                projectTypologyId, productBacklog);
    }
}
