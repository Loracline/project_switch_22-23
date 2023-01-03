package org.switch2022.project.model;

import java.util.List;
import java.util.Objects;

public class AccountCountainer {
  private List<Account> accountList;

  public AccountCountainer(List<Account> accountList){
    this.accountList = accountList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AccountCountainer)) return false;
    AccountCountainer that = (AccountCountainer) o;
    return Objects.equals(accountList, that.accountList);
  }
}
