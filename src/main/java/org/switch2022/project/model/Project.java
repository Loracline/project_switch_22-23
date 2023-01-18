package org.switch2022.project.model;

import org.switch2022.project.container.ProjectTypologyContainer;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by a code, a name, a customer, a status , an startDate and
 * an endDate.
 */

public class Project {

  /**
   * Attributes of the class Project, according to the Class Diagram.
   *
   * @param status is "planned"
   * @param startDate is null
   * @param endDate is null
   */

  public String code;
  public String name;
  public String customer;
  public String status;
  public String projectTypology;
  public String businessSector;
  public String customerSector;

  /**
   * Constructor of the class Project.
   * New instance is created using as parameter the essential attributes.
   *
   * @param code            of the new project
   * @param name            of the new project
   * @param customer        of the new project
   * @param projectTypology of the new project
   * @param businessSector  of the new project
   * @param customerSector  of the new project
   */
  public Project(String code, String name, String customer, String projectTypology,
                 String businessSector, String customerSector) {
    this.code = code;
    this.name = name;
    this.customer = customer;
    this.status = "planned";
    this.projectTypology = projectTypology;
    this.businessSector = businessSector;
    this.customerSector = customerSector;
  }

  public String getProjectTypology(ProjectTypologyContainer typologies, int index){
    return this.projectTypology = typologies.getTypologies().get(index).toString();
  }

  /*public ProjectTypology getBusinessSector(ProjectTypologyContainer typologies, int index){
    return typologies.getTypologies().get(index);
  }

  public ProjectTypology getCustomerSector(ProjectTypologyContainer typologies, int index){
    return typologies.getTypologies().get(index);
  }*/
}
