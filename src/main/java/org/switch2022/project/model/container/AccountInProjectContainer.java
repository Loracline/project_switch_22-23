package org.switch2022.project.model.container;

import org.switch2022.project.utils.mapper.AccountInProjectDTOMapper;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.utils.Util;
import org.switch2022.project.utils.dto.AccountInProjectDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class AccountInProjectContainer is built to access and manipulate a set of
 * accounts that are associated with a project with specific role.
 */
public class AccountInProjectContainer {
    /**
     * Attributes
     */
    private final List<AccountInProject> accountsInProject;

    /**
     * Constructor
     */
    public AccountInProjectContainer(List<AccountInProject> accountsInProject) {
        this.accountsInProject = accountsInProject;
    }

    /**
     * Method that creates a new instance of AccountInProject and adds it to the
     * list of accounts in project if the instance doesn't already exist and the
     * role is valid.
     *
     * @param accountInProjectDTO data transfer object with allocation information.
     * @return TRUE if added, and FALSE otherwise.
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


    /**
     * This method returns a list of Accounts Allocated To a Project
     *
     * @return a list of Accounts
     */
    public List<Account> listAccountsByProject(String projectCode) {
        List<Account> accounts = new ArrayList<>();
        int i = 0;
        while (Util.isLower(i, accountsInProject.size())) {
            Account requestedAccount = accountsInProject.get(i).getAccountByProject(projectCode);
            if (requestedAccount != null) {
                accounts.add(requestedAccount);
            }
            i++;
        }
        return accounts;
    }

    /**
     * Method that verifies if an AccountInProject already exists (same account,
     * project, role and start date)
     *
     * @param accountInProject one intend to search for
     * @return TRUE if allocation was already made, and FALSE otherwise
     */
    private boolean doesAccountInProjectExist(AccountInProject accountInProject) {
        return this.accountsInProject.contains(accountInProject);
    }
}
