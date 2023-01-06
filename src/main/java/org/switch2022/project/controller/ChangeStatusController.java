package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

import java.util.Objects;

public class ChangeStatusController {
  /**
   * Attributes of the class ChangeStatusController, needed to implement the methods
   * used in the constructors Account.
   */
  private Company company;

  public ChangeStatusController(Company company) {
    this.company = company;
  }

  /**
   * Method changeStatus used to change the existing account status.
   * @param email
   * @param status
   * @return the new account status
   */
  public void changeStatus(String email, boolean status) {
    company.getAccountContainer().getAccountByEmail(email).setStatus(status);
  }

}