package org.switch2022.project.ddd.infrastructure;

import org.springframework.stereotype.Component;
import org.switch2022.project.ddd.domain.model.project.IProjectRepository;
import org.switch2022.project.ddd.domain.model.project.Project;
import org.switch2022.project.ddd.domain.value_object.Code;

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
    public Optional<Project> findByCode(Code code) {
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
    public int count() {
        return projects.size();
    }

    /**
     * This method adds a Project to Project repository if the Project does not exist.
     *
     * @param project to be added.
     * @return TRUE if the Project was added to the Project Repository and false otherwise.
     */
    public boolean save(Project project) {
        boolean projectRegistered = false;
        if (!projects.contains(project)) {
            projects.add(project);
            projectRegistered = true;
        }
        return projectRegistered;
    }

    /**
     * This method returns an unmodifiable view of the projects.
     *
     * @return an unmodifiable view of the projects.
     */
    public List<Project> findAll() {
        return Collections.unmodifiableList(projects);
    }

    /**
     * This method retrieves a list of all projects whose project code is contained in a list of project codes.
     *
     * @param projectCodes the list of String representations of project codes to search the corresponding projects for.
     * @return a list of projects whose project codes were found in the list passed as argument, and en empty list if
     * no project with the given codes were found.
     */
    public List<Project> findAllByProjectCodes(List<Code> projectCodes){
        List<Project> projects = new ArrayList<>();

        for (int i = 0; i < this.projects.size(); i++) {
            for (int j = 0; j < projectCodes.size(); j++) {
                if(this.projects.get(i).hasProjectCode(projectCodes.get(j))){
                    projects.add(this.projects.get(i));
                }
            }
        }
        return Collections.unmodifiableList(projects);
    }
}
