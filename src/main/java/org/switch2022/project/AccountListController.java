package org.switch2022.project;

import java.util.List;
public class AccountListController {
    /**
     * Class AccountListController is built to allow access the account list
     * in Repository Class.
     **/
    private Repository accountList;
    private Repository repository;

    public AccountListController (Repository accountList) {
        this.accountList = accountList;
    }
    public List<Account> requestAccountList() {
        return this.repository.getAccountsList();
    }
}