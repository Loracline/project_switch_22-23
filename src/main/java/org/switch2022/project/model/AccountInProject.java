package org.switch2022.project.model;

import java.time.LocalDate;

/**
 * Class AccountInProject is built to associate a certain account with a
 * specific role to a project.
 * An account in project is defined by account, project, role, cost per hour,
 * percentage of allocation, start and end date.
 */
public class AccountInProject {
    /**
     * Attributes
     */
    private final Account account;
    private final Project project;
    private final float costPerHour;
    private final float percentageAllocation;
    private final LocalDate startDate;
    private String role;
    private String endDate;

    /**
     * Constructor
     */
    public AccountInProject(Account account, Project project, float costPerHour,
                            float percentageAllocation, LocalDate startDate) {
        this.account = account;
        this.project = project;
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
    }

    /**
     * This method checks if two instances of AccountInProject are equal by
     * comparing their attributes.
     *
     * @param toCompare AccountInProject instance to compare with
     * @return TRUE if the two have the same attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null || getClass() != toCompare.getClass()) {
            return false;
        }
        AccountInProject that = (AccountInProject) toCompare;
        return account.equals(that.account) && project.equals(that.project) && role.equals(that.role) && startDate.isEqual(that.startDate);
    }

    /**
     * Setter method for the attribute: role.
     *
     * @param role of the account in designed project.
     * @return TRUE if role is set and FALSE otherwise.
     */
    public boolean setRole(String role) {
        switch (role) {
            case "team member":
                this.role = "Team Member";
                return true;
            case "product owner":
                this.role = "Product Owner";
                return true;
            case "project manager":
                this.role = "Project Manager";
                return true;
            case "scrum master":
                this.role = "Scrum Master";
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
    private boolean isAccountAllocatedToProject(String projectCode) {
        boolean isAllocated = false;
        if (this.project.getProjectCode().equals(projectCode)) {
            isAllocated = true;
        }
        return isAllocated;
    }
}
