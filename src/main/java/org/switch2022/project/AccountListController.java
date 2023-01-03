package org.switch2022.project;

import java.util.List;
public class AccountListController {
    /**
     * Class AccountListController is built to allow access the account list
     * in Repository Class.
     **/
    private Repository repository;

    public AccountListController (Repository repository) {
        this.repository = repository;
    }
    public List<Account> requestAccountList() {
        return this.repository.getAccountsList();
    }
}