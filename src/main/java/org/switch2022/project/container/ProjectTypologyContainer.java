package org.switch2022.project.container;

import org.switch2022.project.model.ProjectTypology;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ProjectTypology is built to access and manipulate a set of typologies
 * of projects of this company.
 */
public class ProjectTypologyContainer {
    /**
     * Attributes
     */
    private final List<ProjectTypology> typologies = new ArrayList<>();

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
        if (!projectTypology.isEmpty() && !doesTypologyExist(newProjectTypology)) {
            typologies.add(newProjectTypology);
            isAddedToList = true;
        }
        return isAddedToList;
    }

    public ProjectTypology getProjectTypology(String typology) {
        ProjectTypology requestedProjectTypology = new ProjectTypology(typology);
        int i = 0;
        while (i < typologies.size()) {
            if (typologies.get(i).getProjectTypologyName().equals(typology)) {
                requestedProjectTypology = typologies.get(i);
            }
            i++;
        }
        return requestedProjectTypology;
    }
}