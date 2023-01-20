package org.switch2022.project.controller;

import org.switch2022.project.DTO.AccountDTO;
import org.switch2022.project.mapper.AccountMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsInProjectController {
    /**
     * Class CreateProfileController is built to allow access to Company
     */
    private Company company;
    /**
     * This method first verify if Manager has permission to generate a list of Accounts
     * and then returns a list of Accounts Allocated To a Project.
     *
     * @param email
     * @param projectCode
     *
     * @return a list of Accounts
     */
    public List<AccountDTO> listAccountsByProject(String email, String projectCode) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        if (company.validateManager(email)) {
            List<Account> accounts = company.listAccountsByProject(projectCode);
            AccountMapper accountMapper = new AccountMapper();
            accountsDTO = accountMapper.accountsToDTO(accounts);
        }
        return accountsDTO;
    }
}
