package org.switch2022.project.utils.dto;

import java.util.Objects;

public class ProjectDTO {

  /**
   * Attributes of the class ProjectDTO, according to the Class Diagram.
   */

  public String code;
  public String name;
  public String customer;
  public String status;
  public String projectTypology;
  public String businessSector;

  @Override
  public int hashCode() {
    return Objects.hash(code, name, customer, status, projectTypology, businessSector);
  }

  /**
   * Constructor of the class GetProjectsDTO.
   */

  public ProjectDTO(String code, String name, String customer,
                    String projectTypology, String businessSector) {
    this.code = code;
    this.name = name;
    this.customer = customer.toLowerCase();
    this.status = "planned";
    this.projectTypology = projectTypology.toLowerCase();
    this.businessSector = businessSector.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProjectDTO)) return false;
    ProjectDTO that = (ProjectDTO) o;
    return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(customer, that.customer) && Objects.equals(status, that.status) && Objects.equals(projectTypology, that.projectTypology) && Objects.equals(businessSector, that.businessSector);
  }
}
