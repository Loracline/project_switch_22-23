package org.switch2022.project.DTO;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;

public class ProjectDTO {
  /**
   * Attributes of the class ProjectDTO, according to the Class Diagram.
   */

  private String code;
  private String name;
  private Customer customer;
  private String status;
  private ProjectTypology projectTypology;
  private BusinessSector businessSector;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
