package org.switch2022.project.factories;

import org.switch2022.project.model.Sprint;

import java.time.LocalDate;

/**
 * Implementation of the FactorySprint interface that creates instances of the Sprint class
 */
public class FactorySprint implements IFactorySprint {

    /**
     * This method creates a new Sprint object with the start date, sprint duration, sprint
     * number, iFactoryPeriod and
     * iFactorySprintBacklog
     *
     * @param startDate             the start date of the Sprint
     * @param sprintDuration        duration of the Sprint
     * @param sprintNumber          number of the Sprint
     * @param iFactoryPeriod        implementation FactoryPeriod
     * @param iFactorySprintBacklog implementation FactorySprintBacklog
     * @return Sprint
     */
    public Sprint createSprint(LocalDate startDate, int sprintDuration,
                               String sprintNumber,
                               IFactoryPeriod iFactoryPeriod,
                               IFactorySprintBacklog iFactorySprintBacklog) {
        return Sprint.createSprint(startDate, sprintDuration, sprintNumber, iFactoryPeriod,
                iFactorySprintBacklog);
    }
}
