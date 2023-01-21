package org.switch2022.project.model.container;

import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.ProjectDTO;
import org.switch2022.project.utils.mapper.ProjectMapper;

import java.util.List;

/**
 * Class ProjectContainer is built to access and manipulate the set of projects
 * of this company.
 */
public class ProjectContainer {
    /**
     * Attributes
     */
    private final List<Project> projects;

    /**
     * Constructor
     */
    public ProjectContainer(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Getter method for the attribute: projects.
     *
     * @return a list of all the projects in the container.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * This method creates a new project and adds it to the container if it
     * doesn't already exist.
     *
     * @param dto data transfer object of the attributes of project.
     * @return TRUE if registered and FALSE otherwise.
     */
    public boolean registerProject(ProjectDTO dto) {
        ProjectMapper projectMapper = new ProjectMapper();
        Project project = projectMapper.fromDTO(dto);
        boolean projectRegistered = false;
        if (!doesProjectExist(project)) {
            projects.add(project);
            projectRegistered = true;
        }
        return projectRegistered;
    }

    /**
     * This method verifies if the project already exists in the container.
     *
     * @param project one intend to search for.
     * @return TRUE if exists, and FALSE otherwise.
     */
    private boolean doesProjectExist(Project project) {
        boolean projectExistence = false;
        int i = 0;
        while (i < projects.size()) {
            if (projects.get(i).getProjectCode().equals(project.getProjectCode())) {
                projectExistence = true;
                break;
            }
            i++;
        }
        return projectExistence;
    }
}
