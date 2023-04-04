package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;

import java.util.List;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by code, it's your ID. Its other attributes are budget, name, description, project Status, <br>
 * number of planned sprints, period, sprint duration, sprints (list of sprints), business sector, customer, project <br>
 * typology and product backlog .
 * The project status attribute defaults to "Planned"
 */
public class Project implements Entity<Project> {

    private final Code projectCode;
    private Budget budget;
    private Name projectName;
    private Description description;
    private ProjectStatus projectStatus = ProjectStatus.PLANNED;
    private NumberOfPlannedSprints numberOfPlannedSprints;
    private Period period;
    private SprintDuration sprintDuration;
    private List<SprintId> sprints;
    private BusinessSectorId businessSectorId;
    private CustomerId customerId;
    private ProjectTypologyId projectTypologyId;
    private ProductBacklog productBacklog;

    /**
     * Constructor: the constructor only has the attribute that defines it, in this case, the project code.
     */
    protected Project(final Code projectCode) {
        this.projectCode = projectCode;
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
     * @return the code of the project.
     */
    public Code getProjectCode() {
        return this.projectCode;
    }

    /**
     * Setter method for the attribute: productBacklog.
     * <p>
     * This method creates a Product backlog for the project
     */
    protected void setProductBacklog(IFactoryProductBacklog iFactoryProductBacklog) {
        this.productBacklog = iFactoryProductBacklog.createProductBacklog(this.projectCode.toString());
    }

    /**
     * This method sets the project name attribute to a new Name value object created with the projectName parameter.
     *
     * @param projectName the name of the project
     */
    protected void setName(String projectName) {
        this.projectName = new Name(projectName);
    }

    /**
     * This method sets the project description attribute to a new Description value object created with the description
     * parameter.
     *
     * @param description the description of the project.
     */
    protected void setDescription(String description) {
        this.description = new Description(description);
    }

    /**
     * This method sets the business sector ID attribute to the businessSectorId parameter.
     *
     * @param businessSectorId is the identifier attribute of type value object of the business sector entity.
     */
    protected void setBusinessSector(BusinessSectorId businessSectorId) {
        this.businessSectorId = businessSectorId;
    }

    /**
     * This method sets the customer ID attribute to the customerId parameter.
     *
     * @param customerId is the identifier attribute of type value object of the Customer entity.
     */
    protected void setCustomer(CustomerId customerId) {
        this.customerId = customerId;
    }

    /**
     * This method sets the project typology ID attribute to the projectTypologyId parameter.
     *
     * @param projectTypologyId is the identifier attribute of type value object of the Project Typology entity.
     */
    protected void setTypology(ProjectTypologyId projectTypologyId) {
        this.projectTypologyId = projectTypologyId;
    }

    /**
     * This method sets the sprint duration for this project to the specified sprint duration object.
     *
     * @param sprintDuration the sprint duration object to set for this project.
     */
    protected void setSprintDuration(int sprintDuration) {
        this.sprintDuration = new SprintDuration(sprintDuration);
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
}


