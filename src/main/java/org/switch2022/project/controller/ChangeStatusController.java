package org.switch2022.project.controller;

import org.switch2022.project.model.Company;

public class ChangeStatusController {
  /**
   * Attributes of the class ChangeStatusController, needed to implement the methods
   * used in the constructors Account.
   */
  private Company accountList;

  public ChangeStatusController(Company accountList) {
    this.accountList = accountList;
  }

  /**
   * Method changeStatus used to change the existing account status.
   * @param email
   * @param status
   * @return the new account status
   */
  public boolean changeStatus(String email, boolean status) {
    boolean finalStatus = false;
    accountList.getAccountContainer().getAccountByEmail(email).setStatus(status);
    if (status) {
      finalStatus = true;
    } else {
      finalStatus = false;
    }
    return finalStatus;
  }
}