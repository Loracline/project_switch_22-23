package org.switch2022.project.ddd.infrastructure;

import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Class ProjectRepository is built to access and manipulate the set of projects
 * of this company.
 */
public class ProjectRepository implements IProjectRepository {
    /**
     * Attributes
     */
    private final List<Project> projects = new ArrayList<>();

    /**
     * This method verify the existence of a project by code confirmation.
     *
     * @return the project with given code.
     */
    public Optional<Project> getProjectByCode(Code code) {
        Project projectRequested = null;
        int i = 0;
        while (i < this.projects.size() && projectRequested == null) {
            if (projects.get(i) != null && projects.get(i).hasProjectCode(code)) {
                projectRequested = projects.get(i);
            }
            i++;
        }
        return Optional.ofNullable(projectRequested);
    }

    /**
     * This method returns the number of projects contained in the list
     *
     * @return int equivalent to the number of elements in the list
     */
    public int getProjectNumber() {
        return projects.size();
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