package org.switch2022.project.container;

import org.switch2022.project.model.ProjectTypology;

import java.util.List;

public class ProjectTypologyContainer {

  private List<ProjectTypology> typologies;

  public ProjectTypologyContainer(List<ProjectTypology> typologies) {
    this.typologies = typologies;
  }

  public boolean doesTypologyExist(ProjectTypology projectTypology) {
    return this.typologies.contains(projectTypology);
  }

  public boolean createProjectTypology(String projectTypology) {
    ProjectTypology newprojectTypology = new ProjectTypology(projectTypology);
    boolean isAddedToList = false;
    if (!doesTypologyExist(newprojectTypology)) {
      typologies.add(newprojectTypology);
      isAddedToList = true;
    }
    return isAddedToList;
  }

  /**
   * This method returns a list of project typologies
   *
   * @return list
   */

  public List<ProjectTypology> getTypologies() {
    return typologies;
  }

  /**
   * This method returns a project typology from the list of project typologies
   *
   * @param typology
   * @return project typology
   */

  public ProjectTypology getProjectTypology(String typology) {
    ProjectTypology requestedTypology = null;
    for (int i = 0; i < typologies.size(); i++) {
      if (typologies.get(i).equals(typology)) {
        requestedTypology = typologies.get(i);
        break;
      }
    }
    return requestedTypology;
  }
}