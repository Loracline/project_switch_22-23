package org.switch2022.project.factories;

import org.switch2022.project.model.ProjectTypology;

/**
 * Class IFactoryProjectTypology implements FactoryProjectTypology ir order to create
 * an object of ProjectTypology.
 */

public class FactoryProjectTypology implements IFactoryProjectTypology {

    /**
     * This method creates a projectTypology object.
     *
     * @param projectTypologyName is an attribute of projectTypology.
     * @return a new object ProjectTypology.
     */

    @Override
    public ProjectTypology createProjectTypology(String projectTypologyName) {
        return new ProjectTypology(projectTypologyName);
    }
}
