package org.switch2022.project.factories;

import org.switch2022.project.utils.Period;

import java.time.LocalDate;

/**
 * Implementation of the FactoryPeriod interface that creates instances of the
 * Period class.
 */
public class FactoryPeriod implements IFactoryPeriod {
    /**
     * This method creates a new Period object with the given start date and duration in days.
     *
     * @param startDate      the start date of the period
     * @param sprintDuration the duration of the period in days
     * @return a new Period object with the specified attributes.
     */
    public Period createPeriod(LocalDate startDate, int sprintDuration) {
        return new Period(startDate, sprintDuration);
    }
}


