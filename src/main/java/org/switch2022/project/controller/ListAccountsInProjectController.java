package org.switch2022.project.controller;

import org.switch2022.project.utils.dto.AccountDTO;
import org.switch2022.project.utils.mapper.AccountMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsInProjectController {
    /**
     * Class CreateProfileController is built to allow access to Company
     */
    private final Company company;

    public ListAccountsInProjectController(Company company) {
        this.company = company;
    }

    /**
     * This method first verify if Manager has permission to generate a list of Accounts
     * and then returns a list of Accounts Allocated To a Project.
     *
     * @param emailManager
     * @param projectCode
     *
     * @return a list of Accounts
     */
    public List<AccountDTO> listAccountsByProject(String emailManager, String projectCode) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        if (this.company.validateManager(emailManager)) {
            List<Account> accounts = this.company.listAccountsByProject(projectCode);
            accountsDTO = AccountMapper.ListAccountsToDTO(accounts);
        }
        return accountsDTO;
    }

}
