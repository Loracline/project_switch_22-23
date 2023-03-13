package org.switch2022.project.model;

import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.IFactoryPeriod;
import org.switch2022.project.factories.IFactoryUserStory;
import org.switch2022.project.utils.Effort;
import org.switch2022.project.factories.IFactorySprintBacklog;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */
public class Sprint {
    private final String sprintNumber;
    private Period period;
    private SprintBacklog sprintBacklog;
    private IFactoryPeriod iFactoryPeriod;
    private IFactorySprintBacklog iFactorySprintBacklog;

    /**
     * Constructor
     *
     * @param sprintNumber the number of the sprint.
     */
    private Sprint(String sprintNumber) {
        this.sprintNumber = sprintNumber.toLowerCase().trim();
    }

    /**
     * Constructor for Sprint class.
     *
     * @param startDate      the start date of the sprint.
     * @param sprintDuration the duration of the sprint in days.
     * @param sprintNumber   the number of the sprint.
     */
    public static Sprint createSprint(LocalDate startDate, int sprintDuration,
                                      String sprintNumber,
                                      IFactoryPeriod iFactoryPeriod,
                                      IFactorySprintBacklog iFactorySprintBacklog) {
        Sprint sprint = new Sprint(sprintNumber);
        sprint.iFactoryPeriod = iFactoryPeriod;
        sprint.setPeriod(iFactoryPeriod, sprintDuration, startDate);
        sprint.iFactorySprintBacklog = iFactorySprintBacklog;
        sprint.setSprintBacklog();
        return sprint;
    }

    /**
     * This method checks if two instances of Sprint are equal by comparing
     * the userStoryNumber.
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
        return sprintNumber.equals(sprint.sprintNumber);
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
     * Sets the period for the current instance of the object using the specified
     * factory period,
     * sprint duration, and start date.
     *
     * @param sprintDuration the duration of the sprint
     * @param startDate      the start date of the period
     */
    private void setPeriod(IFactoryPeriod iFactoryPeriod, int sprintDuration,
                           LocalDate startDate) {
        this.iFactoryPeriod = iFactoryPeriod;
        this.period = iFactoryPeriod.createPeriod(startDate, sprintDuration);
    }

    /**
     * Set method for the attribute Sprint Backlog.
     */
    private void setSprintBacklog() {
        this.sprintBacklog = iFactorySprintBacklog.createSprintBacklog();
    }

    /**
     * This method adds a new User Story to the Sprint Backlog
     *
     * @param userStory the new User Story to be added
     * @return TRUE if the User Story was successfully added to the list and FALSE
     * otherwise.
     */
    public boolean addUserStoryToSprintBacklog(UserStory userStory) {
        return this.sprintBacklog.addUserStory(userStory);
    }

    /**
     * This method sets the effort of a userStory.
     *
     * @param userStoryDto to estimate the effort.
     * @param effort       of the userStory.
     * @return true if the effort is set and false otherwise.
     */
    public boolean estimateEffortUserStory(UserStoryDto userStoryDto, Effort effort) {
        return (sprintBacklog.estimateEffortUserStory(userStoryDto, effort));
    }

    /**
     * This method verifies if the Sprint has the given Sprint Number
     *
     * @param sprintNumber of the seeked Sprint.
     * @return TRUE if Sprint has the given Sprint Number, and FALSE otherwise.
     */
    public boolean hasSprintNumber(String sprintNumber) {
        return sprintNumber.equalsIgnoreCase(this.sprintNumber);
    }

    /**
     * This method verifies if the User Story is already in the list.
     *
     * @param userStoryNumber to check the presence in the list through the
     *                        hasStoryNumber method.
     * @return TRUE if the User Story is present in the list and FALSE otherwise.
     */
    public boolean hasUserStory(String userStoryNumber) {
        return sprintBacklog.hasUserStory(userStoryNumber);
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
     * This method makes a copy of the list of user stories contained in the sprint
     * backlog and stores it in another instance of sprint backlog.
     * <br>
     *
     * @return a sprint backlog with list of copies of user stories.
     */
    public SprintBacklog getSprintBacklogCopy(IFactoryUserStory iFactoryUserStory) {
        SprintBacklog sprintBacklogCopy = new SprintBacklog();
        List<UserStory> userStoryCopyList =
                this.sprintBacklog.getUserStoriesCopy(iFactoryUserStory);
        for (int i = 0; i < userStoryCopyList.size(); i++) {
            sprintBacklogCopy.addUserStory(userStoryCopyList.get(i));
            i++;
        }
        return sprintBacklogCopy;
    }
}
