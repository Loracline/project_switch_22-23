package org.switch2022.project.controller;

import org.switch2022.project.model.AccountContainer;
import org.switch2022.project.model.Company;

public class AccountListController {
    /**
     * Class AccountListController is built to allow access the account list
     * in Repository Class.
     **/
    private Company company;

    public AccountListController (Company company) {
        this.company = company;
    }
    public AccountContainer requestAccountList() {
        return this.company.getAccountContainer();
    }
}