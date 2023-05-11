package org.switch2022.project.ddd.domain.model.project_resource;

import org.springframework.stereotype.Component;
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
        this.timeInProject = allocationPeriod;
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
     * @param projectCode project code of interest.
     * @return true if the project code of interest is equal to the project code of the instance of ProjectResuorce,
     * and false otherwise.
     */
    public boolean hasProjectCode(Code projectCode){
        return this.projectCode.equals(projectCode);
    }
}
