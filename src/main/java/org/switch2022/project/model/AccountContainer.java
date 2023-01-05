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
}
