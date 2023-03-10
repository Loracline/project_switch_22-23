package org.switch2022.project.factories;
import org.switch2022.project.utils.Period;

import java.time.LocalDate;

public interface IFactoryPeriod {
    /**
     * Creates a new period object with the given start date and duration in days.
     *
     * @param startDate the start date of the period
     * @param sprintDuration the duration of the period in days
     * @return a new Period object
     */
    Period createPeriod(LocalDate startDate, int sprintDuration);
}