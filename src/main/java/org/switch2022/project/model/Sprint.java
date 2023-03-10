package org.switch2022.project.model;


import org.switch2022.project.dto.UserStoryDto;
import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.utils.Effort;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;
import java.util.Objects;


/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */
class Sprint {
    private final String sprintNumber;
    private final SprintBacklog sprintBacklog = new SprintBacklog();
    private FactoryPeriod factoryPeriod;
    private Period period;

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
                                      FactoryPeriod factoryPeriod) {
        Sprint sprint = new Sprint(sprintNumber);
        sprint.factoryPeriod = factoryPeriod;
        sprint.setPeriod(factoryPeriod, sprintDuration, startDate);
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
     * Getter method for the attribute: period.
     */
    public Period getPeriod() {
        return period;
    }

    /**
     * Sets the period for the current instance of the object using the specified
     * factory period,
     * sprint duration, and start date.
     *
     * @param factoryPeriod  the factory period to use for creating the period
     * @param sprintDuration the duration of the sprint
     * @param startDate      the start date of the period
     */


    private void setPeriod(FactoryPeriod factoryPeriod, int sprintDuration,
                           LocalDate startDate) {
        this.factoryPeriod = factoryPeriod;
        this.period = factoryPeriod.createPeriod(startDate, sprintDuration);
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
}
