package org.switch2022.project.ddd.domain.model.project_resource;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectResource implements Entity<ProjectResource> {

    private final ProjectResourceId projectResourceId;
    private final Code projectCode;
    private final Email accountEmail;
    private final Role roleInProject;
    private final Period allocationPeriod;
    private final CostPerHour costPerHour;
    private final PercentageOfAllocation percentageOfAllocation;

    /**
     * Constructor
     */
    protected ProjectResource(final ProjectResourceId projectResourceId, final Code code, final Email email,
                              final Role roleInProject, final Period allocationPeriod, final CostPerHour costPerHour,
                              final PercentageOfAllocation percentageOfAllocation) {

        Validate.notNull(projectResourceId, "Project Resource ID must not be null");
        Validate.notNull(code, "Project Code must not be null");
        Validate.notNull(email, "Email must not be null");
        if (roleInProject == null) {
            throw new InvalidInputException("Role must not be null");
        }
        Validate.notNull(allocationPeriod, "Period must not be null");
        Validate.notNull(costPerHour, "Cost/hour must not be null");
        Validate.notNull(percentageOfAllocation, "Percentage of Allocation can not be null");

        this.projectResourceId = projectResourceId;
        this.projectCode = code;
        this.accountEmail = email;
        this.roleInProject = roleInProject;
        this.allocationPeriod = allocationPeriod;
        this.costPerHour = costPerHour;
        this.percentageOfAllocation = percentageOfAllocation;
    }

    /**
     * This method checks if two instances of ProjectResource are equal by comparing the value of the attribute
     * ProjectResourceId
     *
     * @param other ProjectResource instance to compare with.
     * @return <code>true</code> if the two instances have the same attribute value, and <code>false</code> otherwise.
     */
    @Override
    public boolean sameIdentityAs(ProjectResource other) {
        Validate.notNull(other, "Project to compare can not be null");
        return this.projectResourceId.equals(other.projectResourceId);
    }

    /**
     * This method checks if an instance of ProjectResource is equal to another object.
     *
     * @param other object to compare with.
     * @return <code>true</code> if the two instances are equals, and <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other) {
        Validate.notNull(other, "The Object To Compare must not be null");
        if (this == other) {
            return true;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        return sameIdentityAs((ProjectResource) other);
    }

    /**
     * This method is used to generate a unique hash code for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectResourceId);
    }

    /**
     * This method checks if an instance of ProjectResource has a given project code.
     *
     * @param projectCode project code of interest.
     * @return true if the project code of interest is equal to the project code of the instance of ProjectResource,
     * and false otherwise.
     */
    public boolean hasProjectCode(Code projectCode) {
        return this.projectCode.equals(projectCode);
    }

    /**
     * This method checks if an instance of ProjectResource has the same basic allocation info (projectCode,
     * accountEmail, roleInProject, and timeInProject).
     *
     * @param otherResource project resource to compare to.
     * @return true if this project resource has the same allocation info as the other project resource, and false
     * otherwise.
     */
    public boolean hasSameAllocationInfo(ProjectResource otherResource) {
        return this.projectCode.equals(otherResource.projectCode) && this.accountEmail.equals(
                otherResource.accountEmail) && this.roleInProject.equals(
                otherResource.roleInProject) && this.allocationPeriod.equals(otherResource.allocationPeriod);
    }

    /**
     * This method checks if an instance of ProjectResource has a given role.
     *
     * @param role to check.
     * @return <code>true</code> if the role is equal to the role of the ProjectResource and <code>false</code>
     * otherwise.
     */
    public boolean hasRole(Role role) {
        return this.roleInProject == role;
    }

    /**
     * This method checks if an instance of ProjectResource has a period that overlaps with another period.
     *
     * @param period to check.
     * @return <code>true</code> if the period is overlapping and <code>false</code> otherwise.
     */

    public boolean isPeriodOverlapping(Period period) {
        return !isPeriodNotOverlapping(period);
    }

    /**
     * This method checks if an instance of ProjectResource has a period that overlaps with another period.
     *
     * @param period to check.
     * @return <code>true</code> if the period is not overlapping and <code>false</code> otherwise.
     */
    public boolean isPeriodNotOverlapping(Period period) {
        return this.allocationPeriod.isPeriodNotOverlapping(period);
    }

    /**
     * This method counts the number of days included in a time period including the first day, and the last day.
     *
     * @return the number of days contained in period.
     */
    public int numberOfDaysContainedInPeriod() {
        return this.allocationPeriod.numberOfDaysContainedInPeriod();
    }

    /**
     * Checks if the given date falls within the allocation period.
     *
     * @param date the date to check.
     * @return TRUE if the date is within the allocation period, FALSE otherwise.
     */
    public boolean allocationPeriodIncludesDate(LocalDate date) {
        return this.allocationPeriod.isDateEqualOrGreaterThanStartDate(date)
                && this.allocationPeriod.isDateEqualOrLowerThanEndDate(date);
    }

    /**
     * Checks if the account email matches the given email.
     *
     * @param email the email to check.
     * @return TRUE if the account email matches the given email, FALSE otherwise.
     */
    public boolean hasAccount(Email email) {
        return this.accountEmail.equals(email);
    }

    /**
     * Gets the percentage value of this allocation.
     *
     * @return the percentage allocation value.
     */
    public float getPercentageOfAllocation() {
        return percentageOfAllocation.getValue();
    }

    /**
     * This method returns a String representation of the Project Resource email.
     *
     * @return the email of the project resource.
     */
    public String getEmail() {
        return accountEmail.getEmail();
    }

    /**
     * This method returns a String representation of the Project Resource projectCode.
     *
     * @return the projectCode of the project resource.
     */
    public String getCode() {
        return projectCode.getCode();
    }

    /**
     * This method returns the Project Resource period.
     *
     * @return the period of the project resource.
     */
    public Period getPeriod() {
        return allocationPeriod;
    }
    /**
     * This method returns a String representation of the Project Resource ID.
     *
     * @return the ID of the project resource.
     */
    public String getProjectResourceId() {
        return projectResourceId.getProjectResourceId();
    }
    /**
     * This method returns a String representation of the role.
     *
     * @return the role of the project resource.
     */
    public String getRoleInProject() {
        return roleInProject.toString();
    }

    /**
     * This method returns the cost per hour.
     *
     * @return the role of the project resource.
     */
    public float getCostPerHour() {
        return costPerHour.getCost();
    }
}



