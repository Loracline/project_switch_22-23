package org.switch2022.project.container;

import org.switch2022.project.DTO.AccountInProjectDTO;
import org.switch2022.project.mapper.AccountInProjectDTOMapper;
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

    /**
     * Constructor of the class AccountInProjectContainer.
     * New instance is created using as parameter the essential attributes.
     *
     * @param accountsInProject: list of accounts in project
     */
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
