package org.switch2022.project.model.container;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;
import org.switch2022.project.utils.dto.AllocationDto;

import java.time.LocalDate;
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
                                    AllocationDto allocationDTO) {
        boolean accountInProjectAdded = false;
        AccountInProject accountInProject = new AccountInProject(account, project,
                allocationDTO.role, allocationDTO.costPerHour,
                allocationDTO.percentageAllocation, allocationDTO.startDate, allocationDTO.endDate);

        if (!accountsInProject.contains(accountInProject) &&
                isAccountInProjectValid(accountInProject)) {
            accountsInProject.add(accountInProject);
            accountInProjectAdded = true;
        }

        return accountInProjectAdded;
    }


    private boolean isAccountInProjectValid(AccountInProject accountInProject) {
        return accountInProject.isRoleValid() &&

                accountInProject.isPercentageOfAllocationValid
                        (accountInProject.getPercentageOfAllocation()) &&

                isTotalPercentageOfAllocationValid(accountInProject.getAccount(),
                        accountInProject.getPercentageOfAllocation()) &&

                accountInProject.isEndDateValid() &&

                isAllocationValid(accountInProject);
    }

    private boolean isPeriodOverlapping(LocalDate startDateOne,
                                        LocalDate endDateOne,
                                        LocalDate startDateTwo,
                                        LocalDate endDateTwo) {
        return startDateOne.isBefore(endDateTwo) && startDateTwo.isBefore(endDateOne);
    }

    private boolean isAllocationValid(AccountInProject accountInProject) {
        boolean isAllocationValid = true;
        LocalDate startDateNewAllocation = accountInProject.getStartDate();
        LocalDate endDateNewAllocation = accountInProject.getEndDate();

        int i = 0;
        while (i < accountsInProject.size() && isAllocationValid) {
            LocalDate startDateExistingAllocation = accountsInProject.get(i).getStartDate();
            LocalDate endDateExistingAllocation = accountsInProject.get(i).getEndDate();

            if (isScrumMasterOrProductOwner(i) &&
                    isPeriodOverlapping(startDateExistingAllocation,
                            endDateExistingAllocation, startDateNewAllocation,
                            endDateNewAllocation)) {
                isAllocationValid = false;
            }

            i++;
        }

        return isAllocationValid;
    }

    private boolean isScrumMasterOrProductOwner(int i) {
        final String SCRUM_MASTER = "Scrum Master";
        final String PRODUCT_OWNER = "Product Owner";
        return accountsInProject.get(i).getRole().equalsIgnoreCase(SCRUM_MASTER) ||
                accountsInProject.get(i).getRole().equalsIgnoreCase(PRODUCT_OWNER);
    }

    public boolean addUserToProject(Account account, Project project) {
        boolean accountInProjectAdded = false;
        AccountInProject accountInProject = new AccountInProject(account, project);
        if (!accountsInProject.contains(accountInProject)) {
            accountsInProject.add(accountInProject);
            accountInProjectAdded = true;
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
            if (isAccountTheSame(account, i) &&
                    doesPeriodIncludeCurrentDate(i)) {

                sumOfPercentages = sumOfPercentages +
                        accountsInProject.get(i).getPercentageOfAllocation();

            }
            i++;
        }
        return sumOfPercentages;
    }

    private boolean isAccountTheSame(Account account, int i) {
        return accountsInProject.get(i).getAccount().equals(account);
    }

    private boolean doesPeriodIncludeCurrentDate(int i) {
        return accountsInProject.get(i).isStartDateBeforeNow() && accountsInProject.get(i).isEndDateAfterNow();
    }

    /**
     * Method that verifies if the total percentage of allocation of a user does not
     * exceed 100%.
     *
     * @return TRUE if it doesn't exceed 100, and FALSE otherwise.
     */
    boolean isTotalPercentageOfAllocationValid(Account account,
                                               float newPercentageAllocation) {
        final byte MAXIMUM_PERCENTAGE = 100;
        boolean percentageOfAllocationValid = false;

        float totalPercentageAllocation =
                currentPercentageOfAllocation(account) +
                        newPercentageAllocation;

        if (totalPercentageAllocation <= MAXIMUM_PERCENTAGE) {
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
     * This method returns a list of Projects Allocated To an Account.
     * creates an empty list, uses while loop to iterate through accountsInProject
     * for each iteration it calls the method getProjectByAccount, if the requested project is
     * not null, it's added to the list.
     *
     * @param email given email to search project allocated to it.
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
