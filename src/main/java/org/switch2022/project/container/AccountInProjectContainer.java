package org.switch2022.project.container;

import org.switch2022.project.dto.AllocationDto;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountInProject;
import org.switch2022.project.model.Project;

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
     * Method that adds a new instance of AccountInProject to the list of accounts in
     * project if the instance is in a valid state.
     * <p>
     * If this condition is met, the method then checks if the account is already
     * allocated to the project (with no
     * role attributed or associated with a different
     * role) and if the period of the new allocation does not overlap with any
     * existing allocation.
     * <p>
     * If these conditions are met, then the method checks if there is not already an
     * existing Scrum Master or Product Owner on the project, or if the role of the new
     * allocation is a Team Member. If there is no Scrum
     * Master or Product Owner or the new account is a Team Member, the
     * accountInProject  object is added to the accountsInProject list, and
     * the method returns true.
     *
     * @param account       to allocate to a given project.
     * @param project       to which a given account is allocated.
     * @param allocationDto containing the allocation information.
     * @return TRUE if a new instance of AccountInProject is successfully added to the
     * list, and FALSE otherwise.
     */
    public boolean addUserToProject(Account account, Project project,
                                    AllocationDto allocationDto) {
        boolean accountInProjectAdded = false;
        AccountInProject accountInProject = new AccountInProject(account, project,
                allocationDto.role, allocationDto.costPerHour,
                allocationDto.percentageAllocation, allocationDto.startDate,
                allocationDto.endDate);

        if (isAccountInProjectValid(accountInProject)) {

            int i = 0;
            while (i < accountsInProject.size() && !accountInProjectAdded) {
                if (accountsInProject.get(i).isAccountAllocatedToProject(account,
                        project) && arePeriodAndRoleValid(project, allocationDto,
                        accountInProject,
                        accountsInProject.get(i))) {

                    accountsInProject.add(accountInProject);
                    accountInProjectAdded = true;
                    removeIncompleteAccountInProject(account, project);

                } else {
                    if (isRoleValidInProject(project, allocationDto, accountInProject)) {

                        accountsInProject.add(accountInProject);
                        accountInProjectAdded = true;

                    }
                }
                i++;
            }
        }
        return accountInProjectAdded;
    }

    /**
     * Method that checks if the allocation period of a new account in project does not
     * overlap the allocation period of another account in the same project and if
     * the allocation role is valid.
     *
     * @param project               to which the account is allocated.
     * @param allocationDto         containing the allocation information.
     * @param accountInProject      with information to validate.
     * @param otherAccountInProject to compare to.
     * @return TRUE if the allocation period of a new account in project does
     * not overlap the allocation period of another account in the same project and if
     * the allocation role is valid and FALSE otherwise.
     */
    public boolean arePeriodAndRoleValid(Project project,
                                         AllocationDto allocationDto,
                                         AccountInProject accountInProject,
                                         AccountInProject otherAccountInProject) {
        return isPeriodValid(accountInProject, otherAccountInProject) && isRoleValidInProject(project,
                allocationDto, accountInProject);
    }

    /**
     * Method that checks if a given role in an allocation is valid within a given
     * project. A role is valid if is either Team Member or is a unique Scrum Master /
     * Product Owner.
     *
     * @param project          to which the account is allocated.
     * @param allocationDTO    containing the allocation information.
     * @param accountInProject with information to validate.
     * @return TRUE if role is a Team Member or if Scrum Master / Product Owner is
     * unique in given project.
     */
    public boolean isRoleValidInProject(Project project, AllocationDto allocationDTO,
                                        AccountInProject accountInProject) {
        return accountInProject.isTeamMember() || isRoleInProjectUnique(project,
                allocationDTO.role,
                accountInProject);
    }

    /**
     * Method that checks and removes incomplete instances of AccountInProject (created
     * only with account and project, i.e., resulting from allocation without role and
     * associated information).
     *
     * @param account allocated to a given project.
     * @param project to which a given account is allocated.
     */
    private void removeIncompleteAccountInProject(Account account,
                                                  Project project) {
        int i = 0;
        while (Math.abs(i) < accountsInProject.size()) {
            if (accountsInProject.get(i).isAccountInProjectIncomplete(account, project)) {
                accountsInProject.remove(accountsInProject.get(i));
            }
            i++;
        }
    }


    /**
     * Method that checks if an instance of AccountInProject is valid (not
     * previously existing in the list of accounts in project, valid role,
     * valid new percentage of allocation, valid total percentage of allocation, valid
     * end date and valid allocation).
     * <p>
     * The role is valid if is one of the following: Scrum Master, Product Owner, Team
     * Member, and Project Manager.
     * <p>
     * A new percentage of allocation (the one provided when creating a new instance of
     * AccountInProject) is valid if it is above zero and below or equal to one hundred.
     * <p>
     * The total percentage of allocation (the sum of all partial percentages of
     * allocation the same account has in different projects at the same time) is valid
     * if  it is below or equal to one hundred.
     * <p>
     * The end date is valid if it is after the start date.
     *
     * @param accountInProject with information to validate.
     * @return incomplete instance of AccountInProject, and NULL otherwise.
     */
    private boolean isAccountInProjectValid(AccountInProject accountInProject) {
        return isAccountInProjectNotInList(accountInProject) &&

                accountInProject.isRoleValid() &&

                accountInProject.isPercentageOfAllocationValid
                        (accountInProject.getPercentageOfAllocation()) &&

                isTotalPercentageOfAllocationValid(accountInProject.getAccount(),
                        accountInProject.getPercentageOfAllocation()) &&

                accountInProject.isEndDateValid();
    }

    /**
     * Method that checks if an instance of account in project does not exist in the list.
     */
    private boolean isAccountInProjectNotInList(AccountInProject accountInProject) {
        return !accountsInProject.contains(accountInProject);
    }

    /**
     * Method that checks if two periods are overlapping. Each period is characterized
     * by a start date and an end date. Two periods are overlapping if the start
     * date one is before the end date two, and start date two is before end date one.
     *
     * @param startDateOne as the start date of period one.
     * @param endDateOne   as the end date of period one.
     * @param startDateTwo as the start date of period two.
     * @param endDateTwo   as the end date of period two.
     * @return TRUE if two periods are overlapping, and FALSE otherwise.
     */
    private boolean isPeriodOverlapping(LocalDate startDateOne,
                                        LocalDate endDateOne,
                                        LocalDate startDateTwo,
                                        LocalDate endDateTwo) {
        return startDateOne.isBefore(endDateTwo) && startDateTwo.isBefore(endDateOne);
    }

    /**
     * Method that checks whether a Scrum Master(SM) or Product Owner(PO) is unique in a
     * project during the allocation period (startDate and endDate of the
     * accountInProject).
     *
     * @return TRUE if there is no other account with the same role (SM or PO) in the
     * same project during the same period, and FALSE otherwise.
     */
    public boolean isRoleInProjectUnique(Project project, String role,
                                         AccountInProject accountInProject) {
        boolean isUnique = true;

        int i = 0;
        while (i < accountsInProject.size() && isUnique) {
            if (accountsInProject.get(i).hasProject(project) && accountsInProject.get(i).hasRole(role) &&
                    !isPeriodValid(accountInProject, accountsInProject.get(i))) {
                isUnique = false;
            }
            i++;
        }
        return isUnique;
    }

    /**
     * Method that checks if the allocation period of a new account in project
     * overlaps with the allocation period of an existing account in the same project.
     *
     * @return TRUE if there is no overlapping periods between the allocations, and
     * FALSE otherwise
     */
    private boolean isPeriodValid(AccountInProject newAccountInProject,
                                  AccountInProject existingAccountInProject) {
        boolean isPeriodValid = true;
        LocalDate startDateNewAllocation = newAccountInProject.getStartDate();
        LocalDate endDateNewAllocation = newAccountInProject.getEndDate();

        LocalDate startDateExistingAllocation = existingAccountInProject.getStartDate();
        LocalDate endDateExistingAllocation = existingAccountInProject.getEndDate();

        if (isPeriodOverlapping(startDateExistingAllocation,
                endDateExistingAllocation, startDateNewAllocation,
                endDateNewAllocation)) {
            isPeriodValid = false;
        }

        return isPeriodValid;
    }

    /**
     * Method that adds an incomplete instance of AccountInProject and adds it to the
     * list of accounts in project if it does not already exist in the list of accounts
     * in project.
     *
     * @param account to allocate to a given project.
     * @param project into which a given account is allocated.
     * @return TRUE if an incomplete instance of AccountInProject is successfully
     * added to the list, and FALSE otherwise.
     */
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
    public float currentPercentageOfAllocation(Account account) {
        int i = 0;
        float sumOfPercentages = 0;
        while (Math.abs(i) < accountsInProject.size()) {
            if (isAccountTheSame(account, i) &&
                    doesPeriodIncludeCurrentDate(i)) {

                sumOfPercentages = sumOfPercentages +
                        accountsInProject.get(i).getPercentageOfAllocation();

            }
            i++;
        }
        return sumOfPercentages;
    }

    /**
     * Method that checks if one account that is passed as a parameter is the same as
     * an account that exists in a list in a given index.
     *
     * @param account to compare with.
     * @param i       as the index of the list.
     * @return TRUE if accounts are equal, and FALSE otherwise.
     */
    private boolean isAccountTheSame(Account account, int i) {
        return accountsInProject.get(i).getAccount().equals(account);
    }

    /**
     * Method that checks whether a given period in a given index of a list includes
     * the current date.
     *
     * @param i as the index of the list.
     * @return TRUE if it includes the current date, and FALSE otherwise.
     */
    private boolean doesPeriodIncludeCurrentDate(int i) {
        return accountsInProject.get(i).isStartDateBeforeNow() && accountsInProject.get(i).isEndDateAfterNow();
    }

    /**
     * Method that verifies if the total percentage of allocation of a user does not
     * exceed 100%.
     *
     * @return TRUE if it doesn't exceed 100, and FALSE otherwise.
     */
    public boolean isTotalPercentageOfAllocationValid(Account account,
                                                      float newPercentageAllocation) {
        final byte MAXIMUM_PERCENTAGE = 100;
        boolean percentageOfAllocationValid = false;

        float totalPercentageAllocation =
                currentPercentageOfAllocation(account) +
                        newPercentageAllocation;

        if (totalPercentageAllocation <= MAXIMUM_PERCENTAGE &&
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
        while (Math.abs(i) < accountsInProject.size()) {
            Account requestedAccount =
                    accountsInProject.get(i).getAccountByProject(projectCode);
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
     * for each iteration it calls the method getProjectByAccount, if the requested
     * project is
     * not null, it's added to the list.
     *
     * @param email given email to search project allocated to it.
     * @return a list of Projects
     */
    public List<Project> listProjectsByAccount(String email) {
        List<Project> projects = new ArrayList<>();
        int i = 0;
        while (Math.abs(i) < accountsInProject.size()) {
            Project requestedProject =
                    accountsInProject.get(i).getProjectByAccount(email);
            if (requestedProject != null) {
                projects.add(requestedProject);
            }
            i++;
        }
        return projects;
    }
}
