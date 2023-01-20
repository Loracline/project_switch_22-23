package org.switch2022.project.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.Project;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class AccountContainer is built to allow access to class Account.
 */
public class AccountContainer {
  /**
   * AccountContainer contains accounts
   */
  private List<Account> accounts;
  private AccountInProjectContainer accountInProjectContainer;
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
   * Add account method to register and save accounts in the attribute accounts.
   * It adds an account if the email doesn't exist in accounts.
   *
   * @param name        of the new account
   * @param email       of the new account
   * @param phoneNumber of the new account
   * @param photo       of the new account
   * @return true if Account is added
   * @return false if Account is not added
   */
  public boolean addAccount(String name, String email, long phoneNumber,
                            BufferedImage photo) {
    boolean isAccountAdded = false;
    Account account = new Account(name, email, phoneNumber, photo);
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
  public boolean validateManager(String email){
    boolean isManager=false;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).checkAccountFromEmail(email)){
        Account account= accounts.get(i);
        if (account.IsManager()){
          isManager=true;
        }
        }
      }
    return isManager;
  }
  public boolean validateAdministrator(String email){
    boolean isAdministrator=false;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).checkAccountFromEmail(email)){
        Account account= accounts.get(i);
        if (account.IsAdministrator()){
          isAdministrator=true;
        }
      }
    }
    return isAdministrator;
  }
  public boolean validateUser(String email){
    boolean isUser=false;
    for (int i = 0; i < this.accounts.size(); i++) {
      if (accounts.get(i).checkAccountFromEmail(email)){
        Account account= accounts.get(i);
        if (account.IsUser()){
          isUser=true;

        }
      }
      break;
    }
    return isUser;
  }

  public List<Project> listProjectsByAccount(String email) {
    Account account = getAccountByEmail(email);
    return accountInProjectContainer.listProjectsByAccount(account);
  }


}
