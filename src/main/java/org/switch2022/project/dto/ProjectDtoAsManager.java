package org.switch2022.project.dto;

import java.util.Objects;

public class ProjectDtoAsManager {

  /**
   * Attributes of the class ProjectDtoAsManager, according to the Class Diagram.
   */

  public final String code;
  public final String name;
  public final String customerName;
  public final String customerNif;
  public final String status;
  public final String projectTypology;
  public final String businessSector;


  /**
   * Constructor of the class ProjectDtoAsManager.
   */

  public ProjectDtoAsManager(String code, String name, String customerName, String customerNif,
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
  public int hashCode() {
    return Objects.hash(code, name, customerName, customerNif, status,
            projectTypology, businessSector);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (o.getClass() != this.getClass()) {
      return false;
    }
    ProjectDtoAsManager that = (ProjectDtoAsManager) o;
    return Objects.equals(code, that.code) && Objects.equals(name, that.name)
            && Objects.equals(customerName, that.customerName) &&
            Objects.equals(customerNif, that.customerNif) &&
            Objects.equals(projectTypology, that.projectTypology) &&
            Objects.equals(businessSector, that.businessSector);
  }
}
