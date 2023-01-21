package org.switch2022.project.model.container;

import org.switch2022.project.mapper.AccountInProjectDTOMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.utils.dto.AccountInProjectDTO;

import java.util.ArrayList;
import java.util.List;

public class AccountInProjectContainer {

    /**
     * AccountInProjectContainer contains accounts in projects
     */
    private List<AccountInProject> accountsInProject;

    /**
     * Constructor of the class AccountInProjectContainer.
     * New instance is created using as parameter the essential attributes.
     *
     * @param accountsInProject: list of accounts in project
     */
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

    /**
     * Method that verifies if an AccountInProject already exists (same account,
     * project, role and start date)
     *
     * @param accountInProject
     * @return true if AccountInProject already exists, and false otherwise
     */
    private boolean doesAccountInProjectExist(AccountInProject accountInProject) {
        boolean result = false;
        if (this.accountsInProject.contains(accountInProject)) {
            result = true;
        }
        return result;
    }

    /**
     * Method that creates a new instance of AccountInProject and adds it to the list
     * of accounts in project if the instance doesn't already exist and the role is valid
     *
     * @param accountInProjectDTO
     * @return true if AccountInProject is added to list, and false otherwise
     */
    public boolean addUserToProject(AccountInProjectDTO accountInProjectDTO) {
        boolean accountInProjectAdded = false;
        AccountInProjectDTOMapper mapper = new AccountInProjectDTOMapper();
        AccountInProject accountInProject =
                mapper.accountInProjectFromDTO(accountInProjectDTO);

        boolean isRoleValid = accountInProject.isRoleValid();
        boolean doesAccountInProjectExist = doesAccountInProjectExist(accountInProject);

        if (isRoleValid && !doesAccountInProjectExist) {
            accountsInProject.add(accountInProject);
            accountInProjectAdded = true;
        }
        return accountInProjectAdded;
    }
}
