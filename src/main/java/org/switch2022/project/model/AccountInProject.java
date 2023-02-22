package org.switch2022.project.model;

import java.time.LocalDate;
import java.util.Objects;

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
    private String role;
    private LocalDate startDate;
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

    public AccountInProject(Account account, Project project){
        this.account = account;
        this.project = project;
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
        return account.equals(that.account) && project.equals(that.project) &&
                role.equals(that.role) && startDate.equals(that.startDate);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(account, project, role, startDate, costPerHour,
                percentageAllocation, endDate);
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
     * This method returns the percentage of allocation of an Account Allocated To a
     * Project.
     *
     * @return percentage of allocation of an Account Allocated To a Project
     */
    public float getPercentageOfAllocation() {
        return this.percentageAllocation;
    }

    /**
     * This method returns the percentage of allocation of an Account Allocated To a
     * Project.
     *
     * @return percentage of allocation of an Account Allocated To a Project
     */
    boolean isPercentageOfAllocationValid(float percentageOfAllocation){
        final float MAXIMUM_PERCENTAGE = 100;
        final float MINIMUM_PERCENTAGE = 0;
        return percentageOfAllocation > MINIMUM_PERCENTAGE && percentageOfAllocation <= MAXIMUM_PERCENTAGE;
    }


    /**
     * This method returns an Account Allocated To a Project filtered by project.
     *
     * @param projectCode
     * @return an Account.
     */
    public Account getAccountByProject(String projectCode) {
        Account requestedAccount = null;
        if (hasProject(projectCode)) {
            requestedAccount = this.account;
        }
        return requestedAccount;
    }

    /**
     * This method returns an account Allocated To a Project.
     *
     * @return an Account.
     */
    public Account getAccount() {
        return new Account(this.account);
    }

    /**
     * This method returns an account Allocated To a Project.
     *
     * @return an Account.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * This method sets an end date of allocation to a project with a specific role
     */
    public void setEndDate(LocalDate endDate) {
        if (endDate.isAfter(this.startDate)) {
            this.endDate = endDate;
        }
    }

    /**
     * This method checks if an account is allocated to a Project.
     *
     * @param projectCode one must check.
     * @return TRUE if there is an account allocated to a Project and FALSE otherwise.
     */
    private boolean hasProject(String projectCode) {
        return project.hasProjectCode(projectCode);
    }


    public Project getProjectByAccount(String email) {
        Project requestedProject = null;
        if (validate(email)) {
            requestedProject = this.project;
        }
        return requestedProject;
    }

    private boolean validate(String email) {
        boolean validate = false;
        if (this.account.getEmail().equals(email)) {
            validate = true;
        }
        return validate;
    }
}
