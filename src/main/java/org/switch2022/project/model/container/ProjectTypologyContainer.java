package org.switch2022.project.model.container;

import org.switch2022.project.model.ProjectTypology;

import java.util.List;

/**
 * Class ProjectTypology is built to access and manipulate a set of typologies
 * of projects of this company.
 */
public class ProjectTypologyContainer {
    /**
     * Attributes
     */
    private final List<ProjectTypology> typologies;

    /**
     * Constructor
     */
    public ProjectTypologyContainer(List<ProjectTypology> typologies) {
        this.typologies = typologies;
    }

    /**
     * This method checks if project typology already exists in the container.
     *
     * @param projectTypology one intend to search.
     * @return TRUE if exists and FALSE otherwise.
     */
    public boolean doesTypologyExist(ProjectTypology projectTypology) {
        return this.typologies.contains(projectTypology);
    }

    /**
     * This method creates and adds new typology if it doesn't already exist.
     *
     * @param projectTypology one intend to add.
     * @return TRUE is added to the Container, and FALSE otherwise.
     */
    public boolean createProjectTypology(String projectTypology) {
        ProjectTypology newProjectTypology = new ProjectTypology(projectTypology);
        boolean isAddedToList = false;
        if (!doesTypologyExist(newProjectTypology)) {
            typologies.add(newProjectTypology);
            isAddedToList = true;
        }
        return isAddedToList;
    }
}