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
                            float percentageAllocation, LocalDate startDate,
                            LocalDate endDate) {
        this.account = account;
        this.project = project;
        this.role = role.toLowerCase();
        this.costPerHour = costPerHour;
        this.percentageAllocation = percentageAllocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AccountInProject(Account account, Project project) {
        this.account = account;
        this.project = project;
        this.role = "";
        this.costPerHour = 0.0F;
        this.percentageAllocation = 0.0F;
        this.startDate = LocalDate.of(1, 1, 1);
        this.endDate = LocalDate.of(1, 1, 1);
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

    public String getRole() {
        return role;
    }

    /**
     * This method returns the percentage of allocation of an Account Allocated To a
     * Project.
     *
     * @return percentage of allocation of an Account Allocated To a Project
     */
    public boolean isPercentageOfAllocationValid(float percentageOfAllocation) {
        final float MAXIMUM_PERCENTAGE = 100;
        final float MINIMUM_PERCENTAGE = 0;
        return percentageOfAllocation > MINIMUM_PERCENTAGE && percentageOfAllocation <= MAXIMUM_PERCENTAGE;
    }


    /**
     * This method returns an Account Allocated To a Project filtered by project.
     *
     * @param projectCode code of the project.
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
     * This method returns the project to which an account is allocated.
     *
     * @return a project.
     */
    public Project getProject() {
        return new Project(this.project.getProjectCode(), this.project.getProjectName()
                , this.project.getCustomer(), this.project.getProjectTypology(),
                this.project.getBusinessSector());
    }

    /**
     * This method returns the end date of an allocation.
     *
     * @return the end date of the allocation.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * This method returns an account Allocated To a Project.
     *
     * @return an Account.
     */
    public LocalDate getStartDate() {
        return this.startDate;
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

    /**
     * This method returns a Project Allocated To an Account filtered by account.
     *
     * @param email one must get
     * @return a Project.
     */
    public Project getProjectByAccount(String email) {
        Project requestedProject = null;
        if (validate(email)) {
            requestedProject = this.project;
        }
        return requestedProject;
    }

    /**
     * This method validates the email of the account object stored within
     * the class instance is equal to the provided email
     *
     * @param email of an account
     * @return validates email
     */

    private boolean validate(String email) {
        return this.account.getEmail().equals(email);
    }

    public boolean isStartDateBeforeNow() {
        return this.startDate.isBefore(LocalDate.now()) || this.startDate.isEqual(LocalDate.now());
    }

    public boolean isEndDateAfterNow() {
        return this.endDate.isAfter(LocalDate.now()) || this.endDate.isEqual(LocalDate.now());
    }

    public boolean isEndDateValid() {
        return this.endDate.isAfter(this.startDate);
    }

    public boolean hasProject(Project project) {
        return this.project.equals(project);
    }

    public boolean hasAccount(Account account) {
        return this.account.equals(account);
    }

    public boolean isAccountInProjectIncomplete(Account account, Project project){
        return hasAccount(account) &&
                hasProject(project) &&
                isRoleEmpty();
    }

    public boolean isScrumMasterOrProductOwner() {
        final String SCRUM_MASTER = "Scrum Master";
        final String PRODUCT_OWNER = "Product Owner";
        return SCRUM_MASTER.equalsIgnoreCase(this.role) || PRODUCT_OWNER.equalsIgnoreCase(this.role);
    }

    public boolean isRoleEmpty() {
        return this.role.isEmpty();
    }
}