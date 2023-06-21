package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private final BusinessSectorId businessSectorId;
    private final TaxId customerTaxId;
    private final ProjectTypologyId projectTypologyId;
    private final ProductBacklog productBacklog;

    /**
     * Constructs a new project.
     *
     * @param projectNumber     the number of the project
     * @param projectName       the name of the project
     * @param description       the description of the project
     * @param businessSectorId  the ID of the business sector for the project
     * @param customerTaxId     the tax ID of the customer associated with the project
     * @param projectTypologyId the ID of the project typology
     */
    protected Project(int projectNumber, Name projectName, Description description,
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
    public void setProjectStatus(ProjectStatus projectStatus) {
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
    public String getDescription() {
        return description.getDescription();
    }

    /**
     * Getter method for the attribute: businessSectorId.
     *
     * @return a String with the business sector ID of the project.
     */
    public String getBusinessSectorId() {
        return businessSectorId.getBusinessSectorId();
    }

    /**
     * Getter method for the attribute: customerTaxId.
     *
     * @return a TaxID with the customer ID of the project.
     */
    public TaxId getCustomerTaxId() {
        return customerTaxId;
    }

    /**
     * Getter method for the attribute: customerTaxId.
     *
     * @return a String with the customer ID of the project.
     */
    public String getCustomerTaxIDAsString() {
        return customerTaxId.getNumber();
    }

    /**
     * Getter method for the attribute: projectTypologyId.
     *
     * @return a String with the project typology ID of the project.
     */
    public String getProjectTypologyId() {
        return projectTypologyId.getProjectTypologyId();
    }

    /**
     * This method sets the sprint duration for this project to the specified sprint duration object.
     *
     * @param sprintDuration the sprint duration object to set for this project.
     */
    public void setSprintDuration(int sprintDuration) {
        hasEditableStatus();
        this.sprintDuration = new SprintDuration(sprintDuration);
    }

    /**
     * This method sets the period for this project to the specified.
     *
     * @param period the period object.
     * @return TRUE if period is assigned, and FALSE otherwise.
     */
    public boolean isPeriodAssigned(Period period) {
        hasEditableStatus();
        this.period = period;
        return true;
    }

    /**
     * This method sets the budget for the project.
     *
     * @param budget the budget object.
     * @return TRUE if budget is assigned, and FALSE otherwise.
     */
    public boolean isBudgetAssigned(Budget budget) {
        hasEditableStatus();
        this.budget = budget;
        return true;
    }

    /**
     * This method sets the numberOfPlannedSprints for the project.
     *
     * @param numberOfPlannedSprints of the project.
     * @return TRUE if number is defined, and FALSE otherwise.
     */
    public boolean isNumberOfPlannedSprintsDefined(NumberOfPlannedSprints numberOfPlannedSprints) {
        hasEditableStatus();
        this.numberOfPlannedSprints = numberOfPlannedSprints;
        return true;
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
     * This method adds a new User Story to the userStories list if the User Story doesn't already exist.
     *
     * @param priority of the User Story.
     * @param usId     of the User Story.
     * @return true if the userStory was created with success.
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
     * Method to check if status given is the same as the project status.
     *
     * @param projectStatus status of the project.
     * @return true if status given is the same as the project status.
     */
    public boolean hasStatus(ProjectStatus projectStatus) {
        return this.projectStatus.equals(projectStatus);
    }

    /**
     * Checks if the project has an editable status.
     *
     * @return {@code true} if the project has an editable status, {@code false} otherwise
     */
    public boolean hasEditableStatus() {
        if (this.projectStatus.equals(ProjectStatus.PLANNED) ||
                this.projectStatus.equals(ProjectStatus.CLOSED)) {
            throw new IllegalArgumentException(
                    "The project must be in either phase: INCEPTION, ELABORATION, " +
                            "CONSTRUCTION, TRANSITION or WARRANTY");
        } else {
            return true;
        }
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
     * Getter method for the attribute: endDate.
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
     * If the project is in status planned the duration will be null.
     *
     * @return an optional of a sprint duration
     */
    public Optional<SprintDuration> getSprintDuration() {
        Optional<SprintDuration> result = Optional.empty();
        if (!projectStatus.equals(ProjectStatus.PLANNED) && sprintDuration != null) {
            result = Optional.of(this.sprintDuration);
        }
        return result;
    }

    /**
     * Retrieves the budget of the project.
     *
     * @return The budget of the project as a double value.
     */
    public double getBudget() {
        double result = 0;
        if (this.budget != null) {
            result = this.budget.getBudget().doubleValue();
        }
        return result;
    }

    /**
     * Retrieves the number of planned sprints for the project.
     *
     * @return The number of planned sprints as an integer. If the number of planned sprints is null, it returns 0.
     */
    public int getPlannedSprints() {
        int result = 0;
        if (this.numberOfPlannedSprints != null) {
            result = this.numberOfPlannedSprints.getNumberOfPlannedSprints();
        }
        return result;
    }

    /**
     * Retrieves the identifier of the product backlog associated with the project.
     *
     * @return The identifier of the product backlog as a string.
     */
    public String getProductBacklogId() {
        return this.productBacklog.getProductBacklogId();
    }

    /**
     * Checks if this period contains the specified period.
     *
     * @param otherPeriod The period to check for containment.
     * @return {@code TRUE} if this period contains the specified period, {@code FALSE} otherwise.
     */
    public boolean contains(Period otherPeriod) {
        return this.period.contains(otherPeriod);
    }

    /**
     * This method verifies that current date is contained in Project period.
     *
     * @return true if the current date is contained in Project period, and false otherwise.
     */
    public boolean containsCurrentDate() {
        return this.period.containsCurrentDate();
    }

    /**
     * This method sets the project period (start and end date).
     *
     * @param startDate of the project.
     * @param endDate   of the project.
     */
    public void setPeriod(LocalDate startDate, LocalDate endDate) {
        this.period = new Period(startDate, endDate);
    }

    /**
     * Sets the project history with the specified budget, number of planned sprints, sprint duration, start date, and
     * end date.
     *
     * @param budget                 the budget for the project
     * @param numberOfPlannedSprints the number of planned sprints for the project
     * @param sprintDuration         the duration of each sprint in days
     * @param startDate              the start date of the project
     * @param endDate                the end date of the project
     */
    public void setProjectHistory(BigDecimal budget,
                                  NumberOfPlannedSprints numberOfPlannedSprints,
                                  int sprintDuration, LocalDate startDate, LocalDate endDate) {
        if (this.projectStatus == ProjectStatus.CLOSED) {
            this.budget = new Budget(budget);
            this.numberOfPlannedSprints = numberOfPlannedSprints;
            this.sprintDuration = new SprintDuration(sprintDuration);
            this.setPeriod(startDate, endDate);
        }
    }
}



