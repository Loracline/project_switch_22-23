package org.switch2022.project.container;

import org.switch2022.project.dto.ProjectCreationDto;
import org.switch2022.project.dto.ProjectDto;
import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.mapper.ProjectCreationMapper;
import org.switch2022.project.factories.IFactoryProductBacklog;
import org.switch2022.project.factories.IFactoryProject;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ProjectContainer is built to access and manipulate the set of projects
 * of this company.
 */
public class ProjectContainer {
    /**
     * Attributes
     */
    private final List<Project> projects = new ArrayList<>();



    /**
     * This method verify the existence of a project by code confirmation.
     *
     * @return the project with given code.
     */
    private Project getProjectByCode(String code) {
        Project projectRequested = null;
        int i = 0;
        while (i < this.projects.size() && (projectRequested != (projects.get(i)))) {
            if (projects.get(i).hasProjectCode(code)) {
                projectRequested = projects.get(i);
            }
            i++;
        }
        return projectRequested;
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
     * @param projectCreationDto data transfer object of the attributes of project.
     * @return TRUE if registered and FALSE otherwise.
     */
    public boolean registerProject(ProjectCreationDto projectCreationDto, ProjectTypologyContainer
            projectTypologyContainer, CustomerContainer customerContainer, BusinessSectorContainer
                                           businessSectorContainer, IFactoryProductBacklog factoryProductBacklog,
    IFactoryUserStory factoryUserStory, IFactoryProject factoryProject) {
        Project project = ProjectCreationMapper.getProjectFromDto(projectCreationDto, projectTypologyContainer,
                customerContainer, businessSectorContainer,  factoryProductBacklog,
                 factoryUserStory,factoryProject);
        boolean projectRegistered = false;
        if (!doesProjectExist(project)) {
            projects.add(project);
            projectRegistered = true;
        }
        return projectRegistered;
    }

    /**
     * This method verifies if the project already exists in the container through the project code.
     *
     * @param project existence that shall be verified.
     * @return TRUE if exists, and FALSE otherwise.
     */
    private boolean doesProjectExist(Project project) {
        return projects.contains(project);
    }

    /**
     * This method creates an userStory in the requested project
     * returns true if the userStory is successfully created
     */
    public boolean createUserStory(ProjectDto projectDto, UserStoryCreationDto userStoryCreationDto) {
        Project project = getProjectByCode(projectDto.code);
         return project != null && project.createUserStory(userStoryCreationDto);
    }
}