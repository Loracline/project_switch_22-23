package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;

public class AccountContainer {
  private List<Account> accountList;

  public AccountContainer(List<Account> accountList) {
    this.accountList = accountList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountContainer)) return false;
    AccountContainer that = (AccountContainer) o;
    return Objects.equals(accountList, that.accountList);
  }

  public Account getAccountWithEmail(String email) {
    String accountEmail = email;
    Account requestedAccount = null;
    for (int i = 0; i < this.accountList.size(); i++) {
      if (accountList.get(i).getEmail().equals(accountEmail)) {
        requestedAccount = accountList.get(i);
        break;
      }
    }
    return requestedAccount;
  }
}
