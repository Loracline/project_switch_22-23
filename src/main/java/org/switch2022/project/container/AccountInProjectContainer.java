package org.switch2022.project.container;

import org.switch2022.project.model.AccountInProject;

import java.util.List;

public class AccountInProjectContainer {
    /**
     * AccountInProjectContainer contains accounts in projects
     */
    private List<AccountInProject> accountsInProject;

    public AccountInProjectContainer(List<AccountInProject> accountsInProject) {
        this.accountsInProject = accountsInProject;
    }

    private boolean doesAccountExist(AccountInProject accountInProject) {
        boolean result = false;
        if (!this.accountsInProject.contains(accountInProject)) {
            result = true;
        }
        return result;
    }
}

