package org.switch2022.project.model;

import org.switch2022.project.dto.UserStoryCreationDto;
import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.IFactoryProductBacklog;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.utils.Effort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Class Project is built to create and manage new projects.
 * A project is defined by code, name, customer, status , project typology and
 * business sector.
 */
public class Project {
    /**
     * Attributes
     */
    private final String projectCode;
    private final String projectName;
    private final Customer customer;
    private final ProjectTypology projectTypology;
    private final BusinessSector businessSector;
    private String projectStatus;
    private int sprintDuration;
    private List<Sprint> sprints;
    private ProductBacklog productBacklog;
    private IFactoryProductBacklog iFactoryProductBacklog;
    private IFactoryUserStory iFactoryUserStory;

    /**
     * Constructor
     */
    public Project(String projectCode, String name, Customer customer,
                   ProjectTypology projectTypology,
                   BusinessSector businessSector) {
        this.projectCode = projectCode;
        this.projectName = name;
        this.customer = customer;
        this.projectStatus = "planned";
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
        this.sprintDuration = 0;
    }

    /**
     * This constructor will create a new project receiving also the interface
     * IFactoryProductBacklog and IFactoryUserStory.
     */
    public Project(String projectCode, String name, Customer customer,
                   ProjectTypology projectTypology,
                   BusinessSector businessSector,
                   IFactoryProductBacklog iFactoryProductBacklog,
                   IFactoryUserStory iFactoryUserStory) {
        this.projectCode = projectCode;
        this.projectName = name;
        this.customer = customer;
        this.projectStatus = "planned";
        this.projectTypology = projectTypology;
        this.businessSector = businessSector;
        this.sprintDuration = 0;
        this.iFactoryProductBacklog = iFactoryProductBacklog;
        this.iFactoryUserStory = iFactoryUserStory;
        this.productBacklog = this.iFactoryProductBacklog.createProductBacklog(this.iFactoryUserStory);
        this.sprints = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, projectName, customer, projectTypology,
                businessSector, projectStatus, sprintDuration);
    }

    /**
     * This method checks if two instances of Project are equal by comparing
     * all their attributes.
     *
     * @param toCompare Project instance to compare with
     * @return TRUE if the two have the same attributes, and FALSE otherwise
     */
    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (toCompare.getClass() != this.getClass()) {
            return false;
        }
        Project project = (Project) toCompare;
        return Objects.equals(projectCode, project.projectCode) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(customer, project.customer) &&
                Objects.equals(projectStatus, project.projectStatus) &&
                Objects.equals(projectTypology, project.projectTypology) &&
                Objects.equals(businessSector, project.businessSector);
    }

    /**
     * Getter method for the attribute: projectCode.
     *
     * @return the code of the project.
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * Getter method for the attribute: projectName.
     *
     * @return the name of the project.
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Getter method of the attribute: customer.
     *
     * @return the customer of the project.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Getter method for the attribute: projectStatus.
     *
     * @return the status of the project.
     */
    public String getProjectStatus() {
        return this.projectStatus;
    }

    public void setProjectStatus(String status) {
        this.projectStatus = status.toLowerCase();
    }

    /**
     * Getter method for the attribute: projectTypology.
     *
     * @return the typology of the project.
     */
    public ProjectTypology getProjectTypology() {
        return this.projectTypology;
    }

    /**
     * Getter method for the attribute: businessSector.
     *
     * @return the business sector of the project.
     */
    public BusinessSector getBusinessSector() {
        return this.businessSector;
    }

    public boolean hasProjectCode(String projectCode) {
        return projectCode.equals(this.projectCode);
    }

    public boolean isProjectOpen() {
        boolean result = false;
        final String STATUS = "closed";
        if (!STATUS.equalsIgnoreCase(this.projectStatus)) {
            result = true;
        }
        return result;
    }

    /**
     * getters and setters for sprintDuration.
     *
     * @return the sprint duration of the project.
     */
    public int getSprintDuration() {
        return this.sprintDuration;
    }

    public boolean setSprintDuration(int sprintDuration) {
        boolean isSprintDurationValid = false;

        if (sprintDuration > 0 && sprintDuration < 5) {
            this.sprintDuration = sprintDuration;
            isSprintDurationValid = true;
        }
        return isSprintDurationValid;
    }

    /**
     * This method returns a Sprint Backlog from Project which Period is within the given
     * date.
     *
     * @param date within the period of the Sprint.
     * @return an Optional with a Sprint.
     */
    public Optional<SprintBacklog> getSprintBacklogByDate(LocalDate date,
                                                          IFactoryUserStory iFactoryUserStory) {
        SprintBacklog sprintBacklog = null;
        int i = 0;
        while (i < this.sprints.size() && sprintBacklog == null) {
            if (sprints.get(i).isDateWithinPeriod(date)) {
                sprintBacklog = sprints.get(i).getSprintBacklogCopy(iFactoryUserStory);
            }
            i++;
        }
        return Optional.ofNullable(sprintBacklog);
    }

    /**
     * This method sets the effort estimation of a user story.
     *
     * @param userStoryDto The UserStoryDto object to estimate the effort for.
     * @param effort       The effort object representing the estimated effort for the
     *                     user story.
     * @param sprintNumber The number of the sprint in which the user story is being
     *                     estimated.
     * @return true if the effort estimation is successfully set, false otherwise.
     */
    public boolean estimateEffortUserStory(UserStoryDto userStoryDto, Effort effort, String sprintNumber) {
        boolean result = false;
        int i = 0;
        while (i < sprints.size()) {
            Sprint sprint = sprints.get(i);
            if (sprint.hasSprintNumber(sprintNumber)) {
                result = sprint.estimateEffortUserStory(userStoryDto, effort);
                break;
            }
            i++;
        }
        return result;
    }

    /**
     * This method adds a new sprint to the list of sprints for this project.
     *
     * @param sprintNumber the sprint to be added
     * @return true if the sprint was added successfully, false if the sprint was already in the
     * list of sprints
     */
    public boolean addSprint(Sprint sprintNumber) {
        boolean result = true;
        if (sprints.contains(sprintNumber)) {
            result = false;
        } else {
            sprints.add(sprintNumber);
        }
        return result;
    }

    /**
     * This method verifies if a copy of a Product Backlog with list of copies of user
     * stories is
     * correctly returned.
     *
     * @return a product backlog.
     */
    public ProductBacklog getProductBacklog() {
        return this.productBacklog.getProductBacklogCopy();
    }

    /**
     * This method checks if there is any UserStory in the sprints of the project with that userStoryNumber
     * return true if the userStory is present in any sprint.
     */
    private boolean hasUserStoryNumberInSprints(String userStoryNumber) {
        boolean result = false;
        int i = 0;
        while (i < sprints.size() && !result && userStoryNumber != null) {
            if (sprints.get(i).hasUserStory(userStoryNumber)) {
                result = true;
            }
            i++;
        }
        return result;
    }

    /**
     * This method creates an userStory by checking if the userStory is not present in any sprint and
     * makes use of the method createUserStory from productBacklog.
     * return true if the userStory was created with success.
     */
    public boolean createUserStory(UserStoryCreationDto userStoryCreationDto) {
        return userStoryCreationDto != null &&
                !hasUserStoryNumberInSprints(userStoryCreationDto.userStoryNumber) &&
                productBacklog.createUserStory(userStoryCreationDto);
    }
}

