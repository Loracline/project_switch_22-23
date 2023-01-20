package org.switch2022.project.container;

import org.switch2022.project.DTO.AllocationDTO;
import org.switch2022.project.mapper.AllocationMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import java.util.ArrayList;
import java.util.List;

public class AccountInProjectContainer {

    private AccountInProject accountInProject;

    /**
     * AccountInProjectContainer contains accounts in projects
     */
    private List<AccountInProject> accountsInProject;

    public AccountInProjectContainer(List<AccountInProject> accountsInProject) {
        this.accountsInProject = accountsInProject;
    }

    public List<Account> listAccountsByProject(Project project) {
        List<Account> accounts = new ArrayList<Account>();
        for (int i = 0; i < accounts.size(); i++) {
            accounts.add(accountInProject.getAccountByProject(project));
        }
        return accounts;
    }

    private boolean doesAccountExist(AccountInProject accountInProject) {
        boolean result = false;
        if (this.accountsInProject.contains(accountInProject)) {
            result = true;
        }
        return result;
    }

    public boolean addTeamMemberToProject(AllocationDTO accountDTO) {
        boolean accountAdded = false;
        AllocationMapper mapper = new AllocationMapper();
        AccountInProject account = mapper.addTeamMemberToProject(accountDTO);
        if (!doesAccountExist(account)) {
            accountsInProject.add(account);
            accountAdded = true;
        }
        return accountAdded;
    }
}
