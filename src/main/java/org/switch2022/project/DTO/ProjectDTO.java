package org.switch2022.project.DTO;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;

public class ProjectDTO {
  /**
   * Attributes of the class ProjectDTO, according to the Class Diagram.
   */

  public String code;
  public String name;
  public Customer customer;
  public String status;
  public ProjectTypology projectTypology;
  public BusinessSector businessSector;

  public ProjectDTO(String code, String name, Customer customer, ProjectTypology projectTypology,
                    BusinessSector businessSector) {
    this.code = code;
    this.name = name;
    this.customer = customer;
    this.status = "planned";
    this.projectTypology = projectTypology;
    this.businessSector = businessSector;
  }
}
