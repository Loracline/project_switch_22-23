package org.switch2022.project.factories;

import org.switch2022.project.model.ProjectTypology;

/**
 * Factory of ProjectTypology class.
 */

public interface FactoryProjectTypology {

  /**
   * This method creates a ProjectTypology object with no return.
   *
   * @param projectTypologyName
   */

  public ProjectTypology createProjectTypology(String projectTypologyName);
}
