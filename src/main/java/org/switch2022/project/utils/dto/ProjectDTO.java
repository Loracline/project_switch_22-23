package org.switch2022.project.utils.dto;

import org.switch2022.project.model.BusinessSector;
import org.switch2022.project.model.Customer;
import org.switch2022.project.model.ProjectTypology;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProjectDTO)) return false;
    ProjectDTO that = (ProjectDTO) o;
    return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(customer, that.customer) && Objects.equals(status, that.status) && Objects.equals(projectTypology, that.projectTypology) && Objects.equals(businessSector, that.businessSector);
  }

}
