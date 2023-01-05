package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountContainer;
import org.switch2022.project.model.Company;

import java.util.List;

public class AccountListController {
    /**
     * Class AccountListController is built to allow access the account list
     * in Repository Class.
     **/
    private Company company;

    public AccountListController (Company company) {
        this.company = company;
    }

    public List<Account> listAllAccounts() {
        return this.company.getAccountContainer().getAccounts();
    }
}