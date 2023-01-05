package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class AccountContainer {
  /**
   * AccountContainer contains a list of accounts
   */
  private List<Account> accounts;

  /**
   * @param accounts
   *
   * @return list of accounts
   */
  public AccountContainer(List<Account> accounts) {
    this.accounts = accounts;
  }

  public AccountContainer(){
    this.accounts = new ArrayList<Account>();
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountContainer)) return false;
    AccountContainer that = (AccountContainer) o;
    return Objects.equals(accounts, that.accounts);
  }

  public List<Account> getAccounts(){
    return this.accounts;
  }

  public Account getAccountByEmail(String email) {
    String accountEmail = email;
    Account requestedAccount = null;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).getEmail().equals(accountEmail)) {
        requestedAccount = accounts.get(i);
        break;
      }
    }
    return requestedAccount;
  }

  /**
   * This method checks if there is any account in the list that has the
   * e-mail passed by parameter.
   *
   * @param email that one must find out if exists in the Repository
   * @return TRUE if there is an account with given e-mail and FALSE otherwise
   */

  public boolean doesEmailExist(String email) {
    boolean emailExists = false;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).getEmail().equals(email)) {
        emailExists = true;
        break;
      }
    }
    return emailExists;
  }

  /**
   * Add account method to save registered accounts in the attribute ACCOUNTS LIST.
   * It adds an account if the email doesn't exist in the accounts list.
   *
   * @param acc instance of Account to be added to accountList
   */
  public boolean addAccount(Account acc) {
    boolean isAccountAdded = false;
    String email = acc.getEmail();
    if (!doesEmailExist(email)) {
      this.accounts.add(acc);
      isAccountAdded = true;
    }
    return isAccountAdded;
  }
}
