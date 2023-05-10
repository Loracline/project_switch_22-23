package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.util.List;
import java.util.Optional;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by a code, its ID. Other attributes are the budget, name, description, project status, <br>
 * number of planned sprints, period, sprint duration, list of sprints, business sector, customer, <br>
 * project typology and product backlog .
 * The project status attribute defaults to "Planned".
 */
public class Project implements Entity<Project> {

    private final Code projectCode;
    private Budget budget;
    private final Name projectName;
    private final Description description;
    private ProjectStatus projectStatus;
    private NumberOfPlannedSprints numberOfPlannedSprints;
    private Period period;
    private SprintDuration sprintDuration;
    private List<SprintId> sprints;
    private final BusinessSectorId businessSectorId;
    private final TaxId customerTaxId;
    private final ProjectTypologyId projectTypologyId;
    private final ProductBacklog productBacklog;

    /**
     * Constructor: the constructor with relevant attributes for a project to be in a valid state.
     */
    protected Project(Number projectNumber, Name projectName, Description description,
                      BusinessSectorId businessSectorId, TaxId customerTaxId, ProjectTypologyId projectTypologyId) {
        this.projectCode = new Code(projectNumber);
        this.projectStatus = ProjectStatus.PLANNED;
        this.projectName = projectName;
        this.description = description;
        this.businessSectorId = businessSectorId;
        this.customerTaxId = customerTaxId;
        this.projectTypologyId = projectTypologyId;
        this.productBacklog = new ProductBacklog(this.projectCode.getCode());
    }

    /**
     * This method checks if an instance of Project is equal to another object.
     *
     * @param toCompare object to compare with.
     * @return true if the two have the same ID, and false otherwise.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (this.getClass() != toCompare.getClass()) {
            return false;
        }
        Project project = (Project) toCompare;

        return sameIdentityAs(project);
    }

    /**
     * This method is used to generate a unique hash for an object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return projectCode.hashCode();
    }

    /**
     * This method checks if two instances of Project are equal by comparing the value of the attribute project code.
     *
     * @param other Project instance to compare with.
     * @return true if the two have the same attribute value, and false otherwise.
     */
    @Override
    public boolean sameIdentityAs(Project other) {
        return other != null && this.projectCode.equals(other.projectCode);
    }

    /**
     * Getter method for the attribute: projectCode.
     *
     * @return a String with the code of the project.
     */
    public String getProjectCode() {
        return this.projectCode.getCode();
    }

    /**
     * Setter method for the attribute: projectStatus.
     *
     * @param projectStatus of the project.
     */
    protected void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Getter method for the attribute: projectName.
     *
     * @return a String with the name of the project.
     */
    public String getProjectName() {
        return projectName.getName();
    }

    /**
     * Getter method for the attribute: description.
     *
     * @return a String with the description of the project.
     */
    protected String getDescription() {
        return description.getDescription();
    }

    /**
     * Getter method for the attribute: businessSectorId.
     *
     * @return a String with the business sector ID of the project.
     */
    protected String getBusinessSectorId() {
        return businessSectorId.getBusinessSectorId();
    }


    /**
     * Getter method for the attribute: customerTaxId.
     *
     * @return a String with the customer ID of the project.
     */
    public TaxId getCustomerTaxId() {
        return customerTaxId;
    }

    /**
     * Getter method for the attribute: projectTypologyId.
     *
     * @return a String with the project typology ID of the project.
     */
    protected String getProjectTypologyId() {
        return projectTypologyId.getProjectTypologyId();
    }

    /**
     * This method sets the sprint duration for this project to the specified sprint duration object.
     *
     * @param sprintDuration the sprint duration object to set for this project.
     */
    protected void setSprintDuration(int sprintDuration) {
        Utils.hasStatus(this.projectStatus, ProjectStatus.INCEPTION);
        this.sprintDuration = new SprintDuration(sprintDuration);
    }

    /**
     * This method sets the period for this project to the specified.
     *
     * @param period the period object
     */
    protected boolean isPeriodAssigned(Period period) {
        boolean isAssignedPeriod = false;
        if (projectStatus == ProjectStatus.INCEPTION) {
            this.period = period;
            isAssignedPeriod = true;
        }
        return isAssignedPeriod;
    }

    /**
     * This method sets the budget for the project.
     *
     * @param budget the budget object
     */
    protected boolean isBudgetAssigned(Budget budget) {
        boolean isBudgetAssigned = false;
        if (projectStatus == ProjectStatus.INCEPTION) {
            this.budget = budget;
            isBudgetAssigned = true;
        }
        return isBudgetAssigned;
    }

    /**
     * This method sets the numberOfPlannedSprints for the project.
     *
     * @param numberOfPlannedSprints of the project.
     */
    protected boolean isNumberOfPlannedSprintsDefined(NumberOfPlannedSprints numberOfPlannedSprints) {
        boolean isNumberOfPlannedSprintsDefined = false;
        if (projectStatus == ProjectStatus.INCEPTION) {
            this.numberOfPlannedSprints = numberOfPlannedSprints;
            isNumberOfPlannedSprintsDefined = true;
        }
        return isNumberOfPlannedSprintsDefined;
    }

    /**
     * This method verifies if the Project has the given Project code
     *
     * @param projectCode of the Project.
     * @return TRUE if Project has the given Project Code, and FALSE otherwise.
     */
    public boolean hasProjectCode(Code projectCode) {
        return projectCode.equals(this.projectCode);
    }

    /**
     * This method adds a new User Story to the userStories list if the User Story
     * doesn't already exist.
     * return true if the userStory was created with success.
     */
    public boolean addUserStory(int priority, UsId usId) {
        return productBacklog.addUserStory(priority, usId);
    }

    /**
     * This method returns the list of user stories in the product backlog.
     *
     * @return a list of {@link UsId} representing the user stories in the product backlog.
     */
    public List<UsId> getProductBacklog() {
        return productBacklog.getUserStories();
    }

    /**
     * Getter method for the attribute: status
     *
     * @return a String with the status of the project.
     */

    public String getProjectStatus() {
        return this.projectStatus.getStatus();
    }

    /**
     * Method to check if status given is the same as the project status
     * @param projectStatus
     * @return true if status given is the same as the project status
     */

    public boolean hasStatus(ProjectStatus projectStatus){
        return this.projectStatus.equals(projectStatus);
    }

    /**
     * Getter method for the attribute: startDate
     *
     * @return a String with the startDate of the project if there is a period otherwise it will
     * return an empty string
     */

    public String getStartDate() {
        String startDate = "";
        if (period != null) {
            startDate = this.period.getStartDate();
        }
        return startDate;
    }

    /**
     * Getter method for the attribute: endDate
     *
     * @return a String with the endDate of the project if there is a period otherwise it will
     * return an empty string
     */

    public String getEndDate() {
        String endDAte = "";
        if (period != null) {
            endDAte = this.period.getEndDate();
        }
        return endDAte;
    }

    /**
     *This method returns an optional of a sprint duration
     * If the project is in status planned the duration will be null.
     */
    public Optional<SprintDuration> getSprintDuration(){
        Optional<SprintDuration> result = Optional.empty();
        if (!projectStatus.equals(ProjectStatus.PLANNED) && sprintDuration!= null){
            result = Optional.of(this.sprintDuration);
        }
        return result;
    }
}



