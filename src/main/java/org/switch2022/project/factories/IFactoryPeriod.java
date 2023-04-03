package org.switch2022.project.factories;

import org.switch2022.project.utils.Period;

import java.time.LocalDate;

/**
 * Interface for a Period factory.
 */
public interface IFactoryPeriod {
    /**
     * Creates a new Period object with the given start date and duration in days with no return.
     *
     * @param startDate      the start date of the Period
     * @param sprintDuration the duration of the Period in days
     */
    Period createPeriod(LocalDate startDate, int sprintDuration);
}