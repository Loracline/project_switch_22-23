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
        if (this.company.validateManager(email)) {
            List<Account> accounts = this.company.listAccountsByProject(projectCode);
            AccountMapper accountMapper = new AccountMapper();
            accountsDTO = accountMapper.cloneList(accounts);
        }
        return accountsDTO;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    //perguntar se é melhor usar um setter para os testes ou um construtor
    // (para atribuir uma company a um controller)
}
