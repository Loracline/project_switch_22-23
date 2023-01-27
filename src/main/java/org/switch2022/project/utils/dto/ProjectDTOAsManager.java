package org.switch2022.project.utils.dto;

import java.util.Objects;

public class ProjectDTOAsManager {

  /**
   * Attributes of the class ProjectDTO, according to the Class Diagram.
   */

  public final String code;
  public final String name;
  public String customerName;
  public String customerNif;
  public final String status;
  public String projectTypology;
  public String businessSector;


  @Override
  public int hashCode() {
    return Objects.hash(code, name, customerName, customerNif, status, projectTypology, businessSector);
  }

  /**
   * Constructor of the class GetProjectsDTO.
   */

  public ProjectDTOAsManager(String code, String name, String customerName, String customerNif,
                             String projectTypology, String businessSector) {
    this.code = code;
    this.name = name;
    this.customerName = customerName.toLowerCase();
    this.customerNif = customerNif;
    this.status = "planned";
    this.projectTypology = projectTypology.toLowerCase();
    this.businessSector = businessSector.toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ProjectDTOAsManager)) return false;
    ProjectDTOAsManager that = (ProjectDTOAsManager) o;
    return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(customerName, that.customerName)
            && Objects.equals(customerNif, that.customerNif)
            && Objects.equals(status, that.status) && Objects.equals(projectTypology, that.projectTypology) &&
            Objects.equals(businessSector, that.businessSector);
  }
}
