package org.switch2022.project.model;

import java.util.Objects;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by a code, a name, a customer, a status , a project typology and
 * a business sector.
 */

public class Project {

  /**
   * Attributes of the class Project, according to the Class Diagram.
   */

  public String code;
  public String name;
  public Customer customer;
  public String status;
  public ProjectTypology projectTypology;
  public BusinessSector businessSector;

  /**
   * Constructor of the class Project.
   * New instance is created using as parameter the essential attributes.
   *
   * @param code            of the new project
   * @param name            of the new project
   * @param customer       of the new project
   * @param projectTypology of the new project
   * @param businessSector  of the new project
   */
  public Project(String code, String name, Customer customer, ProjectTypology projectTypology,
                 BusinessSector businessSector) {
    this.code = code;
    this.name = name;
    this.customer = customer;
    this.status = "planned";
    this.projectTypology = projectTypology;
    this.businessSector = businessSector;
  }

  /**
   * This method sets a project typology
   *
   * @param projectTypology
   * @return project typology
   */

  public void setProjectTypology(ProjectTypology projectTypology) {
    this.projectTypology = projectTypology;
  }

  /**
   * This method sets a business sector
   *
   * @param businessSector
   * @return business sector
   */

  public void setBusinessSector(BusinessSector businessSector) {
    this.businessSector = businessSector;
  }

  /**
   * This method sets a customer
   *
   * @param customer
   * @return customer
   */

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Project)) return false;
    Project project = (Project) o;
    return Objects.equals(code, project.code) && Objects.equals(name, project.name) && Objects.equals(customer, project.customer) && Objects.equals(status, project.status) && Objects.equals(projectTypology, project.projectTypology) && Objects.equals(businessSector, project.businessSector);
  }
}
