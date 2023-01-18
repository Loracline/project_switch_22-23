package org.switch2022.project.container;

import org.switch2022.project.model.ProjectTypology;

import java.util.List;

public class ProjectTypologyContainer {
  private List<ProjectTypology> typologies;

  public ProjectTypologyContainer(List<ProjectTypology> typologies) {
    this.typologies = typologies;
  }

  public List<ProjectTypology> getTypologies() {
    return typologies;
  }
}
