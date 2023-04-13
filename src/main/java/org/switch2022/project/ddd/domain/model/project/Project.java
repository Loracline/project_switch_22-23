package org.switch2022.project.ddd.domain.model.project;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Utils;

import java.util.List;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by code, it's your ID. Its other attributes are budget, name, description, project Status, <br>
 * number of planned sprints, period, sprint duration, sprints (list of sprints), business sector, customer, <br>
 * project typology and product backlog .
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
    protected Code getProjectCode() {
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
     * Getter method for the attribute: projectName.
     *
     * @return the name of the project.
     */
    protected Name getProjectName() {
        return projectName;
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
     * Getter method for the attribute: description.
     *
     * @return the description of the project.
     */
    protected Description getDescription() {
        return description;
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
     * Getter method for the attribute: businessSectorId.
     *
     * @return the business sector ID of the project.
     */
    protected BusinessSectorId getBusinessSectorId() {
        return businessSectorId;
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
     * Getter method for the attribute: customerId.
     *
     * @return the customer ID of the project.
     */
    protected CustomerId getCustomerId() {
        return customerId;
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
     * Getter method for the attribute: projectTypologyId.
     *
     * @return the project typology ID of the project.
     */
    protected ProjectTypologyId getProjectTypologyId() {
        return projectTypologyId;
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
     * Getter method for the attribute: sprintDuration.
     *
     * @return the sprint duration of the project.
     */
    protected SprintDuration getSprintDuration() {
        return sprintDuration;
    }

    /**
     * This method sets the relevant attributes for a project to be in a valid state.
     *
     * @param projectName            of a project.
     * @param description            of a project.
     * @param businessSectorId       of a project.
     * @param customerId             of a project.
     * @param projectTypologyId      of a project.
     * @param iFactoryProductBacklog that creates the product backlog of a project.
     */
    protected void setValidProject(String projectName, String description, BusinessSectorId businessSectorId,
                                   CustomerId customerId, ProjectTypologyId projectTypologyId,
                                   IFactoryProductBacklog iFactoryProductBacklog) {
        setName(projectName);
        setDescription(description);
        setBusinessSector(businessSectorId);
        setCustomer(customerId);
        setTypology(projectTypologyId);
        setProductBacklog(iFactoryProductBacklog);
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
}



