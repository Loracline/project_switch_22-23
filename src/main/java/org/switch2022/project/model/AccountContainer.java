package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;

public class AccountContainer {
  private List<Account> accountList;

  public AccountContainer(List<Account> accountList){
    this.accountList = accountList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountContainer)) return false;
    AccountContainer that = (AccountContainer) o;
    return Objects.equals(accountList, that.accountList);
  }
}
