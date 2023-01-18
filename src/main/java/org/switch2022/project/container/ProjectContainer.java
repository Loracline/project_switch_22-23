package org.switch2022.project.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

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
}
