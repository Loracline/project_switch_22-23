package org.switch2022.project.model;


import org.switch2022.project.factories.FactoryPeriod;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;
import java.util.Objects;


/**
 * Represents a short period of time defined by a Sprint Number, Period and Sprint
 * Backlog.
 */
class Sprint {
    private final String sprintNumber;
    private FactoryPeriod factoryPeriod;
    private Period period;

    private final SprintBacklog sprintBacklog = new SprintBacklog();

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
        sprint.setPeriod(factoryPeriod, sprintDuration, startDate);
        return sprint;
    }

    /**
     * Constructor
     *
     * @param sprintNumber the number of the sprint.
     */
    private Sprint(String sprintNumber) {
        this.sprintNumber = sprintNumber.toLowerCase().trim();
    }

    /**
     * Creates a new period within the sprint.
     *
     * @param startDate      the start date of the period
     * @param periodDuration the duration of the period in weeks
     */
    public void createPeriod(LocalDate startDate, int periodDuration) {
        // create a new period object using the FactoryPeriod class
        this.period = this.factoryPeriod.createPeriod(startDate, periodDuration);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return sprintNumber.equals(sprint.sprintNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintNumber);
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


   /* public isSprintCodeUnique(){

    }

    public isSprintCodeValid() {


    }  */

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
     * This method verifies if the Sprint has the given Sprint Number
     * @param sprintNumber of the seeked Sprint.
     * @return TRUE if Sprint has the given Sprint Number, and FALSE otherwise.
     */
    public boolean hasSprintNumber (String sprintNumber){
        return sprintNumber.equalsIgnoreCase(this.sprintNumber);
    }
}