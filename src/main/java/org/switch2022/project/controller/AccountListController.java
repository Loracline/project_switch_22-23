package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;

import java.util.List;
/**
 * Class AccountListController is built to allow access to the AccountContainer
 * in Company Class.
 **/
public class AccountListController {
  /**
   * Attributes of the class AccountListController, according to the Class Diagram.
   */
  private Company company;
  /**
   * AccountListController constructor
   */
  public AccountListController(Company company) {
    this.company = company;
  }
  /**
   * @return list of accounts
   */
  public List<Account> listAllAccounts() {
    return this.company.getAccountContainer().getAccounts();
  }
}