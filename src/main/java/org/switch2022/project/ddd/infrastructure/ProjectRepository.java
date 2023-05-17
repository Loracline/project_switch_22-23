package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;
import org.switch2022.project.ddd.domain.value_object.Period;
import org.switch2022.project.ddd.domain.value_object.ProjectStatus;

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
     * This method verifies the existence of a project by code confirmation.
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
     * @return TRUE if the Project was added to the Project Repository and false otherwise.
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
     * Checks if a project with the specified project code exists.
     *
     * @param projectCode The project code to search for.
     * @return {@code TRUE} if a project with the given project code exists, {@code FALSE} otherwise.
     */
    private boolean doesProjectExist(Code projectCode) {
        boolean exists = false;
        for (int i = 0; i < this.projects.size(); i++) {
            if (this.projects.get(i).hasProjectCode(projectCode)) {
                exists = true;
            }
        }
        return exists;
    }

    /**
     * Checks if a project with the specified project code is in the specified project status.
     *
     * @param projectStatus The project status to check against.
     * @return {@code TRUE} if a project with the given project code exists and has the specified project status,
     * {@code FALSE} otherwise.
     */
    private boolean isProjectInStatus(ProjectStatus projectStatus) {
        boolean statusOfInterest = false;
        for (int i = 0; i < this.projects.size(); i++) {
            if (this.projects.get(i).hasStatus(projectStatus)) {
                statusOfInterest = true;
            }
        }
        return statusOfInterest;
    }

    /**
     * Checks if any project within this collection contains the specified allocation period.
     *
     * @param allocationPeriod The allocation period to check for containment within the projects.
     * @return {@code TRUE} if any project within this collection contains the specified allocation period,
     * {@code FALSE} otherwise.
     */
    private boolean doesProjectContainsPeriod(Period allocationPeriod) {
        boolean contains = false;
        for (int i = 0; i < this.projects.size(); i++) {
            if (this.projects.get(i).contains(allocationPeriod)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Checks if a project with the specified project code is valid for allocation within the specified period.
     *
     * @param projectCode      The project code to check for validity.
     * @param allocationPeriod The allocation period to check against.
     * @return {@code TRUE} if the project with the given project code exists,
     * is not in the "PLANNED" or "CLOSED" status, and contains the specified allocation period;
     * {@code FALSE} otherwise.
     */
    public boolean isProjectValidForAllocation(Code projectCode, Period allocationPeriod) {
        return doesProjectExist(projectCode) &&
                !isProjectInStatus(ProjectStatus.PLANNED) &&
                !isProjectInStatus(ProjectStatus.CLOSED) &&
                doesProjectContainsPeriod(allocationPeriod);
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
