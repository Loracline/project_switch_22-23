package org.switch2022.project.model.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.AllocationDTO;

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
     * @param allocationDTO data transfer object with allocation information.
     * @return TRUE if added, and FALSE otherwise.
     */
    public boolean addUserToProject(Account account, Project project,
                                    AllocationDTO allocationDTO) {
        boolean accountInProjectAdded = false;

        if (account != null && project != null) {
            AccountInProject accountInProject = new AccountInProject(account, project,
                    allocationDTO.role, allocationDTO.costPerHour,
                    allocationDTO.percentageAllocation, allocationDTO.startDate);

            boolean isRoleValid = accountInProject.isRoleValid();
            boolean doesAccountInProjectExist =
                    doesAccountInProjectExist(accountInProject);
            boolean isPercentageOfAllocationValid =
                    isTotalPercentageOfAllocationValid(account,
                            allocationDTO.percentageAllocation);


            if (isRoleValid && !doesAccountInProjectExist && isPercentageOfAllocationValid) {
                accountsInProject.add(accountInProject);
                accountInProjectAdded = true;
            }
        }

        return accountInProjectAdded;
    }


    /**
     * Method that returns the current total percentage of allocation of an account in
     * all projects
     *
     * @return sum of percentages of allocation of all projects an account is involved in.
     */
    float currentPercentageOfAllocation(Account account) {
        int i = 0;
        float sumOfPercentages = 0;
        while (i < accountsInProject.size()) {
            if (accountsInProject.get(i).getAccount().equals(account) &&
                    accountsInProject.get(i).getEndDate() == null) {
                sumOfPercentages = sumOfPercentages +
                        accountsInProject.get(i).getPercentageOfAllocation();
            }
            i++;
        }
        return sumOfPercentages;
    }

    /**
     * Method that verifies if the total percentage of allocation of a user does not
     * exceed 100%.
     *
     * @return TRUE if it doesn't exceed 100, and FALSE otherwise.
     */
    boolean isTotalPercentageOfAllocationValid(Account account,
                                               float newPercentageAllocation) {
        boolean percentageOfAllocationValid = false;
        float totalPercentageAllocation =
                currentPercentageOfAllocation(account) +
                        newPercentageAllocation;
        byte maximumPercentageOfAllocation = 100;
        if (totalPercentageAllocation <= maximumPercentageOfAllocation &&
                newPercentageAllocation > 0) {
            percentageOfAllocationValid = true;
        }
        return percentageOfAllocationValid;
    }

    /**
     * This method returns a list of Accounts Allocated To a Project
     *
     * @return a list of Accounts
     */
    public List<Account> listAccountsByProject(String projectCode) {
        List<Account> accounts = new ArrayList<>();
        int i = 0;
        while (i < accountsInProject.size()) {
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

    /**
     * This method returns a list of Projects Allocated To an Account
     *
     * @return a list of Projects
     */
    public List<Project> listProjectsByAccount(String email) {
        List<Project> projects = new ArrayList<>();
        int i = 0;
        while (i < accountsInProject.size()) {
            Project requestedProject = accountsInProject.get(i).getProjectByAccount(email);
            if (requestedProject != null) {
                projects.add(requestedProject);
            }
            i++;
        }
        return projects;
    }
}
