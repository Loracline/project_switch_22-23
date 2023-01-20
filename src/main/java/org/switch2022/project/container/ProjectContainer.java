package org.switch2022.project.container;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class ProjectContainer is built to allow access to class Project.
 */

public class ProjectContainer {
    /**
     * ProjectContainer contains projects
     */
    private List<Project> projects;

    public ProjectContainer(List<Project> projects) {
        this.projects = projects;
    }
  /**
   * This method returns list of projects
   *
   * @return list
   */
  public List<Project> getProjectsList() {
    return projects;
  }

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

  private boolean doesProjectExist(Project project) {
    boolean projectExistence = false;
    for (int i = 0; i < projects.size(); i++) {
      if (projects.get(i).getProjectCode().equals(project.getProjectCode())){
        projectExistence = true;
        break;
      }
    }
    return projectExistence;
  }
}
