package org.switch2022.project.factories;

import org.switch2022.project.model.Sprint;

import java.time.LocalDate;

public class FactorySprint implements IFactorySprint {

    public Sprint createSprint(LocalDate startDate, int sprintDuration,
                               String sprintNumber, IFactoryPeriod iFactoryPeriod,
                               IFactorySprintBacklog iFactorySprintBacklog) {
        return Sprint.createSprint(startDate, sprintDuration, sprintNumber,
                iFactoryPeriod, iFactorySprintBacklog);
    }
}
