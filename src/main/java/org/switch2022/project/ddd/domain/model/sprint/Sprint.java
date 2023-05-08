package org.switch2022.project.ddd.domain.model.sprint;

import org.switch2022.project.ddd.domain.shared.Entity;
import org.switch2022.project.ddd.domain.value_object.*;
import org.switch2022.project.ddd.utils.Validate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;


/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */

public class Sprint implements Entity<Sprint> {
    private final SprintId sprintId;
    private final SprintNumber sprintNumber;

    private final Period period;

    private final List<UserStoryInSprint> userStoriesInSprint;


    /**
     * Constructor
     *
     * @param sprintNumber the number of the sprint.
     * @param period       the duration of the sprint.
     */

    protected Sprint(String projectCode, int sprintNumber, Period period) {
        Validate.notNull(period, "Period cannot be null");
        Validate.notNegative(sprintNumber, "Sprint Number");
        Validate.notNullOrEmptyOrBlank(projectCode, "project code");

        SprintNumber sprintNumberVO = new SprintNumber(sprintNumber);
        this.sprintId = new SprintId(projectCode, sprintNumberVO.getSprintNumber());
        this.sprintNumber = sprintNumberVO;
        this.period = period;
        this.userStoriesInSprint = new ArrayList<>();
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
        return Objects.hash(sprintNumber);
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
        return this.sprintNumber.equals(other.sprintNumber);
    }

    /**
     * This method adds a new User Story to the list of user stories in sprint
     *
     * @param usId   from the new User Story to be added
     * @param effort of the user story in this sprint.
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */


    public boolean addUserStory(UsId usId, Effort effort) {
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(usId, effort);
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
        int i = 0;
        while (i < userStoriesInSprint.size() && !hasUs) {
            UserStoryInSprint userStory = userStoriesInSprint.get(i);
            if (userStory.getUsId().equals(usId)) {
                hasUs = true;
            }
            i++;
        }
        return hasUs;
    }

    /**
     * This method sets the effort of a userStory.
     *
     * @param usId from the user story to estimate the effort.
     * @param effort  of the userStory.
     * @param date         to check of the date is before the start date of the sprint
     * @return true if the effort is set and false otherwise.
     */

    public boolean estimateEffortUserStory(UsId usId, Effort effort,

                                           LocalDate date){
        boolean hasEffortChanged = false;
        if (isDateBeforeStartDate(date)) {
            for (UserStoryInSprint userStory : userStoriesInSprint) {
                if (userStory.getUsId().equals(usId)) {
                    hasEffortChanged = userStory.changeEffort(effort) ;
                }
            }
        }
        return  hasEffortChanged;
    }

    /**
     * This method verifies if the Sprint has the given Sprint Number
     *
     * @param sprintNumber of the seeked Sprint.
     * @return TRUE if Sprint has the given Sprint Number, and FALSE otherwise.
     */

    public boolean hasSprintNumber(String sprintNumber) {
        return this.sprintNumber.getSprintNumber().equalsIgnoreCase(sprintNumber);
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
     * This returns the list of user stories contained in the sprint backlog.
     *
     * @return a sprint backlog with list of copies of user stories.
     */


    public List<UsId> getSprintBacklog() {
        List<UsId> sprintBacklog = new ArrayList<>();
        for (UserStoryInSprint userStory : userStoriesInSprint) {
            sprintBacklog.add(userStory.getUsId());
        }
        return sprintBacklog;
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
     * This method checks if date is equal or greater than start date
     *
     * @param date to compare
     * @return true if date is equal or greater than start date or false otherwise.
     */
    private boolean isDateBeforeStartDate(LocalDate date) {
        return !this.period.isDateEqualOrGreaterThanStartDate(date);
    }
}