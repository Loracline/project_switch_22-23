package org.switch2022.project;

import java.awt.image.BufferedImage;

public class ChangeStatusController {
  /**
   * Attributes of the class ChangeStatusController, needed to implement the methods
   * used in the constructors Account.
   */
  private Account account;
  private Repository accountList;

  public ChangeStatusController(Account account, Repository accountList) {
    this.account = account;
    this.accountList = accountList;
  }

  /**
   * Method changeStatus used to change the existing account status.
   *
   * @param status
   * @return the new account status
   */
  public void changeStatus(String email, boolean status) {
    accountList.getAccountsList().contains(email);
    if (this.accountList.doesEmailExist(email)) {
      if (account.accountStatus() != status) {
        account.updateAccountStatus(status);
      } else {
        account.accountStatus();
      }
    } else {
      throw new IllegalArgumentException("E-mail does not exist");
    }
  }
}
