package org.switch2022.project;

public class ChangeStatusController {
  /**
   * Attributes of the class ChangeStatusController, needed to implement the methods
   * used in the constructors Account.
   */
  private Account account;

  public ChangeStatusController(Account account) {
    this.account = account;
  }

  /**
   * Method changeStatus used to change the existing account status.
   * @param status
   * @return the new account status
   */
  public void changeStatus(boolean status) {
    if (account.accountStatus() != status) {
      account.updateAccountStatus(status);
    } else {
      account.accountStatus();
    }
  }
}
