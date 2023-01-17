package org.switch2022.project.container;

import org.switch2022.project.model.Account;

import java.util.List;
import java.util.ArrayList;
/**
 * Class AccountContainer is built to allow access to class Account.
 */
public class AccountContainer {
  /**
   * AccountContainer contains accounts
   */
  private List<Account> accounts;

  public AccountContainer(List<Account> accounts) {
    this.accounts = accounts;
  }


  public List<Account> getAccounts() {
    List<Account> result = new ArrayList<>(List.copyOf(accounts));
    return result;
  }

  /**
   * This method verify the existence of an account by email confirmation
   *
   * @return an object Account
   */
  public Account getAccountByEmail(String email) {
    Account requestedAccount = null;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).getEmail().equalsIgnoreCase(email)) {
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
   * @param email that one must find out if exists
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
   * Add account method to save registered accounts in the attribute accounts.
   * It adds an account if the email doesn't exist in accounts.
   *
   * @param account instance of Account to be added to accounts
   */
  public boolean addAccount(Account account) {
    boolean isAccountAdded = false;
    String email = account.getEmail();
    if (!doesEmailExist(email)) {
      this.accounts.add(account);
      isAccountAdded = true;
    }
    return isAccountAdded;
  }

  /**
   * Setter method for the attribute ACCOUNT STATUS.
   *
   * @param status TRUE = "ACTIVE" or FALSE = "INACTIVE"
   */
  public boolean changeStatus(String email, boolean status) {
    return (getAccountByEmail(email).setStatus(status));
  }
}
