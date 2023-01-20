package org.switch2022.project.controller;

import org.switch2022.project.DTO.ProjectDTO;
import org.switch2022.project.mapper.ProjectMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.Company;
import org.switch2022.project.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsInProjectController {
    private Company company;





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
