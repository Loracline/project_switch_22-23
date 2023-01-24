package org.switch2022.project.model;

import java.time.LocalDate;

/**
 * Class AccountInProject is built to associate a certain account with a role to
 * a specific project.
 * An accountInProject is defined by an account, project, role, cost per hour,
 * percentage of allocation, start and end date.
 */
public class AccountInProject {
    /**
     * Attributes
     */
    private final Account account;
    private final Project project;
    private final String role;
    private final LocalDate startDate;
    private float costPerHour;
    private float percentageAllocation;
    private LocalDate endDate;

    /**
     * Constructor
     */
    public AccountInProject(Account account, Project project, String role,
                            float costPerHour,
                            float percentageAllocation, LocalDate startDate) {
        this.account = account;
        this.project = project;
        this.role = role.toLowerCase();
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
    }

    /**
     * Method that checks if two instances of AccountInProject are equal by
     * comparing the account, project, role and startDate attributes.
     *
     * @param objectToCompare AccountInProject instance to compare with
     * @return TRUE if two instances of AccountInProject have the same account,
     * project, role and startDate attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) {
            return false;
        }
        AccountInProject that = (AccountInProject) objectToCompare;
        return account.equals(that.account) && project.equals(that.project) && role.equals(that.role) && startDate.equals(that.startDate);
    }

    /**
     * This method validates the role of the instance of AccountInProject class.
     *
     * @return TRUE if is a valid role, and FALSE otherwise.
     */
    public boolean isRoleValid() {
        final String PRODUCT_OWNER = "product owner";
        final String SCRUM_MASTER = "scrum master";
        final String TEAM_MEMBER = "team member";
        final String PROJECT_MANAGER = "project manager";
        switch (this.role) {
            case TEAM_MEMBER:
            case PRODUCT_OWNER:
            case SCRUM_MASTER:
            case PROJECT_MANAGER:
                return true;
            default:
                return false;
        }
    }





    /**
     * This method returns an Account Allocated To a Project.
     *
     * @param projectCode
     * @return an Account.
     */
    public Account getAccountByProject(String projectCode) {
        Account requestedAccount = null;
        if (isAccountAllocatedToProject(projectCode)) {
            requestedAccount = this.account;
        }
        return requestedAccount;
    }

    /**
     * This method checks if an account is allocated to a Project.
     *
     * @param projectCode one must check.
     * @return TRUE if there is an account allocated to a Project and FALSE otherwise.
     */
    public boolean isAccountAllocatedToProject(String projectCode) {
        boolean isAllocated = false;
        if (this.project.getProjectCode().equals(projectCode)) {
            isAllocated = true;
        }
        return isAllocated;
    }


    public Project getProjectsByAccount(Account account) {
        // code to retrieve project by account email
        return project;
    }





}
