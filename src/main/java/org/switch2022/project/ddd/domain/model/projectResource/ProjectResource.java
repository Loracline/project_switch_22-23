package org.switch2022.project.ddd.domain.model.projectResource;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.exceptions.InvalidInputException;
import org.switch2022.project.ddd.utils.Validate;

import java.util.Objects;

public class ProjectResource implements Entity<ProjectResource> {

    private final ProjectResourceId projectResourceId;
    private final Code projectCode;
    private final Email accountEmail;
    private Role roleInProject;
    private Period timeInProject;
    private CostPerHour costPerHour;
    private PercentageOfAllocation percentageOfAllocation;

    /**
     * Constructor
     */
    protected ProjectResource(final ProjectResourceId projectResourceId, final Code code, final Email email) {
        Validate.notNull(projectResourceId, "Project Resource ID must not be null");
        Validate.notNull(code, "Project Code must not be null");
        Validate.notNull(email, "Email must not be null");
        this.projectResourceId = projectResourceId;
        this.projectCode = code;
        this.accountEmail = email;
    }

    /**
     * Setter method for the attribute: Role.
     *
     * @param roleInProject of the project.
     */
    protected void setRole(Role roleInProject) {
        if(roleInProject == null){
            throw new InvalidInputException("Role can not be null");
        }
        this.roleInProject = roleInProject;
    }

    /**
     * Setter method for the attribute: Period.
     *
     * @param timeInProject of the project.
     */
    public void setPeriod(Period timeInProject) {
        Validate.notNull(timeInProject, "Period can not be null");
        this.timeInProject = timeInProject;
    }

    /**
     * Setter method for the attribute: CostPerHour.
     *
     * @param costPerHour of the project.
     */
    public void setCost(CostPerHour costPerHour) {
        Validate.notNull(costPerHour, "Cost can not be null");
        this.costPerHour = costPerHour;
    }

    /**
     * Setter method for the attribute: Percentage of Allocation.
     *
     * @param percentageOfAllocation of the project.
     */
    public void setPercentageOfAllocation(PercentageOfAllocation percentageOfAllocation) {
        Validate.notNull(percentageOfAllocation, "Percentage of Allocation can not be null");
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
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;
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
}
