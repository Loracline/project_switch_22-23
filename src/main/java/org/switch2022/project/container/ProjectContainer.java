package org.switch2022.project.container;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.*;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
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
    private AccountInProjectContainer accountInProjectContainer;

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

  /**
   * This method verify the existence of a project by projectCode confirmation
   *
   * @return an object Project if Project exist.
   * @return null if Project does not exist.
   */
  private Project getProjectByCode(String projectCode) {
    Project requestedProject = null;
    for (int i = 0; i < this.projects.size(); i++) {
      if (projects.get(i).getProjectCode().equalsIgnoreCase(projectCode)) {
        requestedProject = projects.get(i);
        break;
      }
    }
    return requestedProject;
  }
  /**
   * This method returns a list of Accounts Allocated To a Project
   *
   * @return a list of Accounts if Project with projectCode exist.
   * @return NoSuchElementException if Project with projectCode does not exist.
   */
  public List<Account> listAccountsByProject(String projectCode) {
    Project project = getProjectByCode(projectCode);
    if (project == null){
      throw new NoSuchElementException("Unable to locate element " + projectCode);
    }
    return accountInProjectContainer.listAccountsByProject(project);
  }
}
