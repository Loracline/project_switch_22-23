package org.switch2022.project.container;

import org.switch2022.project.dto.ProjectDtoAsManager;
import org.switch2022.project.dto.mapper.ProjectMapper;
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
  public Project getProjectByCode(String code) {
    Project projectRequested = null;
    int i = 0;
    while (i < this.projects.size() && (projectRequested != (projects.get(i)))) {
      if (projects.get(i).getProjectCode().equalsIgnoreCase(code)) {
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
   * @param projectDtoAsManager data transfer object of the attributes of project.
   * @return TRUE if registered and FALSE otherwise.
   */
  public boolean registerProject(ProjectDtoAsManager projectDtoAsManager, ProjectTypologyContainer
          projectTypologyContainer, CustomerContainer customerContainer, BusinessSectorContainer
                                         businessSectorContainer) {
    Project project = ProjectMapper.getProjectFromDto(projectDtoAsManager, projectTypologyContainer,
            customerContainer, businessSectorContainer);
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
    boolean projectExistence = false;
    int i = 0;
    while (i < projects.size() && !projectExistence) {
      if (projects.get(i).getProjectCode().equals(project.getProjectCode())) {
        projectExistence = true;
      }
      i++;
    }
    return projectExistence;
  }
}