package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

/**
 * Class ChangeStatusController is built to allow access to the AccountContainer
 * in Company Class.
 **/
public class ChangeStatusController {
  /**
   * Attributes of the class ChangeStatusController, according to the Class Diagram.
   */
  private Company company;

  /**
   * ChangeStatusController constructor
   */
  public ChangeStatusController(Company company) {
    this.company = company;
  }

  /**
   * Method changeStatus used to change the existing account status.
   *
   * @param email
   * @param status
   */
  public boolean changeStatus(String email, boolean status) {
    return (company.changeStatus(email, status));
  }
}