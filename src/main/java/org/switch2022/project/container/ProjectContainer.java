package org.switch2022.project.container;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.*;

import java.util.List;

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

  public boolean registerProject(ProjectDTO dto) {
    ProjectMapper projectMapper = new ProjectMapper();
    Project project = projectMapper.fromDTO(dto);
    boolean projectRegistered = false;
      if (doesProjectExist(project)){
        projects.add(project);
      }
    return projectRegistered;
  }

  private boolean doesProjectExist(Project project) {
    boolean projectExistance = false;
    if(projects.contains(project)){
      projectExistance = true;
    }
    return projectExistance;
  }
}
