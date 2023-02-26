package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.container.Company;
import org.switch2022.project.model.Profile;
import org.switch2022.project.dto.AccountEmailStatusDTO;
import org.switch2022.project.dto.mapper.AccountListEmailStatusMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountListController is built to allow access to the AccountContainer and ProfileContainer
 * in Company Class.
 **/
public class ListAccountController {
    /**
     * Attributes of the class AccountListController, according to the Class Diagram.
     */
    private final Company company;

    /**
     * AccountListController constructor
     */
    public ListAccountController(Company company) {
        this.company = company;
    }

    /**
     * This method returns list of accounts
     *
     * @return list of accounts
     */
    public List<AccountEmailStatusDTO> listAllAccounts(String actorEmail) {
        List<Account> accounts = new ArrayList<>();
        if (
                company.validateProfileRequired(actorEmail, Profile.ADMINISTRATOR)) {
            accounts = this.company.getAccountContainer().getAccounts();
        }
        return AccountListEmailStatusMapper.listAccountsToDTO(accounts);
    }
}