package org.switch2022.project.container;

import org.switch2022.project.DTO.AllocationDTO;
import org.switch2022.project.mapper.AllocationMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import java.util.ArrayList;
import java.util.List;

public class AccountInProjectContainer {

    /**
     * AccountInProjectContainer contains accounts in projects
     */
    private List<AccountInProject> accountsInProject;

    public AccountInProjectContainer(List<AccountInProject> accountsInProject) {
        this.accountsInProject = accountsInProject;
    }

    /**
     * This method returns a list of Accounts Allocated To a Project
     *
     * @return a list of Accounts
     */
    public List<Account> listAccountsByProject(String projectCode) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            Account requestedAccount = accountsInProject.get(i).getAccountByProject(projectCode);
            if (requestedAccount != null) {
                accounts.add(requestedAccount);
            }
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

    public boolean addTeamMemberToProject(AllocationDTO allocationDTO) {
        boolean accountAdded = false;
        AllocationMapper mapper = new AllocationMapper();
        AccountInProject account = mapper.addTeamMemberToProject(allocationDTO);
        if (!doesAccountExist(account)) {
            accountsInProject.add(account);
            accountAdded = true;
        }
        return accountAdded;
    }
}
