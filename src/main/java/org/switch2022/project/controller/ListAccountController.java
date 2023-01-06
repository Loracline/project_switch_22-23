package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;

import java.util.List;
/**
 * Class AccountListController is built to allow access to the AccountContainer and ProfileContainer
 * in Company Class.
 **/
public class ListAccountController {
  /**
   * Attributes of the class AccountListController, according to the Class Diagram.
   */
  private Company company;
  /**
   * AccountListController constructor
   */
  public ListAccountController(Company company) {
    this.company = company;
  }
  /**
   * This method returns list of accounts
   *
   * @return list of accounts
   */
  public List<Account> listAllAccounts() {
    return this.company.getAccountContainer().getAccounts();
  }
}