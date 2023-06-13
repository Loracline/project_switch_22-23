package org.switch2022.project.ddd.domain.model.sprint;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;

import java.time.LocalDate;
import java.util.*;

import static java.lang.Integer.parseInt;


/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */
public class Sprint implements Entity<Sprint> {

    private final SprintId sprintId;
    private final SprintNumber sprintNumber;
    private final Code projectCode;
    private final Period period;
    private final List<UserStoryInSprint> userStoriesInSprint;
    private SprintStatus status;

    /**
     * Constructor
     *
     * @param projectCode  the code of the project
     * @param sprintId     the id of the sprint
     * @param sprintNumber the number of the sprint.
     * @param period       the duration of the sprint.
     */
    protected Sprint(Code projectCode, SprintId sprintId, SprintNumber
            sprintNumber, Period period) {
        this.projectCode = projectCode;
        this.sprintId = sprintId;
        this.sprintNumber = sprintNumber;
        this.period = period;
        this.userStoriesInSprint = new ArrayList<>();
        this.status = SprintStatus.PLANNED;
    }

    /**
     * This method checks if two instances of Sprint are equal by comparing
     * the sprintNumber.
     *
     * @param o userStory instance to compare with.
     * @return TRUE if the two have the same attribute, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sprint sprint = (Sprint) o;
        return sprintId.equals(sprint.sprintId);
    }

    /**
     * The hashCode() method is used to generate a unique hash code for an
     * object, based on the object's state.
     *
     * @return a unique value that represents the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sprintId);
    }

    /**
     * This method checks if two instances of Sprint are equal by comparing the value
     * of the
     * attribute Sprint Number.
     *
     * @param other Sprint instance to compare with.
     * @return TRUE if the two have the same attribute value, and FALSE otherwise.
     */
    @Override
    public boolean sameIdentityAs(Sprint other) {
        return this.sprintId.equals(other.sprintId);
    }

    /**
     * This method adds a new User Story to the list of user stories in sprint
     *
     * @param usId   from the new User Story to be added
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */
    public boolean addUserStory(UsId usId) {
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId);
        boolean isAdded = true;
        if (userStoriesInSprint.contains(userStoryInSprint)) {
            isAdded = false;
        } else {
            userStoriesInSprint.add(userStoryInSprint);
        }
        return isAdded;
    }

    /**
     * This method verifies if the User Story is already in the list.
     *
     * @param usId to check the presence in the list.
     * @return TRUE if the User Story is present in the list and FALSE otherwise.
     */
    public boolean hasUserStory(UsId usId) {
        boolean hasUs = false;
        Iterator<UserStoryInSprint> iterator = userStoriesInSprint.iterator();
        while (iterator.hasNext() && !hasUs) {
            UserStoryInSprint userStory = iterator.next();
            if (userStory.getUsId().equals(usId)) {
                hasUs = true;
            }
        }
        return hasUs;
    }

    /**
     * This method sets the effort of a userStory.
     *
     * @param usId   from the user story to estimate the effort.
     * @param effort of the userStory.
     * @return true if the effort is set and false otherwise.
     */
    public boolean estimateEffortUserStory(UsId usId, int effort) {
        boolean hasEffortChanged = false;
        for (UserStoryInSprint userStory : userStoriesInSprint) {
            if (userStory.getUsId().equals(usId)) {
                hasEffortChanged = userStory.changeEffort(effort);
            }
        }
        return hasEffortChanged;
    }

    /**
     * This method verifies if the Sprint has the given Sprint ID
     *
     * @param sprintId of the seeked Sprint.
     * @return TRUE if Sprint has the given Sprint ID, and FALSE otherwise.
     */
    public boolean hasSprintId(SprintId sprintId) {
        return this.sprintId.equals(sprintId);
    }

    /**
     * This method verifies if the Sprint has the given Project Code
     *
     * @param projectCode of the seeked Sprint.
     * @return TRUE if Sprint has the given projectCode, and FALSE otherwise.
     */
    public boolean hasProjectCode(Code projectCode) {
        return this.projectCode.equals(projectCode);
    }

    /**
     * Getter method that returns the sprintId
     * @return a String representing the sprintId.
     */
    public String getSprintId() {
        return this.sprintId.getSprintId();
    }

    /**
     * Getter method that returns the Sprint projectCode.
     * @return a String representing the projectCode.
     */
    public String getProjectCode() {
        return this.projectCode.getCode();
    }

    /**
     * Getter method that returns the Sprint startDate.
     * @return a String representing the startDate.
     */
    public String getStartDate() {
        return this.period.getStartDate();
    }

    /**
     * Getter method that returns the Sprint endDate.
     * @return a String representing the endDate.
     */
    public String getEndDate() {
        return this.period.getEndDate();
    }

    /**
     * Returns the sprint number of the current object.
     * This method splits the sprint number string using "S" as a delimiter and returns
     * the second element of the resulting array
     * as an integer value.
     *
     * @return the sprint number as an integer value.
     */
    public int getSprintNumber() {
        String[] array = this.sprintNumber.getSprintNumber().split("s", -2);
        return parseInt(array[1]);
    }

    /**
     * This method returns the full sprint number (format: S00d).
     * @return a String with that represents the sprintNUmber.
     */
    public String getFullSprintNumber() {
        return this.sprintNumber.getSprintNumber();
    }

    /**
     * This returns the list of user stories contained in the sprint backlog.
     *
     * @return a sprint backlog with list of copies of user stories.
     */
    public List<UsId> getSprintBacklog() {
        List<UsId> sprintBacklog = new ArrayList<>();
        for (UserStoryInSprint userStory : userStoriesInSprint) {
            sprintBacklog.add(userStory.getUsId());
        }
        return Collections.unmodifiableList(sprintBacklog);
    }

    /**
     * This method checks if date is equal or greater than start date and equal or
     * lower than end date.
     *
     * @param date to compare
     * @return true if date is equal or greater than start date and equal or lower than
     * end date or false otherwise.
     */
    public boolean isDateWithinPeriod(LocalDate date) {
        return this.period.isDateEqualOrGreaterThanStartDate(date) && this.period.isDateEqualOrLowerThanEndDate(date);
    }

    /**
     * This method checks if date is before the sprint period.
     *
     * @param date to compare
     * @return true if the date is before the Sprint period and false otherwise.
     */
    public boolean isDateBeforePeriod(LocalDate date) {
        return !this.period.isDateEqualOrGreaterThanStartDate(date);
    }

    /**
     * Determines if this period does not overlap with the given period.
     *
     * @param sprint the period to compare with.
     * @return true if the periods do not overlap, false otherwise.
     */
    public boolean isPeriodNotOverlapping(Sprint sprint) {
        return this.period.isPeriodNotOverlapping(sprint.period);
    }

    /**
     * This method checks if the sprint start date is after or equal the date
     *
     * @param date to be compared in the period.
     * @return true if the sprint start date is after or equal the date
     */
    public boolean isPeriodAfterOrEqualThanDate(LocalDate date) {
        return period.isDateEqualOrLowerThanStartDate(date);

    }

    /**
     * This method checks if the sprint end date is before or equal the date
     *
     * @param date to be compared in the period.
     * @return true if the sprint end date is before or equal the  date
     */
    public boolean isEndDateBeforeOrGreaterThanDate(LocalDate date) {
        return period.isDateEqualOrGreaterThanEndDate(date);
    }

    /**
     * Getter method for the attribute: userStoriesInSprint
     *
     * @return a list of User Story In Sprint
     */
    public List<UserStoryInSprint> getUserStoriesInSprint() {
        return userStoriesInSprint;
    }

    /**
     * This method changes the status of the sprint.
     *
     * @param status to be changed to.
     * @return true if the status was successfully updated, or propagates an exception from the generateSprintStatus
     * otherwise.
     */
    public boolean changeStatus(String status) {
        SprintStatus sprintStatus = SprintStatus.generateSprintStatus(status);
        this.status = sprintStatus;
        return true;
    }

    /**
     * Getter method for the attribute: status.
     *
     * @return a string representation of the SprintStatus enum.
     */
    public String getStatus() {
        return this.status.getStatus();
    }

    /**
     * This method verifies if the Sprint has the given status.
     *
     * @param status to compare to.
     * @return TRUE if Sprint has the given status, and FALSE otherwise.
     */
    public boolean hasStatus(SprintStatus status) {
        return this.status.sameValueAs(status);
    }


    /**
     * This method checks if the sprint status is valid
     * @return true if the sprint status is OPEN, false otherwise.
     */
    public boolean isOpen() {
       return this.status == SprintStatus.OPEN;
    }
}
