package org.switch2022.project.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import java.util.ArrayList;
import java.util.List;

public class AccountInProjectContainer {

    private AccountInProject accountInProject;

    public List<Account> listAccountsByProject(Project project) {
        List<Account> accounts = new ArrayList<Account>();
        for (int i = 0; i < accounts.size(); i++) {
            accounts.add(accountInProject.getAccountByProject(project));
        }
        return accounts;
    }
}
