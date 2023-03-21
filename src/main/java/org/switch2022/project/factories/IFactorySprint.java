package org.switch2022.project.factories;

import org.switch2022.project.model.Sprint;

import java.time.LocalDate;

/**
 * Interface for a Sprint factory.
 */
public interface IFactorySprint {
    /**
     * This method creates a new Sprint object with the specified attributes with no return.
     *
     * @param startDate             the start date of the Sprint
     * @param sprintDuration        duration of the Sprint
     * @param sprintNumber          number of the Sprint
     * @param iFactoryPeriod        implementation FactoryPeriod
     * @param iFactorySprintBacklog implementation FactorySprintBacklog
     */
    Sprint createSprint(LocalDate startDate, int sprintDuration,
                        String sprintNumber,
                        IFactoryPeriod iFactoryPeriod,
                        IFactorySprintBacklog iFactorySprintBacklog);
}
