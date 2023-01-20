package org.switch2022.project.container;

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
        if (!this.accountsInProject.contains(accountInProject)) {
            result = true;
        }
        return result;
    }


    public List<Project> listProjectsByAccount(Account account) {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < projects.size();i++) {
            projects.add(accountInProject.getProjectsByAccount(account));
        }
        return projects;
    }

   /* public List<Project> listProjectByAccount(Account account) {
        List<Project> projects = new ArrayList<Project>();
        for (AccountInProject accountInProject : getAccounts()) {
            Project project = accountInProject.getProjectsByAccount(account);
            projects.add(project);
        }
        return projects;
    }
    public List<AccountInProject> getAccounts() {
        // code to retrieve all accounts in the container
        return accounts;
    }
}
*/

}


