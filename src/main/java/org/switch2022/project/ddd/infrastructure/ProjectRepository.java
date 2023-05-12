package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.exceptions.NotFoundInRepoException;

import java.util.*;

/**
 * Class ProjectRepository is built to access and manipulate the set of projects
 * of this company.
 */
@Component
public class ProjectRepository implements IProjectRepository {

    /**
     * Attributes
     */
    private final List<Project> projects = new ArrayList<>();

    /**
     * This method checks if an instance of ProjectRepository is equal to another object.
     *
     * @param o object to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ProjectRepository that = (ProjectRepository) o;
        return Objects.equals(projects, that.projects);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the
     * object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projects);
    }

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
     * This method adds a Project to Project repository if the Project does not exist.
     *
     * @param project to be added.
     * @return TRUE if the Project was added to Project Repository and false otherwise.
     */
    public boolean addProjectToProjectRepository(Project project) {
        boolean projectRegistered = false;
        if (!projects.contains(project)) {
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
    public boolean doesProjectExist(Project project) {
        if (projects.contains(project)) {
            return true;
        } else throw new NotFoundInRepoException("The requested project does not exist.");
    }

    /**
     * Checks if the given project is not closed.
     *
     * @param project the project to check
     * @return true if the project is not closed, false otherwise
     * @throws InvalidInputException if the project has already been closed
     */
    public boolean isNotClosed(Project project) {
        if (!project.hasStatus(ProjectStatus.CLOSED)) {
            return true;
        } else throw new InvalidInputException("The requested project has already been closed.");
    }

    /**
     * This method returns an unmodifiable view of the projects.
     *
     * @return an unmodifiable view of the projects.
     */
    public List<Project> findAll() {
        return Collections.unmodifiableList(projects);
    }
}
