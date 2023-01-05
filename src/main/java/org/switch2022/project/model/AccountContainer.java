package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class AccountContainer {
  private List<Account> accountList;

  public AccountContainer(List<Account> accountList) {
    this.accountList = accountList;
  }

  public AccountContainer(){
    this.accountList = new ArrayList<Account>();
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountContainer)) return false;
    AccountContainer that = (AccountContainer) o;
    return Objects.equals(accountList, that.accountList);
  }

  /**
   * This method verify the existence of an account by email confirmation
   *
   * @return an object Account
   */
  public Account getAccountByEmail(String email) {
    Account requestedAccount = null;
    for (int i = 0; i < this.accountList.size(); i++) {
      if (accountList.get(i).getEmail().equals(email)) {
        requestedAccount = accountList.get(i);
        break;
      }
    }
    return requestedAccount;
  }
}
