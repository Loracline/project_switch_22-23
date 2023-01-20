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

  private String code;
  private String name;
  private Customer customer;
  private String status;
  private ProjectTypology projectTypology;
  private BusinessSector businessSector;


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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Project)) return false;
    Project project = (Project) o;
    return Objects.equals(code, project.code) && Objects.equals(name, project.name) && Objects.equals(customer, project.customer) && Objects.equals(status, project.status) && Objects.equals(projectTypology, project.projectTypology) && Objects.equals(businessSector, project.businessSector);
  }

  public String getProjectCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public String getStatus() {
    return this.status;
  }

  public ProjectTypology getProjectTypology() {
    return this.projectTypology;
  }

  public BusinessSector getBusinessSector() {
    return this.businessSector;
  }

}