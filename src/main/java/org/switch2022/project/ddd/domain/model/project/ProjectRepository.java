package org.switch2022.project.ddd.domain.model.project;

import java.util.ArrayList;
import java.util.List;


/**
 * Class ProjectRepository is built to access and manipulate the set of projects
 * of this company.
 */
public class ProjectRepository {
    /**
     * Attributes
     */
    private final List<Project> projects = new ArrayList<>();

    /**
     * Getter method for the attribute: projects.
     *
     * @return a list of all the projects in the container.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * This method adds a Project to Project Container if the Project does not exist.
     *
     * @param project to be added.
     * @return TRUE if the Project was added to Project Repository and false otherwise.
     */
    public boolean addProjectToProjectRepository(Project project) {
        boolean projectRegistered = false;
        if (!doesProjectExist(project)) {
            projects.add(project);
            projectRegistered = true;
        }
        return projectRegistered;
    }

    /**
     * This method verifies if the project already exists in the container through the
     * project code.
     *
     * @param project existence that shall be verified.
     * @return TRUE if exists, and FALSE otherwise.
     */
    private boolean doesProjectExist(Project project) {
        return projects.contains(project);
    }

}
